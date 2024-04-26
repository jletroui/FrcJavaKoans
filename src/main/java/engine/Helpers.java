package engine;

import static engine.Texts.AND;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Helpers {
    private static Random rng = new Random();
    private static Scanner scanner = null;

    static void cleanupStdInForKoan() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    public static String readLine() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    static void setupRandomForKoan(long seed) {
        rng.setSeed(seed);
    }

    public static double random() {
        return rng.nextDouble();
    }

    static String formatSequence(Locale locale, double[] toFormat) {
        return formatSequence(
            locale,
            Arrays.stream(toFormat).mapToObj(Double::toString).toArray(String[]::new)
        );
    }

    static String formatSequence(Locale locale, Object[] toFormat) {
        if (toFormat == null || toFormat.length == 0) {
            return "";
        }
        var result = new StringBuilder();
        result.append(toFormat[0]);

        if (toFormat.length > 1) {
            for(int i=1; i < toFormat.length - 1; i++) {
                result.append(", ");
                result.append(toFormat[i]);
            }
            result.append(String.format(AND.get(locale), toFormat[toFormat.length - 1]));
        }

        return result.toString();
    }

    static boolean isInstantiable(Class<?> clasz) {
        int modifiers = clasz.getModifiers();
        return
            Modifier.isPublic(modifiers) &&
            !clasz.isInterface() &&
            !Modifier.isAbstract(modifiers) &&
            !clasz.isArray() &&
            !clasz.isPrimitive() &&
            !clasz.equals(void.class);
    }
}
