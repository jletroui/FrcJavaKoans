package engine;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

import static engine.Texts.*;

/**
 * Library of various assertions which can be run about the result of a koan execution.
 */
public class Assertions {
    private static String resolveTemplateParam(KoanResult res, Object param) {
        if (param instanceof FormatParam) {
            return ((FormatParam)param).format(res);
        }

        return Optional.ofNullable(param).map((v) -> v.toString()).orElse("");
    }

    private static String whenCalling(KoanResult res) {
        if (res.targetMethod.hasParameters()) {
            return String.format(" when calling %s", res.targetMethod);
        }
        return "";
    }

    public static ResultAssertion assertIf(boolean condition, ResultAssertion inner) {
        return (p, res) -> condition ? inner.validate(p, res) : true;
    }

    public static ResultAssertion assertNextStdOutLineEquals(Localizable<String> expectedTemplate, Object... params) {
        return (p, res) -> {
            final var realParams = Arrays.stream(params)
                .map((param) -> Assertions.resolveTemplateParam(res, param))
                .toArray();
            final var expected = String.format(expectedTemplate.get(res.locale), realParams);
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                return false;
            }
            if (!lineContent.get().equals(expected)) {
                if (lineContent.get().equals("")) {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING), expected, whenCalling(res));
                } else {
                    p.println(Color.red(EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD), expected, whenCalling(res), lineContent.get());
                }
                return false;
            }

            p.println(Color.green(OK_DISPLAYED_IN_CONSOLE), expected, whenCalling(res));
            return true;
        };
    }

    public static ResultAssertion assertNextStdOutLineIsEmpty() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING));
                return false;
            }
            if (!lineContent.get().equals("")) {
                p.println(Color.red(EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_INSTEAD), lineContent.get());
                return false;
            }

            p.println(Color.green(OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE));
            return true;
        };
    }

    public static ResultAssertion assertNoMoreLineInStdOut() {
        return (p, res) -> {
            final var lineContent = res.nextStdOutLine();

            if (!lineContent.isEmpty()) {
                p.println(Color.red(EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD), whenCalling(res), lineContent.get());
                return false;
            }

            return true;
        };
    }

    public static ResultAssertion assertAskedInStdIn() {
        return (p, res) -> {
            final var lineContent = res.nextStdInLine();

            if (lineContent.isPresent()) {
                p.println(Color.green(OK_ASKED_FOR_LINE_IN_CONSOLE));
                return true;
            }
            p.println(Color.red(EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE));
            return false;
        };
    }

    public static ResultAssertion assertReturnValueEquals(final int expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.targetMethod, expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED), res.targetMethod, expected, ((Integer)res.methodReturnValue).intValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT), res.targetMethod, expected);
            return true;
        }; 
    }

    private static final double EPSILON = 0.0000000001;
    private static boolean equals(Double actual, double expected) {
        var diff = Math.abs(actual.doubleValue() - expected);
        return diff < EPSILON;
    }

    public static ResultAssertion assertReturnValueEquals(final double expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_NULL), res.targetMethod, expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Double)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (!equals((Double)res.methodReturnValue, expected)) {
                p.println(Color.red(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED), res.targetMethod, expected, ((Double)res.methodReturnValue).doubleValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_DOUBLE), res.targetMethod, expected);
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final boolean expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_NULL), res.targetMethod, expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Boolean)) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Boolean)res.methodReturnValue).booleanValue() != expected) {
                p.println(Color.red(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED), res.targetMethod, expected, ((Boolean)res.methodReturnValue).booleanValue());
                return false;
            }

            p.println(Color.green(OK_RETURNED_BOOLEAN), res.targetMethod, expected);
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final Localizable<String> expected) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_NULL), res.targetMethod, expected.get(res.locale));
                return false;
            } else if (!(res.methodReturnValue instanceof String)) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (!((String)res.methodReturnValue).equals(expected.get(res.locale))) {
                p.println(Color.red(EXPECTED_TO_RETURN_STRING_BUT_RETURNED), res.targetMethod, expected.get(res.locale), (String)res.methodReturnValue);
                return false;
            }

            p.println(Color.green(OK_RETURNED_STRING), res.targetMethod, expected.get(res.locale));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueStringRepresentationEquals(final Localizable<String> expected, final String expectedType) {
        return (p, res) -> {
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_BUT_RETURNED_NULL), res.targetMethod, expected.get(res.locale));
                return false;
            } else if (!res.methodReturnValue.getClass().getName().equals(expectedType)) {
                p.println(Color.red(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE), res.targetMethod, expectedType, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (!res.methodReturnValue.toString().equals(expected.get(res.locale))) {
                p.println(Color.red(EXPECTED_TO_RETURN_BUT_RETURNED), res.targetMethod, expected.get(res.locale), res.methodReturnValue.toString());
                return false;
            }

            p.println(Color.green(OK_RETURNED), res.targetMethod, expected.get(res.locale));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueWithRandomEquals(DoubleToIntFunction expected) {
        return (p, res) -> {
            var randomNumber = res.randomNumber();
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.targetMethod, expected.applyAsInt(randomNumber));
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected.applyAsInt(randomNumber)) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED), 
                    res.targetMethod,
                    expected.applyAsInt(randomNumber),
                    randomNumber,
                    ((Integer)res.methodReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOM), res.targetMethod, expected.applyAsInt(randomNumber), randomNumber);
            return true;
        }; 
    }

    /**
     * Assert the result of a method using random numbers.
     * @param count the number of random numbers expected to be generated
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static ResultAssertion assertReturnValueWithRandomEquals(int count, ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(count), buildExpected);
    }

    /**
     * Assert the result of a method using random numbers.
     * @param fromOffset the start of the random numbers sequence to pick
     * @param count the number of random numbers to pick
     * @param buildExpected given the generated numbers, returns the expected result
     */
    public static ResultAssertion assertReturnValueWithRandomEquals(int fromOffset, int count, ResToIntFunction buildExpected) {
        return assertReturnValueWithRandomEquals(res -> res.randomNumbers(fromOffset, count), buildExpected);
    }

    private static ResultAssertion assertReturnValueWithRandomEquals(Function<KoanResult, double[]> randomNumbersFunc, ResToIntFunction buildExpected) {
        return (p, res) -> {
            var randomNumbers = randomNumbersFunc.apply(res);
            var formatRandomNumbers = Helpers.formatSequence(res.locale, randomNumbers);

            int expected = buildExpected.apply(res);
            if (res.methodReturnValue == null) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL), res.targetMethod, expected);
                return false;
            } else if (!(res.methodReturnValue instanceof Integer)) {
                p.println(Color.red(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE), res.targetMethod, res.methodReturnValue.getClass().getSimpleName());
                return false;
            } else if (((Integer)res.methodReturnValue).intValue() != expected) {
                p.println(
                    Color.red(EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED), 
                    res.targetMethod,
                    expected,
                    formatRandomNumbers,
                    ((Integer)res.methodReturnValue).intValue()
                );
                return false;
            }

            p.println(Color.green(OK_RETURNED_INT_FROM_RANDOMS), res.targetMethod, expected, formatRandomNumbers);
            return true;
        }; 
    }

    public static KoanAssertion assertClassIsInstantiable() {
        return (p, locale, koan) -> {
            try {
                var clasz = koan.koanClass.get(locale).resolve();
                if (!Helpers.isInstantiable(clasz)) {
                    p.println(Color.red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), koan.koanClass.get(locale).className);
                    return false;
                }
            } catch (ClassNotFoundException cnfe) {
                    p.println(Color.red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), koan.koanClass.get(locale).simpleClassName, koan.koanClass.get(locale).packageName);
                return false;
            }

            return true;
        };
    }
    
    public static KoanAssertion assertConstructorIsInvokable() {
        return (p, locale, koan) -> {
            var clasz = koan.koanClass.get(locale).unsafeResolve();

            try {
                var constructor = clasz.getConstructor(Type.unsafeResolveTypes(koan.constructorParamTypes));
                if (!Modifier.isPublic(constructor.getModifiers())) {
                    p.println(
                        Color.red(EXPECTED_CONSTRUCTOR_TO_BE_PUBLIC),
                        koan.koanClass.get(locale).simpleClassName
                    );
                }
            }
            catch(NoSuchMethodException nsme) {
                if (koan.constructorParamTypes.length == 0) {
                    p.println(
                        Color.red(EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS),
                        koan.koanClass.get(locale).simpleClassName
                    );
                } else if (koan.constructorParamTypes.length == 1) {
                    p.println(
                        Color.red(EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM),
                        koan.koanClass.get(locale).simpleClassName,
                        koan.constructorParamTypes[0]
                    );
                } else {
                    final var expectedParams = Arrays
                        .stream(koan.constructorParamTypes)
                        .map(type -> "'" + type + "'")
                        .toArray(String[]::new);
                    p.println(
                        Color.red(EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS),
                        koan.koanClass.get(locale).simpleClassName,
                        Helpers.formatSequence(locale, expectedParams)
                    );
                }
                return false;
            }

            return true;
        };
    }
    
    public static KoanAssertion assertMethodIsInvokable(String methodName, boolean isStatic, Type... paramTypes) {
        return assertMethodIsInvokable(methodName, isStatic, Type.unsafeResolveTypes(paramTypes));
    }

    public static KoanAssertion assertMethodIsInvokable(String methodName, boolean isStatic, Class<?>... methodParamTypes) {
        return (p, locale, koan) -> {
            var clasz = koan.koanClass.get(locale).unsafeResolve();

            try {
                var method = clasz.getMethod(methodName, methodParamTypes);
                if (isStatic && !Modifier.isStatic(method.getModifiers())) {
                    p.println(Color.red(EXPECTED_METHOD_TO_NOT_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!isStatic && Modifier.isStatic(method.getModifiers())) {
                    p.println(Color.red(EXPECTED_METHOD_TO_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    p.println(
                        Color.red(EXPECTED_METHOD_TO_BE_PUBLIC),
                        koan.koanClass.get(locale).simpleClassName
                    );
                }
            }
            catch(NoSuchMethodException nsme) {
                if (methodParamTypes.length == 0) {
                    p.println(
                        Color.red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS),
                        methodName,
                        clasz.getName().replace(".", "/")
                    );
                } else if (methodParamTypes.length == 1) {
                    p.println(
                        Color.red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM),
                        methodName,
                        clasz.getName().replace(".", "/"),
                        methodParamTypes[0].getSimpleName()
                    );
                } else {
                    final var expectedParams = Arrays
                        .stream(methodParamTypes)
                        .map(type -> "'" + type.getSimpleName() + "'")
                        .toArray(String[]::new);
                    p.println(
                        Color.red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS),
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
    
    public static KoanAssertion assertFieldIsPrivate(String fieldName) {
        return (p, locale, koan) -> {
                var clasz = koan.koanClass.get(locale).unsafeResolve();

            try {
                var field = clasz.getDeclaredField(fieldName);
                if (!Modifier.isPrivate(field.getModifiers())) {
                    p.println(Color.red(EXPECTED_FIELD_TO_BE_PRIVATE), fieldName, clasz.getName());
                    return false;
                }
            }
            catch(NoSuchFieldException nsfe) {
                p.println(Color.red(EXPECTED_TO_FIND_FIELD_IN_CLASS), fieldName, clasz.getName());
                return false;
            }

            return true;
        };
    }
    
    public static KoanAssertion assertFieldIsFinal(String fieldName) {
        return (p, locale, koan) -> {
                var clasz = koan.koanClass.get(locale).unsafeResolve();

            try {
                var field = clasz.getDeclaredField(fieldName);
                if (!Modifier.isFinal(field.getModifiers())) {
                    p.println(Color.red(EXPECTED_FIELD_TO_BE_FINAL), fieldName, clasz.getName());
                    return false;
                }
            }
            catch(NoSuchFieldException nsfe) {
                p.println(Color.red(EXPECTED_TO_FIND_FIELD_IN_CLASS), fieldName, clasz.getName());
                return false;
            }

            return true;
        };
    }    
    
    public static KoanAssertion assertFieldType(String fieldName, Type fieldType) {
        return (p, locale, koan) -> {
                var clasz = koan.koanClass.get(locale).unsafeResolve();

            try {
                var field = clasz.getDeclaredField(fieldName);
                if (!field.getType().equals(fieldType.unsafeResolve())) {
                    p.println(Color.red(EXPECTED_FIELD_TO_BE_OF_TYPE), fieldName, clasz.getName(), fieldType, field.getType().getSimpleName());
                    return false;
                }
            }
            catch(NoSuchFieldException nsfe) {
                p.println(Color.red(EXPECTED_TO_FIND_FIELD_IN_CLASS), fieldName, clasz.getName());
                return false;
            }

            return true;
        };
    } 
}
