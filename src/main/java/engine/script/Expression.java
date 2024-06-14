package engine.script;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;

import engine.KoanBugException;
import engine.Locale;
import engine.Localizable;

/**
 * Allows to specify expressions to be executed, whether the student class / method is already defined or not.
 * 
 * For example, if we don't have the Point class yet, because the student must create it first, we can define a call to its constructor by:
 * 
 *     import static engine.script.Expression.newObject;
 * 
 *     var expressionToCreatePoint = newObject("geom.Point", 2.0, 2.0);
 */
public sealed interface Expression {
    /**
     * Executes this expression and returns the result or null if the expression is a statement without a return value.
     */
    Object execute(final ExecutionContext ctx);

    /**
     * Show to the student what the Java source code of this expression would be.
     */
    String formatSourceCode(final Locale locale);

    /**
     * Executes all the given expressions. Useful for getting actual values for parameters of a method or constructor.
     */
    public static Object[] executeAll(final ExecutionContext ctx, final Expression[] values) {
        final var resolved = new Object[values.length];
        for(int i=0; i<values.length; i++) {
            resolved[i] = values[i].execute(ctx);
        }
        return resolved;
    }

    /**
     * Creates an expression representing a call to a static method within the Koan's class.
     */
    public static Expression callKoanMethod(String methodName, Object... params) {
        return new CallKoanMethod(methodName, params);
    }

    /**
     * Creates an expression representing a call to a static method within the given class.
     */
    public static Expression callStaticMethod(String className, String methodName, Object... params) {
        return new CallStaticMethod(className, methodName, params);
    }

    /**
     * Creates an expression representing a call to this object.
     */
    public default Expression call(String methodName, Object... params) {
        return new CallMethod(this, methodName, params);
    }

    /**
     * Creates an expression representing a call to the constructor of the given class.
     */
    public static Expression newObject(String className, Object... params) {
        return new NewObject(className, params);
    }

    /**
     * Creates an expression representing a variable assignment.
     */
    public static Expression assignVariable(String variableName, Expression value) {
        return new AssignVariable(variableName, value);
    }

    /**
     * Creates an expression representing a reference to variable's value.
     */
    public static Expression variable(String variableName) {
        return new Variable(variableName);
    }

    public static String formatSourceCode(final Expression[] script, final Locale locale) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < script.length; i++) {
            builder.append(String.format("%s;\n", script[i].formatSourceCode(locale)));
        }

        return builder.toString();
    }

    /**
     * Same as formatSourceCode(), except it ommits the last expression (considered the main one a test is testing).
     */
    public static String formatPreparationSourceCode(final Expression[] script, final Locale locale, final String identation) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < script.length - 1; i++) {
            builder.append(String.format("%s%s;\n", identation, script[i].formatSourceCode(locale)));
        }

        return builder.toString();
    }

    static Expression[] normalizeToExpression(final Object[] objects) {
        return Arrays
            .stream(objects)
            .map(p -> p instanceof Expression ? p : new Literal(p))
            .toArray(Expression[]::new);
    }

    static String formatLiteralSourceCode(final Object value) {
        if (value instanceof String) {
            return String.format("\"%s\"", value);
        }
        if (value.getClass().isArray()){
            return Arrays.toString((int[])value);
        }
        return value.toString();
    }

    static String formatParamListSourceCode(final Object[] params, final Locale locale) {
        return formatParamListSourceCode(normalizeToExpression(params), locale);
    }

    static String formatParamListSourceCode(final Expression[] params, final Locale locale) {
        return String.join(
            ", ",
            Arrays.stream(params)
                .map(p -> p.formatSourceCode(locale))
                .toArray(String[]::new)
        );
    }

    private static Method getMethod(final Class<?> clasz, final String methodName, final Expression[] params) {
        final var methodCandidates = Arrays
            .stream(clasz.getMethods())
            .filter(meth -> meth.getName().equals(methodName) && meth.getParameterCount() == params.length)
            .toList();

        if (methodCandidates.size() > 1) {
            throw new KoanBugException(String.format(
                "Koans do not support exercises with multiple methods with the same name and number of arguments. Found %d methods %s with %d params in %s",
                methodCandidates.size(),
                methodName,
                params.length,
                clasz.getName()
            ));
        } else if (methodCandidates.size() == 0) {
            throw new KoanBugException(String.format(
                "Koans should assert that methods have the right number of arguments. Did not find a method %s in %s with %d arguments.",
                methodName,
                clasz.getName(),
                params.length
            ));
        }

        return methodCandidates.get(0);
    }
    
    static Method getStaticMethod(final Class<?> clasz, final String methodName, final Expression[] params) {
        final var method = getMethod(clasz, methodName, params);
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new KoanBugException(String.format("The method %s is static, which should have been already caught by a missing assertion in this or a previous Koans.", methodName));
        }
        return method;
    }

    static Method getObjectMethod(final Class<?> clasz, final String methodName, final Expression[] params) {
        final var method = getMethod(clasz, methodName, params);
        if (Modifier.isStatic(method.getModifiers())) {
            throw new KoanBugException(String.format("The method %s is not static, which should have been already caught by a missing assertion in this or a previous Koans.", methodName));
        }
        return method;
    }

    static Object invoke(final ExecutionContext ctx, final Object instance, final Method method, final Expression[] params) {
        try {
            return method.invoke(instance, Expression.executeAll(ctx, params));
        } catch(IllegalAccessException iae) {
            throw new KoanBugException(String.format("The method %s is not accessible, which should have been already caught by a missing assertion in this or a previous Koans.", method.getName()));
        } catch(IllegalArgumentException iae) {
            throw new KoanBugException(String.format("The method %s do not possess the right parameters, which should have been already caught by a missing assertion in this or a previous Koans.", method.getName()));
        } catch(InvocationTargetException ite) {
            throw new ScriptExecutionException(ite, String.format("%s.%s()", method.getClass().getSimpleName(), method.getName()));
        }
    }
}

