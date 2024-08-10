package engine.test;

import engine.Koan;
import engine.KoanBugException;
import engine.ResultAssertion;
import engine.TestSensei;
import engine.text.Localizable;

import static engine.Texts.BUG_FOUND;
import static engine.Texts.THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH;
import static engine.Texts.THE_METHOD_APPEARS_TO_DIVIDE_BY_0;
import static engine.Texts.THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR;
import static engine.Texts.THE_METHOD_SEEMS_TO_RECURSE_INFINITELY;
import static engine.console.Fmt.code;
import static engine.console.Fmt.normal;
import static engine.console.Fmt.red;
import static engine.console.Fmt.sameStyle;
import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.RunnerAssertions.assertKoanFails;
import static engine.text.Localizable.global;

public class ExceptionAssertionsTests {
    private static final Localizable<Class<?>> CLASS = global(ExceptionAssertionsTests.class);

    public static void doNothing() {
    }

    public static int throwDivisionPer0() {
        return 1/0;
    }

    public static int throwUnknownException() {
        throw new RuntimeException("oops");
    }

    public static int recurseInfinitely(int x) {
        return recurseInfinitely(x);
    }

    public static void loopInfinitely() {
        while(true) {
            // How cool is it to really have a use for this loop as a programmer?
        }
    }

    public static void whenDivideBy0() {
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenDivideBy0"))
                .when(
                    callKoanMethod("throwDivisionPer0")
                )
        );

        assertKoanFails(
            res[0],
            red(
                THE_METHOD_APPEARS_TO_DIVIDE_BY_0,
                code("ExceptionAssertionsTests.throwDivisionPer0()")
            )
        );
    }

    public static void whenThrowUnknownException() {
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenThrowUnknownException"))
                .when(
                    callKoanMethod("throwUnknownException")
                )
        );

        assertKoanFails(
            res[0],
            red(
                THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR,
                code("ExceptionAssertionsTests.throwUnknownException()"),
                sameStyle("oops")
            )
        );
    }

    public static void whenRecurseInfinitely() {
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenRecurseInfinitely"))
                .when(
                    callKoanMethod("recurseInfinitely", 1)
                )
        );

        assertKoanFails(
            res[0],
            red(
                THE_METHOD_SEEMS_TO_RECURSE_INFINITELY,
                code("ExceptionAssertionsTests.recurseInfinitely()")
            )
        );
    }

    public static void whenLoopInfinitely() {
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenLoopInfinitely"))
                .when(
                    callKoanMethod("loopInfinitely")
                )
        );

        assertKoanFails(
            res[0],
            red(THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH),
            normal(""),
            code("loopInfinitely();\n")
        );
    }

    public static void whenBugDetectedInAssertions() {
        final ResultAssertion buggyAssertion = (_p, _output) -> {
            throw new KoanBugException("buggy...");
        };
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenBugDetectedInAssertions"))
                .when(
                    callKoanMethod("doNothing")
                )
                .then(
                    buggyAssertion
                )
        );

        assertKoanFails(
            res[0],
            red(
                BUG_FOUND,
                sameStyle("buggy...")
            )
        );
    }

    public static void whenBugDetectedInCall() {
        final var res = TestSensei.execute(
            new Koan(CLASS, global("whenBugDetectedInCall"))
                .when(
                    callKoanMethod("unkown")
                )
        );

        assertKoanFails(
            res[0],
            red(
                BUG_FOUND,
                sameStyle("Koans should assert that methods have the right number of arguments. Did not find a method unkown in engine.test.ExceptionAssertionsTests with 0 arguments.")
            )
        );
    }    
}
