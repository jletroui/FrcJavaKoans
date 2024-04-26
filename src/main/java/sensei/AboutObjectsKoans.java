package sensei;

import static engine.Assertions.assertFieldIsFinal;
import static engine.Assertions.assertFieldIsPrivate;
import static engine.Assertions.assertFieldType;
import static engine.Assertions.assertMethodIsInvokable;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueStringRepresentationEquals;
import static engine.Factories.global;
import static engine.Factories.type;
import static engine.Factories.value;
import static engine.ObjectPreCall.invokeMethod;

import java.util.List;

import engine.Koan;
import engine.Type;

public class AboutObjectsKoans {
    private static final Type DOUBLE = type(double.class);
    private static final Type POINT = type("geom.Point");

    public static final List<Koan> koans = List.of(
        // new Koan("geom.Point", "toString")
        //     .usingConstructor(DOUBLE, DOUBLE)
        //     .beforeTesting(
        //         assertFieldIsPrivate("x"),
        //         assertFieldIsFinal("x"),
        //         assertFieldType("x", DOUBLE),
        //         assertFieldIsPrivate("y"),
        //         assertFieldIsFinal("y"),
        //         assertFieldType("y", DOUBLE)
        //     )
        //     .onObjectConstructedWith(2.0, 2.0)
        //     .whenCalled(),
        // new Koan("geom.Point", "toString")
        //     .usingConstructor(DOUBLE, DOUBLE)
        //     .onObjectConstructedWith(2.0, 2.0)
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Point(2.0, 2.0)"))
        //     )
        //     .onObjectConstructedWith(-2.0, 4.5)
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Point(-2.0, 4.5)"))
        //     ),
        // new Koan("geom.Point", "translate", double.class, double.class)
        //     .usingConstructor(DOUBLE, DOUBLE)
        //     .onObjectConstructedWith(2.0, 2.0)
        //     .whenCalledWith(0.0, 0.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Point(2.0, 2.0)"), "geom.Point")
        //     )
        //     .onObjectConstructedWith(2.0, 2.0)
        //     .whenCalledWith(1.0, -2.5)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Point(3.0, -0.5)"), "geom.Point")
        //     )
        //     .onObjectConstructedWith(-2.0, 2.0)
        //     .whenCalledWith(1.0, 1.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Point(-1.0, 3.0)"), "geom.Point")
        //     ),
        // new Koan("geom.Triangle", "toString")
        //     .usingConstructor(POINT, POINT, POINT)
        //     .beforeTesting(
        //         assertFieldIsPrivate("a"),
        //         assertFieldIsFinal("a"),
        //         assertFieldType("a", POINT),
        //         assertFieldIsPrivate("b"),
        //         assertFieldIsFinal("b"),
        //         assertFieldType("b", POINT),
        //         assertFieldIsPrivate("c"),
        //         assertFieldIsFinal("c"),
        //         assertFieldType("c", POINT)
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
        //     .whenCalled(),
        // new Koan("geom.Triangle", "toString")
        //     .usingConstructor(POINT, POINT, POINT)
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))"))
        //     )
        //     .onObjectConstructedWith(value(POINT, 0.0, -2.0), value(POINT, 0.0, 0.0), value(POINT, 2.5, 5.3))
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Triangle(Point(0.0, -2.0), Point(0.0, 0.0), Point(2.5, 5.3))"))
        //     ),
        // new Koan("geom.Triangle", "translate", double.class, double.class)
        //     .usingConstructor(POINT, POINT, POINT)
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
        //     .whenCalledWith(0.0, 0.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))"), "geom.Triangle")
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
        //     .whenCalledWith(1.0, -2.5)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, -0.5), Point(1.0, -2.5), Point(3.0, -3.5))"), "geom.Triangle")
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
        //     .whenCalledWith(1.0, 1.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, 3.0), Point(1.0, 1.0), Point(3.0, 0.0))"), "geom.Triangle")
        //     ),                
        // new Koan("geom.Circle", "toString")
        //     .usingConstructor(POINT, DOUBLE)
        //     .beforeTesting(
        //         assertFieldIsPrivate("center"),
        //         assertFieldIsFinal("center"),
        //         assertFieldType("center", POINT),
        //         assertFieldIsPrivate("radius"),
        //         assertFieldIsFinal("radius"),
        //         assertFieldType("radius", DOUBLE)
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Circle(Point(2.0, 2.0), 1.7)"))
        //     )
        //     .onObjectConstructedWith(value(POINT, -2.5, 8.2), value(4.5))
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("Circle(Point(-2.5, 8.2), 4.5)"))
        //     ),
        // new Koan("geom.Circle", "translate", double.class, double.class)
        //     .usingConstructor(POINT, DOUBLE)
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
        //     .whenCalledWith(0.0, 0.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Circle(Point(2.0, 2.0), 1.7)"), "geom.Circle")
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
        //     .whenCalledWith(1.0, -2.5)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, -0.5), 1.7)"), "geom.Circle")
        //     )
        //     .onObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
        //     .whenCalledWith(1.0, 1.0)
        //     .then(
        //         assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, 3.0), 1.7)"), "geom.Circle")
        //     ),
        // new Koan("frc.RobotAutoScore", "toString")
        //     .usingConstructor()
        //     .beforeTesting(
        //         assertFieldIsPrivate("notesInSpeaker"),
        //         assertFieldType("notesInSpeaker",  type(int.class)),
        //         assertFieldIsPrivate("notesInAmp"),
        //         assertFieldType("notesInAmp",  type(int.class))
        //     )
        //     .onObjectConstructedWith()
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("RobotScore: notes in speaker = 0; notes in amp = 0"))
        //     ),
        // new Koan("frc.RobotAutoScore", "toString")
        //     .usingConstructor()
        //     .beforeTesting(
        //         assertMethodIsInvokable("noteScoredInSpeaker", false, new Type[0])
        //     )
        //     .onObjectConstructedWith()
        //     .onObjectPreparedWith(
        //         invokeMethod("noteScoredInSpeaker")
        //     )
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("RobotScore: notes in speaker = 1; notes in amp = 0"))
        //     )
        //     .onObjectConstructedWith()
        //     .onObjectPreparedWith(
        //         invokeMethod("noteScoredInSpeaker"),
        //         invokeMethod("noteScoredInSpeaker")
        //     )
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("RobotScore: notes in speaker = 2; notes in amp = 0"))
        //     ),
        // new Koan("frc.RobotAutoScore", "toString")
        //     .usingConstructor()
        //     .beforeTesting(
        //         assertMethodIsInvokable("noteScoredInAmp", false, new Type[0])
        //     )
        //     .onObjectConstructedWith()
        //     .onObjectPreparedWith(
        //         invokeMethod("noteScoredInAmp")
        //     )
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("RobotScore: notes in speaker = 0; notes in amp = 1"))
        //     )
        //     .onObjectConstructedWith()
        //     .onObjectPreparedWith(
        //         invokeMethod("noteScoredInAmp"),
        //         invokeMethod("noteScoredInAmp")
        //     )
        //     .whenCalled()
        //     .then(
        //         assertReturnValueEquals(global("RobotScore: notes in speaker = 0; notes in amp = 2"))
        //     ),
        new Koan("frc.RobotAutoScore", "totalScore")
            .usingConstructor()
            .onObjectConstructedWith()
            .whenCalled()
            .then(
                assertReturnValueEquals(0)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInAmp")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(2)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInSpeaker")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(5)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInAmp"),
                invokeMethod("noteScoredInSpeaker")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(7)
            ),
        new Koan("frc.AllianceAutoScore", "totalScore")
            .usingConstructor(type("frc.RobotAutoScore"), type("frc.RobotAutoScore"), type("frc.RobotAutoScore"))
            .onObjectConstructedWith(value(type("frc.RobotAutoScore")), value(type("frc.RobotAutoScore")), value(type("frc.RobotAutoScore")))
            .whenCalled()
            .then(
                assertReturnValueEquals(0)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInAmp")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(2)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInSpeaker")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(5)
            )
            .onObjectConstructedWith()
            .onObjectPreparedWith(
                invokeMethod("noteScoredInAmp"),
                invokeMethod("noteScoredInSpeaker")
            )
            .whenCalled()
            .then(
                assertReturnValueEquals(7)
            )
    );
}
