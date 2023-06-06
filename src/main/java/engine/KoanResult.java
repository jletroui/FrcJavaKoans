package engine;

import java.util.Optional;

import engine.Koan.KoanMethodCall;

/**
 * Stores all the information about what happened during the execution of a koan.
 */
public class KoanResult {
    private final String[] stdOutLines;
    private int stdOutIndex = 0;
    private final String[] stdInLines;
    private int stdInIndex = 0;
    public final Object methodReturnValue;
    public final KoanMethodCall call;
    public final Locale locale;

    public KoanResult(Locale locale, KoanMethodCall call, String[] stdOutLines, String[] stdInLines, Object methodReturnValue) {
        this.locale = locale;
        this.call = call;
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.methodReturnValue = methodReturnValue;
    }

    public String formatCall() {
        return call.toString(locale);
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
     * Creates a FormatParam taking the stdIn input for the given index, convert it to an int, and increment it by the given increment.
     * 
     * Ex: if input 0 contains "3", then paramPlus(0, 10) will return "13".
     */
    public static FormatParam addToStdInInput(int inputIndex, int increment) { 
        return res -> 
            res.inputLine(inputIndex)
                .map(line -> {
                    try {
                        int value = Integer.parseInt(line);
                        return  String.valueOf(value + increment);
                    } catch(NumberFormatException nfe) {
                        // Ignore
                    }
                    return "";
                })
                .orElse("");
    }

    public static FormatParam stdInInput(int inputIndex) {
        return res -> res.inputLine(inputIndex).orElse("");
    }

    /**
     * Returns the random number generated during the koan execution.
     */
    public double randomNumber() {
        call.setupRandomForKoan();
        return Helpers.random();
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(int count) {
        call.setupRandomForKoan();
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
        call.setupRandomForKoan();
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
        call.setupRandomForKoan();
        var res = 0.0;
        for(int i=0; i<=n; i++) {
            res = Helpers.random();
        }
        return res;
    }

    public boolean executeAssertions(Printer p) {
        return executeAssertions(p, call.assertions);
    }

    public boolean executeAssertions(Printer p, ResultAssertion... assertions) {
        for (ResultAssertion as : assertions) {
            if (!as.validate(p, this)) {
                return false;
            }
        }
        return true;
    }
}
