package engine.test.runner;

import java.util.Arrays;

public class RunnerAssertions {
    public static class AssertionFailure extends RuntimeException {
        public AssertionFailure() {
            super("Assertion failure");
        }
    }

    public static void fail(String msg, Object... args) {
        System.out.println(String.format(msg, args));
        throw new AssertionFailure();
    }

    public static void assertTrue(boolean condition, String msg, Object... args) {
        if (!condition) {
            fail(msg, args);
        }
    }

    private static String format(int[] array) {
        return String.join(
            ",",
            Arrays.stream(array).mapToObj(Integer::toString).toArray(String[]::new)
        );
    }

    public static void assertEquals(Class<?> expected, Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected.getName());
        assertTrue(actual instanceof Class, "Expected %s but got a %s", expected.getName(), actual.getClass().getName());
        assertTrue(expected.equals(actual), "%s is not equal to expected %s", ((Class<?>)actual).getName(), expected.getName());
    }

    public static void assertEquals(String expected, Object actual) {
        assertTrue(actual != null, "Expected \"%s\" but got null", expected);
        assertTrue(actual instanceof String, "Expected \"%s\" but got a %s", expected, actual.getClass().getName());
        assertTrue(expected.equals(actual), "\"%s\" is not equal to expected \"%s\"", actual, expected);
    }

    public static void assertEquals(int expected, Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Integer, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (int)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(double expected, Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Double, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (double)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(boolean expected, Object actual) {
        assertTrue(actual != null, "Expected %s but got null", expected);
        assertTrue(actual instanceof Boolean, "Expected %s but got a %s", expected, actual.getClass().getName());
        assertTrue(expected == (boolean)actual, "%s is not equal to expected %s", actual, expected);
    }

    public static void assertEquals(int[] expected, Object actual) {
        final var expectedFmted = format(expected);
        assertTrue(actual != null, "Expected %s but got null", expectedFmted);
        assertTrue(actual instanceof int[], "Expected %s but got a %s", expectedFmted, actual.getClass().getName());
        assertTrue(Arrays.equals(expected, (int[])actual), "%s is not equal to expected %s", format((int[])actual), expectedFmted);
    }
}
