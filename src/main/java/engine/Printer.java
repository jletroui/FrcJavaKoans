package engine;

/**
 * A Printer allows to print feedback to the student.
 */
public interface Printer {
    /**
     * A Printer which is silent, hence displaying nothing.
     * Useful when executing koans outside the view of the student.
     */
    static Printer SILENT = SilentPrinter.INSTANCE;

    /**
     * Prints a single empty line.
     */
    void println();
    /**
     * Prints the given line out of the given String template and its parameters.
     */
    void println(String template, Object... params);
    /**
     * Prints the given line out of the given localizable String template and its parameters.
     */
    void println(Localizable<String> template, Object... params);
}
