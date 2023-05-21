package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutConditions;
import static engine.Assertions.*;
import engine.Local;
import static sensei.Texts.*;
import static engine.Localizable.*;

public class AboutConditionsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutConditions.class)
        .fr(AboutConditions.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "sign", int.class)
            .whenCallingWith(2)
            .then(
                assertResultEquals(POSITIVE)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(POSITIVE)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(NEGATIVE)
            ),
        new Koan(CLASS, "signOrZero", int.class)
            .whenCallingWith(2)
            .then(
                assertResultEquals(POSITIVE)
            )
            .whenCallingWith(0)
            .then(
                assertResultEquals(ZERO)
            )
            .whenCallingWith(-2)
            .then(
                assertResultEquals(NEGATIVE)
            ),
        new Koan(CLASS, "gradeComment", int.class)
            .whenCallingWith(0)
            .then(
                assertResultEquals(OOPS)
            )
            .whenCallingWith(1)
            .then(
                assertResultEquals(YOU_FAILED)
            )
            .whenCallingWith(59)
            .then(
                assertResultEquals(YOU_FAILED)
            )
            .whenCallingWith(60)
            .then(
                assertResultEquals(YOU_PASS)
            )            
            .whenCallingWith(99)
            .then(
                assertResultEquals(YOU_PASS)
            )            
            .whenCallingWith(100)
            .then(
                assertResultEquals(GOT_EVERYTHING_RIGHT)
            )            
            .whenCallingWith(101)
            .then(
                assertResultEquals(YOU_CHEAT)
            )            
            .whenCallingWith(-1)
            .then(
                assertResultEquals(YOU_CHEAT)
            )            
    );
}
