package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.global;
import static engine.Factories.localClass;

import java.util.List;

import engine.Koan;
import engine.Local;


public class AboutLoopsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutLoops.class)
        .fr(koans.french.AboutLoops.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "helloNTimes", int.class)
            .useConsole()
            .whenCallingWith(2)
            .then(
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(3)
            .then(
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(0)
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "reverseNumbers", int.class)
            .useConsole()
            .whenCallingWith(2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(3)
            .then(
                assertNextStdOutLineEquals(global("3")),
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(0)
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "sevens", int.class)
            .useConsole()
            .whenCallingWith(30)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("21")),
                assertNextStdOutLineEquals(global("28")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(28)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("21")),
                assertNextStdOutLineEquals(global("28")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(6)
            .then(
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(7)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "sevensOrEights", int.class)
            .useConsole()
            .whenCallingWith(20)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("8")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("16")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(16)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("8")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("16")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(6)
            .then(
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(7)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "exponent", int.class, int.class)
            .whenCallingWith(2, 1)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(2, 4)
            .then(
                assertReturnValueEquals(16)
            )
            .whenCallingWith(5, 1)
            .then(
                assertReturnValueEquals(5)
            )
            .whenCallingWith(5, 3)
            .then(
                assertReturnValueEquals(125)
            ),
        new Koan(CLASS, "exponent2", int.class, int.class)
            .whenCallingWith(2, 1)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(2, 4)
            .then(
                assertReturnValueEquals(16)
            )
            .whenCallingWith(5, 1)
            .then(
                assertReturnValueEquals(5)
            )
            .whenCallingWith(5, 3)
            .then(
                assertReturnValueEquals(125)
            )
            .whenCallingWith(5, 0)
            .then(
                assertReturnValueEquals(1)
            ),
        new Koan(CLASS, "factorial", int.class)
            .whenCallingWith(1)
            .then(
                assertReturnValueEquals(1)
            )
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(3)
            .then(
                assertReturnValueEquals(6)
            )
            .whenCallingWith(4)
            .then(
                assertReturnValueEquals(24)
            )
            .whenCallingWith(5)
            .then(
                assertReturnValueEquals(120)
            )
            .whenCallingWith(6)
            .then(
                assertReturnValueEquals(720)
            )
    );
}
