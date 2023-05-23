package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutDecimalNumbers;

import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutDecimalNumbersKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutDecimalNumbers.class)
        .fr(AboutDecimalNumbers.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "toCm", double.class)
            .whenCallingWith(0.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(1.0)
            .then(
                assertResultEquals(2.54)
            )
            .whenCallingWith(2.0)
            .then(
                assertResultEquals(5.08)
            ),
        new Koan(CLASS, "toInches", double.class)
            .whenCallingWith(0.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(2.54)
            .then(
                assertResultEquals(1.0)
            )
            .whenCallingWith(5.08)
            .then(
                assertResultEquals(2.0)
            ),
        new Koan(CLASS, "rectangleArea", double.class, double.class)
            .whenCallingWith(0.0, 0.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(0.0, 4.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(1.0, 4.0)
            .then(
                assertResultEquals(4.0)
            )
            .whenCallingWith(3.5, 3.0)
            .then(
                assertResultEquals(10.5)
            ),
        new Koan(CLASS, "wheelCircumference", double.class)
            .whenCallingWith(0.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(1.0)
            .then(
                assertResultEquals(6.28)
            )
            .whenCallingWith(2.0)
            .then(
                assertResultEquals(12.56)
            ),
        new Koan(CLASS, "wheelRotations", double.class, double.class)
            .whenCallingWith(0.0, 1.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(0.15, 1.0)
            .then(
                assertResultEquals(0.15)
            )
            .whenCallingWith(0.15, 10.0)
            .then(
                assertResultEquals(1.5)
            ),
        new Koan(CLASS, "toDistance", double.class, double.class, double.class)
            .whenCallingWith(0.0, 4.0, 1.0)
            .then(
                assertResultEquals(0.0)
            )
            .whenCallingWith(1.0, 1.0, 1.0)
            .then(
                assertResultEquals(6.28)
            )
            .whenCallingWith(1.0, 1.0, 10.0)
            .then(
                assertResultEquals(62.8)
            )
            .whenCallingWith(0.15, 4.0, 1.0)
            .then(
                assertResultEquals(3.768)
            )
            .whenCallingWith(0.15, 3.0, 10.0)
            .then(
                assertResultEquals(28.26)
            )
    );
}
