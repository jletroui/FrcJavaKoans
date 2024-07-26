package engine.test.runner;

public interface UnitTest {
    public record RunResult(int total, int succeeded) {}

    RunResult runTest();
}
