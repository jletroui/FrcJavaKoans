package engine.test;

import static engine.Assertions.assertAskedInStdIn;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNextStdOutLineIsEmpty;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Fmt.code;
import static engine.Fmt.green;
import static engine.Fmt.red;
import static engine.Fmt.sameStyle;
import static engine.Localizable.global;

import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;
import static engine.Texts.*;

public class ConsoleAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);

    public static void whenAssertNextStdOutLineAndItIsThere() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineAndItIsThere"))
                .useConsole()
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
                .then(
                    assertNextStdOutLineEquals(global("hello"))
                )
        );

        assertKoanPass(res[0], green(OK_DISPLAYED_IN_CONSOLE, sameStyle("hello"), code("simpleConsoleOutput()")));
    }

    public static void whenAssertNextStdOutLineAndItIsDifferent() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineAndItIsDifferent"))
                .useConsole()
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
                .then(
                    assertNextStdOutLineEquals(global("bye"))
                )
        );

        assertKoanFails(res[0], red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD, sameStyle("bye"), code("simpleConsoleOutput()"), sameStyle("hello")));
    }

    public static void whenAssertNextStdOutLineAndThereIsNothing() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineAndThereIsNothing"))
                .useConsole()
                .when(
                    callKoanMethod("doNothing")
                )
                .then(
                    assertNextStdOutLineEquals(global("hello"))
                )
        );

        assertKoanFails(res[0], red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING, sameStyle("hello"), code("doNothing()")));
    }

    public static void whenAssertNoMoreLineInStdOutAndThereIsNone() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNoMoreLineInStdOutAndThereIsNone"))
                .useConsole()
                .when(
                    callKoanMethod("doNothing")
                )
                .then(
                    assertNoMoreLineInStdOut()
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertNoMoreLineInStdOutAndThereIsOne() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNoMoreLineInStdOutAndThereIsOne"))
                .useConsole()
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
                .then(
                    assertNoMoreLineInStdOut()
                )
        );

        assertKoanFails(res[0], red(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD, code("simpleConsoleOutput()"), sameStyle("hello")));
    }

    public static void whenAssertNextStdOutLineIsEmptyAndItIs() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineAndItIsDifferent"))
                .useConsole()
                .when(
                    callKoanMethod("emptyConsoleOutput")
                )
                .then(
                    assertNextStdOutLineIsEmpty()
                )
        );

        assertKoanPass(res[0], green(OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE));
    }

    public static void whenAssertNextStdOutLineIsEmptyAndItIsNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineIsEmptyAndItIsNot"))
                .useConsole()
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
                .then(
                    assertNextStdOutLineIsEmpty()
                )
        );

        assertKoanFails(res[0], red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_INSTEAD, sameStyle("hello")));
    }    

    public static void whenAssertNextStdOutLineIsEmptyAndThereIsNone() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertNextStdOutLineIsEmptyAndThereIsNone"))
                .useConsole()
                .when(
                    callKoanMethod("doNothing")
                )
                .then(
                    assertNextStdOutLineIsEmpty()
                )
        );

        assertKoanFails(res[0], red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING));
    }    

    public static void whenAssertAskedInStdInAndAsked() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertAskedInStdInAndAsked"))
                .useConsoleAndShowStdinInputs()
                .when(
                    callKoanMethod("readFromConsole")
                )
                .withStdInInputs("abc")
                .then(
                    assertAskedInStdIn()
                )
        );

        assertKoanPass(res[0], green(OK_ASKED_FOR_LINE_IN_CONSOLE));
    }    

    public static void whenAssertAskedInStdInAndDidNotAsked() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertAskedInStdInAndAsked"))
                .useConsoleAndShowStdinInputs()
                .when(
                    callKoanMethod("doNothing")
                )
                .then(
                    assertAskedInStdIn()
                )
        );

        assertKoanFails(res[0], red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
    }    
}
