package engine.test;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueStringRepresentationEquals;
import static engine.Assertions.assertVariableEquals;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;
import static engine.Localizable.local;

import engine.Koan;
import engine.Locale;
import engine.Localizable;
import engine.TestSensei;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;

import static engine.script.Expression.assignVariable;
import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;

import java.util.List;

import static engine.Texts.*;

public class EqualityAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(EqualityAssertionsTests.class);
    private record ValueData(String methodName, Object expected, Object returnedNotEquals, String codeExpected, String codeNotEquals) {
        Object actual() {
            return expected instanceof final Localizable<?> localizable ? localizable.get(Locale.en) : expected;
        }
    }
    private static final List<ValueData> VALUE_EXAMPLES = List.of(
        new ValueData("returnInt", 3, 4, "3", "4"),
        new ValueData("returnDouble", 3.0, 4.0, "3.0", "4.0"),
        new ValueData("returnBoolean", false, true, "false", "true"),
        new ValueData("returnString", "abc", "def", "\"abc\"", "\"def\""),
        new ValueData("returnString", local("english").fr("french"), "english2", "\"english\"", "\"english2\""),
        new ValueData("returnIntArray", new int[]{1, 3}, new int[]{1, 2}, "new int[]{1,3}", "new int[]{1,2}")
    );
    public static Object returnNull() {
        return null;
    }
    public static int returnInt(int val) {
        return val;
    }
    public static double returnDouble(double val) {
        return val;
    }
    public static boolean returnBoolean(boolean val) {
        return val;
    }
    public static String returnString(String val) {
        return val;
    }
    public static int[] returnIntArray(int[] val) {
        return val;
    }

    public static void whenAssertVariableEquals() {
        for(int i=0; i<VALUE_EXAMPLES.size(); i++) {
            final var val = VALUE_EXAMPLES.get(i);
            var res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertVariableEqualsAndValueIsEqual"))
                    .when(
                        assignVariable("a", val.actual())
                    )
                    .then(
                        assertVariableEquals("a", val.expected)
                    )
            );

            assertKoanPass(
                res[0],
                new Line(format(OK_VARIABLE_EQUAL, Formats.Green, code("a"), code(val.codeExpected)))
            );

            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertVariableEqualsAndValueIsNotEqual"))
                    .when(
                        assignVariable("a", val.returnedNotEquals)
                    )
                    .then(
                        assertVariableEquals("a", val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(
                    EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, Formats.Red,
                    code("var a = " + val.codeNotEquals),
                    code("a"),
                    code(val.codeExpected),
                    code(val.codeNotEquals)
                ))
            );

            final var otherTypeVal = VALUE_EXAMPLES.get(i == 0 ? 1 : 0);
            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertVariableEqualsAndValueIsNotRightType"))
                    .when(
                        assignVariable("a", otherTypeVal.actual())
                    )
                    .then(
                        assertVariableEquals("a", val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(
                    EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE, Formats.Red,
                    code("var a = " + otherTypeVal.codeExpected),
                    code("a"),
                    code(val.actual().getClass().getSimpleName()),
                    code(otherTypeVal.expected.getClass().getSimpleName())
                ))
            );

            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertVariableEqualsAndValueIsNull"))
                    .when(
                        assignVariable("a", null)
                    )
                    .then(
                        assertVariableEquals("a", val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, Formats.Red, code("var a = null"), code("a"), code(val.codeExpected)))
            );            
        }
    }

    public static void whenAssertVariableEqualsOnAlmostEqualsDouble() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertVariableEqualsOnAlmostEqualsDouble"))
                .when(
                    assignVariable("a", 3.00000000001)
                )
                .then(
                    assertVariableEquals("a", 3.0)
                )
        );

        assertKoanPass(
            res[0],
            new Line(format(OK_VARIABLE_EQUAL, Formats.Green, code("a"), code("3.0")))
        );

        res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertVariableEqualsOnAlmostEqualsDouble"))
                .when(
                    assignVariable("a", 3.0000000001)
                )
                .then(
                    assertVariableEquals("a", 3.0)
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(
                EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, Formats.Red,
                code("var a = 3.0000000001"),
                code("a"),
                code("3.0"),
                code("3.0000000001")
            ))
        );

    }

    public static void whenAssertReturnValueEquals() {
        for(int i=0; i<VALUE_EXAMPLES.size(); i++) {
            final var val = VALUE_EXAMPLES.get(i);
            var res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertReturnValueEqualsAndValueIsEqual"))
                    .when(
                        callKoanMethod(val.methodName, val.actual())
                    )
                    .then(
                        assertReturnValueEquals(val.expected)
                    )
            );

            assertKoanPass(
                res[0],
                new Line(format(OK_RETURNED, Formats.Green, code(callKoanMethod(val.methodName, val.actual()).formatSourceCode(Locale.en)), code(val.codeExpected)))
            );

            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertReturnValueEqualsAndValueIsNotEqual"))
                    .when(
                        callKoanMethod(val.methodName, val.returnedNotEquals)
                    )
                    .then(
                        assertReturnValueEquals(val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(
                    EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red,
                    code(callKoanMethod(val.methodName, val.returnedNotEquals).formatSourceCode(Locale.en)),
                    code(val.codeExpected),
                    code(val.codeNotEquals)
                ))
            );

            final var otherTypeVal = VALUE_EXAMPLES.get(i == 0 ? 1 : 0);
            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertReturnValueEqualsAndValueIsNotRightType"))
                    .when(
                        callKoanMethod(otherTypeVal.methodName, otherTypeVal.actual())
                    )
                    .then(
                        assertReturnValueEquals(val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(
                    EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red,
                    code(callKoanMethod(otherTypeVal.methodName, otherTypeVal.actual()).formatSourceCode(Locale.en)),
                    code(val.actual().getClass().getSimpleName()),
                    code(otherTypeVal.expected.getClass().getSimpleName())
                ))
            );

            res = TestSensei.execute(
                new Koan(CLASS, global("whenAssertReturnValueEqualsAndValueIsNull"))
                    .when(
                        callKoanMethod("returnNull")
                    )
                    .then(
                        assertReturnValueEquals(val.expected)
                    )
            );
            
            assertKoanFails(
                res[0],
                new Line(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, code("returnNull()"), code(val.codeExpected)))
            );            
        }
    }

    public static void whenAssertReturnValueEqualsOnAlmostEqualsDouble() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueEqualsOnAlmostEqualsDouble"))
                .when(
                    callKoanMethod("returnDouble", 3.00000000001)
                )
                .then(
                    assertReturnValueEquals(3.0)
                )
        );

        assertKoanPass(
            res[0],
            new Line(format(OK_RETURNED, Formats.Green, code("returnDouble(3.00000000001)"), code("3.0")))
        );

        res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueEqualsOnAlmostEqualsDouble"))
                .when(
                    callKoanMethod("returnDouble", 3.0000000001)
                )
                .then(
                    assertReturnValueEquals(3.0)
                )
        );
        
        assertKoanFails(
            res[0],
            new Line(format(
                EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red,
                code("returnDouble(3.0000000001)"),
                code("3.0"),
                code("3.0000000001")
            ))
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItDoes() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItDoes"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "java.lang.Double")
                )
        );

        assertKoanPass(
            res[0],
            new Line(format(OK_RETURNED, Formats.Green, code("returnDouble(7.5)"), code("7.5")))
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItDoesNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItDoesNot"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("8.5"), "java.lang.Double")
                )
        );

        assertKoanFails(
            res[0],
            new Line(format(
                EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red,
                code("returnDouble(7.5)"),
                8.5,
                "7.5"
            ))
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItIsOtherType() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "Integer")
                )
        );

        assertKoanFails(
            res[0],
            new Line(format(
                EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red,
                code("returnDouble(7.5)"),
                code("Integer"),
                code("Double")
            ))
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnNull")
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "Double")
                )
        );

        assertKoanFails(
            res[0],
            new Line(format(
                EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red,
                code("returnNull()"),
                "7.5"
            ))
        );
    }        
}
