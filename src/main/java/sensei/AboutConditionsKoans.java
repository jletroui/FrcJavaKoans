package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutConditions;
import static engine.Assertions.*;
import engine.Local;
import static sensei.Texts.*;
import static engine.Factories.*;

public class AboutConditionsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutConditions.class)
        .fr(AboutConditions.class); // TODO

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
