package engine;

import java.util.List;
import java.util.stream.IntStream;

import engine.script.ScriptRunner;
import engine.test.Line;

public class TestSensei {
    public static final Locale TEST_LOCALE = Locale.en;

    public record TestResult(int testIndex, KoanTest test, boolean succeeded, CapturingPrinter output) {
        public boolean hasCaptured(final Line... lines) {
            return output.hasCaptured(lines);
        }

        public void displayOutputToConsole() {
            output.displayToConsole();
        }

        public String toString() {
            return String.format("%s/%s[%d]", test.koan.koanClass.get(TEST_LOCALE).simpleClassName, test.koan.koanName, testIndex);
        }
    }

    public static List<TestResult> execute(Koan koan) {
        return IntStream
            .range(0, koan.tests.length)
            .mapToObj(i -> new KoanTestIndex(i, koan.tests[i]))
            .map(TestSensei::executeTest)
            .toList();
    }

    private record KoanTestIndex(int testIndex, KoanTest test) {
        TestResult toResult(final boolean succeed, final CapturingPrinter output) {
            return new TestResult(testIndex, test, succeed, output);
        }
    }

    private static TestResult executeTest(final KoanTestIndex testIndex) {
        final var test = testIndex.test;
        final var capturingPrinter = new CapturingPrinter(TEST_LOCALE);

        test.setupRandomForKoan();

        for(var assertion: test.koan.beforeAssertions) {
            if (!assertion.validate(capturingPrinter, TEST_LOCALE, test.koan)) {
                return testIndex.toResult(false, capturingPrinter);
            }
        }

        final var interceptionResult = StdStreamsInterceptor.capture(
            true,
            () -> ScriptRunner.execute(test.koan.koanClass, TEST_LOCALE, test.script),
            test.stdInInputs(TEST_LOCALE)
        );

        final var result = new KoanResult(
            TEST_LOCALE,
            test,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue
        );

        return testIndex.toResult(result.executeAssertions(capturingPrinter), capturingPrinter);
    }
}
