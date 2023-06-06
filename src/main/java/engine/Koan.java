package engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Stores all the information required to execute and assess the result of a koan.
 */
public class Koan {
    private final Localizable<Class<?>> koanClass;
    public final String methodName;
    public final Class<?>[] methodParamTypes;
    public final Class<?>[] constructorParamTypes;
    public final boolean onObject;
    public final Object[] constructorParams;
    public final KoanMethodCall[] calls;
    public final boolean usesConsole;
    public final boolean showStdInInputs;
    public final Optional<String> exerciseClassName;
    public final Optional<String> exerciseClassPackage;
    public final KoanAssertion[] koanAssertions;
    public MethodDetails methodDetails = null;

    public class MethodDetails {
        public final Koan koan;
        public final Locale locale;
        public final Method method;
        public final Class<?> clasz;
        public final Constructor<?> constructor;

        public MethodDetails(Locale locale) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
            this.koan = Koan.this;
            this.locale = locale;
            
            clasz = exerciseClassName.isPresent() ? Class.forName(exerciseClassName(locale)) : koanClass.get(locale);
            method = clasz.getMethod(methodName, methodParamTypes);

            if (onObject) {
                try {
                    constructor = clasz.getConstructor(constructorParamTypes);
                } catch(NoSuchMethodException nsme) {
                    throw new NoSuchConstructorException();
                }
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new NoDynamicMethodException();
                }
            } else {
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new NoStaticMethodException();
                }
                constructor = null;
            }
        }

        private static String formatParams(Object[] params) {
            return String.join(
                ", ",
                Arrays.stream(params)
                    .map(p -> p == null ? "null": p instanceof String ? String.format("\"%s\"", p) : p.toString())
                    .toArray(String[]::new)
            );
        }

        public String formatCall(Object[] constructorParameters, Object[] parameters) {
            var constructorCall = "";

            if (onObject) {
                constructorCall = String.format("new %s(%s).", clasz.getSimpleName(), formatParams(constructorParameters));
            }

            return String.format("%s%s(%s)", constructorCall, koan.methodName, formatParams(parameters));
        }
    }

    public class KoanMethodCall {
        public static final List<Localizable<String>> NO_STD_IN_INPUTS = List.of();

        private final Object[] params;
        private final Object[] constructorParams;
        public final ResultAssertion[] assertions;
        private final List<Localizable<String>> stdInInputs;
        public final Koan koan;
        private final long seed;

        public KoanMethodCall(Koan koan, Object[] params, Object[] constructorParams) {
            this(koan, params, constructorParams, new ResultAssertion[0], KoanMethodCall.NO_STD_IN_INPUTS, Math.round(Math.random() * Long.MAX_VALUE));
        }

        private KoanMethodCall(Koan koan, Object[] params, Object[] constructorParams, ResultAssertion[] assertions, List<Localizable<String>> stdInInputs, long seed) {
            this.koan = koan;
            this.params = params;
            this.constructorParams = constructorParams;
            this.assertions = assertions;
            this.stdInInputs = stdInInputs;
            this.seed = seed;
        }

        public KoanMethodCall withStdInInputs(List<Localizable<String>> inputs) {
            return new KoanMethodCall(koan, params, constructorParams, assertions, inputs, seed);
        }
        
        public KoanMethodCall withAssertions(ResultAssertion[] assertions) {
            return new KoanMethodCall(koan, params, constructorParams, assertions, stdInInputs, seed);
        }

        @SuppressWarnings("rawtypes")
        public Object[] parameters(Locale locale) {
            return Arrays.stream(params)
                .map(p -> p instanceof Local ? ((Local)p).get(locale) : p)
                .toArray();
        }

        @SuppressWarnings("rawtypes")
        public Object[] constructorParameters(Locale locale) {
            return Arrays.stream(constructorParams)
                .map(p -> p instanceof Local ? ((Local)p).get(locale) : p)
                .toArray();
        }

        public String[] stdInInputs(Locale locale) {
            return stdInInputs.stream()
                .map(p -> p.get(locale))
                .toArray(String[]::new);
        }

        public int paramCount() {
            return params.length;
        }

        public void setupRandomForKoan() {
            Helpers.setupRandomForKoan(seed);
        }

        public String toString(Locale locale) {
            return methodDetails.formatCall(constructorParameters(locale), parameters(locale));
        }
    }

    public Koan(Localizable<Class<?>> koanClass, String methodName, Class<?>... methodParamTypes) {
        this(
            koanClass,
            methodName,
            methodParamTypes,
            new Class<?>[0],
            new Object[0],
            false,
            new KoanMethodCall[0],
            false,
            false,
            Optional.empty(),
            Optional.empty(),
            new KoanAssertion[0]
        );
    }

    private Koan(
        Localizable<Class<?>> koanClass,
        String methodName,
        Class<?>[] methodParamTypes,
        Class<?>[] constructorParamTypes,
        Object[] constructorParams,
        boolean onObject,
        KoanMethodCall[] calls,
        boolean usesConsole,
        boolean showStdInInputs,
        Optional<String> exerciseClassName,
        Optional<String> exerciseClassPackage,
        KoanAssertion[] koanAssertions) {
        this.koanClass = Objects.requireNonNull(koanClass, "koanClass must not be null");
        this.methodName = Objects.requireNonNull(methodName, "methodName must not be null");
        this.methodParamTypes = Objects.requireNonNull(methodParamTypes, "methodParamTypes must not be null");
        this.calls = Objects.requireNonNull(calls, "calls must not be null");
        this.usesConsole = usesConsole;
        this.showStdInInputs = showStdInInputs;
        this.exerciseClassName = exerciseClassName;
        this.exerciseClassPackage = exerciseClassPackage;
        this.constructorParamTypes = constructorParamTypes;
        this.constructorParams = constructorParams;
        this.onObject = onObject;
        this.koanAssertions = koanAssertions;
    }

    public Koan whenCallingWith(Object... params) {
        var newCalls = Arrays.copyOf(calls, calls.length + 1);
        
        newCalls[newCalls.length - 1] = new KoanMethodCall(this, params, constructorParams);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            newCalls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan inClass(String className, Class<?>... constructorParamTypes) {
        var separatorIndex = className.lastIndexOf(".");

        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            calls,
            usesConsole,
            showStdInInputs,
            Optional.of(className.substring(separatorIndex + 1)),
            Optional.of(className.substring(0, separatorIndex)),
            koanAssertions
        );
    }

    public Koan beforeCalling(KoanAssertion... koanAssertions) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            calls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan withObjectConstructedWith(Object... constructorParams) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            true,
            calls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan useConsole() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            calls,
            true,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan useConsoleAndShowStdinInputs() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            calls,
            true,
            true,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan whenCalling() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            new KoanMethodCall[] { new KoanMethodCall(this, new Object[0], constructorParams) },
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan withStdInInputs(List<Localizable<String>> inputs) {
        return withUpdatedCall(call -> {
            if (call.stdInInputs.size() > 0) {
                throw new IllegalArgumentException("Current call already have StdIn inputs");
            }
            return call.withStdInInputs(inputs);
        });
    }

    public Koan withStdInInputs(String... inputs) {
        return withStdInInputs(
            Arrays.stream(inputs)
                .map(input -> (Localizable<String>)new Global<String>(input))
                .toList()
        );
    }

    public Koan then(ResultAssertion... assertions) {
        return withUpdatedCall(call -> {
            if (call.assertions.length > 0) {
                throw new IllegalArgumentException("Current call already have assertions");
            }
            return call.withAssertions(assertions);
        });
    }

    public String koanClassName(Locale locale) {
        return koanClass.get(locale).getSimpleName();
    }

    public String exerciseClassName(Locale locale) {
        if (exerciseClassName.isEmpty()) {
            return koanClass.get(locale).getName();
        }

        return String.format("%s.%s", exerciseClassPackage.get(), exerciseClassName.get());
    }

    MethodDetails resolveMethod(Locale locale) throws NoSuchMethodException, ClassNotFoundException {
        // Kind of expensive, so cache it.  
        if (methodDetails == null) {
            // Fine to cache: we will never call this with 2 different locales in the same session

            methodDetails = new MethodDetails(locale);
        }

        return methodDetails;
    }

    boolean executeAssertions(Printer p) {
        for(var assertion: koanAssertions) {
            var asserted = assertion.validate(p, methodDetails);
            if (!asserted) {
                return false;
            }
        }
        return true;
    }

    private Koan withUpdatedCall(Function<KoanMethodCall, KoanMethodCall> newCall) {
        if (calls.length == 0) {
            throw new IllegalArgumentException("No current call in Koan");
        }
        var currentCall = calls[calls.length - 1];
        var newCalls = calls.clone();
        newCalls[newCalls.length - 1] = newCall.apply(currentCall);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            newCalls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }
}
