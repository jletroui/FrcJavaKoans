package engine.test;

import engine.Locale;
import engine.Localizable;

public abstract sealed class Line permits Line.Empty, Line.Formatted, Line.Localized {
    private Line() {}

    public abstract String resolve(Locale locale);

    public static final class Empty extends Line {
        public String resolve(Locale locale) {
            return "";
        }
    }

    public static final class Formatted extends Line {
        private final String resolved;

        public Formatted(String template, String... params) {
            this.resolved = String.format(template, (Object[])params);
        }

        public String resolve(Locale locale) {
            return resolved;
        }
    }

    public static final class Localized extends Line {
        private final Localizable<String> template;
        private final String[] params;

        public Localized(Localizable<String> template, String... params) {
            this.template = template;
            this.params = params;
        }

        public String resolve(Locale locale) {
            return String.format(template.get(locale), (Object[])params);
        }
    }
}
