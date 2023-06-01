package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutMoreMethods;

import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutMoreMethodsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutMoreMethods.class)
        .fr(AboutMoreMethods.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "abs", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, "min", int.class, int.class)
            .whenCallingWith(2, 3)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(3, 2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(3, 3)
            .then(
                assertReturnValueEquals(3)
            )
            .whenCallingWith(3, -3)
            .then(
                assertReturnValueEquals(-3)
            ),
        new Koan(CLASS, "remainder", int.class, int.class)
            .whenCallingWith(15, 5)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCallingWith(16, 5)
            .then(
                assertReturnValueEquals(1)
            )
            .whenCallingWith(16, 4)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCallingWith(1, 1)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCallingWith(10, 3)
            .then(
                assertReturnValueEquals(1)
            ),
        new Koan(CLASS, "isEven", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCallingWith(1)
            .then(
                assertReturnValueEquals(false)
            )
            .whenCallingWith(33)
            .then(
                assertReturnValueEquals(false)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(true)
            ),
        new Koan(CLASS, "isMultiple", int.class, int.class)
            .whenCallingWith(2, 2)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCallingWith(12, 4)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCallingWith(0, 4)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCallingWith(12, 5)
            .then(
                assertReturnValueEquals(false)
            ),
        new Koan(CLASS, "fizzBuzz", int.class)
            .useConsole()
            .whenCallingWith(33)
            .then(
                assertNextStdOutLineEquals(global("Fizz")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(40)
            .then(
                assertNextStdOutLineEquals(global("Buzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(30)
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(0)
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
    );
}
