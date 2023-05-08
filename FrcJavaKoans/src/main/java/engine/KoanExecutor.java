package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class KoanExecutor {
    private final Class<?> koanClass;
    private final List<String> methodNames;
    private final StdOutInterceptor interceptor = new StdOutInterceptor();

    public KoanExecutor(Class<?> koanClass, List<String> methodNames) {
        this.koanClass = koanClass;
        this.methodNames = methodNames;
    }

    private static void print(String template, Object... params) {
        System.out.println(String.format(template, params));
    }
    
    public boolean execute() {
        for (String methodName : methodNames) {
            var result = executeKoanMethod(methodName);
            if (!result) {
                return false;
            }    
        }
        return true;
    }

    private boolean assertOutEquals(String captured, String expectedTemplate, Object... params) {
        var expected = String.format(expectedTemplate, params);
        if (!captured.equals(expected)) {
            if (captured.trim().equals("")) {
                print("Expected to read '%s' in the console, but read nothing instead", expected);
            } else {
                print("Expected to read '%s' in the console, but read '%s' instead", expected, captured);
            }
            return false;
        }
        return true;
    }

    public boolean executeKoanMethod(String methodName) {
        try {
            print("Looking for wisdom in the %s koan of the %s class", methodName, koanClass.getSimpleName());
            print("---------");
            print("");
            var method = koanClass.getMethod(methodName);
            try {
                String printedToOut = interceptor.capture(() -> method.invoke(null));
                return assertOutEquals(printedToOut, "What is your name?%n");
            }
            catch(IllegalAccessException iae) {
                print("Koan lesson: the method %s() appears to not be public. Koan methods must be public.", methodName);
                return false;
            }
            catch(IllegalArgumentException iae) {
                print("Koan lesson: the method %s() appears to not accept TODO.", methodName);
                return false;
            }
            catch(InvocationTargetException ite) {
                print("Koan lesson: the method %s() appears to produce an error: %s.", methodName, ite.getCause().getMessage());
                return false;
            }
        }
        catch(NoSuchMethodException mnfe) {
            throw new RuntimeException("Oops, something bad happen", mnfe);
        }
    }
}
