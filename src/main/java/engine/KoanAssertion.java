package engine;

/**
 * An assertion is the main way to assert that the student succeeded a koan.
 */
public interface KoanAssertion {
    /**
     * Validates that the given target class of the given Koan is correct, and display feedback using the given Printer.
     * @param p The printer to give feedback through.
     * @return Whether or not the assertion is successful or not.
     */
    boolean validate(Printer p, KoanTargetMethod methodDetails) throws ClassNotFoundException;
}
