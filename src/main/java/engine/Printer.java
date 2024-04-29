package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.test.Line;

/**
 * A Printer allows to print feedback to the student.
 */
public sealed interface Printer {
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
    void println(final String template, final Object... params);
    /**
     * Prints the given line out of the given localizable String template and its parameters.
     */
    void println(final Localizable<String> template, final Object... params);
}

/**
 * A printer displaying feedback to the student in the console.
 */
final class ConsolePrinter implements Printer {
    private final Locale locale;

    public ConsolePrinter(final Locale locale) {
        this.locale = locale;
    }

    @Override
    public void println() {
        synchronized(System.out) {
            System.out.println();
            System.out.flush();
        }
    }

    @Override
    public void println(final String template, final Object... params) {
        synchronized(System.out) {
            System.out.println(String.format(template, params));
            System.out.flush();
        }
    }

    @Override
    public void println(final Localizable<String> template, final Object... params) {
        synchronized(System.out) {
            System.out.println(String.format(template.get(locale), params));
            System.out.flush();
        }
    } 
}

/**
 * A printer capturing feedback displayed to the student in unit tests.
 */
final class CapturingPrinter implements Printer {
    private final Locale locale;
    private final List<String> output = new ArrayList<>();

    public CapturingPrinter(final Locale locale) {
        this.locale = locale;
    }

    public boolean hasCaptured(final Line... lines) {
        return output.equals(
            Arrays
                .stream(lines)
                .map(line -> line.resolve(locale))
                .toList()
        );
    }

    public void displayToConsole() {
        for(var line: output) {
            System.out.println(line);
        }
    }

    @Override
    public void println() {
        output.add("");
    }

    @Override
    public void println(final String template, final Object... params) {
        output.add(String.format(template, params));
    }

    @Override
    public void println(final Localizable<String> template, final Object... params) {
        println(template.get(locale), params);
    } 
}

/**
 * A Printer which is silent, hence displaying nothing.
 * Useful when executing koans outside the view of the student.
 */
final class SilentPrinter implements Printer {
    public static final Printer INSTANCE = new SilentPrinter();

    private SilentPrinter() {
    }

    @Override
    public void println() {
        // Silent        
    }

    @Override
    public void println(final String _template, final Object... _params) {
        // Silent        
    }

    @Override
    public void println(final Localizable<String> _template, final Object... _params) {
        // Silent        
    }
}
