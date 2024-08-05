package engine.test;

import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueStringRepresentationEquals;
import static engine.Assertions.assertReturnValueWithRandomEquals;
import static engine.Assertions.assertVariableEquals;
import static engine.Fmt.classSimpleName;
import static engine.Fmt.code;
import static engine.Fmt.green;
import static engine.Fmt.red;
import static engine.Fmt.sameStyle;
import static engine.Fmt.sequence;
import static engine.Localizable.global;
import static engine.Localizable.local;

import engine.Style;
import engine.Helpers;
import engine.Koan;
import engine.Locale;
import engine.Localizable;
import engine.ResToIntFunction;
import engine.TestSensei;

import static engine.script.Expression.assignVariable;
import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.test.runner.RunnerAssertions.assertKoanPass;

import java.util.List;
import java.util.function.DoubleToIntFunction;
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
    private static final DoubleToIntFunction EXAMPLE_RANDOM_LOGIC = randomNumber -> (int)Math.floor(randomNumber * 1000);
    private static final ResToIntFunction EXAMPLE_MULTIPLE_RANDOM_LOGIC = res -> EXAMPLE_RANDOM_LOGIC.applyAsInt(res.randomNumber(0)) + EXAMPLE_RANDOM_LOGIC.applyAsInt(res.randomNumber(1));

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
    public static int returnRandom() {
        final var r = Helpers.random();
        final var res = EXAMPLE_RANDOM_LOGIC.applyAsInt(r);
        return res;
    }
    public static int returnRandoms() {
        final var r1 = returnRandom();
        final var r2 = returnRandom();
        return r1 + r2;
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
                green(OK_VARIABLE_EQUAL, code("a"), code(val.codeExpected))
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
                red(
                    EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL,
                    code("var a = " + val.codeNotEquals),
                    code("a"),
                    code(val.codeExpected),
                    code(val.codeNotEquals)
                )
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
                red(
                    EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE,
                    code("var a = " + otherTypeVal.codeExpected),
                    code("a"),
                    classSimpleName(val.actual().getClass()),
                    classSimpleName(otherTypeVal.expected.getClass())
                )
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
                red(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, code("var a = null"), code("a"), code(val.codeExpected))
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
            green(OK_VARIABLE_EQUAL, code("a"), code("3.0"))
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
            red(
                EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL,
                code("var a = 3.0000000001"),
                code("a"),
                code("3.0"),
                code("3.0000000001")
            )
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
                green(OK_RETURNED, code(callKoanMethod(val.methodName, val.actual()).formatSourceCode(Locale.en)), code(val.codeExpected))
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
                red(
                    EXPECTED_TO_RETURN_BUT_RETURNED,
                    code(callKoanMethod(val.methodName, val.returnedNotEquals).formatSourceCode(Locale.en)),
                    code(val.codeExpected),
                    code(val.codeNotEquals)
                )
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
                red(
                    EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE,
                    code(callKoanMethod(otherTypeVal.methodName, otherTypeVal.actual()).formatSourceCode(Locale.en)),
                    classSimpleName(val.actual().getClass()),
                    classSimpleName(otherTypeVal.expected.getClass())
                )
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
                red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, code("returnNull()"), code(val.codeExpected))
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
            green(OK_RETURNED, code("returnDouble(3.00000000001)"), code("3.0"))
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
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED,
                code("returnDouble(3.0000000001)"),
                code("3.0"),
                code("3.0000000001")
            )
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItDoes() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItDoes"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "double")
                )
        );

        assertKoanPass(
            res[0],
            green(OK_RETURNED, code("returnDouble(7.5)"), code("7.5"))
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItDoesNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItDoesNot"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("8.5"), "double")
                )
        );

        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED,
                code("returnDouble(7.5)"),
                sameStyle("8.5"),
                sameStyle("7.5")
            )
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItIsOtherType() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "int")
                )
        );

        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE,
                code("returnDouble(7.5)"),
                code("int"),
                code("double")
            )
        );
    }

    public static void whenAssertReturnValueStringRepresentationEqualsAndItIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("assertReturnValueStringRepresentationEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnNull")
                )
                .then(
                    assertReturnValueStringRepresentationEquals(global("7.5"), "double")
                )
        );

        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_NULL,
                code("returnNull()"),
                sameStyle("7.5")
            )
        );
    }        

    public static void whenAssertReturnValueWithRandomEqualsAndItDoes() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithRandomEqualsAndItDoes"))
                .when(
                    callKoanMethod("returnRandom")
                )
                .then(
                    assertReturnValueWithRandomEquals(EXAMPLE_RANDOM_LOGIC)
                )
        );

        var randomNumber = res[0].koanResult().randomNumber();
        var expected = EXAMPLE_RANDOM_LOGIC.applyAsInt(randomNumber);
        assertKoanPass(
            res[0],
            green(OK_RETURNED_INT_FROM_RANDOM, code("returnRandom()"), code(expected), code(randomNumber))
        );
    }

    public static void whenAssertReturnValueWithRandomEqualsAndItDoesNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithRandomEqualsAndItDoesNot"))
                .when(
                    callKoanMethod("returnInt", 0)
                )
                .then(
                    assertReturnValueWithRandomEquals(EXAMPLE_RANDOM_LOGIC)
                )
        );

        var randomNumber = res[0].koanResult().randomNumber();
        var expected = EXAMPLE_RANDOM_LOGIC.applyAsInt(randomNumber);
        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED,
                code("returnInt(0)"),
                code(expected),
                code(randomNumber),
                code(0)
            )
        );
    }

    public static void whenAssertReturnValueWithRandomEqualsAndItIsOtherType() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithRandomEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueWithRandomEquals(EXAMPLE_RANDOM_LOGIC)
                )
        );

        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE,
                code("returnDouble(7.5)"),
                code("int"),
                code("double")
            )
        );
    }

    public static void whenAssertReturnValueWithRandomEqualsAndItIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithRandomEqualsAndItIsNull"))
                .when(
                    callKoanMethod("returnNull")
                )
                .then(
                    assertReturnValueWithRandomEquals(EXAMPLE_RANDOM_LOGIC)
                )
        );

        var randomNumber = res[0].koanResult().randomNumber();
        var expected = EXAMPLE_RANDOM_LOGIC.applyAsInt(randomNumber);
        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_NULL,
                code("returnNull()"),
                code(expected)
            )
        );
    }     

    public static void whenAssertReturnValueWithMultipleRandomEqualsAndItDoes() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithMultipleRandomEqualsAndItDoes"))
                .when(
                    callKoanMethod("returnRandoms")
                )
                .then(
                    assertReturnValueWithRandomEquals(2, EXAMPLE_MULTIPLE_RANDOM_LOGIC)
                )
        );

        var randomNumbers = res[0].koanResult().randomNumbers(2);
        var expected = EXAMPLE_MULTIPLE_RANDOM_LOGIC.apply(res[0].koanResult());
        assertKoanPass(
            res[0],
            green(OK_RETURNED_INT_FROM_RANDOMS, code("returnRandoms()"), code(expected), sequence(randomNumbers, Style.Code))
        );
    }

    public static void whenAssertReturnValueWithMultipleRandomEqualsAndItDoesNot() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithMultipleRandomEqualsAndItDoesNot"))
                .when(
                    callKoanMethod("returnInt", 0)
                )
                .then(
                    assertReturnValueWithRandomEquals(2, EXAMPLE_MULTIPLE_RANDOM_LOGIC)
                )
        );

        var randomNumbers = res[0].koanResult().randomNumbers(2);
        var expected = EXAMPLE_MULTIPLE_RANDOM_LOGIC.apply(res[0].koanResult());
        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED,
                code("returnInt(0)"),
                code(expected),
                sequence(randomNumbers, Style.Code),
                code(0)
            )
        );
    }

    public static void whenAssertReturnValueWithMultipleRandomEqualsAndItIsOtherType() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithMultipleRandomEqualsAndItIsOtherType"))
                .when(
                    callKoanMethod("returnDouble", 7.5)
                )
                .then(
                    assertReturnValueWithRandomEquals(2, EXAMPLE_MULTIPLE_RANDOM_LOGIC)
                )
        );

        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE,
                code("returnDouble(7.5)"),
                code("int"),
                code("double")
            )
        );
    }

    public static void whenAssertReturnValueWithMultipleRandomEqualsAndItIsNull() {
        var res = TestSensei.execute(
            new Koan(CLASS, global("whenAssertReturnValueWithMultipleRandomEqualsAndItIsNull"))
                .when(
                    callKoanMethod("returnNull")
                )
                .then(
                    assertReturnValueWithRandomEquals(2, EXAMPLE_MULTIPLE_RANDOM_LOGIC)
                )
        );

        var expected = EXAMPLE_MULTIPLE_RANDOM_LOGIC.apply(res[0].koanResult());
        assertKoanFails(
            res[0],
            red(
                EXPECTED_TO_RETURN_BUT_RETURNED_NULL,
                code("returnNull()"),
                code(expected)
            )
        );
    }        
}
