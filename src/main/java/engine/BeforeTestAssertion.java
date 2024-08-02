package engine;

/**
 * Asserts something about student's code before koan tests are run.
 */
@FunctionalInterface
public interface BeforeTestAssertion {
    /**
     * Validates that the structure of student code for a given Koan is correct, and display feedback using the given Printer.
     * @param p The printer to give feedback through.
     * @return Whether or not the assertion is successful or not.
     */
    boolean validate(final Printer p, final Locale locale, final Koan koan);
}
