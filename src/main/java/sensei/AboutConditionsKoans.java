package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.localClass;
import static sensei.Texts.GOT_EVERYTHING_RIGHT;
import static sensei.Texts.NEGATIVE;
import static sensei.Texts.OOPS;
import static sensei.Texts.POSITIVE;
import static sensei.Texts.YOU_CHEAT;
import static sensei.Texts.YOU_FAILED;
import static sensei.Texts.YOU_PASS;
import static sensei.Texts.ZERO;

import java.util.List;

import engine.Koan;
import engine.Local;

public class AboutConditionsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutConditions.class)
        .fr(koans.french.AboutConditions.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "sign", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, "signOrZero", int.class)
            .whenCallingWith(2)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(ZERO)
            )
            .whenCallingWith(-2)
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, "gradeComment", int.class)
            .whenCallingWith(0)
            .then(
                assertReturnValueEquals(OOPS)
            )
            .whenCallingWith(1)
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .whenCallingWith(59)
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .whenCallingWith(60)
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .whenCallingWith(99)
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .whenCallingWith(100)
            .then(
                assertReturnValueEquals(GOT_EVERYTHING_RIGHT)
            )            
            .whenCallingWith(101)
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
            .whenCallingWith(-1)
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
    );
}
