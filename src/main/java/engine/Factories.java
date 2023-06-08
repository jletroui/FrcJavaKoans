package engine;

public class Factories {
    public static Type type(String className) {
        return new Type(className);
    }

    public static Type type(Class<?> clasz) {
        return new Type(clasz);
    }

    public static Value value(Object value) {
        return new Value(value);
    }

    public static Value value(Type type, Value... constructorParameters) {
        return new Value(type, constructorParameters);
    }

    public static Value value(Type type, Object... constructorParameters) {
        return new Value(type, constructorParameters);
    }

    public static <T> Global<T> global(T item) {
        return new Global<T>(item);
    }

    public static  <T> Local<T> local(T enItem) {
        return new Local<T>(enItem);
    }

    public static Local<Class<?>> localClass(Class<?> enItem) {
        return new Local<>(enItem);
    }
}
