package sensei;

import static engine.Assertions.assertAskedInStdIn;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.FormatParam.addToStdInInput;
import static engine.script.Expression.callKoanMethod;
import static sensei.Texts.*;


import java.util.List;

import engine.Koan;
import engine.Localizable;


public class AboutMethodsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutMethods.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, REPETITIVE_TASKS)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("computeAgeIn5And10And20YearsConsole")
            )
            .when(callKoanMethod("computeAgeIn5And10And20YearsConsole"))
            .withStdInInputs("14", "6", "10")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_5_YEARS_YOU_WILL_BE, addToStdInInput(0, 5)),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_10_YEARS_YOU_WILL_BE, addToStdInInput(1, 10)),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_20_YEARS_YOU_WILL_BE, addToStdInInput(2, 20)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, REPETITIVE_TASKS_METHODS_TO_THE_RESCUE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("computeAgeIn5And10And20YearsConsoleWithMethod")
            )
            .when(callKoanMethod("computeAgeIn5And10And20YearsConsoleWithMethod"))
            .withStdInInputs("14", "6", "10")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_5_YEARS_YOU_WILL_BE, addToStdInInput(0, 5)),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_10_YEARS_YOU_WILL_BE, addToStdInInput(1, 10)),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_20_YEARS_YOU_WILL_BE, addToStdInInput(2, 20)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, METHODS_CAN_ALSO_RETURN_RESULTS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("square", int.class)
            )
            .when(callKoanMethod("square", 2))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("square", -2))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("square", 3))
            .then(
                assertReturnValueEquals(9)
            )
            .when(callKoanMethod("square", 0))
            .then(
                assertReturnValueEquals(0)
            ),
        new Koan(CLASS, RETURNING_THE_OPPOSITE_OF_A_NUMBER)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("opposite", int.class)
            )
            .when(callKoanMethod("opposite", 2))
            .then(
                assertReturnValueEquals(-2)
            )
            .when(callKoanMethod("opposite", -2))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("opposite", 0))
            .then(
                assertReturnValueEquals(0)
            ),
        new Koan(CLASS, HELPING_A_FARMER)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("legs", int.class, int.class, int.class)
            )
            .when(callKoanMethod("legs", 2, 3, 4))
            .then(
                assertReturnValueEquals(32)
            )
            .when(callKoanMethod("legs", 3, 0, 0))
            .then(
                assertReturnValueEquals(6)
            )
            .when(callKoanMethod("legs", 0, 3, 0))
            .then(
                assertReturnValueEquals(12)
            )
            .when(callKoanMethod("legs", 0, 0, 3))
            .then(
                assertReturnValueEquals(12)
            )
            .when(callKoanMethod("legs", 0, 0, 0))
            .then(
                assertReturnValueEquals(0)
            )
    );
}
