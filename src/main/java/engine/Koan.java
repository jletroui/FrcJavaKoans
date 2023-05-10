package engine;

public class Koan {
    public final Class<?> koanClass;
    public final String methodName;
    public final Assertion[] assertions;
    public final String[] stdInInputs;

    public Koan(Class<?> koanClass, String methodName, Assertion... assertions) {
        this(koanClass, methodName, assertions, new String[0]);
    }

    private Koan(Class<?> koanClass, String methodName, Assertion[] assertions, String[] stdInInputs) {
        this.koanClass = koanClass;
        this.methodName = methodName;
        this.assertions = assertions;
        this.stdInInputs = stdInInputs;
    }

    public Koan withStdInInputs(String... inputs) {
        return new Koan(koanClass, methodName, assertions, inputs);
    }
}
