package engine;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;

import static engine.Texts.*;

/**
 * Library of various assertions which can be run about the result of a koan execution.
 */
public class Assertions {
    private static String resolveParam(KoanResult res, Object p) {
        if (p instanceof StdInInput) {
            return res.inputLine((StdInInput)p);
        } else if (p instanceof FormatParam) {
            return ((FormatParam)p).format(res);
        }

        return Optional.ofNullable(p).map((v) -> v.toString()).orElse("");
    }

    private static String whenCalling(KoanResult res) {
        if (res.call.params.length > 0) {
            return String.format(" when calling %s", res.call);
        }
        return "";
    }

    public static Assertion assertOutEquals(int outLineIndex, Localizable<String> expectedTemplate, Object... params) {
        return (locale, p, res) -> {
            final var realParams = Arrays.stream(params)
                .map((param) -> Assertions.resolveParam(res, param))
                .toArray();
            final var expected = String.format(expectedTemplate.get(locale), realParams);

            if (res.stdOutLines.length < outLineIndex + 1) {
                p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                return false;
            }
            if (!res.stdOutLines[outLineIndex].equals(expected)) {
                if (res.stdOutLines[outLineIndex].trim().equals("")) {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                } else {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD), expected, whenCalling(res), res.stdOutLines[outLineIndex]);
                }
                return false;
            }

            p.println(Color.green(OK_DISPLAYED_IN_CONSOLE), expected, whenCalling(res));
            return true;
        };
    }

    public static Assertion assertOutNoLineAfter(int outLineCount) {
        return (locale, p, res) -> {
            for(int i = outLineCount; i < res.stdOutLines.length; i++) {
                if (!res.stdOutLines[i].trim().equals("")) {
                    p.println(Color.red(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD), whenCalling(res), res.stdOutLines[i]);
                    return false;
                }
            }

            return true;
        };
    }

    public static Assertion assertAskedInStdIn(final int inLineIndex) {
        return (locale, p, res) -> {
            if (res.inputLine(inLineIndex).isPresent()) {
                p.println(Color.green(OK_ASKED_FOR_LINE_IN_CONSOLE));
                return true;
            }
            p.println(Color.red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
            return false;
        };
    }

    public static Assertion assertResultEquals(final int expected) {
        return (locale, p, res) -> {
            if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.call, expected);
                return false;
            } else if (!(res.koanReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.koanReturnValue).intValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED), res.call, expected, ((Integer)res.koanReturnValue).intValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT), res.call, expected);
            return true;
        }; 
    }

    private static final double EPSILON = 0.0000000001;
    private static boolean equals(Double actual, double expected) {
        var diff = Math.abs(actual.doubleValue() - expected);
        return diff < EPSILON;
    }

    public static Assertion assertResultEquals(final double expected) {
        return (locale, p, res) -> {
            if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_NULL), res.call, expected);
                return false;
            } else if (!(res.koanReturnValue instanceof Double)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (!equals((Double)res.koanReturnValue, expected)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED), res.call, expected, ((Double)res.koanReturnValue).doubleValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_DOUBLE), res.call, expected);
            return true;
        }; 
    }

    public static Assertion assertResultEquals(final boolean expected) {
        return (locale, p, res) -> {
            if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_NULL), res.call, expected);
                return false;
            } else if (!(res.koanReturnValue instanceof Boolean)) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Boolean)res.koanReturnValue).booleanValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED), res.call, expected, ((Boolean)res.koanReturnValue).booleanValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_BOOLEAN), res.call, expected);
            return true;
        }; 
    }

    public static Assertion assertResultEquals(final Localizable<String> expected) {
        return (locale, p, res) -> {
            if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_NULL), res.call, expected.get(locale));
                return false;
            } else if (!(res.koanReturnValue instanceof String)) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (!((String)res.koanReturnValue).equals(expected.get(locale))) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED), res.call, expected.get(locale), (String)res.koanReturnValue);
                return false;
            }

            p.println(Color.green(OK_RETURNED_STRING), res.call, expected.get(locale));
            return true;
        }; 
    }

    public static Assertion assertResultWithRandomEquals(DoubleToIntFunction expected) {
        return (locale, p, res) -> {
            var randomNumber = res.randomNumber();
            if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.call, expected.applyAsInt(randomNumber));
                return false;
            } else if (!(res.koanReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.koanReturnValue).intValue() != expected.applyAsInt(randomNumber)) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED), 
                    res.call,
                    expected.applyAsInt(randomNumber),
                    randomNumber,
                    ((Integer)res.koanReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOM), res.call, expected.applyAsInt(randomNumber), randomNumber);
            return true;
        }; 
    }

    public static Assertion assertResultWithRandomEquals(int count, DoubleArrayToIntFunction expected) {
        return (locale, p, res) -> {
            var randomNumbers = res.randomNumbers(count);
            var formatRandomNumbers = String.join(
                ", ",
                Arrays.stream(randomNumbers).mapToObj(Double::toString).toList()
            );
        if (res.koanReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.call, expected.apply(randomNumbers));
                return false;
            } else if (!(res.koanReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.call, res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.koanReturnValue).intValue() != expected.apply(randomNumbers)) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED), 
                    res.call,
                    expected.apply(randomNumbers),
                    formatRandomNumbers,
                    ((Integer)res.koanReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOMS), res.call, expected.apply(randomNumbers), formatRandomNumbers);
            return true;
        }; 
    }
}
