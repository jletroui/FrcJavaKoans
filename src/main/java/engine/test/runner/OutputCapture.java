package engine.test.runner;

public interface OutputCapture {
    boolean hasCaptured(final Line... lines);
    String capturedOutputAsString();
}
