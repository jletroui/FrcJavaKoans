package engine.util;

import engine.TestOutput;

@FunctionalInterface
public interface ResToIntFunction {
    int apply(final TestOutput res);
}
