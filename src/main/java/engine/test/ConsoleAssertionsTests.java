package engine.test;

import static engine.Assertions.assertNextStdOutLineEquals;
 import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;

import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanPass;
import static engine.Texts.*;

public class ConsoleAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);

    public static void whenAssertSimpleConsoleOutput() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertSimpleConsoleOutput"))
                .useConsole()
                .when(callKoanMethod("simpleConsoleOutput"))
                .then(
                    assertNextStdOutLineEquals(global("hello")),
                    assertNoMoreLineInStdOut()
                )
        );

        assertKoanPass(res[0], new Line(format(OK_DISPLAYED_IN_CONSOLE, Formats.Green, "hello", code("simpleConsoleOutput()"))));
    }
}
