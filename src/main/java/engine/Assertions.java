package engine;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

import engine.ConsoleFmt.Formats;
import engine.script.Type;

import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;
import static engine.Texts.*;

/**
 * Library of various assertions which can be run about the result of a koan execution.
 */
public class Assertions {
    private static String resolveTemplateParam(final KoanResult res, final Object param) {
        if (param instanceof FormatParam) {
            return ((FormatParam)param).format(res);
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

    public static ResultAssertion assertReturnValueEquals(final int expected) {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL, Formats.Red, code(res.resultExpressionSourceCode), code(expected)));
                return false;
            } else if (!(res.executionResult instanceof Integer)) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(res.resultExpressionSourceCode), code(res.executionResult.getClass().getSimpleName())));
                return false;
            } else if (((Integer)res.executionResult).intValue() != expected) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED, Formats.Red, code(res.resultExpressionSourceCode), code(Integer.toString(expected)), code(res.executionResult.toString())));
                return false;
            }

            p.println(format(OK_RETURNED_INT, Formats.Green, code(res.resultExpressionSourceCode), code(expected)));
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
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected));
                return false;
            } else if (!(res.executionResult instanceof Double)) {
                p.println(format(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(res.resultExpressionSourceCode), res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (!equals((Double)res.executionResult, expected)) {
                p.println(format(EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED, Formats.Red, res.resultExpressionSourceCode, expected, ((Double)res.executionResult).doubleValue()));
                return false;
            }

            p.println(format(OK_RETURNED_DOUBLE, Formats.Green, code(res.resultExpressionSourceCode), code(expected)));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final boolean expected) {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected));
                return false;
            } else if (!(res.executionResult instanceof Boolean)) {
                p.println(format(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(res.resultExpressionSourceCode), res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (((Boolean)res.executionResult).booleanValue() != expected) {
                p.println(format(EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED, Formats.Red, res.resultExpressionSourceCode, expected, ((Boolean)res.executionResult).booleanValue()));
                return false;
            }

            p.println(format(OK_RETURNED_BOOLEAN, Formats.Green, code(res.resultExpressionSourceCode), code(expected)));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final int[] expected){
         return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected));
                return false;
            } else if (!(res.executionResult instanceof int[])) {
                p.println(format(EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(res.resultExpressionSourceCode), res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (!Arrays.equals(((int[])res.executionResult), expected)) {
                p.println(format(EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED, Formats.Red, res.resultExpressionSourceCode, Arrays.toString(expected), Arrays.toString((int[])res.executionResult)));
                return false;
            }

            p.println(format(OK_RETURNED_INT_ARRAY, Formats.Green, code(res.resultExpressionSourceCode), code(expected)));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueEquals(final Localizable<String> expected) {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected.get(res.locale)));
                return false;
            } else if (!(res.executionResult instanceof String)) {
                p.println(format(EXPECTED_TO_RETURN_STRING_BUT_RETURNED_OTHER_TYPE, Formats.Red, code(res.resultExpressionSourceCode), res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (!((String)res.executionResult).equals(expected.get(res.locale))) {
                p.println(format(EXPECTED_TO_RETURN_STRING_BUT_RETURNED, Formats.Red, res.resultExpressionSourceCode, expected.get(res.locale), (String)res.executionResult));
                return false;
            }

            p.println(format(OK_RETURNED_STRING, Formats.Green, code(res.resultExpressionSourceCode), code(expected.get(res.locale))));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueStringRepresentationEquals(final Localizable<String> expected, final String expectedType) {
        return (p, res) -> {
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected.get(res.locale)));
                return false;
            } else if (!res.executionResult.getClass().getName().equals(expectedType)) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE, Formats.Red, res.resultExpressionSourceCode, expectedType, res.executionResult.getClass().getSimpleName()));
                return false;
            } else if (!res.executionResult.toString().equals(expected.get(res.locale))) {
                p.println(format(EXPECTED_TO_RETURN_BUT_RETURNED, Formats.Red, res.resultExpressionSourceCode, expected.get(res.locale), res.executionResult.toString()));
                return false;
            }

            p.println(format(OK_RETURNED, Formats.Green, code(res.resultExpressionSourceCode), code(expected.get(res.locale))));
            return true;
        }; 
    }

    public static ResultAssertion assertReturnValueWithRandomEquals(final DoubleToIntFunction expected) {
        return (p, res) -> {
            final var randomNumber = res.randomNumber();
            if (res.executionResult == null) {
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected.applyAsInt(randomNumber)));
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
                p.println(format(EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL, Formats.Red, res.resultExpressionSourceCode, expected));
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
    
    public static BeforeTestAssertion assertConstructorIsInvokable(final String className, final Type... constructorParamTypes) {
        return (p, locale, _koan) -> {
            final var type = new Type(className);

            try {
                final var clasz = type.resolve();
                if (!Type.isInstantiable(clasz)) {
                    p.println(ConsoleFmt.red(EXPECTED_CLASS_TO_BE_INSTANTIABLE), className);
                    return false;
                }

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
            } catch (ClassNotFoundException cnfe) {
                    p.println(ConsoleFmt.red(EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE), type.simpleClassName,type.packageName);
                return false;
            }

            return true;
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
        return (p, locale, _koan) -> {
            final var clasz = type.get(locale).unsafeResolve();
            final var methodParamClasses = Type.unsafeResolveTypes(paramTypes);

            try {
                final var method = clasz.getMethod(methodName, methodParamClasses);
                if (isStatic && !Modifier.isStatic(method.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_METHOD_TO_NOT_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!isStatic && Modifier.isStatic(method.getModifiers())) {
                    p.println(ConsoleFmt.red(EXPECTED_METHOD_TO_BE_STATIC), methodName, clasz.getName().replace(".", "/"));
                    return false;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    p.println(
                        ConsoleFmt.red(EXPECTED_METHOD_TO_BE_PUBLIC),
                        methodName
                    );
                }
            }
            catch(NoSuchMethodException nsme) {
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

    public static BeforeTestAssertion assertField(final String className, final String fieldName, final Boolean isFinal, final Type fieldType) {
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
}
