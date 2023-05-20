package sensei;

import engine.Koan;
import koans.AboutMethods;

import static engine.Assertions.*;
import static engine.KoanResult.*;

import java.util.List;

public class AboutMethodsKoans {
    public static final List<Koan> koans = List.of(
        new Koan( AboutMethods.class, "computeAgeIn5And10And20YearsConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14", "6", "10")
            .then(
                assertOutEquals(0, "What is your age?"),
                assertAskedInStdIn(0),
                assertOutEquals(1, "In 5 years from now, you will be %s.", paramPlus(0, 5)),
                assertOutEquals(2, "What is your age?"),
                assertAskedInStdIn(1),
                assertOutEquals(3, "In 10 years from now, you will be %s.", paramPlus(1, 10)),
                assertOutEquals(4, "What is your age?"),
                assertAskedInStdIn(2),
                assertOutEquals(5, "In 20 years from now, you will be %s.", paramPlus(2, 20))
            ),
        new Koan(AboutMethods.class, "computeAgeIn5And10And20YearsConsoleWithMethod")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14", "6", "10")
            .then(
                assertOutEquals(0, "What is your age?"),
                assertAskedInStdIn(0),
                assertOutEquals(1, "In 5 years from now, you will be %s.", paramPlus(0, 5)),
                assertOutEquals(2, "What is your age?"),
                assertAskedInStdIn(1),
                assertOutEquals(3, "In 10 years from now, you will be %s.", paramPlus(1, 10)),
                assertOutEquals(4, "What is your age?"),
                assertAskedInStdIn(2),
                assertOutEquals(5, "In 20 years from now, you will be %s.", paramPlus(2, 20))
            ),
        new Koan(AboutMethods.class, "square", int.class)
            .whenCallingWith(2)
            .then(assertResultEquals(4))
            .whenCallingWith(-2)
            .then(assertResultEquals(4))
            .whenCallingWith(3)
            .then(assertResultEquals(9))
            .whenCallingWith(0)
            .then(assertResultEquals(0)),
        new Koan(AboutMethods.class, "opposite", int.class)
            .whenCallingWith(2)
            .then(assertResultEquals(-2))
            .whenCallingWith(-2)
            .then(assertResultEquals(2))
            .whenCallingWith(0)
            .then(assertResultEquals(0)),
        new Koan(AboutMethods.class, "legs", int.class, int.class, int.class)
            .whenCallingWith(2, 3, 4)
            .then(assertResultEquals(32))
            .whenCallingWith(3, 0, 0)
            .then(assertResultEquals(6))
            .whenCallingWith(0, 3, 0)
            .then(assertResultEquals(12))
            .whenCallingWith(0, 0, 3)
            .then(assertResultEquals(12))
            .whenCallingWith(0, 0, 0)
            .then(assertResultEquals(0))
    );
}
