package sensei;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static sensei.Texts.*;


import java.util.List;

import engine.Koan;
import engine.Localizable;

public class AboutConditionsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutConditions.class);
        
    public static final List<Koan> koans = List.of(
        new Koan(CLASS, IF_CONSTRUCT_AND_CONDITIONS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("sign", int.class)
            )
            .when(callKoanMethod("sign", 2))
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .when(callKoanMethod("sign", 0))
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .when(callKoanMethod("sign", -2))
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, ELSE_IF_CONSTRUCT)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("signOrZero", int.class)
            )
            .when(callKoanMethod("signOrZero", 2))
            .then(
                assertReturnValueEquals(POSITIVE)
            )
            .when(callKoanMethod("signOrZero", 0))
            .then(
                assertReturnValueEquals(ZERO)
            )
            .when(callKoanMethod("signOrZero", -2))
            .then(
                assertReturnValueEquals(NEGATIVE)
            ),
        new Koan(CLASS, AN_EXAM_S_GRADE)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gradeComment", int.class)
            )
            .when(callKoanMethod("gradeComment", 0))
            .then(
                assertReturnValueEquals(OOPS)
            )
            .when(callKoanMethod("gradeComment", 1))
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .when(callKoanMethod("gradeComment", 59))
            .then(
                assertReturnValueEquals(YOU_FAILED)
            )
            .when(callKoanMethod("gradeComment", 60))
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .when(callKoanMethod("gradeComment", 99))
            .then(
                assertReturnValueEquals(YOU_PASS)
            )            
            .when(callKoanMethod("gradeComment", 100))
            .then(
                assertReturnValueEquals(GOT_EVERYTHING_RIGHT)
            )            
            .when(callKoanMethod("gradeComment", 101))
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
            .when(callKoanMethod("gradeComment", -1))
            .then(
                assertReturnValueEquals(YOU_CHEAT)
            )            
    );
}
