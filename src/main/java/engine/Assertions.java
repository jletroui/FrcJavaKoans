package engine;

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

        return (p, res) -> {
            final var realParams = Arrays.stream(params)
                .map((param) -> Assertions.resolveParam(res, param))
                .toArray();
            final var expected = String.format(expectedTemplate, realParams);

            if (res.stdOutLines.length < outLineIndex + 1) {
                p.println("Expected to see '%s' in the console, but read nothing instead!", expected);
                return false;
            }
            if (!res.stdOutLines[outLineIndex].equals(expected)) {
                if (res.stdOutLines[outLineIndex].trim().equals("")) {
                    p.println("Expected to see '%s' in the console, but read nothing instead!", expected);
                } else {
                    p.println("Expected to see '%s' in the console, but read '%s' instead!", expected, res.stdOutLines[outLineIndex]);
                }
                return false;
            }

            p.println("Ok: displayed '%s' in the console.", expected);
            return true;
        };
    }

    public static Assertion assertAskedInStdIn(final int inLineIndex) {
        return (p, res) -> {
            if (res.inputLine(inLineIndex).isPresent()) {
                p.println("Ok: asked for a line in the console.");
                return true;
            }
            p.println("Expected the user to be able to answer in the console, but they were not!");
            return false;
        };
    }

    public static Assertion assertResultEquals(final int expected) {
        return (p, res) -> {
            if (res.koanReturnValue == null) {
                p.println("Expected %s to return %d but returned null instead!", formatMethodCall(res), expected);
                return false;
            } else if (!(res.koanReturnValue instanceof Integer)) {
                p.println("Expected %s to return an integer but returned a %s instead!", formatMethodCall(res), res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.koanReturnValue).intValue() != expected) {
                p.println("Expected %s to return %d but returned %d instead!", formatMethodCall(res), expected, ((Integer)res.koanReturnValue).intValue());
                return false;
            }

            p.println("Ok: %s returned %d.", formatMethodCall(res), expected);
            return true;
        }; 
    }

    public static Assertion assertResultEquals(final String expected) {
        return (p, res) -> {
            if (res.koanReturnValue == null) {
                p.println("Expected %s to return \"%s\" but returned null instead!", formatMethodCall(res), expected);
                return false;
            } else if (!(res.koanReturnValue instanceof String)) {
                p.println("Expected %s to return a String but returned a %s instead!", formatMethodCall(res), res.koanReturnValue.getClass().getSimpleName());
                return false;
            } else if (!((String)res.koanReturnValue).equals(expected)) {
                p.println("Expected %s to return \"%s\" but returned \"%s\" instead!", formatMethodCall(res), expected, (String)res.koanReturnValue);
                return false;
            }

            p.println("Ok: %s returned \"%s\".", formatMethodCall(res), expected);
            return true;
        }; 
    }
}
