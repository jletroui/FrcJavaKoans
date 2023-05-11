package engine;

import java.util.Scanner;

public class Helpers {
    public static String readLine() {
        try(var scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
}
