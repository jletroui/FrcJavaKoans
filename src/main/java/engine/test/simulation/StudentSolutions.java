package engine.test.simulation;

import engine.Helpers;

public class StudentSolutions {
    @SuppressWarnings("unused")
    private final int privateFinalField = 0;
    public final int publicFinalField = 0;
    @SuppressWarnings("unused")
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
