package engine;

import static engine.Texts.BUG_FOUND;
import static engine.Texts.THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH;
import static engine.Texts.THE_METHOD_APPEARS_TO_DIVIDE_BY_0;
import static engine.Texts.THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR;
import static engine.Texts.THE_METHOD_SEEMS_TO_RECURSE_INFINITELY;
import static engine.console.Fmt.code;
import static engine.console.Fmt.red;
import static engine.console.Fmt.sameStyle;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import engine.console.Printer;
import engine.console.StdStreamsInterceptor;
import engine.console.StdStreamsInterceptor.InterceptionResult;
import engine.script.Expression;
import engine.script.ScriptExecutionException;
import engine.script.ScriptRunner;
import engine.script.ScriptRunner.ExecutionReport;
import engine.text.Locale;
import engine.text.Localizable;
import engine.util.Either;

public class KoanTest {
    private static final long TIMEOUT_INFINITE_LOOPS_MS = 1000;
    private static final List<Localizable<String>> NO_STD_IN_INPUTS = List.of();

    final Expression[] script;
    private final ResultAssertion[] assertions;
    private final int index;
    private final List<Localizable<String>> stdInInputs;
    private final long seed;

    public KoanTest(final int index, final Expression[] script) {
        this(index, script, new ResultAssertion[0], KoanTest.NO_STD_IN_INPUTS, Math.round(Math.random() * Long.MAX_VALUE));
    }

    private KoanTest(final int index, final Expression[] script, final ResultAssertion[] assertions, final List<Localizable<String>> stdInInputs, final long seed) {
        this.index = index;
        this.script = script;
        this.assertions = assertions;
        this.stdInInputs = stdInInputs;
        this.seed = seed;
    }

    public String debugTestName(final Locale locale, final Koan koan) {
        return String.format("%s/%s[%d]", koan.koanClass.get(locale).simpleClassName, koan.koanName.get(locale), index);
    }

    public KoanTest withStdInInputs(final List<Localizable<String>> inputs) {
        if (this.stdInInputs.size() > 0) {
            throw new IllegalArgumentException("Current call already have StdIn inputs");
        }

        return new KoanTest(index, script, assertions, inputs, seed);
    }
    
    public KoanTest withAssertions(final ResultAssertion[] assertions) {
        if (this.assertions.length > 0) {
            throw new IllegalArgumentException("Current call already have assertions");
        }

        return new KoanTest(index, script, assertions, stdInInputs, seed);
    }

    public KoanTest withSeed(final long seed) {
        return new KoanTest(index, script, assertions, stdInInputs, seed);
    }

    public String[] stdInInputs(final Locale locale) {
        return stdInInputs.stream()
            .map(p -> p.get(locale))
            .toArray(String[]::new);
    }

    void setupRandomForKoan() {
        Helpers.setupRandomForKoan(seed);
    }

    boolean prepare(final Printer p, final Locale locale, final Koan koan) {
        setupRandomForKoan();

        for(final var assertion: koan.beforeAssertions) {
            if (!assertion.validate(p, locale, koan)) {
                return false;
            }
        }

        return true;
    }

    ValidationResult execute(final Printer p, final Locale locale, final Koan koan, final boolean silent) {
        final Optional<Either<InterceptionResult<ExecutionReport>, RuntimeException>> maybeCapturedOutput = 
            executeTestExpressionInThread(locale, koan, silent);

        if (maybeCapturedOutput.isEmpty()) {
            StdStreamsInterceptor.reset();
            p.println(red(THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH));
            p.println();
            p.println(code(Expression.formatSourceCode(script, locale)));
            return ValidationResult.empty(locale, koan, this);
        }

        return maybeCapturedOutput
            .get()
            .map(
                capturedOutput -> validateAssertions(p, locale, koan, capturedOutput),
                throwable -> handleException(p, locale, koan, throwable)
            )
            .getValue();
    }

    private ValidationResult handleException(final Printer p, final Locale locale, final Koan koan, RuntimeException throwable) {
        if (throwable instanceof final ScriptExecutionException see) {
            if (see.getCause() instanceof final InvocationTargetException ite) {
                if (ite.getTargetException() instanceof StackOverflowError) {
                    p.println(red(THE_METHOD_SEEMS_TO_RECURSE_INFINITELY, code(see.methodName)));
                    return ValidationResult.empty(locale, koan, this);
                } else if (ite.getTargetException() instanceof final ArithmeticException ae) {
                    p.println(red(THE_METHOD_APPEARS_TO_DIVIDE_BY_0, code(see.methodName)));
                    return ValidationResult.empty(locale, koan, this);
                } else {
                    p.println(red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR, code(see.methodName), sameStyle(ite.getCause().getMessage())));
                    return ValidationResult.empty(locale, koan, this);
                }
            } else {
                throw see; // Serious bug
            }
        } else if (throwable instanceof final KoanBugException kbe) {
            p.println(red(BUG_FOUND, sameStyle(kbe.getMessage())));
            return ValidationResult.empty(locale, koan, this);
        } else {
            // Should not happen
            throw new IllegalStateException("Received an unexpected exception", throwable);
        }
    }

    private ValidationResult validateAssertions(final Printer p, final Locale locale, final Koan koan, final InterceptionResult<ExecutionReport> capturedOutput) {
        final var output = new TestOutput(
            locale,
            koan,
            this,
            capturedOutput.stdOutLines,
            capturedOutput.stdInLines,
            capturedOutput.scriptOutput.returnedValue(),
            Optional.of(capturedOutput.scriptOutput.executionContext())
        );

        try {
            return new ValidationResult(output, ResultAssertion.validateAll(p, output, assertions));
        } catch(final KoanBugException kbe) {
            p.println(red(BUG_FOUND, sameStyle(kbe.getMessage())));
            return ValidationResult.empty(locale, koan, this);
        }
    }

    /**
     * In order to detect code looping infinitely, execute the student's code in a separate thread with a timeout.
     */
    private Optional<Either<InterceptionResult<ExecutionReport>, RuntimeException>> executeTestExpressionInThread(final Locale locale, final Koan koan, final boolean silent) {
        final AtomicReference<Optional<Either<InterceptionResult<ExecutionReport>, RuntimeException>>> capturedOutputRef = new AtomicReference<>(Optional.empty());

        final Thread thread = new Thread(() -> {
            try {
                final var res = StdStreamsInterceptor.capture(
                    silent,
                    () -> ScriptRunner.execute(koan.koanClass, locale, script),
                    stdInInputs(locale)
                );
                capturedOutputRef.set(Optional.of(Either.of(res)));
            } catch (final ScriptExecutionException see) {
                capturedOutputRef.set(Optional.of(Either.ofError(see)));
            } catch (final KoanBugException kbe) {
                capturedOutputRef.set(Optional.of(Either.ofError(kbe)));
            }
        });
        thread.setDaemon(true);
        thread.start();
        
        try {
            if (koan.showStdInInputs) {
                // Can't detect infinite loop when the user has to enter data through the console, because we don't know how much time to wait for.
                thread.join();
            } else {
                thread.join(TIMEOUT_INFINITE_LOOPS_MS);
            }
        }
        catch(final InterruptedException ie) {
            throw new IllegalStateException(String.format("Something very weird happened. We should not have been interrupted: %s.", ie.getMessage()));
        }

        return capturedOutputRef.get();
    }
}
