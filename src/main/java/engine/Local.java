package engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements Localizable for items which do vary from one locale to the other.
 */
public class Local<T> implements Localizable<T> {
    private final Map<Locale, T> items = new HashMap<>();

    public Local(T enItem) {
        items.put(Locale.en, enItem);
    }

    public Local<T> fr(T frItem) {
        items.put(Locale.fr, frItem);
        return this;
    }

    public T get(Locale locale) {
        return items.get(locale);
    }
}
