package engine.test;

import static engine.Assertions.assertImplementsInterface;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.ConsoleFmt.red;
import static engine.Localizable.global;

import java.util.function.IntConsumer;

import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;
import static engine.Texts.*;

public class ReflectionAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static void whenAssertClassImplementsInterfaceAndItDoes() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertClassImplementsInterfaceAndItDoes"))
                .beforeFirstTest(
                    assertImplementsInterface("engine.test.simulation.SomeImplementation", SomeInterface.class)
                )
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertClassImplementsInterfaceAndItDoesNot() {
        var res = TestSensei.execute(
             new Koan(CLASS, global("whenAssertClassImplementsInterfaceAndItDoesNot"))
                .beforeFirstTest(
                    assertImplementsInterface("engine.test.simulation.SomeImplementation", IntConsumer.class)
                )
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
        );

        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_CLASS_TO_IMPLEMENT, Formats.Red, code("engine.test.simulation.SomeImplementation"), code("java.util.function.IntConsumer")))
        );
    }

    public static void whenAssertKoanMethodIsInvokableAndMethodDoesExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodIsInvokableAndMethodDoesExist"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("simpleConsoleOutput")
                )
                .when(
                    callKoanMethod("simpleConsoleOutput")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertKoanMethodIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("noMethod")
                )
                .when(
                    callKoanMethod("noMethod")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }
}
