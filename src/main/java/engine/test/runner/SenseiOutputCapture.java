package engine.test.runner;

import engine.console.Fmt;

public interface SenseiOutputCapture {
    boolean stdOutLinesMatch(final Fmt... lines);
    String capturedStdOutAsString();
}
