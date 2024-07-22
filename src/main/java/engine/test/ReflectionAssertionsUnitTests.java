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
import engine.test.simulation.SomeInterface;
import engine.test.simulation.StudentSolutions;

import static engine.script.Expression.callKoanMethod;
import static engine.Texts.*;
import static engine.test.UnitTestExpectation.assertSuccess;
import static engine.test.UnitTestExpectation.assertFailure;

public class ReflectionAssertionsUnitTests {
    private static Localizable<Class<?>> CLASS = global(StudentSolutions.class);
    
    public static final List<UnitTest> items = List.of(
        new UnitTest(
            new Koan(CLASS, global("implements interface"))
                .beforeFirstTest(assertImplementsInterface("engine.test.simulation.SomeImplementation", SomeInterface.class))
                .when(callKoanMethod("simpleConsoleOutput")),
            assertSuccess()
        ),
        new UnitTest(
            new Koan(CLASS, global("does not implement interface"))
                .beforeFirstTest(assertImplementsInterface("engine.test.simulation.SomeImplementation", IntConsumer.class))
                .when(callKoanMethod("simpleConsoleOutput")),
            assertFailure(
                new Line.Localized(format(EXPECTED_CLASS_TO_IMPLEMENT, Formats.Red, code("engine.test.simulation.SomeImplementation"), code("java.util.function.IntConsumer")))
            )
        )
    );
}
