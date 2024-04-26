package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.global;
import static engine.Factories.localClass;

import java.util.List;

import engine.Koan;
import engine.Local;

public class AboutMoreMethodsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutMoreMethods.class)
        .fr(koans.french.AboutMoreMethods.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "abs", int.class)
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCalledWith(-2)
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, "min", int.class, int.class)
            .whenCalledWith(2, 3)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(3, 2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(3, 3)
            .then(
                assertReturnValueEquals(3)
            )
            .whenCalledWith(3, -3)
            .then(
                assertReturnValueEquals(-3)
            ),
        new Koan(CLASS, "remainder", int.class, int.class)
            .whenCalledWith(15, 5)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCalledWith(16, 5)
            .then(
                assertReturnValueEquals(1)
            )
            .whenCalledWith(16, 4)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCalledWith(1, 1)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCalledWith(10, 3)
            .then(
                assertReturnValueEquals(1)
            ),
        new Koan(CLASS, "isEven", int.class)
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCalledWith(1)
            .then(
                assertReturnValueEquals(false)
            )
            .whenCalledWith(33)
            .then(
                assertReturnValueEquals(false)
            )
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCalledWith(-2)
            .then(
                assertReturnValueEquals(true)
            ),
        new Koan(CLASS, "isMultiple", int.class, int.class)
            .whenCalledWith(2, 2)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCalledWith(12, 4)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCalledWith(0, 4)
            .then(
                assertReturnValueEquals(true)
            )
            .whenCalledWith(12, 5)
            .then(
                assertReturnValueEquals(false)
            ),
        new Koan(CLASS, "fizzBuzz", int.class)
            .useConsole()
            .whenCalledWith(33)
            .then(
                assertNextStdOutLineEquals(global("Fizz")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(40)
            .then(
                assertNextStdOutLineEquals(global("Buzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(30)
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(0)
            .then(
                assertNextStdOutLineEquals(global("FizzBuzz")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
    );
}
