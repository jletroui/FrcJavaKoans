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
}
