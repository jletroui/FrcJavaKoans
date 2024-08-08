package engine.test;

import static engine.Assertions.assertReturnValueImplements;
import static engine.Assertions.assertReturnValueIsAnonymousObject;
import static engine.Assertions.assertReturnValueIsLambda;
import static engine.Fmt.code;
import static engine.Fmt.green;
import static engine.Fmt.red;
import static engine.Localizable.global;

import java.util.function.IntPredicate;
import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;
import static engine.Texts.*;

public class ImplementationAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static void whenAssertReturnedValueImplementsInterfaceAndReturnedValueDoesImplementIt() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnedValueImplementsInterfaceAndReturnedValueDoesImplementIt"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueImplements(SomeInterface.class)
                )
        );

        assertKoanPass(
            res[0],
            green(OK_RETURNED_OBJECT_IMPLEMENTS, code("returnedValueImplements()"), code("engine.test.simulation.SomeInterface"))
        );
    }

    public static void whenAssertReturnedValueImplementsInterfaceAndReturnedValueIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnedValueImplementsInterfaceAndReturnedValueIsNull"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueImplements(SomeInterface.class)
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_RETURNED_NULL, code("returnedValueNull()"), code("engine.test.simulation.SomeInterface"))
        );
    }

    public static void whenAssertReturnedValueImplementsInterfaceAndReturnedValueDoesNotImplementIt() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnedValueImplementsInterfaceAndReturnedValueDoesNotImplementIt"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueImplements(IntPredicate.class)
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_NOT, code("returnedValueImplements()"), code("java.util.function.IntPredicate"), code("engine.test.simulation.SomeImplementation"))
        );
    }

    public static void whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsOne() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsOne"))
                .when(
                    callKoanMethod("returnedValueAnonymousImplementation")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                )
        );

        assertKoanPass(
            res[0],
            green(OK_RETURNED_OBJECT_IS_ANONYMOUS, code("returnedValueAnonymousImplementation()"))
        );
    }

    public static void whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsNull() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsNull"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_NULL, code("returnedValueNull()"))
        );
    }

    public static void whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsALambda() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsALambda"))
                .when(
                    callKoanMethod("returnedValueLambda")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_LAMBDA, code("returnedValueLambda()"))
        );
    }

    public static void whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsRegularClass() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsAnonymousClassAndReturnedValueIsRegularClass"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED, code("returnedValueImplements()"), code("engine.test.simulation.SomeImplementation"))
        );
    }

    public static void whenAssertReturnedValueIsLambdaAndReturnedValueIsOne() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnedValueIsLambdaAndReturnedValueIsOne"))
                .when(
                    callKoanMethod("returnedValueLambda")
                )
                .then(
                    assertReturnValueIsLambda()
                )
        );

        assertKoanPass(
            res[0],
            green(OK_RETURNED_OBJECT_IS_LAMBDA, code("returnedValueLambda()"))
        );
    }

    public static void whenAssertReturnedValueIsLambdaAndReturnedValueIsNull() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsLambdaAndReturnedValueIsNull"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueIsLambda()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_NULL, code("returnedValueNull()"))
        );
    }

    public static void whenAssertReturnedValueIsLambdaAndReturnedValueIsAnonymousClass() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsLambdaAndReturnedValueIsAnonymousClass"))
                .when(
                    callKoanMethod("returnedValueAnonymousImplementation")
                )
                .then(
                    assertReturnValueIsLambda()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_ANONYMOUS, code("returnedValueAnonymousImplementation()"))
        );
    }

    public static void whenAssertReturnedValueIsLambdaAndReturnedValueIsRegularClass() {
        var res = TestSensei.execute(
           new Koan(CLASS, global("whenAssertReturnedValueIsLambdaAndReturnedValueIsRegularClass"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueIsLambda()
                )
        );

        assertKoanFails(
            res[0],
            red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED, code("returnedValueImplements()"), code("engine.test.simulation.SomeImplementation"))
        );
    }
}
