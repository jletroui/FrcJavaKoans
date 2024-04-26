package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Factories.localClass;

import java.util.List;

import engine.Koan;
import engine.Local;

public class AboutDecimalNumbersKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutDecimalNumbers.class)
        .fr(koans.french.AboutDecimalNumbers.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "toCm", double.class)
            .whenCalledWith(0.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(1.0)
            .then(
                assertReturnValueEquals(2.54)
            )
            .whenCalledWith(2.0)
            .then(
                assertReturnValueEquals(5.08)
            ),
        new Koan(CLASS, "toInches", double.class)
            .whenCalledWith(0.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(2.54)
            .then(
                assertReturnValueEquals(1.0)
            )
            .whenCalledWith(5.08)
            .then(
                assertReturnValueEquals(2.0)
            ),
        new Koan(CLASS, "rectangleArea", double.class, double.class)
            .whenCalledWith(0.0, 0.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(0.0, 4.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(1.0, 4.0)
            .then(
                assertReturnValueEquals(4.0)
            )
            .whenCalledWith(3.5, 3.0)
            .then(
                assertReturnValueEquals(10.5)
            ),
        new Koan(CLASS, "wheelCircumference", double.class)
            .whenCalledWith(0.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(1.0)
            .then(
                assertReturnValueEquals(6.28)
            )
            .whenCalledWith(2.0)
            .then(
                assertReturnValueEquals(12.56)
            ),
        new Koan(CLASS, "wheelRotations", double.class, double.class)
            .whenCalledWith(1.0, 0.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(1.0, 0.15)
            .then(
                assertReturnValueEquals(0.15)
            )
            .whenCalledWith(10.0, 0.15)
            .then(
                assertReturnValueEquals(1.5)
            ),
        new Koan(CLASS, "toDistance", double.class, double.class, double.class)
            .whenCalledWith(1.0, 0.0, 4.0)
            .then(
                assertReturnValueEquals(0.0)
            )
            .whenCalledWith(1.0, 1.0, 1.0)
            .then(
                assertReturnValueEquals(6.28)
            )
            .whenCalledWith(10.0, 1.0, 1.0)
            .then(
                assertReturnValueEquals(62.8)
            )
            .whenCalledWith(1.0, 0.15, 4.0)
            .then(
                assertReturnValueEquals(3.768)
            )
            .whenCalledWith(10.0, 0.15, 3.0)
            .then(
                assertReturnValueEquals(28.26)
            )
    );
}
