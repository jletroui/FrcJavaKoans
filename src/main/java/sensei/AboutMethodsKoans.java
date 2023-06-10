package sensei;

import static engine.Assertions.assertAskedInStdIn;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.localClass;
import static engine.FormatParam.addToStdInInput;
import static sensei.Texts.IN_10_YEARS_YOU_WILL_BE;
import static sensei.Texts.IN_20_YEARS_YOU_WILL_BE;
import static sensei.Texts.IN_5_YEARS_YOU_WILL_BE;
import static sensei.Texts.WHAT_IS_YOUR_AGE;

import java.util.List;

import engine.Koan;
import engine.Local;


public class AboutMethodsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutMethods.class)
        .fr(koans.french.AboutMethods.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "computeAgeIn5And10And20YearsConsole")
            .useConsole()
            .whenCalling()
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
        new Koan(CLASS, "computeAgeIn5And10And20YearsConsoleWithMethod")
            .useConsole()
            .whenCalling()
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
        new Koan(CLASS, "square", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(4)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(4)
            )
            .whenCallingWith(3)
            .then(
                assertReturnValueEquals(9)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(0)
            ),
        new Koan(CLASS, "opposite", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(-2)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(2)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(0)
            ),
        new Koan(CLASS, "legs", int.class, int.class, int.class)
            .whenCallingWith(2, 3, 4)
            .then(
                assertReturnValueEquals(32)
            )
            .whenCallingWith(3, 0, 0)
            .then(
                assertReturnValueEquals(6)
            )
            .whenCallingWith(0, 3, 0)
            .then(
                assertReturnValueEquals(12)
            )
            .whenCallingWith(0, 0, 3)
            .then(
                assertReturnValueEquals(12)
            )
            .whenCallingWith(0, 0, 0)
            .then(
                assertReturnValueEquals(0)
            )
    );
}
