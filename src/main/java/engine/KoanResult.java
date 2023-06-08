package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * Stores all the information about what happened during the execution of a koan.
 */
public class KoanResult {
    private final String[] stdOutLines;
    private int stdOutIndex = 0;
    private final String[] stdInLines;
    private int stdInIndex = 0;
    public final Object methodReturnValue;
    public final KoanTargetMethod targetMethod;
    public final Locale locale;

    public KoanResult(Locale locale, KoanTargetMethod targetMethod, String[] stdOutLines, String[] stdInLines, Object methodReturnValue) {
        this.locale = locale;
        this.targetMethod = targetMethod;
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.methodReturnValue = methodReturnValue;
    }

    public Optional<String> inputLine(int inputIndex) {
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
        targetMethod.koanTest.setupRandomForKoan();
        return Helpers.random();
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(int count) {
        targetMethod.koanTest.setupRandomForKoan();
        var res = new double[count];
        for(int i=0; i<count; i++) {
            res[i] = Helpers.random();
        }
        return res;
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(int fromOffset, int count) {
        targetMethod.koanTest.setupRandomForKoan();
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
    public double randomNumber(int n) {
        targetMethod.koanTest.setupRandomForKoan();
        var res = 0.0;
        for(int i=0; i<=n; i++) {
            res = Helpers.random();
        }
        return res;
    }

    public boolean executeAssertions(Printer p) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        return executeAssertions(p, targetMethod.koanTest.assertions);
    }

    public boolean executeAssertions(Printer p, ResultAssertion... assertions) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        for (ResultAssertion as : assertions) {
            if (!as.validate(p, this)) {
                return false;
            }
        }
        return true;
    }
}
