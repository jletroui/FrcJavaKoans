package engine;

import java.util.Random;
import java.util.Scanner;

public class Helpers {
    private static final Random rng = new Random();
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

    static void setupRandomForKoan(final long seed) {
        rng.setSeed(seed);
    }

    public static double random() {
        return rng.nextDouble();
    }
}
