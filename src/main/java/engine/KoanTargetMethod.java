package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class KoanTargetMethod {
    final KoanTest koanTest;
    private final Method method;
    private final Class<?> clasz;
    final Object object;
    private final Object[] localizedParams;
    private final Object[] localizedConstructorParams;

    public KoanTargetMethod(KoanTest koanTest, Locale locale) throws InvocationTargetException {
        this.koanTest = koanTest;
        this.clasz = koanTest.koan.koanClass.get(locale).unsafeResolve();

        try {
            this.method = clasz.getMethod(koanTest.koan.methodName, koanTest.koan.methodParamTypes);
        } catch(NoSuchMethodException nsme) {
            throw new KoanBugException(String.format("The method %s is not found, which should have been already caught by a missing assertion in this or a previous Koans.", koanTest.koan.methodName));
        }

        if (koanTest.koan.onObject) {
            if (Modifier.isStatic(method.getModifiers())) {
                throw new KoanBugException(String.format("The method %s is not static, which should have been already caught by a missing assertion in this or a previous Koans.", koanTest.koan.methodName));
            }

            this.object = koanTest.koan.koanClass.get(locale).unsafeInstantiate(locale, koanTest.constructorParams);
            this.localizedConstructorParams = Value.resolve(locale, koanTest.constructorParams);
        } else {
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new KoanBugException(String.format("The method %s is static, which should have been already caught by a missing assertion in this or a previous Koans.", koanTest.koan.methodName));
            }
            this.object = null;
            this.localizedConstructorParams = new Object[0];
        }

        this.localizedParams = Value.resolve(locale, koanTest.params);
    }

    public Object invoke() throws InvocationTargetException {
        try {
            return method.invoke(object, localizedParams);
        } catch(IllegalAccessException iae) {
                throw new KoanBugException(String.format("The method %s is not accessible, which should have been already caught by a missing assertion in this or a previous Koans.", koanTest.koan.methodName));
        }

    }

    public boolean hasParameters() {
        return localizedParams.length > 0;
    }

    private static String formatParams(Object[] params) {
        return String.join(
            ", ",
            Arrays.stream(params)
                .map(p -> p.toString())
                .toArray(String[]::new)
        );
    }

    public String toString() {
        var constructorCall = "";

        if (koanTest.koan.onObject) {
            constructorCall = String.format(
                "new %s(%s).",
                clasz.getSimpleName(),
                formatParams(localizedConstructorParams)
            );
        }

        return String.format("%s%s(%s)", constructorCall, koanTest.koan.methodName, formatParams(localizedParams));
    }
}
