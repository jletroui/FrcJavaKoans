package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.Localizable.global;
import static engine.script.Expression.callKoanMethod;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutMoreMethodsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutMoreMethods.class)
        .fr(koans.french.AboutMoreMethods.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "abs()")
            .beforeFirstTest(
                assertKoanMethodIsInvokable("abs", int.class)
            )
            .when(callKoanMethod("abs", 2))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("abs", 0))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("abs", -2))
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, "min()")
            .beforeFirstTest(
                assertKoanMethodIsInvokable("min", int.class, int.class)
            )
            .when(callKoanMethod("min", 2, 3))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("min", 3, 2))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("min", 3, 3))
            .then(
                assertReturnValueEquals(3)
            )
            .when(callKoanMethod("min", 3, -3))
            .then(
                assertReturnValueEquals(-3)
            ),
        new Koan(CLASS, "remainder()")
            .beforeFirstTest(
                assertKoanMethodIsInvokable("remainder", int.class, int.class)
            )
            .when(callKoanMethod("remainder", 15, 5))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("remainder", 16, 5))
            .then(
                assertReturnValueEquals(1)
            )
            .when(callKoanMethod("remainder", 16, 4))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("remainder", 1, 1))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("remainder", 10, 3))
            .then(
                assertReturnValueEquals(1)
            ),
        new Koan(CLASS, "isEven()")
            .beforeFirstTest(
                assertKoanMethodIsInvokable("isEven", int.class)
            )
            .when(callKoanMethod("isEven", 2))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isEven", 1))
            .then(
                assertReturnValueEquals(false)
            )
            .when(callKoanMethod("isEven", 33))
            .then(
                assertReturnValueEquals(false)
            )
            .when(callKoanMethod("isEven", 0))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isEven", -2))
            .then(
                assertReturnValueEquals(true)
            ),
        new Koan(CLASS, "isMultiple()")
            .beforeFirstTest(
                assertKoanMethodIsInvokable("isMultiple", int.class, int.class)
            )
            .when(callKoanMethod("isMultiple", 2, 2))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isMultiple", 12, 4))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isMultiple", 0, 4))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isMultiple", 12, 5))
            .then(
                assertReturnValueEquals(false)
            ),
        new Koan(CLASS, "fizzBuzz()")
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("fizzBuzz", int.class)
            )
            .when(callKoanMethod("fizzBuzz", 33))
            .then(
                assertNextStdOutLineEquals(global("Fizz")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("fizzBuzz", 40))
            .then(
                assertNextStdOutLineEquals(global("Buzz")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("fizzBuzz", 30))
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("fizzBuzz", 0))
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("fizzBuzz", 2))
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
    );
}
