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
    private static enum Resolution {
        Todo, ToLocalize, Done
    }
    private Object value = null;
    private Resolution resolution;
    private Type type = null;
    private Value[] constructorParams = null;

    public Value(Object value) {
        this.value = value;
        this.resolution = value != null && value instanceof Local ? Resolution.ToLocalize : Resolution.Done;
    }

    public Value(Type type, Value... constructorParams) {
        this.type = type;
        this.constructorParams = constructorParams;
        this.resolution = Resolution.Todo;
    }

    public Value(Type type, Object... constructorParams) {
        this.type = type;
        this.constructorParams = toValues(constructorParams);
        this.resolution = Resolution.Todo;
    }

    public static Value[] toValues(Object[] objects) {
        return Arrays.stream(objects).map(p -> new Value(p)).toArray(Value[]::new);
    }

    public static Object[] resolve(Locale locale, Value[] values) throws InvocationTargetException {
        var resolved = new Object[values.length];
        for(int i=0; i<values.length; i++) {
            resolved[i] = values[i].resolve(locale);
        }
        return resolved;
    }

    @SuppressWarnings("rawtypes")
    private Object resolve(Locale locale) throws InvocationTargetException {
        switch(resolution) {
            case ToLocalize:
                value = ((Local)value).get(locale);
                resolution = Resolution.Done;
                break;
            case Todo:
                value = type.unsafeInstantiate(locale, constructorParams);
                resolution = Resolution.Done;
                break;
            case Done:
                // Nothing to do
                break;
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
        
        return String.format("new %s(%s)", type.simpleClassName, paramsAsString);
    }
}
