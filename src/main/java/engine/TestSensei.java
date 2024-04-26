package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.IntStream;

import engine.test.CapturingPrinter;
import engine.test.Line;

public class TestSensei {
    public static final Locale TEST_LOCALE = Locale.en;

    public record TestResult(int testIndex, KoanTest test, boolean succeeded, CapturingPrinter output) {
        public boolean hasCaptured(Line... lines) {
            return output.hasCaptured(lines);
        }

        public void displayOutputToConsole() {
            output.displayToConsole();
        }

        public String toString() {
            return String.format("%s.%s[%d]", test.koan.koanClass.get(TEST_LOCALE).simpleClassName, test.koan.methodName, testIndex);
        }
    }
    private record KoanTestIndex(int testIndex, KoanTest test) {
        TestResult toResult(boolean succeed, CapturingPrinter output) {
            return new TestResult(testIndex, test, succeed, output);
        }
    }

    public static List<TestResult> execute(Koan koan) {
        return IntStream
            .range(0, koan.tests.length)
            .mapToObj(i -> new KoanTestIndex(i, koan.tests[i]))
            .map(TestSensei::executeTest)
            .toList();
    }

    private static TestResult executeTest(KoanTestIndex testIndex) {
        var test = testIndex.test;
        var capturingPrinter = new CapturingPrinter(TEST_LOCALE);
        try {
            test.setupRandomForKoan();
            if (!test.koan.executeBeforeAssertions(capturingPrinter, TEST_LOCALE)) {
                return testIndex.toResult(false, capturingPrinter);
            }

            final var targetMethod = test.resolveTargetMethod(TEST_LOCALE);

            for(var prepCall: targetMethod.koanTest.objectPrepCalls) {
                prepCall.invoke(targetMethod.object, TEST_LOCALE);
            }

            final var interceptionResult = StdStreamsInterceptor.capture(
                true,
                targetMethod::invoke,
                test.stdInInputs(TEST_LOCALE)
            );

            final var result = new KoanResult(
                TEST_LOCALE,
                targetMethod,
                interceptionResult.stdOutLines,
                interceptionResult.stdInLines,
                interceptionResult.returnValue
            );

            return testIndex.toResult(result.executeAssertions(capturingPrinter), capturingPrinter);
        } catch(InvocationTargetException ite) {
            throw new RuntimeException("Oops, test method thrown an exception", ite);
        }
    }
}