final record Literal(Object value) implements Expression {
    private static final Set<Class<?>> ALLOWED_LITERAL_TYPES = Set.of(
        Integer.class,
        Long.class,
        Float.class,
        Double.class,
        Boolean.class,
        Character.class,
        String.class,
        int[].class
    );

    public Literal {
        if (value != null && !ALLOWED_LITERAL_TYPES.contains(value.getClass())) {
            throw new KoanBugException("Only null, String, int[], and primitive types are allowed as literal in an Expression");
        }
    }

    @Override
    public Object execute(final ExecutionContext ctx) {
        return value;
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return Expression.formatLiteralSourceCode(value);
    }
}

final record Variable(String name) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        // Nice to have: nice exception when name is not found.
        return ctx.getVariableValue(name);
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return name;
    }
}

final record LocalizedLiteral(Localizable<?> local) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        return local.get(ctx.locale);
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return Expression.formatLiteralSourceCode(local.get(locale));
    }
}

final record NewObject(String className, Object[] constructorParams) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        return new Type(className).unsafeInstantiate(ctx, Expression.normalizeToExpression(constructorParams));
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return String.format("new %s(%s)", className, Expression.formatParamListSourceCode(constructorParams, locale));
    }
}

final record CallKoanMethod(String methodName, Object[] params) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        final var paramExpressions = Expression.normalizeToExpression(params);
        final var method = Expression.getStaticMethod(ctx.koanClass, methodName, paramExpressions);
        return Expression.invoke(ctx, null, method, paramExpressions);
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return String.format("%s(%s)", methodName, Expression.formatParamListSourceCode(params, locale));
    }
}

final record CallStaticMethod(String className, String methodName, Object... params) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        final var paramExpressions = Expression.normalizeToExpression(params);
        final var clasz = new Type(className).unsafeResolve();
        final var method = Expression.getStaticMethod(clasz, methodName, paramExpressions);
        return Expression.invoke(ctx, null, method, paramExpressions);
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return String.format("%s.%s(%s)", className, methodName, Expression.formatParamListSourceCode(params, locale));
    }
}

final record CallMethod(Expression instance, String methodName, Object... params) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        final var paramExpressions = Expression.normalizeToExpression(params);
        final var instanceValue = instance.execute(ctx);
        final var method = Expression.getObjectMethod(instanceValue.getClass(), methodName, paramExpressions);
        return Expression.invoke(ctx, instanceValue, method, paramExpressions);
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return String.format("%s.%s(%s)", instance.formatSourceCode(locale), methodName, Expression.formatParamListSourceCode(params, locale));
    }
}

final record AssignVariable(String variableName, Expression value) implements Expression {
    @Override
    public Object execute(final ExecutionContext ctx) {
        ctx.setVariableValue(variableName, value.execute(ctx));
        return null;
    }

    @Override
    public String formatSourceCode(final Locale locale) {
        return String.format("var %s = %s", variableName, value.formatSourceCode(locale));
    }
}
