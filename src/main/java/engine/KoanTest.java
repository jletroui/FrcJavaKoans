package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class KoanTest {
    private static final List<Localizable<String>> NO_STD_IN_INPUTS = List.of();

    final Value[] params;
    final Value[] constructorParams;
    final ResultAssertion[] assertions;
    private final List<Localizable<String>> stdInInputs;
    public final Koan koan;
    private final long seed;
    private KoanTargetMethod targetMethod = null;
    final ObjectPreCall[] objectPrepCalls;

    public KoanTest(Koan koan, Object[] params, Value[] constructorParams, ObjectPreCall[] objectPrepCalls) {
        this(koan, Value.toValues(params), constructorParams, new ResultAssertion[0], KoanTest.NO_STD_IN_INPUTS, Math.round(Math.random() * Long.MAX_VALUE), objectPrepCalls);
    }

    private KoanTest(Koan koan, Value[] params, Value[] constructorParams, ResultAssertion[] assertions, List<Localizable<String>> stdInInputs, long seed, ObjectPreCall[] objectPrepCalls) {
        this.koan = koan;
        this.params = params;
        this.constructorParams = constructorParams;
        this.assertions = assertions;
        this.stdInInputs = stdInInputs;
        this.seed = seed;
        this.objectPrepCalls = objectPrepCalls;
    }

    public KoanTest withStdInInputs(List<Localizable<String>> inputs) {
        if (this.stdInInputs.size() > 0) {
            throw new IllegalArgumentException("Current call already have StdIn inputs");
        }

        return new KoanTest(koan, params, constructorParams, assertions, inputs, seed, objectPrepCalls);
    }
    
    public KoanTest withAssertions(ResultAssertion[] assertions) {
        if (this.assertions.length > 0) {
            throw new IllegalArgumentException("Current call already have assertions");
        }

        return new KoanTest(koan, params, constructorParams, assertions, stdInInputs, seed, objectPrepCalls);
    }

    public KoanTest withSeed(long seed) {
        return new KoanTest(koan, params, constructorParams, assertions, stdInInputs, seed, objectPrepCalls);
    }

    public String[] stdInInputs(Locale locale) {
        return stdInInputs.stream()
            .map(p -> p.get(locale))
            .toArray(String[]::new);
    }

    public void setupRandomForKoan() {
        Helpers.setupRandomForKoan(seed);
    }

    KoanTargetMethod resolveTargetMethod(Locale locale) throws InvocationTargetException {
        // // Kind of expensive, so cache it.  
        // if (targetMethod == null) {
        //     // Fine to cache: we will never call this with 2 different locales in the same session

        //     targetMethod = new KoanTargetMethod(this, locale);
        // }

        // return targetMethod;
        return new KoanTargetMethod(this, locale);
    }
}
