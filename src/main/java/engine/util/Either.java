package engine.util;

import java.util.function.Function;

public sealed interface Either<V, T extends Throwable> {
    boolean hasValue();
    V getValue();
    <U> Either<U, T> map(final Function<V, U> mapValueFunc, final Function<T, U> mapErrorFunc);

    static <V, T extends Throwable> Either<V, T> of(final V value) {
        return new Value<V, T>(value);
    }

    static <V, T extends Throwable> Either<V, T> ofError(final T throwable) {
        return new Error<V, T>(throwable);
    }
}

final class Value<V, T extends Throwable> implements Either<V, T> {
    private final V value;

    public Value(final V value) {
        this.value = value;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public <U> Either<U, T> map(final Function<V, U> mapValueFunc, final Function<T, U> _mapErrorFunc) {
       return new Value<U, T>(mapValueFunc.apply(value));
    }
}

final class Error<V, T extends Throwable> implements Either<V, T> {
    private final T throwable;

    public Error(final T throwable) {
        this.throwable = throwable;
    }

    @Override
    public boolean hasValue() {
        return false;
    }

    @Override
    public V getValue() {
        throw new IllegalStateException("This Either has no value.");
    }

    @Override
    public <U> Either<U, T> map(final Function<V, U> _mapValueFunc, final Function<T, U> mapErrorFunc) {
       return new Value<U, T>(mapErrorFunc.apply(throwable));
    }
}
