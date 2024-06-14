package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static sensei.Texts.*;

import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutArraysKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutArrays.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, INDEXING_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("valueAtIndex", int[].class, int.class)
            )
            .when(callKoanMethod("valueAtIndex", new int[]{1, 2, 3, 4, 5}, 1))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("valueAtIndex", new int[]{1, 2, 3, 4, 5}, 3))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("valueAtIndex", new int[]{6, 15, 23, 10, 7}, 0))
            .then(
                assertReturnValueEquals(6)
            ),
        new Koan(CLASS, SEARCHING_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("indexOfValue", int[].class, int.class)
            )
             .when(callKoanMethod("indexOfValue", new int[]{1, 2, 5, 5, 5}, 5))
            .then(
                assertReturnValueEquals(2)
            )
            .when(callKoanMethod("indexOfValue", new int[]{1, 2, 3, 4, 5}, 1))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("indexOfValue", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11))
            .then(
                assertReturnValueEquals(-1)
            ),
        new Koan(CLASS, SORTING_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("sortArray", int[].class)
            )
            .when(callKoanMethod("sortArray", new int[]{1, 2, 3, 4, 5}))
            .then(
                assertReturnValueEquals(new int[]{1, 2, 3, 4, 5})
            )
            .when(callKoanMethod("sortArray", new int[]{2, 10, 3, 4, 6, 8, 7, 5, 9, 1}))
            .then(
                assertReturnValueEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
            )
            .when(callKoanMethod("sortArray", new int[]{5, 15, 35, -1, 6}))
            .then(
                assertReturnValueEquals(new int[]{-1, 5, 6, 15, 35})
            )
    );
}
