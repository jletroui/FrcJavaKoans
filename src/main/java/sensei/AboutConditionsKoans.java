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
            )
    );
}
