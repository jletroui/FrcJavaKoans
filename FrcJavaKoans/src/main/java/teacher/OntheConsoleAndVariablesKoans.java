package teacher;

import engine.Koan;
import koans.OnTheConsoleAndVariables;
import static engine.Assertions.*;

import java.util.List;

public class OntheConsoleAndVariablesKoans {
    public static final List<Koan> koans = List.of(
        new Koan(
            OnTheConsoleAndVariables.class,
            "sayHelloInConsole",
            assertOutEquals(0, "Hello!")
        ),
        new Koan(
            OnTheConsoleAndVariables.class,
            "askANameInConsole",
            assertOutEquals(0, "What is your name?")
        )
    );
}
