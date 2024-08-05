package engine.test.runner;

import engine.Fmt;

public interface OutputCapture {
    boolean hasCaptured(final Fmt... lines);
    String capturedOutputAsString();
}
