package sensei;

import engine.Koan;
import engine.StdInInput;
import koans.AboutConsoleAndVariables;
import static engine.Assertions.*;
import static engine.KoanResult.*;

import java.util.List;

public class AboutConsoleAndVariablesKoans {
    public static final List<Koan> koans = List.of(
        new Koan(
            AboutConsoleAndVariables.class,
            "sayHelloInConsole",
            assertOutEquals(0, "Hello!")
        ),
        new Koan(
            AboutConsoleAndVariables.class,
            "computeTwoAndTwo",
            assertOutEquals(0, "4")
        ),
        new Koan(
            AboutConsoleAndVariables.class,
            "createAndDisplayAVariable",
            assertOutEquals(0, "4")
        ),
        new Koan(
            AboutConsoleAndVariables.class,
            "createAndDisplayAStringVariable",
            assertOutEquals(0, "Hello!")
        ),
        new Koan(
            AboutConsoleAndVariables.class,
            "askAndDisplayNameInConsole",
            assertOutEquals(0, "What is your name?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "Your name is:"),
            assertOutEquals(2, "%s", new StdInInput(0))
        ).withStdInInputs("Juliette"),
        new Koan(
            AboutConsoleAndVariables.class,
            "askAndDisplayNameOnASingleLineInConsole",
            assertOutEquals(0, "What is your name?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "Your name is: %s", new StdInInput(0))
        ).withStdInInputs("Juliette"),
        new Koan(
            AboutConsoleAndVariables.class,
            "askNameAndAgeInConsole",
            assertOutEquals(0, "What is your name?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "What is your age?"),
            assertAskedInStdIn(1),
            assertOutEquals(2, "Your name is %s and your age is %s.", new StdInInput(0), new StdInInput(1))
        ).withStdInInputs("Juliette", "14"),
        new Koan(
            AboutConsoleAndVariables.class,
            "computeAgeIn5YearsConsole",
            assertOutEquals(0, "What is your age?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "In 5 years from now, you will be %s.", paramPlus(0, 5))
        ).withStdInInputs("14")
    );
}
