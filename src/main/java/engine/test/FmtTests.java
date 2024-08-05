package engine.test;

import engine.Locale;
import engine.Localizable;

import static engine.Localizable.local;
import static engine.script.Type.type;
import static engine.Fmt.*;
import static engine.test.runner.RunnerAssertions.assertEquals;

import java.util.List;

import engine.Fmt;
import engine.Style;

public class FmtTests {
    private static final Locale DEFAULT_LOCALE = Locale.en;
    private static final Localizable<String> SOME_TEXT = local("english").fr("french");
    private static final Localizable<String> SOME_TEMPLATE = local("english(%s, %s)").fr("french(%s, %s)");
    
    public static void whenFormatSameStyle() {
        assertFormatted("\033[0menglish\033[0m", sameStyle(SOME_TEXT));
    }
    
    public static void whenFormatSameStyleText() {
        assertFormatted("\033[0mabc\033[0m", sameStyle("abc"));
    }
    
    public static void whenFormatNotStyled() {
        assertFormatted("\033[0menglish\033[0m", notStyled(SOME_TEXT));
    }
    
    public static void whenFormatNotStyledText() {
        assertFormatted("\033[0mabc\033[0m", notStyled("abc"));
    }
    
    public static void whenFormatRed() {
        assertFormatted("\033[0;31menglish\033[0m", red(SOME_TEXT));
    }
    
    public static void whenFormatRedText() {
        assertFormatted("\033[0;31mabc\033[0m", red("abc"));
    }
    
    public static void whenFormatGreenText() {
        assertFormatted("\033[0;32menglish\033[0m", green(SOME_TEXT));
    }
    
    public static void whenFormatCyan() {
        assertFormatted("\033[0;36menglish\033[0m", cyan(SOME_TEXT));
    }
    
    public static void whenFormatCyanText() {
        assertFormatted("\033[0;36mabc\033[0m", cyan("abc"));
    }
    
    public static void whenFormatStrong() {
        assertFormatted("\033[1menglish\033[0m", strong(SOME_TEXT));
    }
    
    public static void whenFormatStrongRed() {
        assertFormatted("\033[0;31m\033[1menglish\033[0m", strongRed(SOME_TEXT));
    }
    
