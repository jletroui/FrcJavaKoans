package engine;

import java.util.Optional;

import engine.script.ExecutionContext;
import engine.text.Locale;

/**
 * Stores all the information about what happened during the execution of a koan.
 */
public final class TestOutput {
    private final String[] stdOutLines;
    private int stdOutIndex = 0;
    private final String[] stdInLines;
    private int stdInIndex = 0;
    public final Object executionResult;
    public final Optional<ExecutionContext> executionContext;
    /**
     * The source code of the last script expression, the one we are actually asserting the result and/or the console output.
     */
    public final String resultExpressionSourceCode;
    public final Koan koan;
    public final KoanTest test;
    public final Locale locale;

    public TestOutput(final Locale locale, final Koan koan, final KoanTest test, final String[] stdOutLines, final String[] stdInLines, final Object executionResult, final Optional<ExecutionContext> executionContext) {
        this.locale = locale;
        this.koan = koan;
        this.test = test;
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.executionResult = executionResult;
        this.executionContext = executionContext;
        resultExpressionSourceCode = test.script[test.script.length - 1].formatSourceCode(locale);
    }

    public static TestOutput empty(final Locale locale, final Koan koan, final KoanTest test) {
        return new TestOutput(locale, koan, test, new String[0], new String[0], null, Optional.empty());
    }

    public Optional<String> inputLine(final int inputIndex) {
        if (this.stdInLines.length <= inputIndex) {
            return Optional.empty();
        }
        return Optional.of(this.stdInLines[inputIndex].trim());
    }

    public Optional<String> nextStdInLine() {
        if (stdInLines.length <= stdInIndex) {
            return Optional.empty();
        }
        return Optional.of(stdInLines[stdInIndex++].trim());
    }

    Optional<String> nextStdOutLine() {
        if (stdOutLines.length <= stdOutIndex) {
            return Optional.empty();
        }
        return Optional.of(stdOutLines[stdOutIndex++].trim());
    }

    /**
     * Returns the random number generated during the koan execution.
     */
    public double randomNumber() {
        test.setupRandomForKoan();
        return Helpers.random();
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(final int count) {
        test.setupRandomForKoan();
        var res = new double[count];
        for(int i=0; i<count; i++) {
            res[i] = Helpers.random();
        }
        return res;
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(final int fromOffset, final int count) {
        test.setupRandomForKoan();
        var res = new double[count];
        for(int i=0; i< fromOffset + count; i++) {
            final var nb = Helpers.random();
            if (i >= fromOffset) {
                res[i - fromOffset] = nb;
            }
        }
        return res;
    }

    /**
     * Returns the nth random number generated during the koan execution.
     */
    public double randomNumber(final int n) {
        test.setupRandomForKoan();
        var res = 0.0;
        for(int i=0; i<=n; i++) {
            res = Helpers.random();
        }
        return res;
    }

    public String debugTestName() {
        return test.debugTestName(locale, koan);
    }
}
