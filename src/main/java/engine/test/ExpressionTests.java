package engine.test;

import engine.Locale;
import engine.script.ExecutionContext;
import engine.script.Expression;
import static engine.test.runner.RunnerAssertions.*;
import static engine.Localizable.global;
import static engine.Localizable.local;
import static engine.script.Type.type;
import static engine.script.Expression.*;

public class ExpressionTests {
    private static final Expression COMPLEX_EXAMPLE_EXPRESSION =
        assignVariable(
            "a", 
            newObject("engine.test.ExpressionTests$AClass", local("englishArg1").fr("frenchArg1"), 3)
                .call("aStringMethod", local("englishVal").fr("frenchVal"))
                .call("toLowerCase")
        );

    public static void whenExecuteComplexExpressionInEnglishThenReturnResult() {
        final var ctx = new ExecutionContext(global(type(ExpressionTests.class)), Locale.en);

        final var res = COMPLEX_EXAMPLE_EXPRESSION.execute(ctx);

        assertTrue(null == res, "An assignment should return null");
        assertEquals("englishval", ctx.getVariableValue("a"));
    }

    public static void whenExecuteComplexExpressionInFrenchThenReturnResult() {
        final var ctx = new ExecutionContext(global(type(ExpressionTests.class)), Locale.fr);

        final var res = COMPLEX_EXAMPLE_EXPRESSION.execute(ctx);

        assertTrue(null == res, "An assignment should return null");
        assertEquals("frenchval", ctx.getVariableValue("a"));
    }

    public static void whenFormatComplexExpression() {
        assertEquals(
            "var a = new engine.test.ExpressionTests$AClass(\"englishArg1\", 3).aStringMethod(\"englishVal\").toLowerCase()", 
            format(COMPLEX_EXAMPLE_EXPRESSION)
        );
    }

    public static void whenComputeTypeOfComplexExampleExpression() {
        assertEquals(void.class, expressionClass(COMPLEX_EXAMPLE_EXPRESSION));
    }

    public static void whenExecuteLiteralOfStringThenReturnSaidString() {
        assertEquals("abc", execute(lit("abc")));
    }

    public static void whenFormatLiteralOfString() {
         assertEquals("\"abc\"", format(lit("abc")));
    }

    public static void whenComputeTypeOfLiteralOfString() {
        assertEquals(String.class, expressionClass(lit("abc")));
    }

    public static void whenExecuteLiteralOfintThenReturnSaidInt() {
        assertEquals(4, execute(lit(4)));
    }

    public static void whenFormatLiteralOfInt() {
        assertEquals("4", format(lit(4)));
    }

    public static void whenComputeTypeOfLiteralOfInt() {
        assertEquals(Integer.class, expressionClass(lit(4)));
    }

    public static void whenExecuteLiteralOfdoubleThenReturnSaidDouble() {
        assertEquals(4.0, execute(lit(4.0)));
    }

    public static void whenFormatLiteralOfDouble() {
        assertEquals("4.0", format(lit(4.0)));
    }

    public static void whenComputeTypeOfLiteralOfDouble() {
        assertEquals(Double.class, expressionClass(lit(4.0)));
    }

    public static void whenExecuteLiteralOfbooleanThenReturnSaidBoolean() {
        assertEquals(false, execute(lit(false)));
    }

    public static void whenFormatLiteralOfBoolean() {
        assertEquals("false", format(lit(false)));
    }

    public static void whenComputeTypeOfLiteralOfBoolean() {
        assertEquals(Boolean.class, expressionClass(lit(false)));
    }

    public static void whenExecuteLiteralOfIntArrayThenReturnSaidIntArray() {
        assertEquals(new int[]{1, 2, 3}, execute(lit(new int[]{1, 2, 3})));
    }

    public static void whenFormatLiteralOfIntArray() {
        assertEquals("new int[]{1,2,3}", format(lit(new int[]{1, 2, 3})));
    }

    public static void whenComputeTypeOfLiteralOfIntArray() {
        assertEquals(int[].class, expressionClass(lit(new int[]{1, 2, 3})));
    }

    public static void whenExecuteLiteralOflocalizableThenReturnValueInRightLocale() {
        assertEquals("english", execute(lit(local("english").fr("french"))));
    }

    public static void whenFormatLiteralOflocalizable() {
        assertEquals("\"english\"", format(lit(local("english").fr("french"))));
    }

    public static void whenComputeTypeOfLiteralOflocalizable() {
        assertEquals(String.class, expressionClass(lit(local("english").fr("french"))));
    }

    public static void whenExecuteKoanStringMethodThenReturnResult() {
        assertEquals("abc", execute(callKoanMethod("aStringMethod", "abc")));
    }

