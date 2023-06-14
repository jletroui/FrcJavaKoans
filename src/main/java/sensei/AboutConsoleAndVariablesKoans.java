package sensei;

import static engine.Assertions.assertAskedInStdIn;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Factories.global;
import static engine.Factories.localClass;
import static engine.FormatParam.addToStdInInput;
import static engine.FormatParam.stdInInput;
import static sensei.Texts.IN_5_YEARS_YOU_WILL_BE;
import static sensei.Texts.WHAT_IS_YOUR_AGE;
import static sensei.Texts.WHAT_IS_YOUR_NAME;
import static sensei.Texts.YOUR_NAME_IS;
import static sensei.Texts.YOUR_NAME_IS_AND_YOUR_AGE_IS;
import static sensei.Texts.YOUR_NAME_IS_SINGLE_LINE;

import java.util.List;

import engine.Koan;
import engine.Local;

public class AboutConsoleAndVariablesKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutConsoleAndVariables.class)
        .fr(koans.french.AboutConsoleAndVariables.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "sayHelloInConsole")
            .useConsole()
            .whenCalling()
            .then(
                assertNextStdOutLineEquals(global("Hello!")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "computeTwoAndTwo")
            .useConsole()
            .whenCalling()
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "createAndDisplayAVariable")
            .useConsole()
            .whenCalling()
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "createAndDisplayAStringVariable")
            .useConsole()
            .whenCalling()
            .then(
                assertNextStdOutLineEquals(global("Hello!")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "askAndDisplayNameInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS),
                assertNextStdOutLineEquals(global("%s"), stdInInput(0)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "askAndDisplayNameOnASingleLineInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS_SINGLE_LINE, stdInInput(0)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "askNameAndAgeInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette", "14")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS_AND_YOUR_AGE_IS, stdInInput(0), stdInInput(1)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "computeAgeIn5YearsConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_5_YEARS_YOU_WILL_BE, addToStdInInput(0, 5)),
                assertNoMoreLineInStdOut()
            )
    );
}
