package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Allows to specify value, whether we have the class already defined or not.
 * 
 * If we don't have the class yet, because the student must create it, we can define a value by its class and constructor parameters:
 * 
 * var somePoint = new Value(new Type("geom.Point"), new Value(2.0), new Value(2.0))
 */
public class Value {
    private Object value = null;
    private boolean resolved;
    private Type type = null;
    private Value[] constructorParams = null;

    public Value(Object value) {
        this.value = value;
        this.resolved = true;
    }

    public Value(Type type, Value... constructorParams) {
        this.type = type;
        this.constructorParams = constructorParams;
        this.resolved = false;
    }

    public Value(Type type, Object... constructorParams) {
        this.type = type;
        this.constructorParams = toValues(constructorParams);
        this.resolved = false;
    }

    public static Value[] toValues(Object[] objects) {
        return Arrays.stream(objects).map(p -> new Value(p)).toArray(Value[]::new);
    }

    public static Object[] resolve(Value[] values) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        var resolved = new Object[values.length];
        for(int i=0; i<values.length; i++) {
            resolved[i] = values[i].resolve();
        }
        return resolved;
    }

    public Object resolve() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (!resolved) {
            var clasz = type.resolve();
            var constructorCandidates = Arrays
                .stream(clasz.getConstructors())
                .filter(c -> c.getParameters().length == constructorParams.length)
                .toList();

            if (constructorCandidates.size() > 1) {
                throw new IllegalStateException(String.format(
                    "Koans do not support exercises with classes with multiple constructors with the same number of arguments. Found %d constructors with %d params in %s",
                    constructorCandidates.size(),
                    constructorParams.length,
                    type
                ));
            } else if (constructorCandidates.size() == 0) {
                throw new IllegalStateException(String.format(
                    "Koans should assert that constructors have the right number of arguments. Did not find a constructor in %s with %d arguments.",
                    type,
                    constructorParams.length
                ));
            }
            value = constructorCandidates.get(0).newInstance(resolve(constructorParams));
            resolved = true;
        }
        return value;
    }

    @Override
    public String toString() {
        if (type == null) {
            if (value == null) {
                return "null";
            } else if (value instanceof String) {
                return String.format("\"%s\"", value);
            }
            return value.toString();
        }
        var paramsAsString = String.join(
            ", ",
            Arrays.stream(constructorParams)
                .map(p -> p.toString())
                .toArray(String[]::new)
        );
        
        return String.format("new %s(%s)", type.simpleName(), paramsAsString);
    }
}
