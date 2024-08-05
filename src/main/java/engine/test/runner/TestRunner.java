package engine.test.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import engine.Fmt;
import engine.Locale;
import engine.test.runner.RunnerAssertions.AssertionFailure;

/**
 * This is a stripped down, poor's version of JUnit, since we don't have access to any dependency.
 */
public class TestRunner {
    private static final String RED_FAILURE =  Fmt.red("FAILURE").format(Locale.en);

    public static void main(final String[] args) {
        var totalCount = 0;
        var successCount = 0;

        for(final var testSuite: findTestSuites()) {
            for(final var test: testSuite) {
                try {
                    test.run();
                    successCount++;
                } catch(final AssertionFailure _af) {
                    // Nothing else to do other than not increasing successCount, since feedback is already given to the console by the test.
                }
                totalCount ++;
            }
        }

        System.out.printf("%d/%d tests passed.%n", successCount, totalCount);
    }

    private static List<List<Runnable>> findTestSuites() {
        try(final var packageStream = TestRunner.class.getClassLoader().getResourceAsStream("engine/test")) {
            try(final var reader = new BufferedReader(new InputStreamReader(packageStream))) {
                return reader
                    .lines()
                    .filter(line -> line.endsWith("Tests.class"))
                    .map(TestRunner::loadTestClass)
                    .map(TestRunner::findTestsInClass)
                    .toList();
            }
        } catch(final IOException ioe) {
            throw new RuntimeException("Cannot locate unit tests", ioe);
        }
    }

    private static Class<?> loadTestClass(final String testClassSimpleName) {
        final var className = "engine.test." + testClassSimpleName.substring(0, testClassSimpleName.lastIndexOf("."));   
        try {
            return Class.forName(className);
        } catch(final ClassNotFoundException cnfe) {
            throw new RuntimeException(String.format("Cannot load %s", className), cnfe);
        }
    }

    private static boolean isTestMethod(final Method m) {
        final var modifiers = m.getModifiers();
        return
            Modifier.isStatic(modifiers) &&
            Modifier.isPublic(modifiers) &&
            m.getParameters().length == 0 &&
            m.getName().startsWith("when"); // Small constraint on test method name, avoid having to define an @Test annotation.
    }

    private static Runnable toTest(final Method m) {
        return () -> {
            try {
                m.invoke(null);
            } catch(final IllegalAccessException _iae) {
                throw new RuntimeException("We should have verified in isTestMethod() that the method is accessible");
            } catch(final IllegalArgumentException _iae) {
                throw new RuntimeException("We should have verified in isTestMethod() that the method has no argument");
            } catch(final InvocationTargetException ite) {
                if (ite.getCause() instanceof final AssertionFailure af) {
                    System.out.printf("%s %s.%s%n%s%n", RED_FAILURE, m.getDeclaringClass().getName(), m.getName(), af.getMessage());
                }
                if (ite.getCause() instanceof final RuntimeException re) {
                    throw re;
                } else {
                    throw new RuntimeException(ite);
                }
            }
        };
    }

    private static List<Runnable> findTestsInClass(final Class<?> clasz) {
        return Arrays
            .stream(clasz.getMethods())
            .filter(TestRunner::isTestMethod)
            .map(TestRunner::toTest)
            .toList();
    }
}
