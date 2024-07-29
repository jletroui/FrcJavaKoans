package engine;

import java.util.List;
import java.util.stream.IntStream;

import engine.script.ScriptRunner;
import engine.test.runner.Line;

/**
 * A sensei used to run unit tests for the FRC Java Koans, for contributors.
 */
public class TestSensei {
    public static final Locale DEFAULT_TEST_LOCALE = Locale.en;

    public record TestResult(Locale locale, int testIndex, KoanTest test, boolean succeeded, CapturingPrinter output) {
        public boolean hasCaptured(final Line... lines) {
            return output.hasCaptured(lines);
        }

        public void displayOutputToConsole() {
            output.displayToConsole();
        }

        public String toString() {
            return String.format("%s/%s[%d]", test.koan.koanClass.get(locale).simpleClassName, test.koan.koanName.get(locale), testIndex);
        }
    }

    public static List<TestResult> execute(Koan koan) {
        return execute(koan, DEFAULT_TEST_LOCALE);
    }

    public static List<TestResult> execute(Koan koan, Locale locale) {
        return IntStream
            .range(0, koan.tests.length)
            .mapToObj(i -> new KoanTestIndex(locale, i, koan.tests[i]))
            .map(TestSensei::executeTest)
            .toList();
    }

    private record KoanTestIndex(Locale locale, int testIndex, KoanTest test) {
        TestResult toResult(final boolean succeed, final CapturingPrinter output) {
            return new TestResult(locale, testIndex, test, succeed, output);
        }
    }

    private static TestResult executeTest(final KoanTestIndex testIndex) {
        final var test = testIndex.test;
        final var capturingPrinter = new CapturingPrinter(testIndex.locale());

        test.setupRandomForKoan();

        for(var assertion: test.koan.beforeAssertions) {
            if (!assertion.validate(capturingPrinter, testIndex.locale(), test.koan)) {
                return testIndex.toResult(false, capturingPrinter);
            }
        }

        final var interceptionResult = StdStreamsInterceptor.capture(
            true,
            () -> ScriptRunner.execute(test.koan.koanClass, testIndex.locale(), test.script),
            test.stdInInputs(testIndex.locale())
        );

        final var result = new KoanResult(
            testIndex.locale(),
            test,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue.executionResult(),
            interceptionResult.returnValue.context()
        );

        return testIndex.toResult(result.executeAssertions(capturingPrinter), capturingPrinter);
    }
}
