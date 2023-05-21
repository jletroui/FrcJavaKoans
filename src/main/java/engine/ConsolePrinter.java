package engine;

public class ConsolePrinter implements Printer {

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(String template, Object... params) {
        System.out.println(String.format(template, params));
    }
    
}
