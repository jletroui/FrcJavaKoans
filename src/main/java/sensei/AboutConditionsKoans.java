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
        .fr(koans.french.AboutConditions.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "sign", int.class)
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCalledWith(-2)
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, "signOrZero", int.class)
            .whenCalledWith(2)
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(ZERO)
            )
            .whenCalledWith(-2)
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, "gradeComment", int.class)
            .whenCalledWith(0)
            .then(
                assertReturnValueEquals(OOPS)
            )
            .whenCalledWith(1)
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .whenCalledWith(59)
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .whenCalledWith(60)
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .whenCalledWith(99)
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .whenCalledWith(100)
            .then(
                assertReturnValueEquals(GOT_EVERYTHING_RIGHT)
            )            
            .whenCalledWith(101)
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
            .whenCalledWith(-1)
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
    );
}
