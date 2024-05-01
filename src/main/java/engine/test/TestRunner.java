package engine.test;

import java.util.List;
import java.util.Map;

import engine.ConsoleFmt;
import engine.TestSensei;
import engine.TestSensei.TestResult;

public class TestRunner {
    private static final List<List<UnitTest>> TO_RUN = List.of(
        ConsoleUnitTests.items
    );
    private static final Map<Boolean, String> SUCCESS_WORDING = Map.of(
        true, "success",
        false, "failure"
    );
    private static final String RED_FAILURE =  ConsoleFmt.red("FAILURE");

    public static void main(String[] args) {
        var totalCount = 0;
        var successCount = 0;

        for(var unitTestSeries: TO_RUN) {
            for(var unitTest: unitTestSeries) {
                var actualResults = TestSensei.execute(unitTest.koan());
                for(int i=0; i<unitTest.expectations().length; i++) {
                    totalCount++;
                    if (assertResult(unitTest.expectations()[i], actualResults.get(i))) {
                        successCount++;
                    }
                }
            }
        }

        System.out.println(String.format("%d/%d tests passed.", successCount, totalCount));
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
        expected.displayOutputToConsole(TestSensei.TEST_LOCALE);
        System.out.println();
        System.out.println("Actual output:");
        actual.displayOutputToConsole();
        System.out.println();
    }
}
