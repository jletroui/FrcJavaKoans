package engine;

import java.util.Arrays;
import java.util.List;

import engine.script.Type;
import static engine.Localizable.global;
import static engine.Texts.AND;

/**
 * A piece of localizable and stylized text in the console. Allow to create colored output in the console.
 */
public final record Fmt(Localizable<String> templ, Style style, Fmt... children) {
    public String format(final Locale locale) {
        return format(locale, Style.None);
    }

    private String format(final Locale locale, final Style parentStyle) {
        final var actualStyle = style == Style.Inherit ? parentStyle : style;
        final var formattedChildren = Arrays
            .stream(children)
            .map(child -> child.format(locale, actualStyle))
            .toArray(Object[]::new);
        return String.format("%s%s%s", actualStyle.tags, String.format(templ.get(locale), formattedChildren) , parentStyle.tags);
    }

    public static Fmt notStyled(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.None, children);
    }

    public static Fmt notStyled(final String value) {
        return new Fmt(global(value), Style.None);
    }

    public static Fmt notStyled(final int value) {
        return new Fmt(global(Integer.toString(value)), Style.None);
    }

    public static Fmt red(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.Red, children);
    }

    public static Fmt red(final String text) {
        return new Fmt(global(text), Style.Red);
    }

    public static Fmt green(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.Green, children);
    }

    public static Fmt green(final String text) {
        return new Fmt(global(text), Style.Green);
    }

    public static Fmt cyan(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.Cyan, children);
    }

    public static Fmt cyan(final String text) {
        return new Fmt(global(text), Style.Cyan);
    }

    public static Fmt strong(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.Strong, children);
    }

    public static Fmt strongRed(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.StrongRed, children);
    }

    public static Fmt sameStyle(final Localizable<String> templ, final Fmt... children) {
        return new Fmt(templ, Style.Inherit, children);
    }

    public static Fmt sameStyle(final String value) {
        return new Fmt(global(value), Style.Inherit);
    }

    public static <T> Fmt code(final T expressionTextOrValue) {
        return new Fmt(global(expressionTextOrValue.toString()), Style.Code);
    }

    public static Fmt classSimpleName(final Type type) {
        return code(type.simpleClassName);
    }

    public static Fmt classSimpleName(final Class<?> clasz) {
        final var unboxed = Type.UNBOXED.getOrDefault(clasz, clasz);
        return code(unboxed.getSimpleName());
    }

    public static Fmt classFullName(final Class<?> clasz) {
        final var unboxed = Type.UNBOXED.getOrDefault(clasz, clasz);
        return code(unboxed.getName());
    }

    public static Fmt sequence(final double[] items, final Style itemStyle) {
        return sequence(
            Arrays.stream(items).mapToObj(item -> global(Double.toString(item))).toList(),
            itemStyle
        );
    }

    public static Fmt sequence(final String[] items, final Style itemStyle) {
        return sequence(
            Arrays.stream(items).map(item -> global(item)).toList(),
            itemStyle
        );
    }

    public static Fmt sequence(final List<Localizable<String>> items, final Style itemStyle) {
        if (items.isEmpty()) {
            return new Fmt(global(""), Style.Inherit);
        }

        final var children = items
            .stream()
            .map(item -> new Fmt(item, itemStyle))
            .toArray(Fmt[]::new);
        
        final var templ = AND.map(andText -> {
            final var result = new StringBuilder("%s");
            if (items.size() > 1) {
                for(int i=1; i < items.size() - 1; i++) {
                    result.append(", %s");
                }
                result.append(andText);
            }
            return result.toString();
        });

        return new Fmt(templ, Style.Inherit, children);
    }
}
