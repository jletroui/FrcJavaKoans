package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class KoanTargetMethod {
    public final KoanTest koanTest;
    private final Method method;
    public final Class<?> clasz;
    private final Object object;
    private final Value[] localizedParams;
    private final Value[] localizedConstructorParams;

    public KoanTargetMethod(KoanTest koanTest, Locale locale) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.koanTest = koanTest;
        
        this.clasz = koanTest.koan.exerciseClassName.isPresent() ? Class.forName(koanTest.koan.exerciseClassName(locale)) : koanTest.koan.koanClass.get(locale);
        this.method = clasz.getMethod(koanTest.koan.methodName, koanTest.koan.methodParamTypes);

        if (koanTest.koan.onObject) {
            try {
                var constructor = clasz.getConstructor(resolveParamTypes(koanTest.koan.constructorParamTypes));
                this.localizedConstructorParams = localize(locale, koanTest.constructorParams);
                var constructorParams = Value.resolve(localizedConstructorParams);
                this.object = constructor.newInstance(constructorParams);
            } catch(NoSuchMethodException nsme) {
                throw new NoSuchConstructorException();
            }
            if (Modifier.isStatic(method.getModifiers())) {
                throw new NoDynamicMethodException();
            }
        } else {
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new NoStaticMethodException();
            }
            this.object = null;
            this.localizedConstructorParams = new Value[0];
        }

        this.localizedParams = localize(locale, koanTest.params);
    }

    public Object invoke() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        return method.invoke(object, Value.resolve(localizedParams));
    }

    public boolean hasParameters() {
        return localizedParams.length > 0;
    }

    private static Class<?>[] resolveParamTypes(Type[] params) throws ClassNotFoundException {
        final var res = new Class<?>[params.length];

        for(int i = 0; i<params.length; i++) {
            res[i] = params[i].resolve();
        }

        return res;
    }

    @SuppressWarnings("rawtypes")
    private static Value[] localize(Locale locale, Value[] params) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        var localized = new Value[params.length];
        for(int i=0; i<params.length; i++) {
            final var resolved = params[i].resolve();
            localized[i] = resolved instanceof Local ? new Value(((Local)resolved).get(locale)) : params[i];
        }
        return localized;
    }

    private static String formatParams(Value[] params) {
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