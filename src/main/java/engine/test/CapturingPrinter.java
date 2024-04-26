package engine.test;

import java.util.ArrayList;
import java.util.Arrays;

import engine.Locale;
import engine.Localizable;
import engine.Printer;

/**
 * A printer capturing feedback to the student in unit tests.
 */
public class CapturingPrinter implements Printer {
    private final Locale locale;
    private final ArrayList<String> output = new ArrayList<>();

    public CapturingPrinter(Locale locale) {
        this.locale = locale;
    }

    public boolean hasCaptured(Line... lines) {
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
    public void println(String template, Object... params) {
        output.add(String.format(template, params));
    }

    @Override
    public void println(Localizable<String> template, Object... params) {
        println(template.get(locale), params);
    } 
}
