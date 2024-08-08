package engine;

/**
 * Console text styles.
 */
public enum Style {
    None("\033[0m"), // Normal style
    Red("\033[0;31m"),
    Green("\033[0;32m"),
    Cyan("\033[0;36m"),
    Code("\033[38;5;27m"),
    Strong("\033[1m"),
    StrongRed("\033[0;31m" + "\033[1m"),
    Inherit(""); // Inherit from parent text's style

    public final String tags;

    private Style(final String tags) {
        this.tags = tags;
    }
}
