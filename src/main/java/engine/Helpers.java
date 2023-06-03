package engine;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Helpers {
    private static Random rng = new Random();
    private static Scanner scanner = new Scanner(System.in);

    static void setupStdInForKoan() {
        if (scanner != null) {
            scanner.close();
        }
        scanner = new Scanner(System.in);
    }

    static void cleanupStdInForKoan() {
        scanner.close();
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    static void setupRandomForKoan(long seed) {
        rng.setSeed(seed);
    }

    public static double random() {
        return rng.nextDouble();
    }

    public static String formatSequence(double[] toFormat, String localizedAndTemplate) {
        return formatSequence(
            Arrays.stream(toFormat).mapToObj(Double::toString).toArray(String[]::new),
            localizedAndTemplate
        );
    }

    public static String formatSequence(Object[] toFormat, String localizedAndTemplate) {
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
            result.append(String.format(localizedAndTemplate, toFormat[toFormat.length - 1]));
        }

        return result.toString();
    }
}
