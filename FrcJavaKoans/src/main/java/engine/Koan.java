package engine;

public class Koan {
    public final Class<?> koanClass;
    public final String methodName;
    public final Assertion[] assertions;

    public Koan(Class<?> koanClass, String methodName, Assertion... assertions) {
        this.koanClass = koanClass;
        this.methodName = methodName;
        this.assertions = assertions;
    }
}
