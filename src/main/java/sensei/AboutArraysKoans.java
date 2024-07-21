package sensei;

import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertVariableEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.Localizable.global;
import static engine.script.Expression.assignVariable;
import static engine.script.Expression.callKoanMethod;
import static engine.script.Expression.variable;
import static sensei.Texts.*;

import java.util.List;

import engine.Koan;
import engine.Localizable;


public class AboutArraysKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(bonuses.english.AboutArrays.class)
        .fr(bonuses.french.AboutArrays.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, FOR_LOOPS)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("displayNumbers", int.class)
            )
            .when(callKoanMethod("displayNumbers", 2))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNextStdOutLineEquals(global("2")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayNumbers", 3))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNextStdOutLineEquals(global("2")),
                assertNextStdOutLineEquals(global("3")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayNumbers", 1))
            .then(
                assertNextStdOutLineEquals(global("1")),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("displayNumbers", 0))
            .then(
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, FIRST_ELEMENT_OF_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("first", int[].class)
            )
            .when(callKoanMethod("first", new int[]{4}))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("first", new int[]{10, 4, 8}))
            .then(
                assertReturnValueEquals(10)
            ),
        new Koan(CLASS, LAST_ELEMENT_OF_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("last", int[].class)
            )
            .when(callKoanMethod("last", new int[]{4}))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("last", new int[]{10, 4, 8}))
            .then(
                assertReturnValueEquals(8)
            ),
        new Koan(CLASS, FINDING_AN_ELEMENT)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("findFirst", int[].class, int.class)
            )
            .when(callKoanMethod("findFirst", new int[]{3}, 3))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("findFirst", new int[]{10}, 3))
            .then(
                assertReturnValueEquals(-1)
            )
            .when(callKoanMethod("findFirst", new int[0], 3))
            .then(
                assertReturnValueEquals(-1)
            )
            .when(callKoanMethod("findFirst", new int[]{10, 4, 8, 8, 10}, 10))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("findFirst", new int[]{10, 4, 8, 8, 10}, 8))
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, FINDING_AN_ELEMENT_AT_THE_END)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("findLast", int[].class, int.class)
            )
            .when(callKoanMethod("findLast", new int[]{3}, 3))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("findLast", new int[]{10}, 3))
            .then(
                assertReturnValueEquals(-1)
            )
            .when(callKoanMethod("findLast", new int[0], 3))
            .then(
                assertReturnValueEquals(-1)
            )
            .when(callKoanMethod("findLast", new int[]{10, 4, 8, 8, 10}, 10))
            .then(
                assertReturnValueEquals(4)
            )
            .when(callKoanMethod("findLast", new int[]{10, 4, 8, 8, 10}, 8))
            .then(
                assertReturnValueEquals(3)
            ),
        new Koan(CLASS, FINDING_THE_SMALLEST_ELEMENT)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("min", int[].class)
            )
            .when(callKoanMethod("min", new int[]{3}))
            .then(
                assertReturnValueEquals(3)
            )
            .when(callKoanMethod("min", new int[0]))
            .then(
                assertReturnValueEquals(Integer.MAX_VALUE)
            )
            .when(callKoanMethod("min", new int[]{10, 4, 8, 2, 10}))
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, FINDING_THE_SMALLEST_ELEMENT_REVISITED)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("min2", int[].class)
            )
            .when(callKoanMethod("min2", new int[]{3}))
            .then(
                assertReturnValueEquals(3)
            )
            .when(callKoanMethod("min2", new int[0]))
            .then(
                assertReturnValueEquals(Integer.MAX_VALUE)
            )
            .when(callKoanMethod("min2", new int[]{10, 4, 8, 2, 10}))
            .then(
                assertReturnValueEquals(2)
            ),
        new Koan(CLASS, COMPUTING_THE_SUM)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("sum", int[].class)
            )
            .when(callKoanMethod("sum", new int[]{3}))
            .then(
                assertReturnValueEquals(3)
            )
            .when(callKoanMethod("sum", new int[]{10}))
            .then(
                assertReturnValueEquals(10)
            )
            .when(callKoanMethod("sum", new int[0]))
            .then(
                assertReturnValueEquals(0)
            )
            .when(callKoanMethod("sum", new int[]{10, 4, 8}))
            .then(
                assertReturnValueEquals(22)
            ),
        new Koan(CLASS, COMPUTING_THE_AVERAGE)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("avg", int[].class)
            )
            .when(callKoanMethod("avg", new int[]{3}))
            .then(
                assertReturnValueEquals(3.0)
            )
            .when(callKoanMethod("avg", new int[]{10}))
            .then(
                assertReturnValueEquals(10.0)
            )
            .when(callKoanMethod("avg", new int[0]))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("avg", new int[]{9, 4, 8}))
            .then(
                assertReturnValueEquals(7.0)
            ),
        new Koan(CLASS, FILLING_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("fill", int.class, int.class)
            )
            .when(callKoanMethod("fill", 1, 11))
            .then(
                assertReturnValueEquals(new int[]{11})
            )
            .when(callKoanMethod("fill", 0, 11))
            .then(
                assertReturnValueEquals(new int[0])
            )
            .when(callKoanMethod("fill", 5, 11))
            .then(
                assertReturnValueEquals(new int[]{11, 11, 11, 11, 11})
            ),
        new Koan(CLASS, CREATING_A_SERIE)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("serie", int.class)
            )
            .when(callKoanMethod("serie", 1))
            .then(
                assertReturnValueEquals(new int[]{1})
            )
            .when(callKoanMethod("serie", 0))
            .then(
                assertReturnValueEquals(new int[0])
            )
            .when(callKoanMethod("serie", 5))
            .then(
                assertReturnValueEquals(new int[]{1, 2, 3, 4, 5})
            ),
        new Koan(CLASS, SWITCH_TWO_ELEMENTS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("switchFirst2", int[].class)
            )
            .when(
                assignVariable("a", new int[]{1, 5}),
                callKoanMethod("switchFirst2", variable("a"))
            )
            .then(
                assertVariableEquals("a", new int[]{5, 1})
            )
            .when(
                assignVariable("a", new int[]{1}),
                callKoanMethod("switchFirst2", variable("a"))
            )
            .then(
                assertVariableEquals("a", new int[]{1})
            )
            .when(
                assignVariable("a", new int[]{1, 2, 3}),
                callKoanMethod("switchFirst2", variable("a"))
            )
            .then(
                assertVariableEquals("a", new int[]{1, 2, 3})
            )
            .when(
                assignVariable("a", new int[]{}),
                callKoanMethod("switchFirst2", variable("a"))
            )
            .then(
                assertVariableEquals("a", new int[]{})
            ),
        new Koan(CLASS, REVERSE_AN_ARRAY)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("reverse", int[].class)
            )
            .when(
                assignVariable("a", new int[]{1, 5, 8}),
                callKoanMethod("reverse", variable("a"))
            )
            .then(
                assertReturnValueEquals(new int[]{8, 5, 1}),
                assertVariableEquals("a", new int[]{1, 5, 8})
            )
            .when(
                assignVariable("a", new int[]{1}),
                callKoanMethod("reverse", variable("a"))
            )
            .then(
                assertReturnValueEquals(new int[]{1}),
                assertVariableEquals("a", new int[]{1})
            )
            .when(
                assignVariable("a", new int[]{}),
                callKoanMethod("reverse", variable("a"))
            )
            .then(
                assertReturnValueEquals(new int[]{}),
                assertVariableEquals("a", new int[]{})
            )
    );
}
