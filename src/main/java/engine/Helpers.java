package engine;

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

    static String formatSequence(double[] toFormat, String localizedAndTemplate) {
        return formatSequence(
            Arrays.stream(toFormat).mapToObj(Double::toString).toArray(String[]::new),
            localizedAndTemplate
        );
    }

    static String formatSequence(Object[] toFormat, String localizedAndTemplate) {
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
