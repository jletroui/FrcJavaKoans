package engine.test.simulation;

public class StudentSolutions {
    public static void simpleConsoleOutput() {
        System.out.println("hello");
    }

    public static int[] returnedValueEqualsIntArray() {
        return new int[]{1, 3};
    }

    public static int[] returnedValueNotEqualsIntArray() {
        return new int[]{1, 2};
    }

    public static String returnedValueNotIntArray() {
        return "abc";
    }

    public static int[] returnedValueNull() {
        return null;
    }    
}