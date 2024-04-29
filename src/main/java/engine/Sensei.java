package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import engine.script.Expression;
import engine.script.ScriptExecutionException;
import engine.script.ScriptRunner;

import static engine.Texts.*;

/**
 * The main engine class, executing the series of koans.
 */
public class Sensei {
    private static final long TIMEOUT_INFINITE_LOOPS_MS = 2000;
    private final Locale locale;
    private final Printer consolePrinter;
    private Printer p = Printer.SILENT;
    private List<Koan> allKoans;

    public Sensei(Locale locale, List<List<Koan>> koanSeries) {
        this.locale = locale;
        this.consolePrinter = new ConsolePrinter(locale);
        this.allKoans = koanSeries
            .stream()
            .flatMap(kl -> kl.stream())
            .toList();
    }

    public void offerKoans() {
        for(int i = 0; i< allKoans.size(); i++) {
            if (!tryOfferKoan(allKoans.get(i), i)) {
                return;
            }
        }

        consolePrinter.println();
        consolePrinter.println(MOUNTAINS_ARE_AGAIN_MERELY_MOUNTAINS);
        consolePrinter.println();
    }

    private boolean tryOfferKoan(Koan koan, int successfulCount) {
        for(var test: koan.tests) {
            if (!tryOfferTest(test, successfulCount)) {
                return false;
            }
        }
        return true;
    }

    private boolean tryOfferTest(KoanTest test, int successfulCount) {
        // Execute silently the first time.
        // We do not want to display all the outputs of the successful koans to the student.
        p = Printer.SILENT;
        final var succeeded = offerTest(test, successfulCount);

        if (!succeeded) {
            // If failed, execute verbosely the second time, in order to give feedback to the student.
            p = consolePrinter;
            offerTest(test, successfulCount);
            return false;
        }

        return true;
    }

    private boolean offerTest(KoanTest test, int successfulCount) {
        final var koan = test.koan;        

        encourage(koan);

        final AtomicBoolean success = new AtomicBoolean(false);
        final var thread = new Thread(() -> {
            try {
                success.set(executeCall(test));
            } catch (ScriptExecutionException see) {
                // Special case: since the executeCall() method did not complete, the console conclusion was not displayed.
                concludeConsole(koan);
                if (see.getCause() instanceof InvocationTargetException ite && ite.getTargetException() instanceof StackOverflowError) {
                    p.println(Color.red(THE_METHOD_SEEMS_TO_RECURSE_INFINITELY), see.methodName);
                } else if (see.getCause() instanceof InvocationTargetException ite) {
                    p.println(Color.red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR), see.methodName, ite.getCause().getMessage());
                } else {
                    throw see; // Serious bug
                }
            } catch (KoanBugException kbe) {
                // Special case: since the executeCall() method did not complete, the console conclusion was not displayed.
                concludeConsole(koan);
                p.println(Color.red(BUG_FOUND), kbe.getMessage());
            }
        });

        thread.setDaemon(true);
        thread.start();
        try {
            //thread.join(TIMEOUT_INFINITE_LOOPS_MS);
            thread.join();
        }
        catch(InterruptedException ie) {
            throw new IllegalStateException(String.format("Something very weird happened. We should not have been interrupted: %s.", ie.getMessage()));
        }

        if (thread.isAlive()) {
            StdStreamsInterceptor.reset();
            concludeConsole(koan);
            p.println(Color.red(THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH), Expression.formatSourceCode(test.script, locale));
        }

        offerToMeditate(koan);
        showProgress(successfulCount);

        return success.get();
    }

    private boolean executeCall(final KoanTest test) {

        test.setupRandomForKoan();

        for(var assertion: test.koan.beforeAssertions) {
            if (!assertion.validate(p, locale, test.koan)) {
                return false;
            }
        }

        introduceConsole(test);

        final var interceptionResult = StdStreamsInterceptor.capture(
            p == Printer.SILENT,
            () -> ScriptRunner.execute(test.koan.koanClass, locale, test.script),
            test.stdInInputs(locale)
        );

        final var result = new KoanResult(
            locale,
            test,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue
        );

        concludeConsole(test.koan);

        return result.executeAssertions(p);
    }

    private void introduceConsole(final KoanTest test) {
        String testedExpression = test.script[test.script.length - 1].formatSourceCode(locale);

        p.println();

        if (test.script.length > 1 || test.koan.showStdInInputs) {
            p.println(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN); // Seulement si code de prep ou stdin input
            if (test.koan.showStdInInputs) {
                p.println(WHEN_ANSWERING, Helpers.formatSequence(locale, test.stdInInputs(locale)));
            }
            if (test.script.length > 1) {
                p.println(WHEN_EXECUTING);
                p.println();
                p.println(Expression.formatPreparationSourceCode(test.script, locale, "    "));
                p.println(AND_FINALLY_LOOKING_THE_RESULT_OF, test.script[test.script.length - 1].formatSourceCode(locale));
            } else {
                p.println(WHEN_LOOKING_THE_RESULT_OF, testedExpression);
            }
        } else {
            p.println(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN_LOOKING_AT, testedExpression);
        }

        p.println();
        if (test.koan.usesConsole) {
            p.println();
            p.println(CONSOLE);
            p.println("---------");
            p.println();
        }
    }

    private void concludeConsole(final Koan koan) {
        if (koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }
    }

    private void encourage(final Koan koan) {
        p.println();
        p.println(THINKING, koan.koanClass.get(locale).simpleClassName);
        p.println(Color.red(HAS_DAMAGED_YOUR_KARMA), koan.koanName);
        p.println();
        p.println(THE_MASTER_SAYS);
        p.println(Color.cyan(YOU_HAVE_NOT_REACHED_ENLIGHTMENT));
        p.println();
        p.println("---------");
        p.println();
        p.println(THE_ANSWERS_YOU_SEEK);
        p.println();
    }

    private void offerToMeditate(final Koan koan) {
        p.println();
        p.println(
            PLEASE_MEDITATE_ON, 
            koan.koanName,
            koan.koanClass.get(locale).simpleClassName
        );
        p.println();
    }

    private void showProgress(final int successfulCount) {
        if (successfulCount == 0) {
            // Let's not be discouraging...
            return;
        }

        final var bar = String.format(
            "%s%s%s",
            Color.green(repeat(".", successfulCount)),
            Color.red("X"),
            Color.cyan(repeat("_", allKoans.size() - successfulCount - 1)));
        p.println(YOUR_PROGRESS_THUS_FAR, bar, successfulCount, allKoans.size());
        p.println();
    }

    private static String repeat(final String s, final int times) {
        return new String(new char[times]).replace("\0", s);
    }
}
