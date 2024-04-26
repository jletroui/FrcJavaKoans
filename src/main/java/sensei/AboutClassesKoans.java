package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.global;
import static engine.Factories.localClass;

import java.util.List;

import engine.Koan;
import engine.Local;

public class AboutClassesKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutClasses.class)
        .fr(koans.french.AboutClasses.class);

    public static final List<Koan> koans = List.of(
        new Koan("utils.MathUtils", "cube", int.class)
            .usingConstructor()
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(8)
            )
            .whenCalledWith(1)
            .then(
                assertReturnValueEquals(1)
            )          
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCalledWith(-2)
            .then(
                assertReturnValueEquals(-8)
            ),
        new Koan(CLASS, "displayCube", int.class)
            .useConsole()
            .whenCalledWith(2)
            .then(
                assertNextStdOutLineEquals(global("8")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .whenCalledWith(0)
            .then(
                assertNextStdOutLineEquals(global("0")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(-2)
            .then(
                assertNextStdOutLineEquals(global("-8")),
                assertNoMoreLineInStdOut()
            ),
        new Koan("utils.math.OtherMathUtils", "max", int.class, int.class)
            .usingConstructor()
            .whenCalledWith(2, 2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCalledWith(1, 4)
            .then(
                assertReturnValueEquals(4)
            )          
            .whenCalledWith(4, 1)
            .then(
                assertReturnValueEquals(4)
            ),
        new Koan(CLASS, "displayMax", int.class, int.class)
            .useConsole()
            .whenCalledWith(2, 2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(1, 4)
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            )          
            .whenCalledWith(4, 1)
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "displayMin", int.class, int.class)
            .useConsole()
            .whenCalledWith(2, 2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .whenCalledWith(1, 4)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .whenCalledWith(4, 1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
    );
}
