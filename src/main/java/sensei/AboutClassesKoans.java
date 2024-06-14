package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertStaticMethodIsInvokable;
import static engine.Localizable.global;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static engine.script.Expression.callStaticMethod;
import static sensei.Texts.*;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutClassesKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutClasses.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, CLASSES_AND_PACKAGES)
            .beforeFirstTest(
                assertStaticMethodIsInvokable("utils.MathUtils", "cube", int.class)
            )
            .when(callStaticMethod("utils.MathUtils", "cube", 2))
            .then(
                assertReturnValueEquals(8)
            )
            .when(callStaticMethod("utils.MathUtils", "cube", 1))
            .then(
                assertReturnValueEquals(1)
            )          
            .when(callStaticMethod("utils.MathUtils", "cube", 0))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callStaticMethod("utils.MathUtils", "cube", -2))
            .then(
                assertReturnValueEquals(-8)
            ),
        new Koan(CLASS, USING_A_DIFFERENT_CLASS)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("displayCube", int.class)
            )
            .when(callKoanMethod("displayCube", 2))
            .then(
                assertNextStdOutLineEquals(global("8")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayCube", 1))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .when(callKoanMethod("displayCube", 0))
            .then(
                assertNextStdOutLineEquals(global("0")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayCube", -2))
            .then(
                assertNextStdOutLineEquals(global("-8")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, AN_OTHER_CLASS_IN_A_NESTED_PACKAGE)
            .beforeFirstTest(
                assertStaticMethodIsInvokable("utils.OtherMathUtils", "max", int.class, int.class)
            )
            .when(callStaticMethod("utils.OtherMathUtils", "max", 2, 2))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callStaticMethod("utils.OtherMathUtils", "max", 1, 4))
            .then(
                assertReturnValueEquals(4)
            )          
            .when(callStaticMethod("utils.OtherMathUtils", "max", 4, 1))
            .then(
                assertReturnValueEquals(4)
            ),
        new Koan(CLASS, USING_A_CLASS_IN_A_NESTED_PACKAGE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("displayMax", int.class, int.class)
            )
            .when(callKoanMethod("displayMax", 2, 2))
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayMax", 1, 4))
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            )          
            .when(callKoanMethod("displayMax", 4, 1))
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, USING_A_CLASS_FROM_A_PREVIOUS_KOAN)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("displayMin", int.class, int.class)
            )
            .when(callKoanMethod("displayMin", 2, 2))
            .then(
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayMin", 1, 4))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )          
            .when(callKoanMethod("displayMin", 4, 1))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
    );
}
