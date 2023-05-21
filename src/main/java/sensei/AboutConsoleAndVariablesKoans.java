package sensei;

import engine.Koan;
import engine.Local;
import engine.StdInInput;
import java.util.List;

import koans.english.AboutConsoleAndVariables;
import static engine.Assertions.*;
import static engine.KoanResult.*;
import static engine.Localizable.*;
import static sensei.Texts.*;

public class AboutConsoleAndVariablesKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutConsoleAndVariables.class)
        .fr(AboutConsoleAndVariables.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "sayHelloInConsole")
            .useConsole()
            .whenCalling()
            .then(
                assertOutEquals(0, global("Hello!"))
            ),
        new Koan(CLASS, "computeTwoAndTwo")
            .useConsole()
            .whenCalling()
            .then(
                assertOutEquals(0, global("4"))
            ),
        new Koan(CLASS, "createAndDisplayAVariable")
            .useConsole()
            .whenCalling()
            .then(
                assertOutEquals(0, global("4"))
            ),
        new Koan(CLASS, "createAndDisplayAStringVariable")
            .useConsole()
            .whenCalling()
            .then(
                assertOutEquals(0, global("Hello!"))
            ),
        new Koan(CLASS, "askAndDisplayNameInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(0),
                assertOutEquals(1, YOUR_NAME_IS),
                assertOutEquals(2, global("%s"), new StdInInput(0))
            ),
        new Koan(CLASS, "askAndDisplayNameOnASingleLineInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(0),
                assertOutEquals(1, YOUR_NAME_IS, new StdInInput(0))
            ),
        new Koan(CLASS, "askNameAndAgeInConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("Juliette", "14")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_NAME),
                assertAskedInStdIn(0),
                assertOutEquals(1, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(1),
                assertOutEquals(2, YOUR_NAME_IS_AND_YOUR_AGE_IS, new StdInInput(0), new StdInInput(1))
            ),
        new Koan(CLASS, "computeAgeIn5YearsConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(0),
                assertOutEquals(1, IN_5_YEARS_YOU_WILL_BE, paramPlus(0, 5))
            )
    );
}
