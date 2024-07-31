package engine;

import java.util.stream.IntStream;

import engine.script.ScriptRunner;
import engine.test.runner.CapturingPrinter;
import engine.test.runner.Line;
import engine.test.runner.OutputCapture;

/**
 * A sensei used by automated tests. Used by koans engine contributors.
 */
public class TestSensei {
    private static final Locale DEFAULT_TEST_LOCALE = Locale.en;

    public record TestResult(Locale locale, int testIndex, Koan koan, boolean succeeded, OutputCapture output) {
        public boolean hasCaptured(final Line... lines) {
            return output.hasCaptured(lines);
        }

        public String toString() {
            return String.format("%s/%s[%d]", koan.koanClass.get(locale).simpleClassName, koan.koanName.get(locale), testIndex);
        }
    }

    private record KoanTestIndex(Locale locale, int testIndex, KoanTest test) {
        TestResult toResult(final boolean succeed, final CapturingPrinter output) {
            return new TestResult(locale, testIndex, test.koan, succeed, output);
        }
    }

    public static TestResult[] execute(final Koan koan) {
        return execute(koan, DEFAULT_TEST_LOCALE);
    }

    public static TestResult[] execute(final Koan koan, final Locale locale) {
        return IntStream
            .range(0, koan.tests.length)
            .mapToObj(i -> new KoanTestIndex(locale, i, koan.tests[i]))
            .map(TestSensei::executeTest)
            .toArray(TestResult[]::new);
    }

    private static TestResult executeTest(final KoanTestIndex testIndex) {
        final var test = testIndex.test;
        final var capturingPrinter = new CapturingPrinter(testIndex.locale());

        test.setupRandomForKoan();

        for(final var assertion: test.koan.beforeAssertions) {
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
