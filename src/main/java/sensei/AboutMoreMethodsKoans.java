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
                assertResultEquals(2)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(0)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(2)
            ),
        new Koan(CLASS, "min", int.class, int.class)
            .whenCallingWith(2, 3)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(3, 2)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(3, 3)
            .then(
                assertResultEquals(3)
            )
            .whenCallingWith(3, -3)
            .then(
                assertResultEquals(-3)
            ),
        new Koan(CLASS, "remainder", int.class, int.class)
            .whenCallingWith(15, 5)
            .then(
                assertResultEquals(0)
            )
            .whenCallingWith(16, 5)
            .then(
                assertResultEquals(1)
            )
            .whenCallingWith(16, 4)
            .then(
                assertResultEquals(0)
            )
            .whenCallingWith(1, 1)
            .then(
                assertResultEquals(0)
            )
            .whenCallingWith(10, 3)
            .then(
                assertResultEquals(1)
            ),
        new Koan(CLASS, "isEven", int.class)
            .whenCallingWith(2)
            .then(
                assertResultEquals(true)
            )
            .whenCallingWith(1)
            .then(
                assertResultEquals(false)
            )
            .whenCallingWith(33)
            .then(
                assertResultEquals(false)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(true)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(true)
            ),
        new Koan(CLASS, "isMultiple", int.class, int.class)
            .whenCallingWith(2, 2)
            .then(
                assertResultEquals(true)
            )
            .whenCallingWith(12, 4)
            .then(
                assertResultEquals(true)
            )
            .whenCallingWith(0, 4)
            .then(
                assertResultEquals(true)
            )
            .whenCallingWith(12, 5)
            .then(
                assertResultEquals(false)
            ),
        new Koan(CLASS, "fizzBuzz", int.class)
            .useConsole()
            .whenCallingWith(33)
            .then(
                assertOutEquals(0, global("Fizz"))
            )
            .whenCallingWith(40)
            .then(
                assertOutEquals(0, global("Buzz"))
            )
            .whenCallingWith(30)
            .then(
                assertOutEquals(0, global("FizzBuzz"))
            )
            .whenCallingWith(0)
            .then(
                assertOutEquals(0, global("FizzBuzz"))
            )
            .whenCallingWith(2)
            .then(
                assertOutEquals(0, global("2"))
            )
    );
}
