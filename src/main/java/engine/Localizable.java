package engine;

/**
 * Supports a localizable item, typically a String to be displayed, which could be different from one language to the other.
 */
public interface Localizable<T> {
    /**
     * Returns the item for the given locale.
     */
    T get(Locale locale);
}
