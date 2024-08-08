package engine.script;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

import engine.KoanBugException;

public class Type {
    public static final Map<Class<?>, Class<?>> UNBOXED = Map.of(
        Integer.class, int.class,
        Double.class, double.class,
        Boolean.class, boolean.class,
        Long.class, long.class,
        Float.class, float.class,
        Character.class, char.class
    );

    private Class<?> clasz = null;
    public final String className;
    public final String simpleClassName;
    public final String packageName;

    public Type(final String className) {
        this.className = className;
        var lastDotIndex = className.lastIndexOf(".");
        this.simpleClassName = className.substring(lastDotIndex + 1);
        this.packageName = className.substring(0, lastDotIndex);
    }

    public Type(final Class<?> clasz) {
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
            } catch(final ClassNotFoundException cnfe) {
                throw new KoanBugException(String.format("The class %s is not found, which should have been already caught by a missing assertion in this or a previous Koans.", className));
            }
        }
        return clasz;
    }

    public Object unsafeInstantiate(final ExecutionContext ctx, final Expression[] constructorParams) {
        final var clasz = unsafeResolve();
        if (!isInstantiable(clasz)) {
            throw new KoanBugException(String.format("The class %s is not instantiable, which should have been already caught by a missing assertion in this or a previous Koans.", clasz.getSimpleName()));
        }

        final var constructorCandidates = Arrays
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
            return constructorCandidates.get(0).newInstance(Expression.executeAll(ctx, constructorParams));
        } catch (final InstantiationException _ie) {
            throw new KoanBugException(String.format(
                "Koans should assert that class %s is instantiable.",
                clasz.getName()
            ));
        } catch(final IllegalAccessException _iae) {
            throw new KoanBugException(String.format("The constructor of %s is not accessible, which should have been already caught by a missing assertion in this or a previous Koans.", clasz.getSimpleName()));
        } catch(final SecurityException _se) {
            throw new KoanBugException(String.format("Stop messing with class loaders ;). Cannot instantiate %s.", clasz.getSimpleName()));
        } catch(final IllegalArgumentException _iae) {
            throw new KoanBugException(String.format("The constructor of %s do not possess the right parameters, which should have been already caught by a missing assertion in this or a previous Koans.", clasz.getSimpleName()));
        } catch(final InvocationTargetException ite) {
            throw new ScriptExecutionException(ite, String.format("new %s(...)", clasz.getSimpleName()));
        }
}

    public static Class<?>[] unsafeResolveTypes(final Type[] types) {
        final var res = new Class<?>[types.length];

        for(int i = 0; i<types.length; i++) {
            res[i] = types[i].unsafeResolve();
        }

        return res;
    }

    public static Type type(String className) {
        return new Type(className);
    }

    public static Type type(Class<?> clasz) {
        return new Type(clasz);
    }

    public static Type[] toTypes(final Class<?>[] classes) {
        return Arrays.stream(classes).map(Type::type).toArray(Type[]::new);
    }

    public static boolean isInstantiable(final Class<?> clasz) {
        final int modifiers = clasz.getModifiers();
        return
            Modifier.isPublic(modifiers) &&
            !clasz.isInterface() &&
            !Modifier.isAbstract(modifiers) &&
            !clasz.isArray() &&
            !clasz.isPrimitive() &&
            !clasz.equals(void.class);
    }

    @Override
    public String toString() {
        return className;
    }
}
