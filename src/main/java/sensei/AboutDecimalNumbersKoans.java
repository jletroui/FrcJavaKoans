package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static sensei.Texts.*;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutDecimalNumbersKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutDecimalNumbers.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, CONVERTING_A_MEASURE_OF_LENGTH)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("toCm", double.class)
            )
            .when(callKoanMethod("toCm", 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("toCm", 1.0))
            .then(
                assertReturnValueEquals(2.54)
            )
            .when(callKoanMethod("toCm", 2.0))
            .then(
                assertReturnValueEquals(5.08)
            ),
        new Koan(CLASS, CONVERTING_THE_OTHER_WAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("toInches", double.class)
            )
            .when(callKoanMethod("toInches", 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("toInches", 2.54))
            .then(
                assertReturnValueEquals(1.0)
            )
            .when(callKoanMethod("toInches", 5.08))
            .then(
                assertReturnValueEquals(2.0)
            ),
        new Koan(CLASS, COMPUTING_SOME_GEOMETRY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("rectangleArea", double.class, double.class)
            )
            .when(callKoanMethod("rectangleArea", 0.0, 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("rectangleArea", 0.0, 4.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("rectangleArea", 1.0, 4.0))
            .then(
                assertReturnValueEquals(4.0)
            )
            .when(callKoanMethod("rectangleArea", 3.5, 3.0))
            .then(
                assertReturnValueEquals(10.5)
            ),
        new Koan(CLASS, COMPUTING_HOW_LONG_A_ROBOT_IS_TRAVELING_STEP_1)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("wheelCircumference", double.class)
            )
            .when(callKoanMethod("wheelCircumference", 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("wheelCircumference", 1.0))
            .then(
                assertReturnValueEquals(6.28)
            )
            .when(callKoanMethod("wheelCircumference", 2.0))
            .then(
                assertReturnValueEquals(12.56)
            ),
        new Koan(CLASS, COMPUTING_HOW_LONG_A_ROBOT_IS_TRAVELING_STEP_2)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("wheelRotations", double.class, double.class)
            )
            .when(callKoanMethod("wheelRotations", 1.0, 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("wheelRotations", 1.0, 0.15))
            .then(
                assertReturnValueEquals(0.15)
            )
            .when(callKoanMethod("wheelRotations", 10.0, 0.15))
            .then(
                assertReturnValueEquals(1.5)
            ),
        new Koan(CLASS, COMPUTING_HOW_LONG_A_ROBOT_IS_TRAVELING_FINAL_STEP)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("toDistance", double.class, double.class, double.class)
            )
            .when(callKoanMethod("toDistance", 1.0, 0.0, 4.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("toDistance", 1.0, 1.0, 1.0))
            .then(
                assertReturnValueEquals(6.28)
            )
            .when(callKoanMethod("toDistance", 10.0, 1.0, 1.0))
            .then(
                assertReturnValueEquals(62.8)
            )
            .when(callKoanMethod("toDistance", 1.0, 0.15, 4.0))
            .then(
                assertReturnValueEquals(3.768)
            )
            .when(callKoanMethod("toDistance", 10.0, 0.15, 3.0))
            .then(
                assertReturnValueEquals(28.26)
            )
    );
}