    public static void whenFormatNotStyledTemplate() {
        assertFormatted("\033[0menglish(\033[0menglish\033[0m, \033[0;31menglish\033[0m)\033[0m", notStyled(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatRedTemplate() {
        assertFormatted("\033[0;31menglish(\033[0menglish\033[0;31m, \033[0;31menglish\033[0;31m)\033[0m", red(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatGreenTemplate() {
        assertFormatted("\033[0;32menglish(\033[0menglish\033[0;32m, \033[0;31menglish\033[0;32m)\033[0m", green(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatCyanTemplate() {
        assertFormatted("\033[0;36menglish(\033[0menglish\033[0;36m, \033[0;31menglish\033[0;36m)\033[0m", cyan(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatStrongTemplate() {
        assertFormatted("\033[1menglish(\033[0menglish\033[1m, \033[0;31menglish\033[1m)\033[0m", strong(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatStrongTemplateInFrench() {
        assertFormattedInFrench("\033[1mfrench(\033[0mfrench\033[1m, \033[0;31mfrench\033[1m)\033[0m", strong(SOME_TEMPLATE, notStyled(SOME_TEXT), red(SOME_TEXT)));
    }
    
    public static void whenFormatCodeText() {
        assertFormatted("\033[38;5;27mnew String()\033[0m", code("new String()"));
    }
    
    public static void whenFormatClassSimpleNameWithAClass() {
        assertFormatted("\033[38;5;27mString\033[0m", classSimpleName(String.class));
    }

    public static void whenFormatClassSimpleNameWithAType() {
        assertFormatted("\033[38;5;27mString\033[0m", classSimpleName(type(String.class)));
    }

    public static void whenFormatClassFullName() {
        assertFormatted("\033[38;5;27mjava.lang.String\033[0m", classFullName(String.class));
    }

    public static void whenFormatEmptyDoubleSequence() {
        assertFormatted("\033[0m\033[0m", sequence(new double[0], Style.Green));
    }

    public static void whenFormat1DoubleSequence() {
        assertFormatted("\033[0m\033[0;32m2.5\033[0m\033[0m", sequence(new double[]{2.5}, Style.Green));
    }

    public static void whenFormat2DoublesSequence() {
        assertFormatted("\033[0m\033[0;32m2.5\033[0m and \033[0;32m-1.2\033[0m\033[0m", sequence(new double[]{2.5, -1.2}, Style.Green));
    }

    public static void whenFormatManyDoublesSequence() {
        assertFormatted("\033[0m\033[0;32m2.5\033[0m, \033[0;32m-1.2\033[0m, \033[0;32m0.0\033[0m and \033[0;32m3.14\033[0m\033[0m", sequence(new double[]{2.5, -1.2, 0, 3.14}, Style.Green));
    }

    public static void whenFormatManyDoublesSequenceInFrench() {
        assertFormattedInFrench("\033[0m\033[0;32m2.5\033[0m, \033[0;32m-1.2\033[0m, \033[0;32m0.0\033[0m et \033[0;32m3.14\033[0m\033[0m", sequence(new double[]{2.5, -1.2, 0, 3.14}, Style.Green));
    }

    public static void whenFormatEmptyStringSequence() {
        assertFormatted("\033[0m\033[0m", sequence(new String[0], Style.Green));
    }

    public static void whenFormat1ItemStringSequence() {
        assertFormatted("\033[0m\033[0;32ma\033[0m\033[0m", sequence(new String[]{"a"}, Style.Green));
    }

    public static void whenFormat2ItemsStringSequence() {
        assertFormatted("\033[0m\033[0;32ma\033[0m and \033[0;32mb\033[0m\033[0m", sequence(new String[]{"a", "b"}, Style.Green));
    }

    public static void whenFormatManyStringsSequence() {
        assertFormatted("\033[0m\033[0;32ma\033[0m, \033[0;32mb\033[0m, \033[0;32mc\033[0m and \033[0;32md\033[0m\033[0m", sequence(new String[]{"a", "b", "c", "d"}, Style.Green));
    }

    public static void whenFormatEmptySequence() {
        assertFormatted("\033[0m\033[0m", sequence(List.of(), Style.Green));
    }

    public static void whenFormat1ItemSequence() {
        assertFormatted("\033[0m\033[0;32menglish\033[0m\033[0m", sequence(List.of(SOME_TEXT), Style.Green));
    }

    public static void whenFormat2ItemsSequence() {
        assertFormatted("\033[0m\033[0;32menglish\033[0m and \033[0;32menglish\033[0m\033[0m", sequence(List.of(SOME_TEXT, SOME_TEXT), Style.Green));
    }

    public static void whenFormatManyItemsSequence() {
        assertFormatted("\033[0m\033[0;32menglish\033[0m, \033[0;32menglish\033[0m, \033[0;32menglish\033[0m and \033[0;32menglish\033[0m\033[0m", sequence(List.of(SOME_TEXT, SOME_TEXT, SOME_TEXT, SOME_TEXT), Style.Green));
    }

    public static void whenFormatManyItemsSequenceInFrench() {
        assertFormattedInFrench("\033[0m\033[0;32mfrench\033[0m, \033[0;32mfrench\033[0m, \033[0;32mfrench\033[0m et \033[0;32mfrench\033[0m\033[0m", sequence(List.of(SOME_TEXT, SOME_TEXT, SOME_TEXT, SOME_TEXT), Style.Green));
    }

    private static void assertFormatted(String expected, Fmt fmt) {
        String actual = fmt.format(DEFAULT_LOCALE);
        assertEquals(expected, actual);
    }

    private static void assertFormattedInFrench(String expected, Fmt fmt) {
        String actual = fmt.format(Locale.fr);
        assertEquals(expected, actual);
    }
}
