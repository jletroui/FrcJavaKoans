package engine;

import java.util.Optional;
import java.util.OptionalInt;

public class KoanResult {
    public final String[] stdOutLines;
    private final String[] stdInLines;
    public final Object koanReturnValue;
    public final Object[] koanParameters;

    public KoanResult(String[] stdOutLines, String[] stdInLines, Object koanReturnValue, Object[] koanParameters) {
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.koanReturnValue = koanReturnValue;
        this.koanParameters = koanParameters;
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
}
