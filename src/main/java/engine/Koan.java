package engine;

import java.util.Arrays;
import java.util.function.Function;

public class Koan {
    public final Class<?> koanClass;
    public final String methodName;
    public final Class<?>[] methodParamTypes;
    public final KoanMethodCall[] calls;
    public final boolean usesConsole;

    public class KoanMethodCall {
        public final Object[] params;
        public final Assertion[] assertions;
        public final String[] stdInInputs;

        public KoanMethodCall(Object[] params, Assertion[] assertions, String[] stdInInputs) {
            this.params = params;
            this.assertions = assertions;
            this.stdInInputs = stdInInputs;
        }

        public KoanMethodCall withStdInInputs(String[] inputs) {
            return new KoanMethodCall(params, assertions, inputs);
        }
        
        public KoanMethodCall withAssertions(Assertion[] assertions) {
            return new KoanMethodCall(params, assertions, stdInInputs);
        }
    }

    public Koan(Class<?> koanClass, String methodName, Class<?>... methodParamTypes) {
        this(koanClass, methodName, methodParamTypes, new KoanMethodCall[0], false);
    }

    private Koan(Class<?> koanClass, String methodName, Class<?>[] methodParamTypes, KoanMethodCall[] calls, boolean usesConsole) {
        this.koanClass = koanClass;
        this.methodName = methodName;
        this.methodParamTypes = methodParamTypes;
        this.calls = calls;
        this.usesConsole = usesConsole;
    }

    public Koan whenCallingWith(Object... params) {
        var newCalls = Arrays.copyOf(calls, calls.length + 1);
        newCalls[newCalls.length - 1] = new KoanMethodCall(params, new Assertion[0], new String[0]);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            newCalls,
            usesConsole
        );
    }

    public Koan useConsole() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            calls,
            true
        );
    }

    public Koan whenCalling() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            new KoanMethodCall[] { new KoanMethodCall(new Object[0], new Assertion[0], new String[0]) },
            usesConsole
        );
    }

    public Koan withStdInInputs(String... inputs) {
        return withUpdatedCall(call -> {
            if (call.stdInInputs.length > 0) {
                throw new IllegalArgumentException("Current call already have StdIn inputs");
            }
            return call.withStdInInputs(inputs);
        });
    }

    public Koan then(Assertion... assertions) {
        return withUpdatedCall(call -> {
            if (call.assertions.length > 0) {
                throw new IllegalArgumentException("Current call already have assertions");
            }
            return call.withAssertions(assertions);
        });
    }

    private Koan withUpdatedCall(Function<KoanMethodCall, KoanMethodCall> newCall) {
        if (calls.length == 0) {
            throw new IllegalArgumentException("No current call in Koan");
        }
        var currentCall = calls[calls.length - 1];
        var newCalls = calls.clone();
        newCalls[newCalls.length - 1] = newCall.apply(currentCall);
        return new Koan(koanClass, methodName, methodParamTypes, newCalls, usesConsole);
    }
}
