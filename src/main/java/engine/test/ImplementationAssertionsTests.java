package engine.test;

import static engine.Assertions.assertReturnValueImplements;
import static engine.Assertions.assertReturnValueIsAnonymousObject;
import static engine.Assertions.assertReturnValueIsLambda;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;

import java.util.List;
import java.util.function.IntPredicate;
import engine.Koan;
import engine.Localizable;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.runner.KoanEngineAutomatedTest;
import engine.test.runner.KoanEngineIntegrationTest;
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.UnitTestExpectation.assertFailure;
import static engine.test.runner.UnitTestExpectation.assertSuccess;
import static engine.Texts.*;

public class ImplementationAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<KoanEngineAutomatedTest> tests = List.of(
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueImplementsInterface"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueImplements(SomeInterface.class)
                ),
            assertSuccess(
                new Line.Localized(format(OK_RETURNED_OBJECT_IMPLEMENTS, Formats.Green, code("returnedValueImplements()"), code("engine.test.simulation.SomeInterface")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueNullInsteadOfImplementsInterface"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueImplements(SomeInterface.class)
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_RETURNED_NULL, Formats.Red, code("returnedValueNull()"), code("engine.test.simulation.SomeInterface")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueDoesNotImplementInterface"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueImplements(IntPredicate.class)
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_NOT, Formats.Red, code("returnedValueImplements()"), code("java.util.function.IntPredicate"), code("engine.test.simulation.SomeImplementation")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueAnonymous"))
                .when(
                    callKoanMethod("returnedValueAnonymousImplementation")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                ),
            assertSuccess(
                new Line.Localized(format(OK_RETURNED_OBJECT_IS_ANONYMOUS, Formats.Green, code("returnedValueAnonymousImplementation()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedNullInsteadOfAnonymous"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_NULL, Formats.Red, code("returnedValueNull()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueLambdaInsteadOfAnonymous"))
                .when(
                    callKoanMethod("returnedValueLambda")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_LAMBDA, Formats.Red, code("returnedValueLambda()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueNotAnonymous"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueIsAnonymousObject()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED, Formats.Red, code("returnedValueImplements()"), code("engine.test.simulation.SomeImplementation")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueLambda"))
                .when(
                    callKoanMethod("returnedValueLambda")
                )
                .then(
                    assertReturnValueIsLambda()
                ),
            assertSuccess(
                new Line.Localized(format(OK_RETURNED_OBJECT_IS_LAMBDA, Formats.Green, code("returnedValueLambda()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedNullInsteadOfLambda"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueIsLambda()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_NULL, Formats.Red, code("returnedValueNull()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueNotLambda"))
                .when(
                    callKoanMethod("returnedValueAnonymousImplementation")
                )
                .then(
                    assertReturnValueIsLambda()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_ANONYMOUS, Formats.Red, code("returnedValueAnonymousImplementation()")))
            )
        ),
        new KoanEngineIntegrationTest(
            new Koan(CLASS, global("returnedValueNotLambda"))
                .when(
                    callKoanMethod("returnedValueImplements")
                )
                .then(
                    assertReturnValueIsLambda()
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED, Formats.Red, code("returnedValueImplements()"), code("engine.test.simulation.SomeImplementation")))
            )
        )
    );
}
