package engine;

/**
 * A printer displaying feedback to the student in the console.
 */
public class ConsolePrinter implements Printer {
    private final Locale locale;

    public ConsolePrinter(Locale locale) {
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
    public void println(String template, Object... params) {
        synchronized(System.out) {
            System.out.println(String.format(template, params));
            System.out.flush();
        }
    }

    @Override
    public void println(Localizable<String> template, Object... params) {
        synchronized(System.out) {
            System.out.println(String.format(template.get(locale), params));
            System.out.flush();
        }
    } 
}
