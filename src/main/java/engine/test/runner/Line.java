package engine.test.runner;

import engine.Locale;
import engine.Localizable;

public final class Line {
    private final Localizable<String> template;
    private final String[] params;

    public Line(final Localizable<String> template, final String... params) {
        this.template = template;
        this.params = params;
    }

    public String resolve(final Locale locale) {
        return String.format(template.get(locale), (Object[])params);
    }
}
