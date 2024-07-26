package engine.test.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        var totalCount = 0;
        var successCount = 0;

        for(var unitTestSeries: findTestSuites()) {
            for(var unitTest: unitTestSeries) {
                final var unitTestResult = unitTest.runTest();
                totalCount += unitTestResult.total();
                successCount += unitTestResult.succeeded();
            }
        }

        System.out.println(String.format("%d/%d tests passed.", successCount, totalCount));
    }

    private static List<List<UnitTest>> findTestSuites() {
        try(var packageStream = TestRunner.class.getClassLoader().getResourceAsStream("engine/test")) {
            try(var reader = new BufferedReader(new InputStreamReader(packageStream))) {
                return reader
                    .lines()
                    .filter(line -> line.endsWith("Tests.class"))
                    .map(line -> getTestClass(line))
                    .map(clasz -> getTestSuite(clasz))
                    .toList();
            }
        } catch(IOException ioe) {
            throw new RuntimeException("Cannot locate unit tests", ioe);
        }
    }

    private static Class<?> getTestClass(String testClassSimpleName) {
        final var className = "engine.test." + testClassSimpleName.substring(0, testClassSimpleName.lastIndexOf("."));   
        try {
            return Class.forName(className);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(String.format("Cannot load %s", className), cnfe);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<UnitTest> getTestSuite(Class<?> clasz) {
        try {
            return (List<UnitTest>)clasz.getField("tests").get(null);
        } catch(NoSuchFieldException nsfe) {
            System.out.println(String.format("WARNING: did not find a 'tests' static field in %s", clasz.getName()));
            return List.of();
        } catch(IllegalAccessException iae) {
            System.out.println(String.format("WARNING: cannot access the 'tests' static field in %s", clasz.getName()));
            return List.of();
        }
    }
}
