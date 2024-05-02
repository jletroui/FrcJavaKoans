package engine;

import java.util.List;

import engine.script.Expression;

public class KoanTest {
    private static final List<Localizable<String>> NO_STD_IN_INPUTS = List.of();

    final Expression[] script;
    final ResultAssertion[] assertions;
    final Koan koan;
    private final List<Localizable<String>> stdInInputs;
    private final long seed;

    public KoanTest(final Koan koan, final Expression[] script) {
        this(koan, script, new ResultAssertion[0], KoanTest.NO_STD_IN_INPUTS, Math.round(Math.random() * Long.MAX_VALUE));
    }

    private KoanTest(final Koan koan, final Expression[] script, final ResultAssertion[] assertions, final List<Localizable<String>> stdInInputs, final long seed) {
        this.koan = koan;
        this.script = script;
        this.assertions = assertions;
        this.stdInInputs = stdInInputs;
        this.seed = seed;
    }

    public KoanTest withStdInInputs(final List<Localizable<String>> inputs) {
        if (this.stdInInputs.size() > 0) {
            throw new IllegalArgumentException("Current call already have StdIn inputs");
        }

        return new KoanTest(koan, script, assertions, inputs, seed);
    }
    
    public KoanTest withAssertions(final ResultAssertion[] assertions) {
        if (this.assertions.length > 0) {
            throw new IllegalArgumentException("Current call already have assertions");
        }

        return new KoanTest(koan, script, assertions, stdInInputs, seed);
    }

    public KoanTest withSeed(final long seed) {
        return new KoanTest(koan, script, assertions, stdInInputs, seed);
    }

    public String[] stdInInputs(final Locale locale) {
        return stdInInputs.stream()
            .map(p -> p.get(locale))
            .toArray(String[]::new);
    }

    public void setupRandomForKoan() {
        Helpers.setupRandomForKoan(seed);
    }

    public boolean hasStdInputs() {
        return stdInInputs.size() > 0;
    }
}
