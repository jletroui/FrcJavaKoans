package engine.test.runner;

import java.util.Map;

import engine.ConsoleFmt;
import engine.Koan;
import engine.TestSensei;
import engine.TestSensei.TestResult;

/**
 * This kind of unit test allows to test koan mechanisms, by asserting the console output of successful or failing koans.
 */
public record KoanEngineIntegrationTest(Koan koan, UnitTestExpectation... expectations) implements KoanEngineAutomatedTest {
    private static final Map<Boolean, String> SUCCESS_WORDING = Map.of(
        true, "success",
        false, "failure"
    );
    private static final String RED_FAILURE =  ConsoleFmt.red("FAILURE");

    public RunResult runTest() {
        var totalCount = 0;
        var successCount = 0;
        var actualResults = TestSensei.execute(koan);
        for(int i=0; i<expectations.length; i++) {
            totalCount++;
            if (assertResult(expectations[i], actualResults.get(i))) {
                successCount++;
            }
        }
        return new RunResult(totalCount, successCount);
    }

    private static boolean assertResult(UnitTestExpectation expected, TestResult actual) {
        if (expected.koanSucceeded() != actual.succeeded()) {
            System.out.println(String.format("%s %s: expected a %s but got a %s", RED_FAILURE, actual, SUCCESS_WORDING.get(expected.koanSucceeded()), SUCCESS_WORDING.get(actual.succeeded())));
            displayOutputDiff(expected, actual);
            return false;
        }
        if (!actual.hasCaptured(expected.expectedOutput())) {
            System.out.println(String.format("%s %s: output differ from expected", RED_FAILURE, actual));
            displayOutputDiff(expected, actual);
            return false;
        }
        return true;
    }

    private static void displayOutputDiff(UnitTestExpectation expected, TestResult actual) {
        System.out.println("Expected output:");
        expected.displayOutputToConsole(actual.locale());
        System.out.println();
        System.out.println("Actual output:");
        actual.displayOutputToConsole();
        System.out.println();
    }    
}
