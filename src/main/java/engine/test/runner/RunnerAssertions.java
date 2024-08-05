package engine.test.runner;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

import engine.Fmt;
import engine.TestSensei.TestResult;

public class RunnerAssertions {
    private static final Map<Boolean, String> SUCCESS_WORDING = Map.of(
        true, "PASS",
        false, "FAIL"
    );

    public static class AssertionFailure extends RuntimeException {
        public AssertionFailure(final String msg) {
            super(msg);
        }
    }

    public static void fail(final String msg, final Object... args) {
        throw new AssertionFailure(String.format(msg, args));
    }

    public static void assertTrue(final boolean condition, final String msg, Object... args) {
        if (!condition) {
            fail(msg, args);
        }
    }

    private static String format(final int[] array) {
        return String.join(
            ",",
            Arrays.stream(array).mapToObj(Integer::toString).toArray(String[]::new)
        );
    }

    public static void assertEquals(final Class<?> expected, final Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected.getName());
        assertTrue(actual instanceof Class, "Expected %s but got a %s", expected.getName(), actual.getClass().getName());
        assertTrue(expected.equals(actual), "%s is not equal to expected %s", ((Class<?>)actual).getName(), expected.getName());
    }

    public static void assertEquals(final String expected, final Object actual) {
        assertTrue(actual != null, "Expected \"%s\" but got null", expected);
        assertTrue(actual instanceof String, "Expected \"%s\" but got a %s", expected, actual.getClass().getName());
        assertTrue(expected.equals(actual), "\"%s\" is not equal to expected \"%s\"", actual, expected);
    }

    public static void assertEquals(final int expected, final Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Integer, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (int)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(final double expected, final Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Double, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (double)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(final boolean expected, final Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Boolean, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (boolean)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(final int[] expected, final Object actual) {
        final var expectedFmted = format(expected);
        assertTrue(actual != null, "Expected %s but got null", expectedFmted);
        assertTrue(actual instanceof int[], "Expected %s but got a %s", expectedFmted, actual.getClass().getName());
        assertTrue(Arrays.equals(expected, (int[])actual), "%s is not equal to expected %s", format((int[])actual), expectedFmted);
    }

    public static void assertKoanPass(final TestResult[] results) {
        assertTrue(results.length == 0, "Expected koan to not have any assertions.");
    }

    public static void assertKoanPass(final TestResult actual, final Fmt... expectedConsoleLines) {
        assertResult(true, actual, expectedConsoleLines);
    }

    public static void assertKoanFails(final TestResult actual, final Fmt... expectedConsoleLines) {
        assertResult(false, actual, expectedConsoleLines);
    }

    private static void assertResult(final boolean expectedSucceeded, final TestResult actual, final Fmt... expectedConsoleLines) {
        if (expectedSucceeded != actual.succeeded()) {
            fail(String.format(
                "%s: expected a %s but got a %s%n%s",
                actual, 
                SUCCESS_WORDING.get(expectedSucceeded), 
                SUCCESS_WORDING.get(actual.succeeded()),
                outputDiff(expectedConsoleLines, actual)
            ));
        }
        if (!actual.hasCaptured(expectedConsoleLines)) {
            fail(String.format(
                "%s: output differ from expected%n%s",
                actual,
                outputDiff(expectedConsoleLines, actual)
            ));
        }
    }

    private static String outputDiff(final Fmt[] expectedOutput, final TestResult actual) {
        final var diff = new StringBuilder();
        final Consumer<String> println = (s) -> diff.append(String.format("%s%n", s));

        println.accept("Expected output:");
        for(final var line: expectedOutput) {
            println.accept(line.format(actual.locale()));
        }
        println.accept("Actual output:");
        diff.append(actual.output().capturedOutputAsString());

        return diff.toString();
    }           
}
