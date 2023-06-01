package engine;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

import static engine.Texts.*;

/**
 * Library of various assertions which can be run about the result of a koan execution.
 */
public class Assertions {
    private static String resolveParam(KoanResult res, Object p) {
        if (p instanceof FormatParam) {
            return ((FormatParam)p).format(res);
        }

        return Optional.ofNullable(p).map((v) -> v.toString()).orElse("");
    }

    private static String whenCalling(KoanResult res) {
        if (res.call.paramCount() > 0) {
            return String.format(" when calling %s", res.formatCall());
        }
        return "";
    }

    public static Assertion assertIf(boolean condition, Assertion inner) {
        return (p, res) -> condition ? inner.validate(p, res) : true;
    }

    public static Assertion assertNextStdOutLineEquals(Localizable<String> expectedTemplate, Object... params) {
        return (p, res) -> {
            final var realParams = Arrays.stream(params)
                .map((param) -> Assertions.resolveParam(res, param))
                .toArray();
            final var expected = String.format(expectedTemplate.get(res.locale), realParams);
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                return false;
            }
            if (!lineContent.get().equals(expected)) {
                if (lineContent.get().equals("")) {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                } else {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD), expected, whenCalling(res), lineContent.get());
                }
                return false;
            }

            p.println(Color.green(OK_DISPLAYED_IN_CONSOLE), expected, whenCalling(res));
            return true;
        };
    }

    public static Assertion assertNextStdOutLineIsEmpty() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING));
                return false;
            }
            if (!lineContent.get().equals("")) {
                p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD), lineContent.get());
                return false;
            }

            p.println(Color.green(OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE));
            return true;
        };
    }

    public static Assertion assertNoMoreLineInStdOut() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (!lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD), whenCalling(res), lineContent.get());
                return false;
            }

            return true;
        };
    }

    public static Assertion assertAskedInStdIn() {
        return (p, res) -> {
            final var lineContent = res.nextStdInLine();

            if (lineContent.isPresent()) {
                p.println(Color.green(OK_ASKED_FOR_LINE_IN_CONSOLE));
                return true;
            }
            p.println(Color.red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
            return false;
        };
    }

    public static Assertion assertReturnValueEquals(final int expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.formatCall(), expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED), res.formatCall(), expected, ((Integer)res.methodReturnValue).intValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT), res.formatCall(), expected);
            return true;
        }; 
    }

    private static final double EPSILON = 0.0000000001;
    private static boolean equals(Double actual, double expected) {
        var diff = Math.abs(actual.doubleValue() - expected);
        return diff < EPSILON;
    }

    public static Assertion assertReturnValueEquals(final double expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_NULL), res.formatCall(), expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Double)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (!equals((Double)res.methodReturnValue, expected)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED), res.formatCall(), expected, ((Double)res.methodReturnValue).doubleValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_DOUBLE), res.formatCall(), expected);
            return true;
        }; 
    }

    public static Assertion assertReturnValueEquals(final boolean expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_NULL), res.formatCall(), expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Boolean)) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Boolean)res.methodReturnValue).booleanValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED), res.formatCall(), expected, ((Boolean)res.methodReturnValue).booleanValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_BOOLEAN), res.formatCall(), expected);
            return true;
        }; 
    }

    public static Assertion assertReturnValueEquals(final Localizable<String> expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_NULL), res.formatCall(), expected.get(res.locale));
                return false;
            } else if (!(res.methodReturnValue instanceof String)) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (!((String)res.methodReturnValue).equals(expected.get(res.locale))) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED), res.formatCall(), expected.get(res.locale), (String)res.methodReturnValue);
                return false;
            }

            p.println(Color.green(OK_RETURNED_STRING), res.formatCall(), expected.get(res.locale));
            return true;
        }; 
    }

    public static Assertion assertReturnValueWithRandomEquals(DoubleToIntFunction expected) {
        return (p, res) -> {
            var randomNumber = res.randomNumber();
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.formatCall(), expected.applyAsInt(randomNumber));
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected.applyAsInt(randomNumber)) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED), 
                    res.call,
                    expected.applyAsInt(randomNumber),
                    randomNumber,
                    ((Integer)res.methodReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOM), res.formatCall(), expected.applyAsInt(randomNumber), randomNumber);
            return true;
        }; 
    }

    /**
     * Assert the result of a method using random numbers.
     * @param count the number of random numbers expected to be generated
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static Assertion assertReturnValueWithRandomEquals(int count, ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(count), buildExpected);
    }

    /**
     * Assert the result of a method using random numbers.
     * @param fromOffset the start of the random numbers sequence to pick
     * @param count the number of random numbers to pick
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static Assertion assertReturnValueWithRandomEquals(int fromOffset, int count, ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(fromOffset, count), buildExpected);
    }

    private static Assertion assertReturnValueWithRandomEquals(Function<KoanResult, double[]> randomNumbersFunc, ResToIntFunction buildExpected) {
        return (p, res) -> {
            var randomNumbers = randomNumbersFunc.apply(res);
            var formatRandomNumbers = Helpers.formatSequence(randomNumbers, AND.get(res.locale));

            int expected = buildExpected.apply(res);
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.formatCall(), expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.formatCall(), res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED), 
                    res.call,
                    expected,
                    formatRandomNumbers,
                    ((Integer)res.methodReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOMS), res.formatCall(), expected, formatRandomNumbers);
            return true;
        }; 
    }
}
