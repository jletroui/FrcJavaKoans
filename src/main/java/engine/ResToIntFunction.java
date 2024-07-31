package engine;

@FunctionalInterface
public interface ResToIntFunction {
    int apply(final KoanResult res);
}
