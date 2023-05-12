package engine;

import java.util.Scanner;

public class Helpers {
    private static Scanner scanner = null;

    static void setupForKoan() {
        scanner = new Scanner(System.in);
    }

    static void cleanupForKoan() {
        scanner.close();
    }

    public static String readLine() {
        return scanner.nextLine();
    }
}
