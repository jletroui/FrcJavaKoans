package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.Localizable.global;
import static engine.script.Expression.callKoanMethod;
import static sensei.Texts.*;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutMoreMethodsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutMoreMethods.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, COMPUTING_THE_ABSOLUTE_VALUE_OF_A_NUMBER)
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
        new Koan(CLASS, WHAT_S_THE_MIN_VALUE)
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
        new Koan(CLASS, COMPUTING_THE_REMAINDER_OF_AN_INTEGER_DIVISION)
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
        new Koan(CLASS, COMPUTING_WHETHER_A_NUMBER_IS_EVEN_OR_NOT)
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
        new Koan(CLASS, COMPUTING_WHETHER_A_NUMBER_IS_A_MULTIPLE_OF_ANOTHER)
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
        new Koan(CLASS, FIZZ_BUZZ)
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
