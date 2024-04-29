package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Supports a localizable item, typically a String to be displayed, which could be different from one language to the other.
 */
public sealed interface Localizable<T> {
    /**
     * Returns the item for the given locale.
     */
    T get(Locale locale);
    /**
     * Returns a new localizable with an item for the 'fr' locale.
     */
    Localizable<T> fr(T frItem);

    <U> Localizable<U> map(Function<T, U> transformFunction);

    public static <T> Localizable<T> global(T item) {
        return new Global<T>(item);
    }

    public static  <T> Localizable<T> local(T enItem) {
        return new Local<T>(enItem);
    }

    public static Localizable<Class<?>> localClass(Class<?> enItem) {
        return new Local<>(enItem);
    }
}

/**
 * Implements Localizable for items which do vary from one locale to the other.
 */
final class Local<T> implements Localizable<T> {
    private final Map<Locale, T> items;

    public Local(T enItem) {
        this(new HashMap<>(Map.of(Locale.en, enItem)));
    }

    private Local(Map<Locale, T> items) {
        this.items = items;
    }

    @Override
    public Localizable<T> fr(T frItem) {
        items.put(Locale.fr, frItem);
        return this;
    }

    @Override
    public <U> Local<U> map(final Function<T, U> mapFunc) {
        var newItems = items.entrySet()
            .stream()
            .collect(Collectors.toMap(Entry::getKey, entry -> mapFunc.apply(entry.getValue())));
        return new Local<U>(newItems);
    }

    @Override
    public T get(Locale locale) {
        return items.get(locale);
    }
}

/**
 * Implements Localizable for items which do NOT vary from one locale to the other, and are constants accross locales.
 */
final class Global<T> implements Localizable<T> {
    private final T item;

    public Global(T item) {
        this.item = item;
    }

    @Override
    public Localizable<T> fr(T frItem) {
        throw new IllegalStateException("This localizable do not vary from one locale to the other. Did you mean to create the Localizable with Localizable.local(...) instead?");
    }

    @Override
    public T get(Locale locale) {
        return item;
    }

    @Override
    public <U> Localizable<U> map(Function<T, U> transformFunction) {
        return new Global<U>(transformFunction.apply(item));
    }
}
