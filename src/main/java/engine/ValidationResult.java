package engine;

import engine.text.Locale;

public record ValidationResult(TestOutput testOutput, boolean succeeded) {
    public static ValidationResult empty(final Locale locale, final Koan koan, final KoanTest test) {
        return new ValidationResult(TestOutput.empty(locale, koan, test), false);
    }
}
