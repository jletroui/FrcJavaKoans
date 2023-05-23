package engine;

/**
 * Console text coloring.
 */
public class Color {
    private static final String RESET = "\033[0m";

    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String CYAN = "\033[0;36m";

    public static Local<String> red(Local<String> text) {
        return text.map(Color::red);
    }

    public static Local<String> green(Local<String> text) {
        return text.map(Color::green);
    }

    public static Local<String> cyan(Local<String> text) {
        return text.map(Color::cyan);
    }

    public static String red(String text) {
        return String.format("%s%s%s", RED, text, RESET);
    }

    public static String green(String text) {
        return String.format("%s%s%s", GREEN, text, RESET);
    }

    public static String cyan(String text) {
        return String.format("%s%s%s", CYAN, text, RESET);
    }
}
