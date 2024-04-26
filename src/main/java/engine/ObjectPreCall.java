package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Represents a method call to perform on a object before a test.
 */
public class ObjectPreCall {
    private final String methodName;
    private final Value[] paramValues;
    private Object[] localizedParamValues;

    public ObjectPreCall(String methodName,  Value[] paramValues) {
        this.methodName = methodName;
        this.paramValues = paramValues;
    }

    public Object invoke(Object instance, Locale locale) throws InvocationTargetException {
        try {
            var clasz = instance.getClass();
            var methodCandidates = Arrays
                .stream(clasz.getMethods())
                .filter(meth -> meth.getName().equals(methodName) && meth.getParameterCount() == paramValues.length)
                .toList();

            if (methodCandidates.size() > 1) {
                throw new KoanBugException(String.format(
                    "Koans do not support exercises with multiple methods with the same name and number of arguments. Found %d methods %s with %d params in %s",
                    methodCandidates.size(),
                    methodName,
                    paramValues.length,
                    clasz.getName()
                ));
            } else if (methodCandidates.size() == 0) {
                throw new KoanBugException(String.format(
                    "Koans should assert that methods have the right number of arguments. Did not find a method %s in %s with %d arguments.",
                    methodName,
                    clasz.getName(),
                    paramValues.length
                ));
            }
        
            this.localizedParamValues = Value.resolve(locale, paramValues);
            return methodCandidates.get(0).invoke(instance, localizedParamValues);
        } catch(IllegalAccessException iae) {
            throw new KoanBugException(String.format("The method %s is not accessible, which should have been already caught by a missing assertion in this or a previous Koans.", methodName));
        }
    }

    public static ObjectPreCall invokeMethod(String methodName,  Value... paramValues) {
        return new ObjectPreCall(methodName, paramValues);
    }

    private static String formatParams(Object[] params) {
        return String.join(
            ", ",
            Arrays.stream(params)
                .map(p -> p.toString())
                .toArray(String[]::new)
        );
    }

    public String toString() {
        return String.format("%s(%s)", methodName, formatParams(localizedParamValues));

    }
}
