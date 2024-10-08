package engine.test.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.console.Fmt;
import engine.console.Printer;
import engine.text.Locale;
import engine.text.Localizable;

/**
 * A printer capturing feedback displayed to the student in automated tests.
 */
public final class CapturingPrinter implements Printer, SenseiOutputCapture {
    private final Locale locale;
    private final List<String> output = new ArrayList<>();

    public CapturingPrinter(final Locale locale) {
        this.locale = locale;
    }

    @Override
    public boolean stdOutLinesMatch(final Fmt... lines) {
        return output.equals(
            Arrays
                .stream(lines)
                .map(line -> line.format(locale))
                .toList()
        );
    }

    @Override
    public String capturedStdOutAsString() {
        final var res = new StringBuilder();
        for(final var line: output) {
            res.append(line);
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

    @Override
    public void println() {
        output.add("");
    }

    @Override
    public void println(Fmt fmt) {
        output.add(fmt.format(locale));
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
