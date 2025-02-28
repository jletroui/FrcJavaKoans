package engine;

import java.util.Arrays;

import engine.test.runner.CapturingPrinter;
import engine.test.runner.SenseiOutputCapture;
import engine.text.Locale;

/**
 * A sensei used by automated tests. Used by koans engine contributors.
 */
public class TestSensei {
    private static final Locale DEFAULT_TEST_LOCALE = Locale.en;

    public record TestSenseiResult(ValidationResult testResult, SenseiOutputCapture outputCapture) {
        public String testName() {
            return testResult.testOutput().debugTestName();
        }
    }

    public static TestSenseiResult[] execute(final Koan koan) {
        return execute(koan, DEFAULT_TEST_LOCALE);
    }

    public static TestSenseiResult[] execute(final Koan koan, final Locale locale) {
        return Arrays
            .stream(koan.tests)
            .map(test -> executeTest(locale, koan, test))
            .toArray(TestSenseiResult[]::new);
    }

    private static TestSenseiResult executeTest(final Locale locale, final Koan koan, final KoanTest test) {
        final CapturingPrinter printer = new CapturingPrinter(locale);

        final boolean preparationSucceeded = test.prepare(printer, locale, koan);
        if (!preparationSucceeded) {
            return new TestSenseiResult(ValidationResult.empty(locale, koan, test), printer);
        }

        final ValidationResult testResult = test.execute(printer, locale, koan, true);
        return new TestSenseiResult(testResult, printer);
    }
}
