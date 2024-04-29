package engine.test;

import static engine.Assertions.assertNextStdOutLineEquals;
 import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Localizable.global;

import java.util.List;

import engine.Color;
import engine.Koan;
import engine.Localizable;
import engine.test.simulation.StudentSolutions;
import static engine.script.Expression.callKoanMethod;
import static engine.Texts.*;
import static engine.test.UnitTestExpectation.assertSuccess;
import static engine.test.UnitTestExpectation.assertFailure;

public class ConsoleUnitTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<UnitTest> items = List.of(
        new UnitTest(
            new Koan(CLASS, "simpleConsoleOutput()")
                .useConsole()
                .when(callKoanMethod("simpleConsoleOutput"))
                .then(
                    assertNextStdOutLineEquals(global("hello")),
                    assertNoMoreLineInStdOut()
                ),
            assertSuccess(
                new Line.Localized(Color.green(OK_DISPLAYED_IN_CONSOLE), "hello", "")
            )
        ),
        new UnitTest(
            new Koan(CLASS, "noMethod")
                .useConsole()
                .when(callKoanMethod("noMethod"))
                .then(
                    assertNextStdOutLineEquals(global("hello"))
                ),
            assertFailure(
                new Line.Localized(Color.red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
            )
        )
    );
}
