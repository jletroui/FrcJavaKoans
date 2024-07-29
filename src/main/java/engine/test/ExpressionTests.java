package engine.test;

import java.util.List;

import engine.Locale;
import engine.script.ExecutionContext;
import engine.script.Expression;
import engine.test.runner.KoanEngineUnitTest;
import engine.test.runner.KoanEngineAutomatedTest;
import static engine.test.runner.RunnerAssertions.*;
import static engine.Localizable.global;
import static engine.Localizable.local;
import static engine.script.Type.type;
import static engine.script.Expression.*;

public class ExpressionTests {
    private static final Expression complexExampleExpression =
        assignVariable(
            "a", 
            newObject("engine.test.ExpressionTests$AClass", local("englishArg1").fr("frenchArg1"), 3)
                .call("aStringMethod", local("englishVal").fr("frenchVal"))
                .call("toLowerCase")
        );

    public static final List<KoanEngineAutomatedTest> tests = List.of(
        new KoanEngineUnitTest("When execute complex expression in english, return right result", () -> {
            final var ctx = new ExecutionContext(global(type(ExpressionTests.class)), Locale.en);
            final var res = complexExampleExpression.execute(ctx);
            assertTrue(null == res, "An assignment should return null");
            assertEquals("englishval", ctx.getVariableValue("a"));
        }),
        new KoanEngineUnitTest("When execute complex expression in french, return right result", () -> {
            final var ctx = new ExecutionContext(global(type(ExpressionTests.class)), Locale.fr);
            final var res = complexExampleExpression.execute(ctx);
            assertTrue(null == res, "An assignment should return null");
            assertEquals("frenchval", ctx.getVariableValue("a"));
        }),
        new KoanEngineUnitTest("When format complex expression", () ->
            assertEquals("var a = new engine.test.ExpressionTests$AClass(\"englishArg1\", 3).aStringMethod(\"englishVal\").toLowerCase()", format(complexExampleExpression))
        ),
        new KoanEngineUnitTest("When compute type of complexExampleExpression", () ->
            assertEquals(void.class, expressionClass(complexExampleExpression))
        ),

        new KoanEngineUnitTest("When execute literal of String, return said String", () ->
            assertEquals("abc", execute(lit("abc")))
        ),
        new KoanEngineUnitTest("When format literal of String", () ->
            assertEquals("\"abc\"", format(lit("abc")))
        ),
        new KoanEngineUnitTest("When compute type of literal of String", () ->
            assertEquals(String.class, expressionClass(lit("abc")))
        ),

        new KoanEngineUnitTest("When execute literal of int, return said int", () ->
            assertEquals(4, execute(lit(4)))
        ),
        new KoanEngineUnitTest("When format literal of int", () ->
            assertEquals("4", format(lit(4)))
        ),
        new KoanEngineUnitTest("When compute type literal of int", () ->
            assertEquals(Integer.class, expressionClass(lit(4)))
        ),

        new KoanEngineUnitTest("When execute literal of double, return said double", () ->
            assertEquals(4.0, execute(lit(4.0)))
        ),
        new KoanEngineUnitTest("When format literal of double", () ->
            assertEquals("4.0", format(lit(4.0)))
        ),
        new KoanEngineUnitTest("When compute type literal of double", () ->
            assertEquals(Double.class, expressionClass(lit(4.0)))
        ),

        new KoanEngineUnitTest("When execute literal of boolean, return said boolean", () ->
            assertEquals(false, execute(lit(false)))
        ),
        new KoanEngineUnitTest("When format literal of boolean", () ->
            assertEquals("false", format(lit(false)))
        ),
        new KoanEngineUnitTest("When compute type literal of boolean", () ->
            assertEquals(Boolean.class, expressionClass(lit(false)))
        ),

        new KoanEngineUnitTest("When execute literal of int array, return said int array", () ->
            assertEquals(new int[]{1, 2, 3}, execute(lit(new int[]{1, 2, 3})))
        ),
        new KoanEngineUnitTest("When format literal of int array", () ->
            assertEquals("new int[]{1,2,3}", format(lit(new int[]{1, 2, 3})))
        ),
        new KoanEngineUnitTest("When compute type literal of int array", () ->
            assertEquals(int[].class, expressionClass(lit(new int[]{1, 2, 3})))
        ),

        new KoanEngineUnitTest("When execute literal of localizable, return value in right locale", () ->
            assertEquals("english", execute(lit(local("english").fr("french"))))
        ),
        new KoanEngineUnitTest("When format literal of localizable", () ->
            assertEquals("\"english\"", format(lit(local("english").fr("french"))))
        ),
        new KoanEngineUnitTest("When compute type literal of localizable", () ->
            assertEquals(String.class, expressionClass(lit(local("english").fr("french"))))
        ),

        new KoanEngineUnitTest("When execute koan string method, return result", () ->
            assertEquals("abc", execute(callKoanMethod("aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When execute string static method, return result", () ->
            assertEquals("abc", execute(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When execute string object method, return result", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals("abc", execute(obj.call("aStringMethod", "abc")));
        }),
        new KoanEngineUnitTest("When format koan string method", () ->
            assertEquals("aStringMethod(\"abc\")", format(callKoanMethod("aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When format string static method", () ->
            assertEquals("engine.test.ExpressionTests.aStringMethod(\"abc\")", format(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When format string object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).aStringMethod(\"abc\")", format(obj.call("aStringMethod", "abc")));
        }),
        new KoanEngineUnitTest("When compute type of koan string method", () ->
            assertEquals(String.class, expressionClass(callKoanMethod("aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When compute type of string static method", () ->
            assertEquals(String.class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")))
        ),
        new KoanEngineUnitTest("When compute type of string object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals(String.class, expressionClass(obj.call("aStringMethod", "abc")));
        }),

        new KoanEngineUnitTest("When execute koan int method, return result", () ->
            assertEquals(7, execute(callKoanMethod("anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When execute int static method, return result", () ->
            assertEquals(7, execute(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When execute int object method, return result", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals(7, execute(obj.call("anIntMethod", 7)));
        }),
        new KoanEngineUnitTest("When format koan int method", () ->
            assertEquals("anIntMethod(7)", format(callKoanMethod("anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When format int static method", () ->
            assertEquals("engine.test.ExpressionTests.anIntMethod(7)", format(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When format int object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).anIntMethod(7)", format(obj.call("anIntMethod", 7)));
        }),
        new KoanEngineUnitTest("When compute type of koan int method", () ->
            assertEquals(int.class, expressionClass(callKoanMethod("anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When compute type of int static method", () ->
            assertEquals(int.class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)))
        ),
        new KoanEngineUnitTest("When compute type of int object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals(int.class, expressionClass(obj.call("anIntMethod", 7)));
        }),

        new KoanEngineUnitTest("When execute koan int array method, return result", () ->
            assertEquals(new int[]{4, 3}, execute(callKoanMethod("anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When execute int array static method, return result", () ->
            assertEquals(new int[]{4, 3}, execute(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When execute int array object method, return result", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals(new int[]{4, 3}, execute(obj.call("anIntArrayMethod", new int[]{4, 3})));
        }),
        new KoanEngineUnitTest("When format koan int array method", () ->
            assertEquals("anIntArrayMethod(new int[]{4,3})", format(callKoanMethod("anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When format int array static method", () ->
            assertEquals("engine.test.ExpressionTests.anIntArrayMethod(new int[]{4,3})", format(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When format int array object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).anIntArrayMethod(new int[]{4,3})", format(obj.call("anIntArrayMethod", new int[]{4, 3})));
        }),
        new KoanEngineUnitTest("When compute type of koan int array method", () ->
            assertEquals(int[].class, expressionClass(callKoanMethod("anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When compute type of int array static method", () ->
            assertEquals(int[].class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})))
        ),
        new KoanEngineUnitTest("When compute type of int array object method", () -> {
            final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
            assertEquals(int[].class, expressionClass(obj.call("anIntArrayMethod", new int[]{4, 3})));
        }),

        new KoanEngineUnitTest("When execute newObject, return created object", () -> {
            final var obj = execute(newObject("engine.test.ExpressionTests$AClass", "arg1", 3));
            assertTrue(obj != null, "created object should not be null");
            assertTrue(obj instanceof AClass, "created object should be of the right class");
            final var aClassInstance = (AClass)obj;
            assertEquals("arg1", aClassInstance.arg1);
            assertEquals(3, aClassInstance.arg2);
        }),
        new KoanEngineUnitTest("When format newObject", () ->
            assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3)", format(newObject("engine.test.ExpressionTests$AClass", "arg1", 3)))
        ),
        new KoanEngineUnitTest("When compute type of newObject", () ->
            assertEquals(AClass.class, expressionClass(newObject("engine.test.ExpressionTests$AClass", "arg1", 3)))
        ),

        new KoanEngineUnitTest("When execute variable, return variable content", () -> {
            final var ctx = context();
            ctx.setVariableValue("a", 123);
            assertEquals(123, variable("a").execute(ctx));
        }),
        new KoanEngineUnitTest("When format variable", () ->
            assertEquals("a", format(variable("a")))
        ),
        new KoanEngineUnitTest("When compute type of variable", () -> {
            final var ctx = context();
            ctx.setVariableValue("a", 123);
            assertEquals(Integer.class, variable("a").expressionClass(ctx));
        }),

        new KoanEngineUnitTest("When execute variable assignment, return null", () ->
            assertTrue(null == execute(assignVariable("a", 123)), "Assignment should return null")
        ),
        new KoanEngineUnitTest("When format variable assignment", () ->
            assertEquals("var a = 123", format(assignVariable("a", 123)))
        ),
        new KoanEngineUnitTest("When compute type of variable assignment", () ->
            assertEquals(void.class, expressionClass(assignVariable("a", 123)))
        ),

        new KoanEngineUnitTest("When formatSourceCode", () -> {
            final var script = new Expression[]{
                assignVariable("a", 123),
                callKoanMethod("anIntMethod", variable("a"))
            };
            assertEquals("var a = 123;\nanIntMethod(a);\n", Expression.formatSourceCode(script, Locale.en));
        }),

        new KoanEngineUnitTest("When formatPreparationSourceCode, omit last expression", () -> {
            final var script = new Expression[]{
                assignVariable("a", 123),
                callKoanMethod("anIntMethod", variable("a"))
            };
            assertEquals("  var a = 123;\n", Expression.formatPreparationSourceCode(script, Locale.en, "  "));
        })
    );

    public static String aStringMethod(String val) {
        return val;
    }
    
    public static int anIntMethod(int val) {
        return val;
    }
    
    public static int[] anIntArrayMethod(int[] val) {
        return val;
    }

    public static class AClass {
        public final String arg1;
        public final int arg2;

        public AClass(String arg1, int arg2) {
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        public String aStringMethod(String val) {
            return val;
        }

        public int anIntMethod(int val) {
            return val;
        }

        public int[] anIntArrayMethod(int[] val) {
            return val;
        }
    }

    private static Object execute(Expression expr) {
        final var ctx = context();
        return expr.execute(ctx);
    }

    private static Class<?> expressionClass(Expression expr) {
        final var ctx = context();
        return expr.expressionClass(ctx);
    }

    private static Object format(Expression expr) {
        return expr.formatSourceCode(Locale.en);
    }

    private static ExecutionContext context() {
        return new ExecutionContext(global(type(ExpressionTests.class)), Locale.en);
    }
}
