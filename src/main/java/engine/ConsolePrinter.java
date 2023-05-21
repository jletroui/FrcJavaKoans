package engine;

public class ConsolePrinter implements Printer {
    private final Locale locale;

    public ConsolePrinter(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(String template, Object... params) {
        System.out.println(String.format(template, params));
    }

    @Override
    public void println(Localizable<String> template, Object... params) {
        System.out.println(String.format(template.get(locale), params));
    } 
}
