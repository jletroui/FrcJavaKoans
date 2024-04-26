package engine;

import java.util.function.Function;

/**
 * Implements Localizable for items which do NOT vary from one locale to the other, and are constants accross locales.
 */
public class Global<T> implements Localizable<T> {
    private final T item;

    public Global(T item) {
        this.item = item;
    }

    public T get(Locale locale) {
        return item;
    }

    public <U> Localizable<U> map(Function<T, U> transformFunction) {
        return new Global<U>(transformFunction.apply(item));
    }
}
