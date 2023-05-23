package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutDecimalNumbers;

import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutNot7GameKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutDecimalNumbers.class)
        .fr(AboutDecimalNumbers.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "die6")
            .whenCalling()
            .then(
                assertResultWithRandomEquals(rand -> die(6, rand))
            )
    );

    private static int die(int faces, double rand) {
        return (int)Math.ceil(faces * rand);
    }
}
