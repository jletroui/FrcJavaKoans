package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Type {
    private Class<?> clasz = null;
    public String className;
    public final String simpleClassName;
    public final String packageName;

    public Type(String className) {
        this.className = className;
        var lastDotIndex = className.lastIndexOf(".");
        this.simpleClassName = className.substring(lastDotIndex + 1);
        this.packageName = className.substring(0, lastDotIndex);
    }

    public Type(Class<?> clasz) {
        this.clasz = clasz;
        this.className = clasz.getName();
        this.simpleClassName = clasz.getSimpleName();
        this.packageName = clasz.getPackageName();
    }

    public Class<?> resolve() throws ClassNotFoundException {
        if (clasz == null) {
            clasz = Class.forName(className);
        }
        return clasz;
    }

    public Class<?> unsafeResolve() {
        if (clasz == null) {
            try {
                clasz = Class.forName(className);
            } catch(ClassNotFoundException cnfe) {
                throw new KoanBugException(String.format("The class %s is not found, which should have been already caught by a missing assertion in this or a previous Koans.", className));
            }
        }
        return clasz;
    }

    public Object unsafeInstantiate(Locale locale, Value[] constructorParams) throws InvocationTargetException {
        var clasz = unsafeResolve();
        if (!Helpers.isInstantiable(clasz)) {
            throw new KoanBugException(String.format("The class %s is not instantiable, which should have been already caught by a missing assertion in this or a previous Koans.", clasz.getSimpleName()));
        }

        var constructorCandidates = Arrays
            .stream(clasz.getConstructors())
            .filter(c -> c.getParameters().length == constructorParams.length)
            .toList();

        if (constructorCandidates.size() > 1) {
            throw new KoanBugException(String.format(
                "Koans do not support exercises with classes with multiple constructors with the same number of arguments. Found %d constructors with %d params in %s",
                constructorCandidates.size(),
                constructorParams.length,
                this
            ));
        } else if (constructorCandidates.size() == 0) {
            throw new KoanBugException(String.format(
                "Koans should assert that constructors have the right number of arguments. Did not find a constructor in %s with %d arguments.",
                this,
                constructorParams.length
            ));
        }

        try {
            return constructorCandidates.get(0).newInstance(Value.resolve(locale, constructorParams));
        } catch (InstantiationException ie) {
            throw new KoanBugException(String.format(
                "Koans should assert that class %s is instantiable.",
                clasz.getName()
            ));
        } catch(IllegalAccessException iae) {
            throw new KoanBugException(String.format("The constructor of %s is not accessible, which should have been already caught by a missing assertion in this or a previous Koans.", clasz.getSimpleName()));
        } catch(SecurityException se) {
            throw new KoanBugException(String.format("Stop messing with class loaders ;). Cannot instantiate %s.", clasz.getSimpleName()));
        }
}

    public static Class<?>[] unsafeResolveTypes(Type[] types) {
        final var res = new Class<?>[types.length];

        for(int i = 0; i<types.length; i++) {
            res[i] = types[i].unsafeResolve();
        }

        return res;
    }

    @Override
    public String toString() {
        return className;
    }
}
