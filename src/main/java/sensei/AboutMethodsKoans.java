package sensei;

import java.util.List;

import engine.Koan;
import engine.Local;
import koans.english.AboutMethods;

import static engine.Assertions.*;
import static engine.KoanResult.*;
import static engine.Localizable.*;
import static sensei.Texts.*;


public class AboutMethodsKoans {
    private static final Local<Class<?>> CLASS = local(AboutMethods.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "computeAgeIn5And10And20YearsConsole")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14", "6", "10")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(0),
                assertOutEquals(1, IN_5_YEARS_YOU_WILL_BE, paramPlus(0, 5)),
                assertOutEquals(2, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(1),
                assertOutEquals(3, IN_10_YEARS_YOU_WILL_BE, paramPlus(1, 10)),
                assertOutEquals(4, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(2),
                assertOutEquals(5, IN_20_YEARS_YOU_WILL_BE, paramPlus(2, 20))
            ),
        new Koan(CLASS, "computeAgeIn5And10And20YearsConsoleWithMethod")
            .useConsole()
            .whenCalling()
            .withStdInInputs("14", "6", "10")
            .then(
                assertOutEquals(0, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(0),
                assertOutEquals(1, IN_5_YEARS_YOU_WILL_BE, paramPlus(0, 5)),
                assertOutEquals(2, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(1),
                assertOutEquals(3, IN_10_YEARS_YOU_WILL_BE, paramPlus(1, 10)),
                assertOutEquals(4, WHAT_IS_YOUR_AGE),
                assertAskedInStdIn(2),
                assertOutEquals(5, IN_20_YEARS_YOU_WILL_BE, paramPlus(2, 20))
            ),
        new Koan(CLASS, "square", int.class)
            .whenCallingWith(2)
            .then(
                assertResultEquals(4)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(4)
            )
            .whenCallingWith(3)
            .then(
                assertResultEquals(9)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(0)
            ),
        new Koan(CLASS, "opposite", int.class)
            .whenCallingWith(2)
            .then(
                assertResultEquals(-2)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(2)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(0)
            ),
        new Koan(CLASS, "legs", int.class, int.class, int.class)
            .whenCallingWith(2, 3, 4)
            .then(
                assertResultEquals(32)
            )
            .whenCallingWith(3, 0, 0)
            .then(
                assertResultEquals(6)
            )
            .whenCallingWith(0, 3, 0)
            .then(
                assertResultEquals(12)
            )
            .whenCallingWith(0, 0, 3)
            .then(
                assertResultEquals(12)
            )
            .whenCallingWith(0, 0, 0)
            .then(
                assertResultEquals(0)
            )
    );
}
