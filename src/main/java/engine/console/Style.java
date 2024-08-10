package engine.console;

/**
 * Console text styles.
 */
public enum Style {
    Normal("\033[0m"), // Normal style: non bold, non italic, white on black background.
    Red("\033[0;31m"),
    Green("\033[0;32m"),
    Cyan("\033[0;36m"),
    Code("\033[38;5;27m"),
    Strong("\033[1m"), // Bold
    StrongRed("\033[0;31m" + "\033[1m"),
    Inherit(""); // Inherit from parent text's style

    public final String tags;

    private Style(final String tags) {
        this.tags = tags;
    }
}
