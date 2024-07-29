package engine.test.runner;

public interface KoanEngineAutomatedTest {
    public record RunResult(int total, int succeeded) {}

    RunResult runTest();
}
