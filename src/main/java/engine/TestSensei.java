package engine;

import java.util.Optional;
import java.util.stream.IntStream;

import engine.script.ScriptRunner;
import engine.test.runner.CapturingPrinter;
import engine.test.runner.OutputCapture;

/**
 * A sensei used by automated tests. Used by koans engine contributors.
 */
public class TestSensei {
    private static final Locale DEFAULT_TEST_LOCALE = Locale.en;

    public record TestResult(Locale locale, int testIndex, KoanResult koanResult, boolean succeeded, OutputCapture output) {
        public boolean hasCaptured(final Fmt... lines) {
            return output.hasCaptured(lines);
        }

        public String toString() {
            return String.format("%s/%s[%d]", koanResult.test.koan.koanClass.get(locale).simpleClassName, koanResult.test.koan.koanName.get(locale), testIndex);
        }
    }

    private record KoanTestIndex(Locale locale, int testIndex, KoanTest test) {
        TestResult toResult(final KoanResult koanResult, final boolean succeed, final CapturingPrinter output) {
            return new TestResult(locale, testIndex, koanResult, succeed, output);
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
                final var emptyResult = new KoanResult(
                    testIndex.locale(),
                    test,
                    new String[0],
                    new String[0],
                    null,
                    Optional.empty()
                );
                return testIndex.toResult(emptyResult, false, capturingPrinter);
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
            Optional.of(interceptionResult.returnValue.context())
        );

        return testIndex.toResult(result, result.executeAssertions(capturingPrinter), capturingPrinter);
    }
}
