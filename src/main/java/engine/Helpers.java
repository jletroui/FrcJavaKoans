package engine;

import java.util.Random;
import java.util.Scanner;

public class Helpers {
    private static Random rng = null;
    private static Scanner scanner = null;

    static void setupStdInForKoan() {
        scanner = new Scanner(System.in);
    }

    static void cleanupStdInForKoan() {
        scanner.close();
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    static long setupRandomForKoan() {
        var seed = Math.round(Math.random() * Long.MAX_VALUE);
        rng = new Random(seed);
        return seed;
    }

    public static double random() {
        return rng.nextDouble();
    }
}
