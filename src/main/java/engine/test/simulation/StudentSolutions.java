package engine.test.simulation;

import engine.Helpers;

public class StudentSolutions {
    private final int privateFinalField = 0;
    public final int publicFinalField = 0;
    private int privateField = 0;
    public int publicField = 0;

    public static void doNothing() {
    }

    public static void simpleConsoleOutput() {
        System.out.println("hello");
    }

    public static void emptyConsoleOutput() {
        System.out.println();
    }

    public static void readFromConsole() {
        Helpers.readLine();
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

    public static SomeInterface returnedValueImplements() {
        return new SomeImplementation();
    }

    public static SomeInterface returnedValueAnonymousImplementation() {
        return new SomeInterface() {
            @Override
            public int op(int a, int b) {
                return a+b;
            }
        };
    }

    public static SomeInterface returnedValueLambda() {
        return (a, b) -> a + b;
    }

    public void nonStaticMethod() {
    }

    public void nonStaticMethodOneParam(int x) {
    }
    
    public void nonStaticMethodMoreParam(int x, double y) {
    }

    static void nonStaticPrivateMethod() {
    }

    static void privateMethod() {
    }
}
