package engine.test;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertVariableEquals;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;

import java.util.List;

import engine.Koan;
import engine.Locale;
import engine.Localizable;
import engine.ConsoleFmt.Formats;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.assignVariable;
import static engine.script.Expression.callKoanMethod;
import static engine.Texts.*;
import static engine.test.UnitTestExpectation.assertSuccess;
import static engine.test.UnitTestExpectation.assertFailure;

public class EqualityAssertionsUnitTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<UnitTest> items = List.of(
        new UnitTest(
            new Koan(CLASS, global("variableEqualsIntArray"))
                .when(
                    assignVariable("a", new int[]{1, 3})
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 3})
                ),
            assertSuccess(
                new Line.Localized(format(OK_VARIABLE_EQUAL, Formats.Green, code("a"), code("new int[]{1,3}")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("variableNotEqualsIntArray"))
                .when(
                    assignVariable("a", new int[]{1, 3})
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, Formats.Red, code(assignVariable("a", new int[]{1, 3}).formatSourceCode(Locale.en)), code("a"), code("new int[]{1,2}"), code("new int[]{1,3}")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("variableNotIntArray"))
                .when(
                    assignVariable("a", "abc")
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE, Formats.Red, code(assignVariable("a", "abc").formatSourceCode(Locale.en)), code("a"), code("int[]"), code("String")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("variableNull"))
                .when(
                    assignVariable("a", null)
                )
                .then(
                    assertVariableEquals("a", new int[]{1, 2})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, Formats.Red, code(assignVariable("a", null).formatSourceCode(Locale.en)), code("a"), code("new int[]{1,2}")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("returnedValueEqualsIntArray"))
                .when(
                    callKoanMethod("returnedValueEqualsIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                ),
            assertSuccess(
                new Line.Localized(format(OK_RETURNED, Formats.Green, code(callKoanMethod("returnedValueEqualsIntArray").formatSourceCode(Locale.en)), code("new int[]{1,3}")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("returnedValueNotEqualsIntArray"))
                .when(
                    callKoanMethod("returnedValueNotEqualsIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red, code(callKoanMethod("returnedValueNotEqualsIntArray").formatSourceCode(Locale.en)), code("new int[]{1,3}"), code("new int[]{1,2}")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("returnedValueNotIntArray"))
                .when(
                    callKoanMethod("returnedValueNotIntArray")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(callKoanMethod("returnedValueNotIntArray").formatSourceCode(Locale.en)), code("int[]"), code("String")))
            )
        ),
        new UnitTest(
            new Koan(CLASS, global("returnedValueNull"))
                .when(
                    callKoanMethod("returnedValueNull")
                )
                .then(
                    assertReturnValueEquals(new int[]{1, 3})
                ),
            assertFailure(
                new Line.Localized(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, code(callKoanMethod("returnedValueNull").formatSourceCode(Locale.en)), code("new int[]{1,3}")))
            )
        )    
    );
}
