package engine;

import static engine.ContentFormatting.print;

public class Assertions {
    public static Assertion assertOutEquals(int outLineIndex, String expectedTemplate, Object... params) {
        final var expected = String.format(expectedTemplate, params);

        return (silent, res) -> {
            if (res.stdOutLines.length < outLineIndex - 1) {
                print(silent, "Expected to read '%s' in the console, but read nothing instead", expected);
                return false;
            }
            if (!res.stdOutLines[outLineIndex].equals(expected)) {
                if (res.stdOutLines[outLineIndex].trim().equals("")) {
                    print(silent, "Expected to read '%s' in the console, but read nothing instead", expected);
                } else {
                    print(silent, "Expected to read '%s' in the console, but read '%s' instead", expected, res.stdOutLines[outLineIndex]);
                }
                return false;
            }
            
            print(silent, "Ok: read '%s' in the console", expected);
            return true;
        };
    }
}
