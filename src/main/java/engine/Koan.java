package engine;

import static engine.Factories.global;
import static engine.Factories.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Stores all the information required to execute and assess the result of a koan.
 */
public class Koan {
    final Localizable<Type> koanClass;
    public final String methodName;
    final Class<?>[] methodParamTypes;
    final Type[] constructorParamTypes;
    private final Value[] constructorParams;
    private final ObjectPreCall[] objectPrepCalls;
    final boolean onObject;
    final KoanTest[] tests;
    final boolean usesConsole;
    final boolean showStdInInputs;
    private final KoanAssertion[] beforeAssertions;

    public Koan(Localizable<Class<?>> koanClass, String methodName, Class<?>... methodParamTypes) {
        this(
            koanClass.map(Type::new),
            methodName,
            methodParamTypes,
            new Type[0],
            new Value[0],
            new ObjectPreCall[0],
            false,
            new KoanTest[0],
            false,
            false,
            new KoanAssertion[0]
        );
    }

    public Koan(String koanClassName, String methodName, Class<?>... methodParamTypes) {
        this(
            global(type(koanClassName)),
            methodName,
            methodParamTypes,
            new Type[0],
            new Value[0],
            new ObjectPreCall[0],
            false,
            new KoanTest[0],
            false,
            false,
            new KoanAssertion[0]
        );
    }

    private Koan(
        Localizable<Type> koanClass,
        String methodName,
        Class<?>[] methodParamTypes,
        Type[] constructorParamTypes,
        Value[] constructorParams,
        ObjectPreCall[] objectPrepCalls,
        boolean onObject,
        KoanTest[] tests,
        boolean usesConsole,
        boolean showStdInInputs,
        KoanAssertion[] koanAssertions) {
        this.koanClass = Objects.requireNonNull(koanClass, "koanClass must not be null");
        this.methodName = Objects.requireNonNull(methodName, "methodName must not be null");
        this.methodParamTypes = Objects.requireNonNull(methodParamTypes, "methodParamTypes must not be null");
        this.tests = Objects.requireNonNull(tests, "calls must not be null");
        this.usesConsole = usesConsole;
        this.showStdInInputs = showStdInInputs;
        this.constructorParamTypes = constructorParamTypes;
        this.constructorParams = constructorParams;
        this.objectPrepCalls = objectPrepCalls;
        this.onObject = onObject;
        this.beforeAssertions = koanAssertions;
    }

    public Koan whenCalled() {
        return whenCalledWith();
    }

    public Koan whenCalledWith(Object... params) {
        var newTests = Arrays.copyOf(tests, tests.length + 1);
        
        newTests[newTests.length - 1] = new KoanTest(this, params, constructorParams, objectPrepCalls);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            newTests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan usingConstructor(Type... constructorParamTypes) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            tests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan beforeTesting(KoanAssertion... koanAssertions) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            tests,
            usesConsole,
            showStdInInputs,
            koanAssertions
        );
    }

    public Koan onObjectConstructedWith(Value... constructorParams) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            true,
            tests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan onObjectConstructedWith(Object... constructorParams) {
        var params = Arrays.stream(constructorParams).map(param -> new Value(param)).toArray(Value[]::new);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            params,
            objectPrepCalls,
            true,
            tests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan onObjectPreparedWith(ObjectPreCall... prepCalls) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            prepCalls,
            true,
            tests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan useConsole() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            tests,
            true,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan useConsoleAndShowStdinInputs() {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            tests,
            true,
            true,
            beforeAssertions
        );
    }

    public Koan withStdInInputs(List<Localizable<String>> inputs) {
        return withUpdatedTest(kTest -> kTest.withStdInInputs(inputs));
    }

    public Koan withStdInInputs(String... inputs) {
        return withStdInInputs(
            Arrays.stream(inputs)
                .map(input -> (Localizable<String>)new Global<String>(input))
                .toList()
        );
    }

    public Koan withSeed(long seed) {
        return withUpdatedTest(kTest -> kTest.withSeed(seed));
    }

    public Koan then(ResultAssertion... assertions) {
        return withUpdatedTest(kTest -> kTest.withAssertions(assertions));
    }

    boolean executeBeforeAssertions(Printer p, Locale locale) {
        var allAssertions = new ArrayList<KoanAssertion>();
        if (onObject) {
            allAssertions.add(Assertions.assertClassIsInstantiable());
            allAssertions.add(Assertions.assertConstructorIsInvokable());
        }
        allAssertions.add(Assertions.assertMethodIsInvokable(methodName, !onObject, methodParamTypes));
        Collections.addAll(allAssertions, beforeAssertions);

        for(var assertion: allAssertions) {
            var asserted = assertion.validate(p, locale, this);
            if (!asserted) {
                return false;
            }
        }

        return true;
    }

    private Koan withUpdatedTest(Function<KoanTest, KoanTest> newCall) {
        if (tests.length == 0) {
            throw new IllegalArgumentException("No current call in Koan");
        }
        var currentTest = tests[tests.length - 1];
        var newTests = tests.clone();
        newTests[newTests.length - 1] = newCall.apply(currentTest);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            objectPrepCalls,
            onObject,
            newTests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }
}
