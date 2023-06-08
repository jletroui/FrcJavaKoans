package engine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Stores all the information required to execute and assess the result of a koan.
 */
public class Koan {
    final Localizable<Class<?>> koanClass;
    public final String methodName;
    public final Class<?>[] methodParamTypes;
    public final Type[] constructorParamTypes;
    public final boolean onObject;
    private final Value[] constructorParams;
    public final KoanTest[] tests;
    public final boolean usesConsole;
    public final boolean showStdInInputs;
    public final Optional<String> exerciseClassName;
    public final Optional<String> exerciseClassPackage;
    public final KoanAssertion[] koanAssertions;

    public Koan(Localizable<Class<?>> koanClass, String methodName, Class<?>... methodParamTypes) {
        this(
            koanClass,
            methodName,
            methodParamTypes,
            new Type[0],
            new Value[0],
            false,
            new KoanTest[0],
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
        Type[] constructorParamTypes,
        Value[] constructorParams,
        boolean onObject,
        KoanTest[] tests,
        boolean usesConsole,
        boolean showStdInInputs,
        Optional<String> exerciseClassName,
        Optional<String> exerciseClassPackage,
        KoanAssertion[] koanAssertions) {
        this.koanClass = Objects.requireNonNull(koanClass, "koanClass must not be null");
        this.methodName = Objects.requireNonNull(methodName, "methodName must not be null");
        this.methodParamTypes = Objects.requireNonNull(methodParamTypes, "methodParamTypes must not be null");
        this.tests = Objects.requireNonNull(tests, "calls must not be null");
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
        var newTests = Arrays.copyOf(tests, tests.length + 1);
        
        newTests[newTests.length - 1] = new KoanTest(this, params, constructorParams);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            newTests,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan inClass(String className, Type... constructorParamTypes) {
        var separatorIndex = className.lastIndexOf(".");

        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            onObject,
            tests,
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
            tests,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan withObjectConstructedWith(Value... constructorParams) {
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            constructorParams,
            true,
            tests,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan withObjectConstructedWith(Object... constructorParams) {
        var params = Arrays.stream(constructorParams).map(param -> new Value(param)).toArray(Value[]::new);
        return new Koan(
            koanClass,
            methodName,
            methodParamTypes,
            constructorParamTypes,
            params,
            true,
            tests,
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
            tests,
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
            tests,
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
            new KoanTest[] { new KoanTest(this, new Object[0], constructorParams) },
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }

    public Koan withStdInInputs(List<Localizable<String>> inputs) {
        return withUpdatedTest(call -> call.withStdInInputs(inputs));
    }

    public Koan withStdInInputs(String... inputs) {
        return withStdInInputs(
            Arrays.stream(inputs)
                .map(input -> (Localizable<String>)new Global<String>(input))
                .toList()
        );
    }

    public Koan then(ResultAssertion... assertions) {
        return withUpdatedTest(call -> call.withAssertions(assertions));
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

    boolean executeAssertions(Printer p, KoanTargetMethod targetMethod) throws ClassNotFoundException {
        for(var assertion: koanAssertions) {
            var asserted = assertion.validate(p, targetMethod);
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
            onObject,
            newTests,
            usesConsole,
            showStdInInputs,
            exerciseClassName,
            exerciseClassPackage,
            koanAssertions
        );
    }
}
