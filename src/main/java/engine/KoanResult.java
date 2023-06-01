package engine;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;

import engine.Koan.KoanMethodCall;

/**
 * Stores all the information about what happened during the execution of a koan.
 */
public class KoanResult {
    public final String[] stdOutLines;
    private final String[] stdInLines;
    public final Object koanReturnValue;
    public final KoanMethodCall call;
    private final long seed;

    public KoanResult(KoanMethodCall call, String[] stdOutLines, String[] stdInLines, Object koanReturnValue, long seed) {
        this.call = call;
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.koanReturnValue = koanReturnValue;
        this.seed = seed;
    }

    public String inputLine(StdInInput input) {
        if (this.stdInLines.length <= input.index) {
            return "";
        }
        return this.stdInLines[input.index].trim();
    }

    public Optional<String> inputLine(int inputIndex) {
        if (this.stdInLines.length <= inputIndex) {
            return Optional.empty();
        }
        return Optional.of(this.stdInLines[inputIndex].trim());
    }

    public OptionalInt inputLineAsInt(int inputIndex) {
        if (this.stdInLines.length <= inputIndex) {
            return OptionalInt.empty();
        }
        try {
            int value = Integer.parseInt(this.stdInLines[inputIndex].trim());
            return OptionalInt.of(value);
        } catch(NumberFormatException nfe) {
            return OptionalInt.empty();
        }
    }

    /**
     * Creates a FormatParam taking the stdIn input for the given index, convert it to an int, and increment it by the given increment.
     * 
     * Ex: if input 0 contains "3", then paramPlus(0, 10) will return "13".
     */
    public static FormatParam paramPlus(int inputIndex, int increment) { 
        return res -> 
            res.inputLineAsInt(inputIndex)
                .stream()
                .mapToObj(n -> String.valueOf(n + increment))
                .findFirst()
                .orElse("");
    }

    /**
     * Returns the random number generated during the koan execution.
     */
    public double randomNumber() {
        var rng = new Random(seed);
        return rng.nextDouble();
    }

    /**
     * Returns the first count random numbers generated during the koan execution.
     */
    public double[] randomNumbers(int count) {
        var rng = new Random(seed);
        var res = new double[count];
        for(int i=0; i<count; i++) {
            res[i] = rng.nextDouble();
        }
        return res;
    }

    /**
     * Returns the nth random number generated during the koan execution.
     */
    public double randomNumber(int n) {
        var rng = new Random(seed);
        var res = 0.0;
        for(int i=0; i<=n; i++) {
            res = rng.nextDouble();
        }
        return res;
    }

    public boolean executeAssertions(Locale locale, Printer p, Assertion... assertions) {
        for (Assertion as : assertions) {
            if (!as.validate(locale, p, this)) {
                return false;
            }
        }
        return true;
    }
}
