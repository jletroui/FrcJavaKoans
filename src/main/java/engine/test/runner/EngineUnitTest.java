package engine.test.runner;

import engine.ConsoleFmt;
import engine.test.runner.RunnerAssertions.AssertionFailure;

/**
 * A 'standard' unit test, making it able to test engine.* classes.
 * This is a stripped down version of JUnit.
 */
public record EngineUnitTest(String testName, Runnable testScript) implements UnitTest {
    private static final RunResult PASS = new RunResult(1, 1);
    private static final RunResult FAIL = new RunResult(1, 0);
    private static final String RED_FAILURE =  ConsoleFmt.red("FAILURE");

    @Override
    public RunResult runTest() {
        try {
            testScript.run();
            return PASS;
        } catch(AssertionFailure _failure) {
            System.out.println(String.format("%s: %s", RED_FAILURE, testName));
            return FAIL;
        }
    }
}
