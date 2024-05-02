package engine.test;

import engine.Locale;

public record UnitTestExpectation(boolean koanSucceeded, Line... expectedOutput) {
    public void displayOutputToConsole(Locale locale) {
        for(var line: expectedOutput) {
            System.out.println(line.resolve(locale));
        }
    }

    public static UnitTestExpectation assertSuccess(Line... expectedOutput) {
        return new UnitTestExpectation(true, expectedOutput);
    }

    public static UnitTestExpectation assertFailure(Line... expectedOutput) {
        return new UnitTestExpectation(false, expectedOutput);
    }
}
