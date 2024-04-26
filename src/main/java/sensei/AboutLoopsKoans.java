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
        .fr(koans.french.AboutLoops.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "helloNTimes", int.class)
            .useConsole()
            .whenCalledWith(2)
            .then(
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(3)
            .then(
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNextStdOutLineEquals(global("Hello")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(0)
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "displayNumbers", int.class)
            .useConsole()
            .whenCalledWith(2)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(3)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("3")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(0)
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "displayReverseNumbers", int.class)
            .useConsole()
            .whenCalledWith(2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(3)
            .then(
                assertNextStdOutLineEquals(global("3")),
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(0)
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "sevens", int.class)
            .useConsole()
            .whenCalledWith(30)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("21")),
                assertNextStdOutLineEquals(global("28")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(28)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("21")),
                assertNextStdOutLineEquals(global("28")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(6)
            .then(
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(7)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "sevensOrEights", int.class)
            .useConsole()
            .whenCalledWith(20)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("8")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("16")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(16)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNextStdOutLineEquals(global("8")),
                assertNextStdOutLineEquals(global("14")),
                assertNextStdOutLineEquals(global("16")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(6)
            .then(
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(7)
            .then(
                assertNextStdOutLineEquals(global("7")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "exponent", int.class, int.class)
            .whenCalledWith(2, 1)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(2, 4)
            .then(
                assertReturnValueEquals(16)
            )
            .whenCalledWith(5, 1)
            .then(
                assertReturnValueEquals(5)
            )
            .whenCalledWith(5, 3)
            .then(
                assertReturnValueEquals(125)
            ),
        new Koan(CLASS, "exponent2", int.class, int.class)
            .whenCalledWith(2, 1)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(2, 4)
            .then(
                assertReturnValueEquals(16)
            )
            .whenCalledWith(5, 1)
            .then(
                assertReturnValueEquals(5)
            )
            .whenCalledWith(5, 3)
            .then(
                assertReturnValueEquals(125)
            )
            .whenCalledWith(5, 0)
            .then(
                assertReturnValueEquals(1)
            ),
        new Koan(CLASS, "factorial", int.class)
            .whenCalledWith(1)
            .then(
                assertReturnValueEquals(1)
            )
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(3)
            .then(
                assertReturnValueEquals(6)
            )
            .whenCalledWith(4)
            .then(
                assertReturnValueEquals(24)
            )
            .whenCalledWith(5)
            .then(
                assertReturnValueEquals(120)
            )
            .whenCalledWith(6)
            .then(
                assertReturnValueEquals(720)
            )
    );
}
