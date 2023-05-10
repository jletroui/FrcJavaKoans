package sensei;

import engine.Koan;
import engine.StdInInput;
import koans.AboutConsoleAndVariables;
import static engine.Assertions.*;

import java.util.List;

public class AboutConsoleAndVariablesKoans {
    public static final List<Koan> koans = List.of(
        // new Koan(
        //     AboutConsoleAndVariables.class,
        //     "sayHelloInConsole",
        //     assertOutEquals(0, "Hello!")
        // ),
        // new Koan(
        //     AboutConsoleAndVariables.class,
        //     "computeTwoAndTwo",
        //     assertOutEquals(0, "4")
        // ),
        // new Koan(
        //     AboutConsoleAndVariables.class,
        //     "createAndDisplayAVariable",
        //     assertOutEquals(0, "4")
        // ),
        // new Koan(
        //     AboutConsoleAndVariables.class,
        //     "createAndDisplayAStringVariable",
        //     assertOutEquals(0, "Hello!")
        // ),
        new Koan(
            AboutConsoleAndVariables.class,
            "askAndDisplayNameInConsole",
            assertOutEquals(0, "What is your name?"),
            assertOutEquals(1, "%s", new StdInInput(0))
        ).withStdInInputs("Juliette")
    );
}
