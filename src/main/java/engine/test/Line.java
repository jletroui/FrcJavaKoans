package engine.test;

import engine.Locale;
import engine.Localizable;

public sealed interface Line {
    String resolve(Locale locale);

    public static final class Empty implements Line {
        @Override
        public String resolve(final Locale _locale) {
            return "";
        }
    }

    public static final class Formatted implements Line {
        private final String resolved;

        public Formatted(final String template, final String... params) {
            this.resolved = String.format(template, (Object[])params);
        }

        @Override
        public String resolve(Locale locale) {
            return resolved;
        }
    }

    public static final class Localized implements Line {
        private final Localizable<String> template;
        private final String[] params;

        public Localized(final Localizable<String> template, final String... params) {
            this.template = template;
            this.params = params;
        }

        @Override
        public String resolve(final Locale locale) {
            return String.format(template.get(locale), (Object[])params);
        }
    }
}
