package engine.test;

import static engine.Assertions.assertImplementsInterface;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertObjectMethodIsInvokable;
import static engine.Assertions.assertStaticMethodIsInvokable;
import static engine.Assertions.assertCanInstantiateClass;
import static engine.Assertions.assertConstructorIsInvokable;
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
import static engine.script.Type.type;
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

    public static void whenAssertClassIsInstantiableAndItIs() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIs"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type("engine.test.simulation.StudentSolutions")))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertClassIsInstantiableAndItDoesNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItDoesNotExist"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type("engine.test.simulation.Unkown")))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), "Unkown", "engine.test.simulation")
        );
    }

    public static void whenAssertClassIsInstantiableAndItIsInterface() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIsInterface"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type("engine.test.simulation.SomeInterface")))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), "SomeInterface")
        );
    } 

    public static void whenAssertClassIsInstantiableAndItIsAbstract() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIsAbstract"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type("engine.test.simulation.AbstractClass")))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), "AbstractClass")
        );
    }       

    public static void whenAssertClassIsInstantiableAndItIsPrimitive() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIsPrimitive"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type(int.class)))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), "int")
        );
    }

    public static void whenAssertClassIsInstantiableAndItIsArray() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIsArray"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type(String[].class)))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), "String[]")
        );
    }

    public static void whenAssertClassIsInstantiableAndItIsVoid() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertClassIsInstantiableAndItIsVoid"))
                .beforeFirstTest(
                    assertCanInstantiateClass(global(type(void.class)))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), "void")
        );
    }

    public static void whenAssertConstructorIsInvokableAndItIs() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertConstructorIsInvokableAndItIs"))
                .beforeFirstTest(
                    assertConstructorIsInvokable("engine.test.simulation.StudentSolutions")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertConstructorIsInvokableAndItIsPrivate() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertConstructorIsInvokableAndItIsPrivate"))
                .beforeFirstTest(
                    assertConstructorIsInvokable("engine.test.simulation.NoPublicConstructor")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS), "NoPublicConstructor")
        );
    }

    public static void whenAssertConstructorNoParamsIsInvokableAndItNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertConstructorNoParamsIsInvokableAndItNotExist"))
                .beforeFirstTest(
                    assertConstructorIsInvokable("engine.test.simulation.ManyParamsConstructor")
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS), "ManyParamsConstructor")
        );
    }

    public static void whenAssertConstructorOneParamIsInvokableAndItNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertConstructorOneParamIsInvokableAndItNotExist"))
                .beforeFirstTest(
                    assertConstructorIsInvokable("engine.test.simulation.ManyParamsConstructor", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM), "ManyParamsConstructor", "int")
        );
    }

    public static void whenAssertConstructorManyParamsIsInvokableAndItNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertConstructorManyParamsIsInvokableAndItNotExist"))
                .beforeFirstTest(
                    assertConstructorIsInvokable("engine.test.simulation.ManyParamsConstructor", type(int.class), type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            new Line(red(EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS), "ManyParamsConstructor", "'int', and 'int'")
        );
    }
}
