package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Implements Localizable for items which do vary from one locale to the other.
 */
public class Local<T> implements Localizable<T> {
    private final Map<Locale, T> items;

    public Local(T enItem) {
        this(new HashMap<>(Map.of(Locale.en, enItem)));
    }

    private Local(Map<Locale, T> items) {
        this.items = items;
    }

    public Local<T> fr(T frItem) {
        items.put(Locale.fr, frItem);
        return this;
    }

    public Local<T> map(Function<T, T> mapFunc) {
        var newItems = Map.copyOf(items);
        items.replaceAll((locale, item) -> mapFunc.apply(item));
        return new Local<T>(newItems);
    }

    public T get(Locale locale) {
        return items.get(locale);
    }
}
