package engine;

import engine.console.Printer;

/**
 * An assertion is the main way to assert that the student succeeded a koan.
 */
@FunctionalInterface
public interface ResultAssertion {
    /**
     * Validates that the given result conforms to expectations, and display feedback using the given Printer.
     * @param p The printer to give feedback through.
     * @param output The output of the Koan execution.
     * @return Whether or not the assertion is successful.
     */
    boolean validate(final Printer p, final TestOutput output);

    public static boolean validateAll(final Printer p, final TestOutput output, final ResultAssertion... assertions) {
        for (final ResultAssertion assertion : assertions) {
            if (!assertion.validate(p, output)) {
                return false;
            }
        }
        
        return true;
    }
}
