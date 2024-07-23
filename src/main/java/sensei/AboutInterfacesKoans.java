package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueImplements;
import static engine.Assertions.assertReturnValueIsAnonymousObject;
import static engine.Assertions.assertReturnValueIsLambda;
import static engine.Assertions.assertConstructorIsInvokable;
import static engine.Assertions.assertImplementsInterface;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static engine.script.Expression.newObject;
import static sensei.Texts.*;

import java.util.List;
import java.util.function.IntPredicate;

import bonuses.teachingmaterial.Combining;
import engine.Koan;
import engine.Localizable;


public class AboutInterfacesKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(bonuses.english.AboutInterfaces.class)
        .fr(bonuses.french.AboutInterfaces.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, FIRST_INTERFACE_IMPLEMENTATIONS)
            .beforeFirstTest(
                assertConstructorIsInvokable("numbers.AddNumbers"),
                assertConstructorIsInvokable("numbers.MultiplyNumbers"),
                assertImplementsInterface("numbers.AddNumbers", Combining.class),
                assertImplementsInterface("numbers.MultiplyNumbers", Combining.class)
            )
            .when(
                newObject("numbers.AddNumbers").call("combine", 5, 7)
            )
            .then(
                assertReturnValueEquals(12)
            )
            .when(
                newObject("numbers.AddNumbers").call("combine", 0, -1)
            )
            .then(
                assertReturnValueEquals(-1)
            )
            .when(
                newObject("numbers.MultiplyNumbers").call("combine", 5, 7)
            )
            .then(
                assertReturnValueEquals(35)
            )
            .when(
                newObject("numbers.MultiplyNumbers").call("combine", 0, -1)
            )
            .then(
                assertReturnValueEquals(0)
            ),
        new Koan(CLASS, ANONYMOUS_INTERFACE_IMPLEMENTATION)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("getAnonymousCombining")
            )
            .when(
                callKoanMethod("getAnonymousCombining")
            )
            .then(
                assertReturnValueIsAnonymousObject(),
                assertReturnValueImplements(Combining.class)
            )
            .when(
                callKoanMethod("getAnonymousCombining").call("combine", 7, 5)
            )
            .then(
                assertReturnValueEquals(2)
            )
            .when(
                callKoanMethod("getAnonymousCombining").call("combine", 5, 7)
            )
            .then(
                assertReturnValueEquals(-2)
            ),
        new Koan(CLASS, LAMBDA_METHODS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("getLambdaCombining")
            )
            .when(
                callKoanMethod("getLambdaCombining")
            )
            .then(
                assertReturnValueIsLambda(),
                assertReturnValueImplements(Combining.class)
            )
            .when(
                callKoanMethod("getLambdaCombining").call("combine", 7, 5)
            )
            .then(
                assertReturnValueEquals(-2)
            )
            .when(
                callKoanMethod("getLambdaCombining").call("combine", 5, 7)
            )
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, COMMON_LAMBDA_INTERFACES)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("getIsEven")
            )
            .when(
                callKoanMethod("getIsEven")
            )
            .then(
                assertReturnValueIsLambda(),
                assertReturnValueImplements(IntPredicate.class)
            )
            .when(
                callKoanMethod("getIsEven").call("test", 8)
            )
            .then(
                assertReturnValueEquals(true)
            )
            .when(
                callKoanMethod("getIsEven").call("test", 3)
            )
            .then(
                assertReturnValueEquals(false)
            )
    );
}
