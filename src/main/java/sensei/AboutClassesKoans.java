package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutClasses;
import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutClassesKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutClasses.class)
        .fr(AboutClasses.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "cube", int.class)
            .inClass("utils.MathUtils")
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(8)
            )
            .whenCallingWith(1)
            .then(
                assertReturnValueEquals(1)
            )          
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(0)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(-8)
            ),
        new Koan(CLASS, "displayCube", int.class)
            .useConsole()
            .whenCallingWith(2)
            .then(
                assertNextStdOutLineEquals(global("8")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .whenCallingWith(0)
            .then(
                assertNextStdOutLineEquals(global("0")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(-2)
            .then(
                assertNextStdOutLineEquals(global("-8")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "max", int.class, int.class)
            .inClass("utils.math.OtherMathUtils")
            .whenCallingWith(2, 2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(1, 4)
            .then(
                assertReturnValueEquals(4)
            )          
            .whenCallingWith(4, 1)
            .then(
                assertReturnValueEquals(4)
            ),
        new Koan(CLASS, "displayMax", int.class, int.class)
            .useConsole()
            .whenCallingWith(2, 2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(1, 4)
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            )          
            .whenCallingWith(4, 1)
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "displayMin", int.class, int.class)
            .useConsole()
            .whenCallingWith(2, 2)
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(1, 4)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .whenCallingWith(4, 1)
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
    );
}
