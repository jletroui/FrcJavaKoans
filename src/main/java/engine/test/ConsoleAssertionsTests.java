package engine.test;

import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertNextStdOutLineEquals;
 import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.ConsoleFmt.red;
import static engine.Localizable.global;

import java.util.List;

import engine.Koan;
import engine.Localizable;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.runner.KoanEngineAutomatedTest;
import engine.test.runner.KoanEngineIntegrationTest;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.UnitTestExpectation.assertFailure;
import static engine.test.runner.UnitTestExpectation.assertSuccess;
import static engine.Texts.*;

public class ConsoleAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<KoanEngineAutomatedTest> tests = List.of(
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("simpleConsoleOutput()"))
                .useConsole()
                .beforeFirstTest(assertKoanMethodIsInvokable("simpleConsoleOutput"))
                .when(callKoanMethod("simpleConsoleOutput"))
                .then(
                    assertNextStdOutLineEquals(global("hello")),
                    assertNoMoreLineInStdOut()
                ),
            assertSuccess(
                new Line.Localized(format(OK_DISPLAYED_IN_CONSOLE, Formats.Green, "hello", code("simpleConsoleOutput()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("noMethod"))
                .useConsole()
                .beforeFirstTest(assertKoanMethodIsInvokable("noMethod"))
                .when(callKoanMethod("noMethod"))
                .then(
                    assertNextStdOutLineEquals(global("hello"))
                ),
            assertFailure(
                new Line.Localized(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
            )
        )
    );
}
