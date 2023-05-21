package engine;

public class SilentPrinter implements Printer {
    public static final Printer INSTANCE = new SilentPrinter();

    private SilentPrinter() {
    }

    @Override
    public void println() {
        // Silent        
    }

    @Override
    public void println(String template, Object... params) {
        // Silent        
    }

    @Override
    public void println(Localizable<String> template, Object... params) {
        // Silent        
    }
}
