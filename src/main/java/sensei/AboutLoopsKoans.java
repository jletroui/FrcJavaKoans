package sensei;

import java.util.List;

import engine.Koan;
import engine.Local;
import koans.english.AboutLoops;

import static engine.Assertions.*;
import static engine.Localizable.*;


public class AboutLoopsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutLoops.class)
        .fr(AboutLoops.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "helloNTimes", int.class)
            .useConsole()
            .whenCallingWith(2)
            .then(
                assertOutEquals(0, global("Hello")),
                assertOutEquals(1, global("Hello")),
                assertOutNoLineAfter(2)
            )
            .whenCallingWith(3)
            .then(
                assertOutEquals(0, global("Hello")),
                assertOutEquals(1, global("Hello")),
                assertOutEquals(2, global("Hello")),
                assertOutNoLineAfter(3)
            )
            .whenCallingWith(0)
            .then(
                assertOutNoLineAfter(0)
            ),
        new Koan(CLASS, "reverseNumbers", int.class)
            .useConsole()
            .whenCallingWith(2)
            .then(
                assertOutEquals(0, global("2")),
                assertOutEquals(1, global("1")),
                assertOutNoLineAfter(2)
            )
            .whenCallingWith(3)
            .then(
                assertOutEquals(0, global("3")),
                assertOutEquals(1, global("2")),
                assertOutEquals(2, global("1")),
                assertOutNoLineAfter(3)
            )
            .whenCallingWith(1)
            .then(
                assertOutEquals(0, global("1")),
                assertOutNoLineAfter(1)
            )
            .whenCallingWith(0)
            .then(
                assertOutNoLineAfter(0)
            ),
        new Koan(CLASS, "sevens", int.class)
            .useConsole()
            .whenCallingWith(30)
            .then(
                assertOutEquals(0, global("7")),
                assertOutEquals(1, global("14")),
                assertOutEquals(2, global("21")),
                assertOutEquals(3, global("28")),
                assertOutNoLineAfter(4)
            )
            .whenCallingWith(28)
            .then(
                assertOutEquals(0, global("7")),
                assertOutEquals(1, global("14")),
                assertOutEquals(2, global("21")),
                assertOutEquals(3, global("28")),
                assertOutNoLineAfter(4)
            )
            .whenCallingWith(6)
            .then(
                assertOutNoLineAfter(0)
            )
            .whenCallingWith(7)
            .then(
                assertOutEquals(0, global("7")),
                assertOutNoLineAfter(1)
            ),
        new Koan(CLASS, "sevensOrEights", int.class)
            .useConsole()
            .whenCallingWith(20)
            .then(
                assertOutEquals(0, global("7")),
                assertOutEquals(1, global("8")),
                assertOutEquals(2, global("14")),
                assertOutEquals(3, global("16")),
                assertOutNoLineAfter(4)
            )
            .whenCallingWith(16)
            .then(
                assertOutEquals(0, global("7")),
                assertOutEquals(1, global("8")),
                assertOutEquals(2, global("14")),
                assertOutEquals(3, global("16")),
                assertOutNoLineAfter(4)
            )
            .whenCallingWith(6)
            .then(
                assertOutNoLineAfter(0)
            )
            .whenCallingWith(7)
            .then(
                assertOutEquals(0, global("7")),
                assertOutNoLineAfter(1)
            ),
        new Koan(CLASS, "exponent", int.class, int.class)
            .whenCallingWith(2, 1)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(2, 4)
            .then(
                assertResultEquals(16)
            )
            .whenCallingWith(5, 1)
            .then(
                assertResultEquals(5)
            )
            .whenCallingWith(5, 3)
            .then(
                assertResultEquals(125)
            ),
        new Koan(CLASS, "exponent2", int.class, int.class)
            .whenCallingWith(2, 1)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(2, 4)
            .then(
                assertResultEquals(16)
            )
            .whenCallingWith(5, 1)
            .then(
                assertResultEquals(5)
            )
            .whenCallingWith(5, 3)
            .then(
                assertResultEquals(125)
            )
            .whenCallingWith(5, 0)
            .then(
                assertResultEquals(1)
            ),
        new Koan(CLASS, "factorial", int.class)
            .whenCallingWith(1)
            .then(
                assertResultEquals(1)
            )
            .whenCallingWith(2)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(3)
            .then(
                assertResultEquals(6)
            )
            .whenCallingWith(4)
            .then(
                assertResultEquals(24)
            )
            .whenCallingWith(5)
            .then(
                assertResultEquals(120)
            )
            .whenCallingWith(6)
            .then(
                assertResultEquals(720)
            )
    );
}
