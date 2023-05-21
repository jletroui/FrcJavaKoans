package engine;

/**
 * An assertion is the main way to assert that the student succeeded a koan.
 */
public interface Assertion {
    /**
     * Validates that the given result is correct, and display feedback using the given Printer.
     * @param p The printer to give feedback through.
     * @param result The details of the Koan execution.
     * @return Whether or not the assertion is successful or not.
     */
    boolean validate(Locale locale, Printer p, KoanResult result);
}
