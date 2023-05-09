package engine;

public class KoanResult {
    public final String[] stdOutLines;
    public final String[] stdInLines;
    public final Object koanReturnValue;
    public final Object[] koanParameters;

    public KoanResult(String[] stdOutLines, String[] stdInLines, Object koanReturnValue, Object[] koanParameters) {
        this.stdOutLines = stdOutLines;
        this.stdInLines = stdInLines;
        this.koanReturnValue = koanReturnValue;
        this.koanParameters = koanParameters;
    }
}
