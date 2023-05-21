package engine;

/**
 * Supports a localizable item, typically a String to be displayed, which could be different from one language to the other.
 */
public interface Localizable<T> {
    /**
     * Returns the item for the given locale.
     */
    T get(Locale locale);

    static Local<Class<?>> localClass(Class<?> enItem) {
        return new Local<>(enItem);
    }

    static <T> Local<T> local(T enItem) {
        return new Local<>(enItem);
    }

    static <T> Global<T> global(T item) {
        return new Global<>(item);
    }
}
