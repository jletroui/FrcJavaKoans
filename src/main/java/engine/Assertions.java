package engine;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;

import engine.script.Expression;
import engine.script.Type;

import static engine.Fmt.classFullName;
import static engine.Fmt.classSimpleName;
import static engine.Fmt.code;
import static engine.Fmt.green;
import static engine.Fmt.red;
import static engine.Fmt.sameStyle;
import static engine.Fmt.sequence;
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
                .toArray(String[]::new);
            final var realParamsFmted = Arrays
                .stream(realParams)
                .map(param -> sameStyle(global(param)))
                .toArray(Fmt[]::new);
            final var expected = String.format(expectedTemplate.get(res.locale), (Object[])realParams);
            final var expectedFmted = sameStyle(expectedTemplate, realParamsFmted);
            final var lineContent = res.nextStdOutLine();
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);

            if (lineContent.isEmpty()) {
                p.println(red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING, expectedFmted, expressionFmted));
                return false;
            }
            if (!lineContent.get().equals(expected)) {
                if (lineContent.get().equals("")) {
                    p.println(red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING, expectedFmted, expressionFmted));
                } else {
                    p.println(red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD, expectedFmted, expressionFmted, sameStyle(lineContent.get())));
                }
                return false;
            }

            p.println(green(OK_DISPLAYED_IN_CONSOLE, expectedFmted, expressionFmted));
            return true;
        };
    }

    public static ResultAssertion assertNextStdOutLineIsEmpty() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING));
                return false;
            }
            if (!lineContent.get().equals("")) {
                p.println(red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_INSTEAD, sameStyle(lineContent.get())));
                return false;
            }

            p.println(green(OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE));
            return true;
        };
    }

    public static ResultAssertion assertNoMoreLineInStdOut() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (!lineContent.isEmpty()) {
                p.println(red(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD, code(res.resultExpressionSourceCode), sameStyle(lineContent.get())));
                return false;
            }

            return true;
        };
    }

    public static ResultAssertion assertAskedInStdIn() {
        return (p, res) -> {
            final var lineContent = res.nextStdInLine();

            if (lineContent.isPresent()) {
                p.println(green(OK_ASKED_FOR_LINE_IN_CONSOLE));
                return true;
            }
            p.println(red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
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
            final Object val = res.executionContext.get().getVariableValue(variableName);
            final Fmt expectedFmted = code(Expression.formatLiteralSourceCode(expectedValue));
            final Fmt actualFmted = code(Expression.formatLiteralSourceCode(val));
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);
            if (val == null) {
                p.println(red(EXPECTED_VARIABLE_TO_EQUAL_BUT_IS_NULL, expressionFmted, code(variableName), expectedFmted));
                return false;
            } else if (val.getClass() != expectedValue.getClass()) {
                p.println(red(EXPECTED_VARIABLE_TO_BE_BUT_WAS_OTHER_TYPE, expressionFmted, code(variableName), classSimpleName(expectedValue.getClass()), classSimpleName(val.getClass())));
                return false;
            } else if (!eq(expectedValue, val)) {
                p.println(red(EXPECTED_VARIABLE_TO_EQUAL_BUT_EQUAL, expressionFmted, code(variableName), expectedFmted, actualFmted));
                return false;
            }

            p.println(green(OK_VARIABLE_EQUAL, code(variableName), expectedFmted));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final Object expected) {
        return (p, res) -> {
            final Object actual = res.executionResult;
            final Object expectedValue = expected instanceof final Localizable<?> localizable ? localizable.get(res.locale) : expected;
            final Fmt expectedFmted = code(Expression.formatLiteralSourceCode(expectedValue));
            final Fmt actualFmted = code(Expression.formatLiteralSourceCode(actual));
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, expressionFmted, expectedFmted));
                return false;
            } else if (actual.getClass() != expectedValue.getClass()) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, expressionFmted, classSimpleName(expectedValue.getClass()), classSimpleName(actual.getClass())));
                return false;
            } else if (!eq(expectedValue, actual)) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED, expressionFmted, expectedFmted, actualFmted));
                return false;
            }

            p.println(green(OK_RETURNED, expressionFmted, expectedFmted));
            return true;
        }; 
    }

    /**
     * Since we can't ask students to provide a proper equals() method, it is a bit tricky to assert the content of the objects they create.
     * The idea is to make them create a "toString()" method, and then assert the String representation of those objects.
     */
    public static ResultAssertion assertReturnValueStringRepresentationEquals(final Localizable<String> expected, final String expectedType) {
        return (p, res) -> {
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);
            final Object actual = res.executionResult;
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, expressionFmted, sameStyle(expected)));
                return false;
            } else if (!Type.UNBOXED.getOrDefault(actual.getClass(), actual.getClass()).getName().equals(expectedType)) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, expressionFmted, code(expectedType), classSimpleName(actual.getClass())));
                return false;
            } else if (!actual.toString().equals(expected.get(res.locale))) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED, expressionFmted, sameStyle(expected), sameStyle(actual.toString())));
                return false;
            }

            p.println(green(OK_RETURNED, expressionFmted, code(expected.get(res.locale))));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueWithRandomEquals(final DoubleToIntFunction expected) {
        return (p, res) -> {
            final var randomNumber = res.randomNumber();
            final Object actual = res.executionResult;
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, expressionFmted, code(expected.applyAsInt(randomNumber))));
                return false;
            } else if (!(actual instanceof Integer)) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, expressionFmted, code("int"), classSimpleName(actual.getClass())));
                return false;
            } else if (((Integer)actual).intValue() != expected.applyAsInt(randomNumber)) {
                p.println(red(
                    EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED,
                    expressionFmted,
                    code(expected.applyAsInt(randomNumber)),
                    code(randomNumber),
                    code(actual.toString())
                ));
                return false;
            }

            p.println(green(OK_RETURNED_INT_FROM_RANDOM, expressionFmted, code(expected.applyAsInt(randomNumber)), code(randomNumber)));
            return true;
        }; 
    }

    /**
     * Assert the result of a method using random numbers.
     * @param count the number of random numbers expected to be generated
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static ResultAssertion assertReturnValueWithRandomEquals(final int count, final ResToIntFunction buildExpected) {
        return (p, res) -> {
            final Object actual = res.executionResult;
            final var randomNumbersFmted = sequence(res.randomNumbers(count), Style.Code);
            final Fmt expressionFmted = code(res.resultExpressionSourceCode);
            final int expected = buildExpected.apply(res);

            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, expressionFmted, code(expected)));
                return false;
            } else if (!(actual instanceof Integer)) {
                p.println(red(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, expressionFmted, code("int"), classSimpleName(actual.getClass())));
                return false;
            } else if (((Integer)actual).intValue() != expected) {
                p.println(red(
                    EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED, 
                    expressionFmted,
                    code(expected),
                    randomNumbersFmted,
                    code(actual.toString())
                ));
                return false;
            }

            p.println(green(OK_RETURNED_INT_FROM_RANDOMS, expressionFmted, code(expected), randomNumbersFmted));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueIsAnonymousObject() {
        return (p, res) -> {
            final Object actual = res.executionResult;
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_NULL, code(res.resultExpressionSourceCode)));
                return false;
            } else if (actual.getClass().getSimpleName().contains("$$Lambda$")) { // Kind of hacky, but only way as far as I know
                p.println(red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED_LAMBDA, code(res.resultExpressionSourceCode)));
                return false;
            } else if (!actual.getClass().isAnonymousClass()) {
                p.println(red(EXPECTED_TO_RETURN_ANONYMOUS_BUT_RETURNED, code(res.resultExpressionSourceCode), classFullName(actual.getClass())));
                return false;
            }

            p.println(green(OK_RETURNED_OBJECT_IS_ANONYMOUS, code(res.resultExpressionSourceCode)));
            return true;
        };
    }

    public static ResultAssertion assertReturnValueIsLambda() {
        return (p, res) -> {
            final Object actual = res.executionResult;
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_NULL, code(res.resultExpressionSourceCode)));
                return false;
            } else if (actual.getClass().isAnonymousClass()) {
                p.println(red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED_ANONYMOUS, code(res.resultExpressionSourceCode)));
                return false;
            } else if (!actual.getClass().getSimpleName().contains("$$Lambda")) { // Kind of hacky, but only way as far as I know
                p.println(red(EXPECTED_TO_RETURN_LAMBDA_BUT_RETURNED, code(res.resultExpressionSourceCode), classFullName(actual.getClass())));
                return false;
            }

            p.println(green(OK_RETURNED_OBJECT_IS_LAMBDA, code(res.resultExpressionSourceCode)));
            return true;
        };
    }

    public static ResultAssertion assertReturnValueImplements(Class<?> valueInterface) {
        return (p, res) -> {
            final Object actual = res.executionResult;
            final var interfaceName = code(valueInterface.getName());
            if (actual == null) {
                p.println(red(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_RETURNED_NULL, code(res.resultExpressionSourceCode), interfaceName));
                return false;
            } else if (!valueInterface.isInstance(actual)) {
                p.println(red(EXPECTED_TO_RETURN_IMPLEMENTING_BUT_NOT, code(res.resultExpressionSourceCode), interfaceName, classFullName(actual.getClass())));
                return false;
            }

            p.println(green(OK_RETURNED_OBJECT_IMPLEMENTS, code(res.resultExpressionSourceCode), interfaceName));
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
                    p.println(red(EXPECTED_CONSTRUCTOR_TO_BE_PUBLIC, classSimpleName(type)));
                }
            }
            catch(NoSuchMethodException nsme) {
                if (constructorParamTypes.length == 0) {
                    p.println(red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS, classSimpleName(type)));
                } else if (constructorParamTypes.length == 1) {
                    p.println(red(EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM, classSimpleName(type), classSimpleName(constructorParamTypes[0])));
                } else {
                    final var expectedParams = Arrays
                        .stream(constructorParamTypes)
                        .map(t -> global(t.simpleClassName))
                        .toList();
                    p.println(red(EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS, classSimpleName(type), sequence(expectedParams, Style.Code)));
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
                    p.println(red(EXPECTED_CLASS_TO_BE_INSTANTIABLE, classSimpleName(actualType)));
                    return false;
                }
                return true;
            } catch (final ClassNotFoundException _cnfe) {
                p.println(red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE, classSimpleName(actualType), code(actualType.packageName)));
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
            final Fmt classLocationFmted = sameStyle(clasz.getName().replace(".", "/"));

            try {
                final var method = clasz.getMethod(methodName, methodParamClasses);
                if (isStatic && !Modifier.isStatic(method.getModifiers())) {
                    p.println(red(EXPECTED_METHOD_TO_BE_STATIC, sameStyle(methodName), classLocationFmted));
                    return false;
                }
                if (!isStatic && Modifier.isStatic(method.getModifiers())) {
                    p.println(red(EXPECTED_METHOD_TO_NOT_BE_STATIC, sameStyle(methodName), classLocationFmted));
                    return false;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    p.println(red(EXPECTED_METHOD_TO_BE_PUBLIC, sameStyle(methodName)));
                }
            }
            catch(final NoSuchMethodException _nsme) {
                if (methodParamClasses.length == 0) {
                    p.println(red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS, sameStyle(methodName), classLocationFmted));
                } else if (methodParamClasses.length == 1) {
                    p.println(red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM, sameStyle(methodName), classLocationFmted, classSimpleName(methodParamClasses[0])));
                } else {
                    final var expectedParams = Arrays
                        .stream(methodParamClasses)
                        .map(t -> global(t.getSimpleName()))
                        .toList();
                    p.println(
                        red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS,
                        sameStyle(methodName),
                        classLocationFmted,
                        sequence(expectedParams, Style.Code)
                    ));
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

    private static BeforeTestAssertion assertField(final String className, final String fieldName, final Boolean isFinal, final Type expectedFieldType) {
        return (p, locale, koan) -> {
            final var type = new Type(className);

            try {
                final var clasz = type.unsafeResolve();
                final var actualField = clasz.getDeclaredField(fieldName);
                if (!Modifier.isPrivate(actualField.getModifiers())) {
                    p.println(red(EXPECTED_FIELD_TO_BE_PRIVATE, code(fieldName), code(className)));
                    return false;
                }
                if (isFinal && !Modifier.isFinal(actualField.getModifiers())) {
                    p.println(red(EXPECTED_FIELD_TO_BE_FINAL, code(fieldName), code(className)));
                    return false;
                } else if (!isFinal && Modifier.isFinal(actualField.getModifiers())) {
                    p.println(red(EXPECTED_FIELD_TO_NOT_BE_FINAL, code(fieldName), code(className)));
                    return false;
                }
                if (!actualField.getType().equals(expectedFieldType.unsafeResolve())) {
                    p.println(red(EXPECTED_FIELD_TO_BE_OF_TYPE, code(fieldName), code(className), classSimpleName(expectedFieldType), classSimpleName(actualField.getType())));
                    return false;
                }
            }
            catch(NoSuchFieldException nsfe) {
                p.println(red(EXPECTED_TO_FIND_FIELD_IN_CLASS, code(fieldName), code(className)));
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
                    p.println(red(EXPECTED_CLASS_TO_IMPLEMENT, code(className), code(interfaceClass.getName())));
            }

            return success;
        };
    } 
}
