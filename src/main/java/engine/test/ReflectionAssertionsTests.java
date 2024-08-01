package engine.test;

import static engine.Assertions.assertImplementsInterface;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertObjectMethodIsInvokable;
import static engine.Assertions.assertStaticMethodIsInvokable;
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
                    callKoanMethod("doNothing")
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
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_CLASS_TO_IMPLEMENT, Formats.Red, code("engine.test.simulation.SomeImplementation"), code("java.util.function.IntConsumer")))
        );
    }

    public static void whenAssertStaticMethodIsInvokableAndMethodDoesExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodIsInvokableAndMethodDoesExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "simpleConsoleOutput")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertStaticMethodIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertStaticMethodIsInvokableAndMethodIsNotStatic() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodIsInvokableAndMethodIsNotStatic"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "nonStaticMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_METHOD_TO_BE_STATIC), "nonStaticMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertStaticMethodNoParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodNoParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertStaticMethodOneParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod", int.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM), "noMethod", "engine/test/simulation/StudentSolutions", "int")
        );
    }

    public static void whenAssertStaticMethodMultipleParamsIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod", int.class, double.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions", "'int', and 'double'")
        );
    }

    public static void whenAssertStaticMethodIsPublicAndItIsNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodIsPublicAndItIsNot"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "privateMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "privateMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertStaticMethodIsInvokableAndClassDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertStaticMethodIsInvokableAndClassDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.NoClass", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), "NoClass", "engine.test.simulation")
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
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertKoanMethodIsInvokableAndMethodIsNotStatic() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodIsInvokableAndMethodIsNotStatic"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("nonStaticMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_METHOD_TO_BE_STATIC), "nonStaticMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertKoanMethodNoParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodNoParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertKoanMethodOneParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertStaticMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod", int.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM), "noMethod", "engine/test/simulation/StudentSolutions", "int")
        );
    }

    public static void whenAssertKoanMethodMultipleParamsIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("noMethod", int.class, double.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions", "'int', and 'double'")
        );
    }

    public static void whenAssertKoanMethodIsPublicAndItIsNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertKoanMethodIsPublicAndItIsNot"))
                .useConsole()
                .beforeFirstTest(
                    assertKoanMethodIsInvokable("privateMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "privateMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertObjectMethodIsInvokableAndMethodDoesExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodIsInvokableAndMethodDoesExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "nonStaticMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertObjectMethodIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertObjectMethodIsInvokableAndMethodIsStatic() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodIsInvokableAndMethodIsNotStatic"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "doNothing")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_METHOD_TO_NOT_BE_STATIC), "doNothing", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertObjectMethodNoParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodNoParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertObjectMethodOneParamIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod", int.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM), "noMethod", "engine/test/simulation/StudentSolutions", "int")
        );
    }

    public static void whenAssertObjectMethodMultipleParamsIsInvokableAndMethodDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodOneParamIsInvokableAndMethodDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "noMethod", int.class, double.class)
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS), "noMethod", "engine/test/simulation/StudentSolutions", "'int', and 'double'")
        );
    }

    public static void whenAssertObjectMethodIsInvokableAndItIsNotPublic() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodIsInvokableAndItIsNotPublic"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.StudentSolutions", "nonStaticPrivateMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS), "nonStaticPrivateMethod", "engine/test/simulation/StudentSolutions")
        );
    }

    public static void whenAssertObjectMethodIsInvokableAndClassDoesNotExist() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertObjectMethodIsInvokableAndClassDoesNotExist"))
                .useConsole()
                .beforeFirstTest(
                    assertObjectMethodIsInvokable("engine.test.simulation.NoClass", "noMethod")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), "NoClass", "engine.test.simulation")
        );
    }

}
