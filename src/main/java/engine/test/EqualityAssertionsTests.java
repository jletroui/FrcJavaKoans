package engine.test;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertVariableEquals;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;

import engine.Koan;
import engine.Localizable;
import engine.TestSensei;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.assignVariable;
import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;
import static engine.Texts.*;

public class EqualityAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static void whenAssertIntArrayVariableAndValueIsEqual() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayVariableAndValueIsEqual"))
                .when(
                    assignVariable("a", new int[]{1, 3})
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 3})
                )
        );

        assertKoanPass(
            res[0],
            new Line(format(OK_VARIABLE_EQUAL, Formats.Green, code("a"), code("new int[]{1,3}")))
        );
    }

    public static void whenAssertVariableNotEqualsIntArray() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayVariableAndValueIsNotEqual"))
                .when(
                    assignVariable("a", new int[]{1, 3})
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, Formats.Red, code("var a = new int[]{1,3}"), code("a"), code("new int[]{1,2}"), code("new int[]{1,3}")))
        );
    }

    public static void whenAssertIntArrayVariableAndValueIsNotAIntArray() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayVariableAndValueIsNotAIntArray"))
                .when(
                    assignVariable("a", "abc")
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE, Formats.Red, code("var a = \"abc\""), code("a"), code("int[]"), code("String")))
        );
    }

    public static void whenAssertIntArrayVariableAndValueIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayVariableAndValueIsNull"))
                .when(
                    assignVariable("a", null)
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, Formats.Red, code("var a = null"), code("a"), code("new int[]{1,2}")))
        );
    }

    public static void whenAssertIntArrayMethodReturnValueAndReturnedValueIsEqual() {
        var res = TestSensei.execute(
             new Koan(CLASS, global("whenAssertIntArrayMethodReturnValueAndReturnedValueIsEqual"))
                .when(
                    callKoanMethod("returnedValueEqualsIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                )
        );

        assertKoanPass(
            res[0],
            new Line(format(OK_RETURNED, Formats.Green, code("returnedValueEqualsIntArray()"), code("new int[]{1,3}")))
        );
    }

    public static void whenAssertIntArrayMethodReturnValueAndReturnedValueIsNotEqual() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayMethodReturnValueAndReturnedValueIsNotEqual"))
                .when(
                    callKoanMethod("returnedValueNotEqualsIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red, code("returnedValueNotEqualsIntArray()"), code("new int[]{1,3}"), code("new int[]{1,2}")))
        );
    }

    public static void whenAssertIntArrayMethodReturnValueAndReturnedValueIsNotAnIntArray() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayMethodReturnValueAndReturnedValueIsNotAnIntArray"))
                .when(
                    callKoanMethod("returnedValueNotIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red, code("returnedValueNotIntArray()"), code("int[]"), code("String")))
        );
    }

    public static void whenAssertIntArrayMethodReturnValueAndReturnedValueIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertIntArrayMethodReturnValueAndReturnedValueIsNull"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, code("returnedValueNull()"), code("new int[]{1,3}")))
        );
    }
}
