package engine;

import java.util.Optional;

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
        return this.stdInLines[input.index];
    }


    public Optional<String> inputLine(int inputIndex) {
        if (this.stdInLines.length <= inputIndex) {
            return Optional.empty();
        }
        return Optional.of(this.stdInLines[inputIndex]);
    }
}
