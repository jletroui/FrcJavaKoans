package engine;

import static engine.ContentFormatting.print;

import java.util.Arrays;
import java.util.Optional;

public class Assertions {
    private static String resolveParam(KoanResult res, Object p) {
        if (p instanceof StdInInput) {
            return res.inputLine((StdInInput)p);
        } else if (p instanceof FormatParam) {
            return ((FormatParam)p).format(res);
        }

        return Optional.ofNullable(p).map((v) -> v.toString()).orElse("");
    }

    private static String formatMethodCall(KoanResult res) {
        String[] params = Arrays.stream(res.koanParameters)
            .map(p -> p == null ? "null": p.toString())
            .toArray(String[]::new);
        return String.format("%s(%s)", res.koan.methodName, String.join(", ", params));
    }

    public static Assertion assertOutEquals(int outLineIndex, String expectedTemplate, Object... params) {

        return (silent, res) -> {
            final var realParams = Arrays.stream(params)
                .map((p) -> Assertions.resolveParam(res, p))
                .toArray();
            final var expected = String.format(expectedTemplate, realParams);

            if (res.stdOutLines.length < outLineIndex + 1) {
                print(silent, "Expected to see '%s' in the console, but read nothing instead!", expected);
                return false;
            }
            if (!res.stdOutLines[outLineIndex].equals(expected)) {
                if (res.stdOutLines[outLineIndex].trim().equals("")) {
                    print(silent, "Expected to see '%s' in the console, but read nothing instead!", expected);
                } else {
                    print(silent, "Expected to see '%s' in the console, but read '%s' instead!", expected, res.stdOutLines[outLineIndex]);
                }
                return false;
            }

            print(silent, "Ok: displayed '%s' in the console.", expected);
            return true;
        };
    }

    public static Assertion assertAskedInStdIn(final int inLineIndex) {
        return (silent, res) -> {
            if (res.inputLine(inLineIndex).isPresent()) {
                print(silent, "Ok: asked for a line in the console.");
                return true;
            }
            print(silent, "Expected the user to be able to answer in the console, but they were not!");
            return false;
        };
    }

    public static Assertion assertResultEquals(final int expected) {
        return (silent, res) -> {
            if (res.koanReturnValue == null) {
                print(silent, "Expected %s to return %d but returned null instead!", formatMethodCall(res), expected);
                return false;
            } else if (!(res.koanReturnValue instanceof Integer)) {
                print(silent, "Expected %s to return an integer but returned a %s instead!", formatMethodCall(res), res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.koanReturnValue).intValue() != expected) {
                print(silent, "Expected %s to return %d but returned %d instead!", formatMethodCall(res), expected, ((Integer)res.koanReturnValue).intValue());
                return false;
            }

            print(silent, "Ok: %s returned %d.", formatMethodCall(res), expected);
            return true;
        }; 
    }
}
