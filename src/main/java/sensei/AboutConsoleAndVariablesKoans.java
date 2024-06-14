package sensei;

import static engine.Assertions.assertAskedInStdIn;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.Localizable.global;
import static engine.FormatParam.addToStdInInput;
import static engine.FormatParam.stdInInput;
import static sensei.Texts.*;
import static engine.script.Expression.callKoanMethod;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutConsoleAndVariablesKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutConsoleAndVariables.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, DISPLAYING_SOME_TEXT_IN_THE_CONSOLE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("sayHelloInConsole")
            )
            .when(callKoanMethod("sayHelloInConsole"))
            .then(
                assertNextStdOutLineEquals(global("Hello!")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, DISPLAYING_SOME_CALCULATION_IN_THE_CONSOLE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("computeTwoAndTwo")
            )
            .when(callKoanMethod("computeTwoAndTwo"))
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, CREATING_A_VARIABLE_TO_STORE_A_VALUE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("createAndDisplayAVariable")
            )
            .when(callKoanMethod("createAndDisplayAVariable"))
            .then(
                assertNextStdOutLineEquals(global("4")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, CREATING_A_VARIABLE_TO_STORE_TEXT)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("createAndDisplayAStringVariable")
            )
            .when(callKoanMethod("createAndDisplayAStringVariable"))
            .then(
                assertNextStdOutLineEquals(global("Hello!")),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, ASKING_FOR_SOME_USER_ANSWER)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("askAndDisplayNameInConsole")
            )
            .when(callKoanMethod("askAndDisplayNameInConsole"))
            .withStdInInputs("Juliette")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS),
                assertNextStdOutLineEquals(global("%s"), stdInInput(0)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, PLAY_WITH_TEXT_CONTENT)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("askAndDisplayNameOnASingleLineInConsole")
            )
            .when(callKoanMethod("askAndDisplayNameOnASingleLineInConsole"))
            .withStdInInputs("Juliette")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS_SINGLE_LINE, stdInInput(0)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, PLAYING_MORE_WITH_TEXT_CONTENT)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("askNameAndAgeInConsole")
            )
            .when(callKoanMethod("askNameAndAgeInConsole"))
            .withStdInInputs("Juliette", "14")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(YOUR_NAME_IS_AND_YOUR_AGE_IS, stdInInput(0), stdInInput(1)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, PLAYING_WITH_TEXT_AND_NUMBERS_AT_THE_SAME_TIME)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("computeAgeIn5YearsConsole")
            )
            .when(callKoanMethod("computeAgeIn5YearsConsole"))
            .withStdInInputs("14")
            .then(
                assertNextStdOutLineEquals(WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(),
                assertNextStdOutLineEquals(IN_5_YEARS_YOU_WILL_BE, addToStdInInput(0, 5)),
                assertNoMoreLineInStdOut()
            )
    );
}