    public static void whenExecuteStringStaticMethodThenReturnResult() {
        assertEquals("abc", execute(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")));
    }

    public static void whenExecuteStringObjectMethodThenReturnResult() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals("abc", execute(obj.call("aStringMethod", "abc")));
    }

    public static void whenFormatKoanStringMethod() {
        assertEquals("aStringMethod(\"abc\")", format(callKoanMethod("aStringMethod", "abc")));
    }

    public static void whenFormatStringStaticMethod() {
        assertEquals("engine.test.ExpressionTests.aStringMethod(\"abc\")", format(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")));
    }

    public static void whenFormatStringObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).aStringMethod(\"abc\")", format(obj.call("aStringMethod", "abc")));
    }

    public static void whenComputeTypeOfKoanStringMethod() {
        assertEquals(String.class, expressionClass(callKoanMethod("aStringMethod", "abc")));
    }

    public static void whenComputeTypeOfStringStaticMethod() {
        assertEquals(String.class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "aStringMethod", "abc")));
    }

    public static void whenComputeTypeOfStringObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals(String.class, expressionClass(obj.call("aStringMethod", "abc")));
    }

    public static void whenExecuteKoanIntMethodThenReturnResult() {
        assertEquals(7, execute(callKoanMethod("anIntMethod", 7)));
    }

    public static void whenExecuteIntStaticMethodThenReturnResult() {
        assertEquals(7, execute(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)));
    }

    public static void whenExecuteIntObjectMethodThenReturnResult() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals(7, execute(obj.call("anIntMethod", 7)));
    }

    public static void whenFormatKoanIntMethod() {
        assertEquals("anIntMethod(7)", format(callKoanMethod("anIntMethod", 7)));
    }

    public static void whenFormatIntStaticMethod() {
        assertEquals("engine.test.ExpressionTests.anIntMethod(7)", format(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)));
    }

    public static void whenFormatIntObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).anIntMethod(7)", format(obj.call("anIntMethod", 7)));
    }

    public static void whenComputeTypeOfKoanIntMethod() {
        assertEquals(int.class, expressionClass(callKoanMethod("anIntMethod", 7)));
    }

    public static void whenComputeTypeOfIntStaticMethod() {
        assertEquals(int.class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "anIntMethod", 7)));
    }

    public static void whenComputeTypeOfIntObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals(int.class, expressionClass(obj.call("anIntMethod", 7)));
    }

    public static void whenExecuteKoanIntArrayMethodThenReturnResult() {
        assertEquals(new int[]{4, 3}, execute(callKoanMethod("anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenExecuteIntArrayStaticMethodThenReturnResult() {
        assertEquals(new int[]{4, 3}, execute(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenExecuteIntArrayObjectMethodThenReturnResult() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals(new int[]{4, 3}, execute(obj.call("anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenFormatKoanIntArrayMethod() {
        assertEquals("anIntArrayMethod(new int[]{4,3})", format(callKoanMethod("anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenFormatIntArrayStaticMethod() {
         assertEquals("engine.test.ExpressionTests.anIntArrayMethod(new int[]{4,3})", format(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenFormatIntArrayObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3).anIntArrayMethod(new int[]{4,3})", format(obj.call("anIntArrayMethod", new int[]{4, 3})));;
    }
    
    public static void whenComputeTypeOfKoanIntArrayMethod() {
        assertEquals(int[].class, expressionClass(callKoanMethod("anIntArrayMethod", new int[]{4, 3})));
    }
     
    public static void whenComputeTypeOfIntArrayStaticMethod() {
        assertEquals(int[].class, expressionClass(callStaticMethod("engine.test.ExpressionTests", "anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenComputeTypeOfIntArrayObjectMethod() {
        final var obj = newObject("engine.test.ExpressionTests$AClass", "arg1", 3);
        assertEquals(int[].class, expressionClass(obj.call("anIntArrayMethod", new int[]{4, 3})));
    }
    
    public static void whenExecuteNewObjectThenReturnCreatedObject() {
        final var obj = execute(newObject("engine.test.ExpressionTests$AClass", "arg1", 3));

        assertTrue(obj != null, "created object should not be null");
        assertTrue(obj instanceof AClass, "created object should be of the right class");
        final var aClassInstance = (AClass)obj;
        assertEquals("arg1", aClassInstance.arg1);
        assertEquals(3, aClassInstance.arg2);
    }
    
    public static void whenFormatNewObject() {
        assertEquals("new engine.test.ExpressionTests$AClass(\"arg1\", 3)", format(newObject("engine.test.ExpressionTests$AClass", "arg1", 3)));
    }
    
    public static void whenComputeTypeOfNewObject() {
        assertEquals(AClass.class, expressionClass(newObject("engine.test.ExpressionTests$AClass", "arg1", 3)));
    }
    
    public static void whenExecutevariableThenReturnVariableContent() {
        final var ctx = context();
        ctx.setVariableValue("a", 123);
        assertEquals(123, variable("a").execute(ctx));
    }
    
    public static void whenFormatVariable() {
        assertEquals("a", format(variable("a")));
    }
    
    public static void whenComputeTypeOfVariable() {
        final var ctx = context();
        ctx.setVariableValue("a", 123);
        assertEquals(Integer.class, variable("a").expressionClass(ctx));
    }
    
    public static void whenExecuteVariableAssignmentThenReturnnull() {
        assertTrue(null == execute(assignVariable("a", 123)), "Assignment should return null");
    }
    
    public static void whenFormatVariableAssignment() {
        assertEquals("var a = 123", format(assignVariable("a", 123)));
    }

    public static void whenComputeTypeOfVariableAssignment() {
        assertEquals(void.class, expressionClass(assignVariable("a", 123)));
    }

    public static void whenFormatSourceCode() {
        final var script = new Expression[]{
            assignVariable("a", 123),
            callKoanMethod("anIntMethod", variable("a"))
        };
        assertEquals("var a = 123;\nanIntMethod(a);\n", Expression.formatSourceCode(script, Locale.en));
    }

    public static void whenFormatPreparationSourceCodeThenOmitLastExpression() {
        final var script = new Expression[]{
            assignVariable("a", 123),
            callKoanMethod("anIntMethod", variable("a"))
        };
        assertEquals("  var a = 123;\n", Expression.formatPreparationSourceCode(script, Locale.en, "  "));
    }

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
