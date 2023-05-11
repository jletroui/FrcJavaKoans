package engine;

import static engine.ContentFormatting.print;

import java.util.Arrays;

public class Assertions {
    public static Assertion assertOutEquals(int outLineIndex, String expectedTemplate, Object... params) {

        return (silent, res) -> {
            final var realParams = Arrays.stream(params)
                .map((p) -> p instanceof StdInInput ? res.inputLine((StdInInput)p) : p)
                .toArray();
            final var expected = String.format(expectedTemplate, realParams);

            if (res.stdOutLines.length < outLineIndex + 1) {
                print(silent, "Expected to see '%s' in the console, but read nothing instead", expected);
                return false;
            }
            if (!res.stdOutLines[outLineIndex].equals(expected)) {
                if (res.stdOutLines[outLineIndex].trim().equals("")) {
                    print(silent, "Expected to see '%s' in the console, but read nothing instead", expected);
                } else {
                    print(silent, "Expected to see '%s' in the console, but read '%s' instead", expected, res.stdOutLines[outLineIndex]);
                }
                return false;
            }

            print(silent, "Ok: displayed '%s' in the console", expected);
            return true;
        };
    }

    public static Assertion assertAskedInStdIn(int inLineIndex) {
        return (silent, res) -> {
            if (res.inputLine(inLineIndex).isPresent()) {
                print(silent, "Ok: asked for a line in the console");
                return true;
            }
            print(silent, "Expected the user to be able to answer in the console, but they were not");
            return false;
        };
    }
}
