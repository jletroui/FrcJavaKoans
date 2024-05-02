package engine;

import java.util.Arrays;

/**
 * Console text coloring.
 */
public final class ConsoleFmt {
    private static final String NO_FORMAT = "\033[0m";

    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String CYAN = "\033[0;36m";
    private static final String DODGERBLUE2="\033[38;5;27m";

    private static final String BOLD= "\033[1m";
    private static final String RED_BOLD= RED + BOLD;

    public static enum Formats {
        None(NO_FORMAT),
        Red(RED),
        Green(GREEN),
        Cyan(CYAN),
        Code(DODGERBLUE2),
        Strong(BOLD),
        StrongRed(RED_BOLD);

        public final String tags;

        private Formats(String tags) {
            this.tags = tags;
        }
    }

    public record Formatted<T>(T text, Formats fmt) {}

    public static <T> Formatted<T> fmt(T text, Formats fmt) {
        return new Formatted<T>(text, fmt);
    }

    public static <T> Formatted<T> code(T text) {
        return new Formatted<T>(text, Formats.Code);
    }

    public static <T> Formatted<T> strong(T text) {
        return new Formatted<T>(text, Formats.Strong);
    }

    public static <T> Formatted<T> strongRed(T text) {
        return new Formatted<T>(text, Formats.StrongRed);
    }

    public static Localizable<String> red(final Localizable<String> text) {
        return text.map(ConsoleFmt::red);
    }

    public static Localizable<String> green(final Localizable<String> text) {
        return text.map(ConsoleFmt::green);
    }

    public static Localizable<String> cyan(final Localizable<String> text) {
        return text.map(ConsoleFmt::cyan);
    }

    public static Localizable<String> strongRed(final Localizable<String> text) {
        return text.map(ConsoleFmt::strongRed);
    }

    public static Localizable<String> strong(final Localizable<String> text) {
        return text.map(ConsoleFmt::strong);
    }

    public static String red(final String text) {
        return String.format("%s%s%s", RED, text, NO_FORMAT);
    }

    public static String green(final String text) {
        return String.format("%s%s%s", GREEN, text, NO_FORMAT);
    }

    public static String cyan(final String text) {
        return String.format("%s%s%s", CYAN, text, NO_FORMAT);
    }

    public static String strong(final String text) {
        return String.format("%s%s%s", BOLD, text, NO_FORMAT);
    }

    public static String strongRed(final String text) {
        return String.format("%s%s%s", RED_BOLD, text, NO_FORMAT);
    }

    public static String format(final String template, Formats fmt, final Object... params) {
        final var formattedParams = Arrays
            .stream(params)
            .map(param -> param instanceof Formatted<?> fmted ? String.format("%s%s%s", fmted.fmt.tags, fmted.text, fmt.tags) : param)
            .toArray(Object[]::new);
        return String.format("%s%s%s", fmt.tags, String.format(template, formattedParams) , NO_FORMAT);
    }

    public static Localizable<String> format(final Localizable<String> template, final Formats fmt, final Object... params) {
        return template.map(txt -> format(txt, fmt, params));
    }
}
