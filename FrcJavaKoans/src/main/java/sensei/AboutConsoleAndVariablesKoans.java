package sensei;

import engine.Koan;
import koans.AboutConsoleAndVariables;
import static engine.Assertions.*;

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
            "askANameInConsole",
            assertOutEquals(0, "What is your name?")
        )
    );
}
