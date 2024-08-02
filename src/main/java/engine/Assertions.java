package engine;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

import engine.ConsoleFmt.Formats;
import engine.ConsoleFmt.Formatted;
import engine.script.Expression;
import engine.script.Type;

import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;
import static engine.Texts.*;

/**
 * Library of various assertions which can be run either before the koan test runs or about the result of a koan execution.
 */
public class Assertions {
    private static String resolveTemplateParam(final KoanResult res, final Object param) {
        if (param instanceof final FormatParam fp) {
            return fp.format(res);
        }

        return Optional.ofNullable(param).map((v) -> v.toString()).orElse("");
    }

    public static ResultAssertion assertIf(final boolean condition, final ResultAssertion inner) {
        return (p, res) -> condition ? inner.validate(p, res) : true;
    }

    public static ResultAssertion assertNextStdOutLineEquals(final Localizable<String> expectedTemplate, final Object... params) {
        return (p, res) -> {
            final var realParams = Arrays.stream(params)
                .map((param) -> Assertions.resolveTemplateParam(res, param))
                .toArray();
            final var expected = String.format(expectedTemplate.get(res.locale), realParams);
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(format(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING, Formats.Red, expected, code(res.resultExpressionSourceCode)));
                return false;
            }
            if (!lineContent.get().equals(expected)) {
                if (lineContent.get().equals("")) {
                    p.println(format(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING, Formats.Red, expected, code(res.resultExpressionSourceCode)));
                } else {
                    p.println(format(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD, Formats.Red, expected, code(res.resultExpressionSourceCode), lineContent.get()));
                }
                return false;
            }

            p.println(format(OK_DISPLAYED_IN_CONSOLE, Formats.Green, expected, code(res.resultExpressionSourceCode)));
            return true;
        };
    }

    public static ResultAssertion assertNextStdOutLineIsEmpty() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(ConsoleFmt.red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING));
                return false;
            }
            if (!lineContent.get().equals("")) {
                p.println(ConsoleFmt.red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_INSTEAD), lineContent.get());
                return false;
            }

            p.println(ConsoleFmt.green(OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE));
            return true;
        };
    }

    public static ResultAssertion assertNoMoreLineInStdOut() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (!lineContent.isEmpty()) {
                p.println(format(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD, Formats.Red, code(res.resultExpressionSourceCode), lineContent.get()));
                return false;
            }

            return true;
        };
    }

    public static ResultAssertion assertAskedInStdIn() {
        return (p, res) -> {
            final var lineContent = res.nextStdInLine();

            if (lineContent.isPresent()) {
                p.println(ConsoleFmt.green(OK_ASKED_FOR_LINE_IN_CONSOLE));
                return true;
            }
            p.println(ConsoleFmt.red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
            return false;
        };
    }

    private static final double EPSILON = 0.0000000001;
    private static boolean eq(final Object expected, final Object actual) {
        if (actual == null) {
            return expected == null;
        } else if (expected instanceof final int[] aIntArr && actual instanceof final int[] bIntArr) {
            return Arrays.equals(aIntArr, bIntArr);
        } else if (expected instanceof final Double aDouble && actual instanceof final Double bDouble) {
            final var diff = Math.abs(aDouble.doubleValue() - bDouble);
            return diff < EPSILON;
        }
        return actual.equals(expected);
    }

    public static ResultAssertion assertVariableEquals(final String variableName, final Object expected) {
        return (p, res) -> {
            final Object expectedValue = expected instanceof final Localizable<?> localizable ? localizable.get(res.locale) : expected;
            final Object val = res.executionContext.getVariableValue(variableName);
            final Formatted<String> expectedFmted = code(Expression.formatLiteralSourceCode(expectedValue));
            final Formatted<String> actualFmted = code(Expression.formatLiteralSourceCode(val));
            final Formatted<String> expressionFmted = code(res.resultExpressionSourceCode);
            if (val == null) {
                p.println(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, Formats.Red, expressionFmted, code(variableName), expectedFmted));
                return false;
            } else if (val.getClass() != expectedValue.getClass()) {
                p.println(format(EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE, Formats.Red, expressionFmted, code(variableName), code(expectedValue.getClass().getSimpleName()), code(val.getClass().getSimpleName())));
                return false;
            } else if (!eq(expectedValue, val)) {
                p.println(format(EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, Formats.Red, expressionFmted, code(variableName), expectedFmted, actualFmted));
                return false;
            }

            p.println(format(OK_VARIABLE_EQUAL, Formats.Green, code(variableName), expectedFmted));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final Object expected) {
        return (p, res) -> {
            final Object expectedValue = expected instanceof final Localizable<?> localizable ? localizable.get(res.locale) : expected;
            final Formatted<String> expectedFmted = code(Expression.formatLiteralSourceCode(expectedValue));
            final Formatted<String> actualFmted = code(Expression.formatLiteralSourceCode(res.executionResult));
            final Formatted<String> expressionFmted = code(res.resultExpressionSourceCode);
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, expressionFmted, expectedFmted));
                return false;
            } else if (res.executionResult.getClass() != expectedValue.getClass()) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red, expressionFmted, code(expectedValue.getClass().getSimpleName()), code(res.executionResult.getClass().getSimpleName())));
                return false;
            } else if (!eq(expectedValue, res.executionResult)) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red, expressionFmted, expectedFmted, actualFmted));
                return false;
            }

            p.println(format(OK_RETURNED, Formats.Green, expressionFmted, expectedFmted));
            return true;
        }; 
    }

    /**
     * Since we can't ask students to provide a proper equals() method, it is a bit tricky to assert the content of the objects they create.
     * The idea is to make them create a "toString()" method, and then assert the String representation of those objects.
     */
    public static ResultAssertion assertReturnValueStringRepresentationEquals(final Localizable<String> expected, final String expectedType) {
        return (p, res) -> {
            final Formatted<String> expressionFmted = code(res.resultExpressionSourceCode);
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, expressionFmted, expected.get(res.locale)));
                return false;
            } else if (!res.executionResult.getClass().getName().equals(expectedType)) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red, expressionFmted, code(expectedType), code(res.executionResult.getClass().getSimpleName())));
                return false;
            } else if (!res.executionResult.toString().equals(expected.get(res.locale))) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red, expressionFmted, expected.get(res.locale), res.executionResult.toString()));
                return false;
            }

            p.println(format(OK_RETURNED, Formats.Green, expressionFmted, code(expected.get(res.locale))));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueWithRandomEquals(final DoubleToIntFunction expected) {
        return (p, res) -> {
            final var randomNumber = res.randomNumber();
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected.applyAsInt(randomNumber)));
                return false;
            } else if (!(res.executionResult instanceof Integer)) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE, Formats.Red, res.resultExpressionSourceCode, res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (((Integer)res.executionResult).intValue() != expected.applyAsInt(randomNumber)) {
                p.println(
                    format(
                        EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED,
                        Formats.Red, 
                        code(res.resultExpressionSourceCode),
                        code(expected.applyAsInt(randomNumber)),
                        randomNumber,
                        code(res.executionResult.toString())
                    )
                );
                return false;
            }

            p.println(format(OK_RETURNED_INT_FROM_RANDOM, Formats.Green, code(res.resultExpressionSourceCode), code(expected.applyAsInt(randomNumber)), randomNumber));
            return true;
        }; 
    }

    /**
     * Assert the result of a method using random numbers.
     * @param count the number of random numbers expected to be generated
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static ResultAssertion assertReturnValueWithRandomEquals(final int count, final ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(count), buildExpected);
    }

    /**
     * Assert the result of a method using random numbers.
     * @param fromOffset the start of the random numbers sequence to pick
     * @param count the number of random numbers to pick
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static ResultAssertion assertReturnValueWithRandomEquals(final int fromOffset, final int count, final ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(fromOffset, count), buildExpected);
    }

    private static ResultAssertion assertReturnValueWithRandomEquals(final Function<KoanResult, double[]> randomNumbersFunc, final ResToIntFunction buildExpected) {
        return (p, res) -> {
            final var randomNumbers = randomNumbersFunc.apply(res);
            final var formatRandomNumbers = Helpers.formatSequence(res.locale, randomNumbers);

            final int expected = buildExpected.apply(res);
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected));
                return false;
            } else if (!(res.executionResult instanceof Integer)) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE, Formats.Red, res.resultExpressionSourceCode, res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (((Integer)res.executionResult).intValue() != expected) {
                p.println(
                    format(
                        EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED, 
                        Formats.Red, 
                        code(res.resultExpressionSourceCode),
                        code(expected),
                        formatRandomNumbers,
                        code(res.executionResult.toString())
                    )
                );
                return false;
            }

            p.println(format(OK_RETURNED_INT_FROM_RANDOMS, Formats.Green, res.resultExpressionSourceCode, expected, formatRandomNumbers));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueIsAnonymousObject() {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_NULL, Formats.Red, code(res.resultExpressionSourceCode)));
                return false;
            } else if (res.executionResult.getClass().getSimpleName().contains("$$Lambda$")) { // Kind of hacky, but only way as far as I know
                p.println(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_LAMBDA, Formats.Red, code(res.resultExpressionSourceCode)));
                return false;
            } else if (!res.executionResult.getClass().isAnonymousClass()) {
                p.println(format(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED, Formats.Red, code(res.resultExpressionSourceCode), code(res.executionResult.getClass().getName())));
                return false;
            }

            p.println(format(OK_RETURNED_OBJECT_IS_ANONYMOUS, Formats.Green, code(res.resultExpressionSourceCode)));
            return true;
        };
    }

    public static ResultAssertion assertReturnValueIsLambda() {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_NULL, Formats.Red, code(res.resultExpressionSourceCode)));
                return false;
            } else if (res.executionResult.getClass().isAnonymousClass()) {
                p.println(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_ANONYMOUS, Formats.Red, code(res.resultExpressionSourceCode)));
                return false;
            } else if (!res.executionResult.getClass().getSimpleName().contains("$$Lambda")) { // Kind of hacky, but only way as far as I know
                p.println(format(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED, Formats.Red, code(res.resultExpressionSourceCode), code(res.executionResult.getClass().getName())));
                return false;
            }

            p.println(format(OK_RETURNED_OBJECT_IS_LAMBDA, Formats.Green, code(res.resultExpressionSourceCode)));
            return true;
        };
    }

    public static ResultAssertion assertReturnValueImplements(Class<?> valueInterface) {
        return (p, res) -> {
            final var interfaceName = code(valueInterface.getName());
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_RETURNED_NULL, Formats.Red, code(res.resultExpressionSourceCode), interfaceName));
                return false;
            } else if (!valueInterface.isInstance(res.executionResult)) {
                p.println(format(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_NOT, Formats.Red, code(res.resultExpressionSourceCode), interfaceName, code(res.executionResult.getClass().getName())));
                return false;
            }

            p.println(format(OK_RETURNED_OBJECT_IMPLEMENTS, Formats.Green, code(res.resultExpressionSourceCode), interfaceName));
            return true;
        };
    }
    
    public static BeforeTestAssertion assertConstructorIsInvokable(final String className, final Type... constructorParamTypes) {
        return (p, locale, koan) -> {
            final var type = new Type(className);

            if (!assertCanInstantiateClass(global(type)).validate(p, locale, koan)) {
                return false;
            }

            try {
                final var clasz = type.unsafeResolve();
                final var constructor = clasz.getConstructor(Type.unsafeResolveTypes(constructorParamTypes));
                if (!Modifier.isPublic(constructor.getModifiers())) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_CONSTRUCTOR_TO_BE_PUBLIC),
                        type.simpleClassName
                    );
                }
            }
            catch(NoSuchMethodException nsme) {
                if (constructorParamTypes.length == 0) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS),
                        type.simpleClassName
                    );
                } else if (constructorParamTypes.length == 1) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM),
                        type.simpleClassName,
                        constructorParamTypes[0]
                    );
                } else {
                    final var expectedParams = Arrays
                        .stream(constructorParamTypes)
                        .map(t -> "'" + t + "'")
                        .toArray(String[]::new);
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS),
                        type.simpleClassName,
                        Helpers.formatSequence(locale, expectedParams)
                    );
                }
                return false;
            }

            return true;
        };
    }

    public static BeforeTestAssertion assertCanInstantiateClass(final Localizable<Type> type) {
        return (p, locale, _koan) -> {
            final var actualType = type.get(locale);
            try {
                final var clasz = actualType.resolve();
                if (!Type.isInstantiable(clasz)) {
                    p.println(ConsoleFmt.red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), actualType.simpleClassName);
                    return false;
                }
                return true;
            } catch (final ClassNotFoundException _cnfe) {
                p.println(ConsoleFmt.red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), actualType.simpleClassName,actualType.packageName);
                return false;
            }
        };
    }

    /**
     * This asserts that the method in the current Koan class is invokable with the given param types.
     * 
     * Note: koan methods should always be static. It would be weird to ask the student to make the Koan class itself to be instantiable.
     */
    public static BeforeTestAssertion assertKoanMethodIsInvokable(final String methodName, final Class<?>... paramTypes) {
        return (p, locale, koan) -> assertMethodIsInvokable(koan.koanClass, methodName, true, Type.toTypes(paramTypes)).validate(p, locale, koan);
    }

    public static BeforeTestAssertion assertStaticMethodIsInvokable(final String className, final String methodName, final Class<?>... paramTypes) {
        return assertMethodIsInvokable(global(new Type(className)), methodName, true, Type.toTypes(paramTypes));
    }

    public static BeforeTestAssertion assertObjectMethodIsInvokable(final String className, final String methodName, final Class<?>... paramTypes) {
        return assertMethodIsInvokable(global(new Type(className)), methodName, false, Type.toTypes(paramTypes));
    }

    private static BeforeTestAssertion assertMethodIsInvokable(final Localizable<Type> type, final String methodName, final boolean isStatic, final Type... paramTypes) {
        return (p, locale, koan) -> {

            if (!assertCanInstantiateClass(type).validate(p, locale, koan)) {
                return false;
            }

            final var clasz = type.get(locale).unsafeResolve();
            final var methodParamClasses = Type.unsafeResolveTypes(paramTypes);

            try {
                final var method = clasz.getMethod(methodName, methodParamClasses);
                if (isStatic && !Modifier.isStatic(method.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_METHOD_TO_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!isStatic && Modifier.isStatic(method.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_METHOD_TO_NOT_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_METHOD_TO_BE_PUBLIC),
                        methodName
                    );
                }
            }
            catch(final NoSuchMethodException _nsme) {
                if (methodParamClasses.length == 0) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS),
                        methodName,
                        clasz.getName().replace(".", "/")
                    );
                } else if (methodParamClasses.length == 1) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM),
                        methodName,
                        clasz.getName().replace(".", "/"),
                        methodParamClasses[0].getSimpleName()
                    );
                } else {
                    final var expectedParams = Arrays
                        .stream(methodParamClasses)
                        .map(t -> "'" + t.getSimpleName() + "'")
                        .toArray(String[]::new);
                    p.println(
                        ConsoleFmt.red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS),
                        methodName,
                        clasz.getName().replace(".", "/"),
                        Helpers.formatSequence(locale, expectedParams)
                    );
                }
                return false;
            }

            return true;
        };
    }  

        
    public static BeforeTestAssertion assertPrivateFinalField(final String className, final String fieldName, final Type fieldType) {
        return assertField(className, fieldName, true, fieldType);
    }

    public static BeforeTestAssertion assertPrivateField(final String className, final String fieldName, final Type fieldType) {
        return assertField(className, fieldName, false, fieldType);
    }

    private static BeforeTestAssertion assertField(final String className, final String fieldName, final Boolean isFinal, final Type fieldType) {
        return (p, locale, koan) -> {
            final var type = new Type(className);

            try {
                final var clasz = type.unsafeResolve();
                final var field = clasz.getDeclaredField(fieldName);
                if (!Modifier.isPrivate(field.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_FIELD_TO_BE_PRIVATE), fieldName, className);
                    return false;
                }
                if (isFinal && !Modifier.isFinal(field.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_FIELD_TO_BE_FINAL), fieldName, className);
                    return false;
                } else if (!isFinal && Modifier.isFinal(field.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_FIELD_TO_NOT_BE_FINAL), fieldName, className);
                    return false;
                }
                if (!field.getType().equals(fieldType.unsafeResolve())) {
                    p.println(ConsoleFmt.red(EXPECTED_FIELD_TO_BE_OF_TYPE), fieldName, className, fieldType, field.getType().getSimpleName());
                    return false;
                }
            }
            catch(NoSuchFieldException nsfe) {
                p.println(ConsoleFmt.red(EXPECTED_TO_FIND_FIELD_IN_CLASS), fieldName, className);
                return false;
            }

            return true;
        };
    } 

    public static BeforeTestAssertion assertImplementsInterface(final String className, final Class<?> interfaceClass) {
        return (p, locale, koan) -> {
            final var type = new Type(className);
            final var clasz = type.unsafeResolve();
            final var success = interfaceClass.isAssignableFrom(clasz);

            if (!success) {
                    p.println(format(EXPECTED_CLASS_TO_IMPLEMENT, Formats.Red, code(className), code(interfaceClass.getName())));
            }

            return success;
        };
    } 
}
