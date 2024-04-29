package engine;

/**
 * Console text coloring.
 */
public final class Color {
    private static final String RESET = "\033[0m";

    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String CYAN = "\033[0;36m";

    public static Localizable<String> red(final Localizable<String> text) {
        return text.map(Color::red);
    }

    public static Localizable<String> green(final Localizable<String> text) {
        return text.map(Color::green);
    }

    public static Localizable<String> cyan(final Localizable<String> text) {
        return text.map(Color::cyan);
    }

    public static String red(final String text) {
        return String.format("%s%s%s", RED, text, RESET);
    }

    public static String green(final String text) {
        return String.format("%s%s%s", GREEN, text, RESET);
    }

    public static String cyan(final String text) {
        return String.format("%s%s%s", CYAN, text, RESET);
    }
}
