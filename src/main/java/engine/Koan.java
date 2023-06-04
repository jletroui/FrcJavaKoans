package engine;

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
    public final KoanMethodCall[] calls;
    public final boolean usesConsole;
    public final boolean showStdInInputs;
    public final Optional<String> exerciseClassName;
    public final Optional<String> exerciseClassPackage;
    private Method method = null;

    public class KoanMethodCall {
        public static final List<Localizable<String>> NO_STD_IN_INPUTS = List.of();

        private final Object[] params;
        public final Assertion[] assertions;
        private final List<Localizable<String>> stdInInputs;
        public final Koan koan;
        private final long seed;

        public KoanMethodCall(Koan koan, Object[] params) {
            this(koan, params, new Assertion[0], KoanMethodCall.NO_STD_IN_INPUTS, Math.round(Math.random() * Long.MAX_VALUE));
        }

        private KoanMethodCall(Koan koan, Object[] params, Assertion[] assertions, List<Localizable<String>> stdInInputs, long seed) {
            this.koan = koan;
            this.params = params;
            this.assertions = assertions;
            this.stdInInputs = stdInInputs;
            this.seed = seed;
        }

        public KoanMethodCall withStdInInputs(List<Localizable<String>> inputs) {
            return new KoanMethodCall(koan, params, assertions, inputs, seed);
        }
        
        public KoanMethodCall withAssertions(Assertion[] assertions) {
            return new KoanMethodCall(koan, params, assertions, stdInInputs, seed);
        }

        @SuppressWarnings("rawtypes")
        public Object[] parameters(Locale locale) {
            return Arrays.stream(params)
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
            String[] params = Arrays.stream(parameters(locale))
                .map(p -> p == null ? "null": p instanceof String ? String.format("\"%s\"", p) : p.toString())
                .toArray(String[]::new);
            return String.format("%s(%s)", koan.methodName, String.join(", ", params));
        }
    }

    public Koan(Localizable<Class<?>> koanClass, String methodName, Class<?>... methodParamTypes) {
        this(koanClass, methodName, methodParamTypes, new KoanMethodCall[0], false, false, Optional.empty(), Optional.empty());
    }

    private Koan(
        Localizable<Class<?>> koanClass,
        String methodName,
        Class<?>[] methodParamTypes,
        KoanMethodCall[] calls,
        boolean usesConsole,
        boolean showStdInInputs,
        Optional<String> exerciseClassName,
        Optional<String> exerciseClassPackage) {
        this.koanClass = Objects.requireNonNull(koanClass, "koanClass must not be null");
        this.methodName = Objects.requireNonNull(methodName, "methodName must not be null");
        this.methodParamTypes = Objects.requireNonNull(methodParamTypes, "methodParamTypes must not be null");
        this.calls = Objects.requireNonNull(calls, "calls must not be null");
        this.usesConsole = usesConsole;
        this.showStdInInputs = showStdInInputs;
        this.exerciseClassName = exerciseClassName;
        this.exerciseClassPackage = exerciseClassPackage;
    }


    public Koan whenCallingWith(Object... params) {
        var newCalls = Arrays.copyOf(calls, calls.length + 1);
        
        newCalls[newCalls.length - 1] = new KoanMethodCall(this, params);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            newCalls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage
        );
    }

    public Koan inClass(String className) {
        var separatorIndex = className.lastIndexOf(".");

        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            calls,
            usesConsole,
            showStdInInputs,
            Optional.of(className.substring(separatorIndex + 1)),
            Optional.of(className.substring(0, separatorIndex))
        );
    }

    public Koan useConsole() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            calls,
            true,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage
        );
    }

    public Koan useConsoleAndShowStdinInputs() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            calls,
            true,
            true,
            exerciseClassName,
            exerciseClassPackage
        );
    }

    public Koan whenCalling() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            new KoanMethodCall[] { new KoanMethodCall(this, new Object[0]) },
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage
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

    public Koan then(Assertion... assertions) {
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

    Method method(Locale locale) throws NoSuchMethodException, ClassNotFoundException {
        // Kind of expensive, so cache it.
        if (method == null) {
            // Fine to cache: we will never call this with 2 different locales in the same session

            Class<?> exerciseClass = koanClass.get(locale);
            if (exerciseClassName.isPresent()) {
                exerciseClass = Class.forName(exerciseClassName(locale));
            }
            var candidateMethod = exerciseClass.getMethod(methodName, methodParamTypes);
            if (!Modifier.isStatic(candidateMethod.getModifiers())) {
                throw new NoStaticMethodException();
            }
            method = candidateMethod;
        }
        return method;
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
            newCalls,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage
        );
    }
}
