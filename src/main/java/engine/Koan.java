package engine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import engine.script.Expression;
import engine.script.Type;

/**
 * Stores all the information required to execute and assess the result of a koan.
 */
public class Koan {
    final Localizable<Type> koanClass;
    final Localizable<String> koanName;
    final KoanTest[] tests;
    final boolean usesConsole;
    final boolean showStdInInputs;
    final BeforeTestAssertion[] beforeAssertions;

    public Koan(final Localizable<Class<?>> koanClass, final Localizable<String> koanName) {
        this(
            koanClass.map(Type::new),
            koanName,
            new KoanTest[0],
            false,
            false,
            new BeforeTestAssertion[0]
        );
    }

    private Koan(
        final Localizable<Type> koanClass,
        final Localizable<String> koanName,
        final KoanTest[] tests,
        final boolean usesConsole,
        final boolean showStdInInputs,
        final BeforeTestAssertion[] koanAssertions) {
        this.koanClass = Objects.requireNonNull(koanClass, "koanClass must not be null");
        this.koanName = Objects.requireNonNull(koanName, "methodName must not be null");
        this.tests = Objects.requireNonNull(tests, "calls must not be null");
        this.usesConsole = usesConsole;
        this.showStdInInputs = showStdInInputs;
        this.beforeAssertions = koanAssertions;
    }

    public Koan when(final Expression... script) {
        var newTests = Arrays.copyOf(tests, tests.length + 1);
        
        newTests[newTests.length - 1] = new KoanTest(this, script);
        return new Koan(
            koanClass,
            koanName,
            newTests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan beforeFirstTest(final BeforeTestAssertion... koanAssertions) {
        return new Koan(
            koanClass,
            koanName,
            tests,
            usesConsole,
            showStdInInputs,
            koanAssertions
        );
    }

    public Koan useConsole() {
        return new Koan(
            koanClass,
            koanName,
            tests,
            true,
            showStdInInputs,
            beforeAssertions
        );
    }

    public Koan useConsoleAndShowStdinInputs() {
        return new Koan(
            koanClass,
            koanName,
            tests,
            true,
            true,
            beforeAssertions
        );
    }

    public Koan withStdInInputs(final List<Localizable<String>> inputs) {
        return withUpdatedTest(kTest -> kTest.withStdInInputs(inputs));
    }

    public Koan withStdInInputs(final String... inputs) {
        return withStdInInputs(
            Arrays.stream(inputs)
                .map(input -> (Localizable<String>)new Global<String>(input))
                .toList()
        );
    }

    public Koan withSeed(final long seed) {
        return withUpdatedTest(kTest -> kTest.withSeed(seed));
    }

    public Koan then(final ResultAssertion... assertions) {
        return withUpdatedTest(kTest -> kTest.withAssertions(assertions));
    }

    private Koan withUpdatedTest(final Function<KoanTest, KoanTest> newCall) {
        if (tests.length == 0) {
            throw new IllegalArgumentException("No current call in Koan");
        }
        var currentTest = tests[tests.length - 1];
        var newTests = tests.clone();
        newTests[newTests.length - 1] = newCall.apply(currentTest);
        return new Koan(
            koanClass,
            koanName,
            newTests,
            usesConsole,
            showStdInInputs,
            beforeAssertions
        );
    }
}
