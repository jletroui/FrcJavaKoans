package engine.test;

import static engine.Assertions.assertImplementsInterface;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertObjectMethodIsInvokable;
import static engine.Assertions.assertPrivateField;
import static engine.Assertions.assertPrivateFinalField;
import static engine.Assertions.assertStaticMethodIsInvokable;
import static engine.Assertions.assertCanInstantiateClass;
import static engine.Assertions.assertConstructorIsInvokable;
import static engine.Localizable.global;

import java.util.List;
import java.util.function.IntConsumer;

import engine.Style;
import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;
import static engine.Fmt.code;
import static engine.Fmt.red;
import static engine.Fmt.sameStyle;
import static engine.Fmt.sequence;
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
            red(EXPECTED_CLASS_TO_IMPLEMENT, code("engine.test.simulation.SomeImplementation"), code("java.util.function.IntConsumer"))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_METHOD_TO_BE_STATIC, sameStyle("nonStaticMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"), code("int"))
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
            red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"), sequence(List.of(global("int"), global("double")), Style.Code))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("privateMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE, code("NoClass"), code("engine.test.simulation"))
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
            red(EXPECTED_METHOD_TO_BE_STATIC, sameStyle("nonStaticMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"), code("int"))
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
            red(
                EXPECTED_TO_FIND_MEHOD_MANY_PARAMS,
                sameStyle("noMethod"),
                sameStyle("engine/test/simulation/StudentSolutions"),
                sequence(List.of(global("int"), global("double")), Style.Code)
            )
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("privateMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_METHOD_TO_NOT_BE_STATIC, sameStyle("doNothing"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"), code("int"))
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
            red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS, sameStyle("noMethod"), sameStyle("engine/test/simulation/StudentSolutions"), sequence(List.of(global("int"), global("double")), Style.Code))
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
            red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle("nonStaticPrivateMethod"), sameStyle("engine/test/simulation/StudentSolutions"))
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
            red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE, code("NoClass"), code("engine.test.simulation"))
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
            red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE, code("Unkown"), code("engine.test.simulation"))
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
            red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, code("SomeInterface"))
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
            red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, code("AbstractClass"))
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
            red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, code("int"))
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
            red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, code("String[]"))
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
            red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, code("void"))
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
            red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS, code("NoPublicConstructor"))
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
            red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS, code("ManyParamsConstructor"))
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
            red(EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM, code("ManyParamsConstructor"), code("int"))
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
            red(EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS, code("ManyParamsConstructor"), sequence(List.of(global("int"), global("int")), Style.Code))
        );
    }

    public static void whenAssertPrivateFinalFieldAndItIs() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFinalFieldAndItIs"))
                .beforeFirstTest(
                    assertPrivateFinalField("engine.test.simulation.StudentSolutions", "privateFinalField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertPrivateFinalFieldAndItIsPublic() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFinalFieldAndItIsPublic"))
                .beforeFirstTest(
                    assertPrivateFinalField("engine.test.simulation.StudentSolutions", "publicFinalField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_BE_PRIVATE, code("publicFinalField"), code("engine.test.simulation.StudentSolutions"))
        );
    }

    public static void whenAssertPrivateFinalFieldAndItIsNotFinal() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFinalFieldAndItIsNotFinal"))
                .beforeFirstTest(
                    assertPrivateFinalField("engine.test.simulation.StudentSolutions", "privateField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_BE_FINAL, code("privateField"), code("engine.test.simulation.StudentSolutions"))
        );
    }

    public static void whenAssertPrivateFinalFieldAndItIsNotRightType() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFinalFieldAndItIsNotRightType"))
                .beforeFirstTest(
                    assertPrivateFinalField("engine.test.simulation.StudentSolutions", "privateFinalField", type(boolean.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_BE_OF_TYPE, code("privateFinalField"), code("engine.test.simulation.StudentSolutions"), code("boolean"), code("int"))
        );
    }

    public static void whenAssertPrivateFinalFieldAndItDoesNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFinalFieldAndItDoesNotExist"))
                .beforeFirstTest(
                    assertPrivateFinalField("engine.test.simulation.StudentSolutions", "unknownField", type(boolean.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_FIND_FIELD_IN_CLASS, code("unknownField"), code("engine.test.simulation.StudentSolutions"))
        );
    }

    public static void whenAssertPrivateFieldAndItIs() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFieldAndItIs"))
                .beforeFirstTest(
                    assertPrivateField("engine.test.simulation.StudentSolutions", "privateField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanPass(res[0]);
    }

    public static void whenAssertPrivateFieldAndItIsPublic() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFieldAndItIsPublic"))
                .beforeFirstTest(
                    assertPrivateField("engine.test.simulation.StudentSolutions", "publicField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_BE_PRIVATE, code("publicField"), code("engine.test.simulation.StudentSolutions"))
        );
    }

    public static void whenAssertPrivateFieldAndItIsFinal() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFieldAndItIsFinal"))
                .beforeFirstTest(
                    assertPrivateField("engine.test.simulation.StudentSolutions", "privateFinalField", type(int.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_NOT_BE_FINAL, code("privateFinalField"), code("engine.test.simulation.StudentSolutions"))
        );
    }

    public static void whenAssertPrivateFieldAndItIsNotRightType() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFieldAndItIsNotRightType"))
                .beforeFirstTest(
                    assertPrivateField("engine.test.simulation.StudentSolutions", "privateField", type(boolean.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_FIELD_TO_BE_OF_TYPE, code("privateField"), code("engine.test.simulation.StudentSolutions"), code("boolean"), code("int"))
        );
    }

    public static void whenAssertPrivateFieldAndItDoesNotExist() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertPrivateFieldAndItDoesNotExist"))
                .beforeFirstTest(
                    assertPrivateField("engine.test.simulation.StudentSolutions", "unknownField", type(boolean.class))
                )
                .when(
                    callKoanMethod("doNothing")
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_FIND_FIELD_IN_CLASS, code("unknownField"), code("engine.test.simulation.StudentSolutions"))
        );
    }    
}
