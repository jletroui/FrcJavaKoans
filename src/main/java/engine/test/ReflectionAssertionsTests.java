package engine.test;

import static engine.Assertions.assertImplementsInterface;
import static engine.ConsoleFmt.code;
import static engine.ConsoleFmt.format;
import static engine.Localizable.global;

import java.util.List;
import java.util.function.IntConsumer;

import engine.Koan;
import engine.Localizable;
import engine.ConsoleFmt.Formats;
import engine.test.runner.Line;
import engine.test.runner.UnitTest;
import engine.test.runner.KoanUnitTest;
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.test.runner.UnitTestExpectation.assertFailure;
import static engine.test.runner.UnitTestExpectation.assertSuccess;
import static engine.Texts.*;

public class ReflectionAssertionsTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<UnitTest> tests = List.of(
        new KoanUnitTest(
            new Koan(CLASS, global("implements interface"))
                .beforeFirstTest(assertImplementsInterface("engine.test.simulation.SomeImplementation", SomeInterface.class))
                .when(callKoanMethod("simpleConsoleOutput")),
            assertSuccess()
        ),
        new KoanUnitTest(
            new Koan(CLASS, global("does not implement interface"))
                .beforeFirstTest(assertImplementsInterface("engine.test.simulation.SomeImplementation", IntConsumer.class))
                .when(callKoanMethod("simpleConsoleOutput")),
            assertFailure(
                new Line.Localized(format(EXPECTED_CLASS_TO_IMPLEMENT, Formats.Red, code("engine.test.simulation.SomeImplementation"), code("java.util.function.IntConsumer")))
            )
        )
    );
}
