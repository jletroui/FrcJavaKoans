package engine;

import engine.text.Locale;

public record ValidationResult(TestOutput testOutput, boolean succeeded) {
    public static ValidationResult empty(Locale locale, KoanTest test) {
        return new ValidationResult(TestOutput.empty(locale, test), false);
    }
}
