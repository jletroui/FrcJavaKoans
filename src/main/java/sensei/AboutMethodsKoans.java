package sensei;

import engine.Koan;
import koans.AboutMethods;

import static engine.Assertions.*;
import static engine.KoanResult.*;

import java.util.List;

public class AboutMethodsKoans {
    public static final List<Koan> koans = List.of(
        new Koan(
            AboutMethods.class,
            "computeAgeIn5And10And20YearsConsole",
            assertOutEquals(0, "What is your age?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "In 5 years from now, you will be %s.", paramPlus(0, 5)),
            assertOutEquals(2, "What is your age?"),
            assertAskedInStdIn(1),
            assertOutEquals(3, "In 10 years from now, you will be %s.", paramPlus(1, 10)),
            assertOutEquals(4, "What is your age?"),
            assertAskedInStdIn(2),
            assertOutEquals(5, "In 20 years from now, you will be %s.", paramPlus(2, 20))
        ).withStdInInputs("14", "6", "10"),
        new Koan(
            AboutMethods.class,
            "computeAgeIn5And10And20YearsConsoleWithMethod",
            assertOutEquals(0, "What is your age?"),
            assertAskedInStdIn(0),
            assertOutEquals(1, "In 5 years from now, you will be %s.", paramPlus(0, 5)),
            assertOutEquals(2, "What is your age?"),
            assertAskedInStdIn(1),
            assertOutEquals(3, "In 10 years from now, you will be %s.", paramPlus(1, 10)),
            assertOutEquals(4, "What is your age?"),
            assertAskedInStdIn(2),
            assertOutEquals(5, "In 20 years from now, you will be %s.", paramPlus(2, 20))
        ).withStdInInputs("14", "6", "10")
    );
}
