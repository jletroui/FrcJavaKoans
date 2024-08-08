package sensei;

import static engine.Assertions.assertConstructorIsInvokable;
import static engine.Assertions.assertPrivateFinalField;
import static engine.Assertions.assertPrivateField;
import static engine.Assertions.assertObjectMethodIsInvokable;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueStringRepresentationEquals;
import static engine.Localizable.localClass;
import static engine.Localizable.global;
import static engine.script.Expression.newObject;
import static engine.script.Expression.assignVariable;
import static engine.script.Expression.variable;
import static engine.script.Type.type;
import static sensei.Texts.*;


import java.util.List;

import engine.Koan;
import engine.Localizable;
import engine.script.Type;

public class AboutObjectsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutObjects.class);
        
    private static final Type DOUBLE = type(double.class);
    private static final Type POINT = type("geom.Point");
    private static final Type ROBOT_AUTO_SCORE = type("frc.RobotAutoScore");

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, THE_FIRST_OBJECT)
            .beforeFirstTest(
                assertConstructorIsInvokable("geom.Point", DOUBLE, DOUBLE),
                assertPrivateFinalField("geom.Point", "x", DOUBLE),
                assertPrivateFinalField("geom.Point", "y", DOUBLE)
            )
            .when(
                newObject("geom.Point", 2.0, 2.0)
            ),
        new Koan(CLASS, AN_OBJECT_METHOD)
            .when(
                newObject("geom.Point", 2.0, 2.0).call("toString")
            )
            .then(
                assertReturnValueEquals("Point(2.0, 2.0)")
            )
            .when(
                newObject("geom.Point", -2.0, 4.5).call("toString")
            )
            .then(
                assertReturnValueEquals("Point(-2.0, 4.5)")
            ),
        new Koan(CLASS, AN_OTHER_OBJECT_METHOD)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("geom.Point", "translate",  double.class, double.class)
            )
            .when(
                newObject("geom.Point", 2.0, 2.0).call("translate", 0.0, 0.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(2.0, 2.0)"), "geom.Point")
            )
            .when(
                newObject("geom.Point", 2.0, 2.0).call("translate", 1.0, -2.5)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(3.0, -0.5)"), "geom.Point")
            )
            .when(
                newObject("geom.Point", -2.0, 2.0).call("translate", 1.0, 1.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(-1.0, 3.0)"), "geom.Point")
            ),
        new Koan(CLASS, AN_OBJECT_USING_OTHER_KIND_OF_OBJECTS)
            .beforeFirstTest(
                assertConstructorIsInvokable("geom.Triangle", POINT, POINT, POINT),
                assertPrivateFinalField("geom.Triangle", "a", POINT),
                assertPrivateFinalField("geom.Triangle", "b", POINT),
                assertPrivateFinalField("geom.Triangle", "c", POINT)
            )
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 2.0, 2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.0, -1.0)
                )
            ),
        new Koan(CLASS, USING_OTHER_OBJECT_S_METHOD)
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 2.0, 2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.0, -1.0)
                ).call("toString")
            )
            .then(
                assertReturnValueEquals("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))")
            )
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 0.0, -2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.5, 5.3)
                ).call("toString")
            )
            .then(
                assertReturnValueEquals("Triangle(Point(0.0, -2.0), Point(0.0, 0.0), Point(2.5, 5.3))")
            ),
        new Koan(CLASS, USING_ANOTHER_OBJECT_S_METHOD)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("geom.Triangle", "translate",  double.class, double.class)
            )
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 2.0, 2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.0, -1.0)
                ).call("translate", 0.0, 0.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))"), "geom.Triangle")
            )
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 2.0, 2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.0, -1.0)
                ).call("translate", 1.0, -2.5)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, -0.5), Point(1.0, -2.5), Point(3.0, -3.5))"), "geom.Triangle")
            )
            .when(
                newObject(
                    "geom.Triangle",
                    newObject("geom.Point", 2.0, 2.0),
                    newObject("geom.Point", 0.0, 0.0),
                    newObject("geom.Point", 2.0, -1.0)
                ).call("translate", 1.0, 1.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, 3.0), Point(1.0, 1.0), Point(3.0, 0.0))"), "geom.Triangle")
            ),                
        new Koan(CLASS, APPLY_LEARNINGS_THE_CIRCLE_CLASS)
            .beforeFirstTest(
                assertConstructorIsInvokable("geom.Circle", POINT, DOUBLE),
                assertPrivateFinalField("geom.Circle", "center", POINT),
                assertPrivateFinalField("geom.Circle", "radius", DOUBLE)
            )
            .when(
                newObject("geom.Circle", newObject("geom.Point", 2.0, 2.0), 1.7).call("toString")
            )
            .then(
                assertReturnValueEquals("Circle(Point(2.0, 2.0), 1.7)")
            )
            .when(
                newObject("geom.Circle", newObject("geom.Point", -2.5, 8.2), 4.5).call("toString")
            )
            .then(
                assertReturnValueEquals("Circle(Point(-2.5, 8.2), 4.5)")
            ),
        new Koan(CLASS, APPLY_LEARNINGS_CIRCLE_TRANSLATION)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("geom.Circle", "translate",  double.class, double.class)
            )
            .when(
                newObject("geom.Circle", newObject("geom.Point", 2.0, 2.0), 1.7).call("translate", 0.0, 0.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(2.0, 2.0), 1.7)"), "geom.Circle")
            )
            .when(
                newObject("geom.Circle", newObject("geom.Point", 2.0, 2.0), 1.7).call("translate", 1.0, -2.5)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, -0.5), 1.7)"), "geom.Circle")
            )
            .when(
                newObject("geom.Circle", newObject("geom.Point", 2.0, 2.0), 1.7).call("translate", 1.0, 1.0)
            )
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, 3.0), 1.7)"), "geom.Circle")
            ),
        new Koan(CLASS, OBJECTS_WITH_MUTATING_FIELDS)
            .beforeFirstTest(
                assertConstructorIsInvokable("frc.RobotAutoScore"),
                assertPrivateField("frc.RobotAutoScore", "notesInSpeaker",  type(int.class)),
                assertPrivateField("frc.RobotAutoScore", "notesInAmp",  type(int.class))
            )
            .when(
                newObject("frc.RobotAutoScore").call("toString")
            )
            .then(
                assertReturnValueEquals(ROBOT_SCORE_TO_STRING_0)
            ),
        new Koan(CLASS, MUTATE_OBJECT_S_FIELDS)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("frc.RobotAutoScore", "noteScoredInSpeaker")
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInSpeaker"),
                variable("autoScore").call("toString")
            )
            .then(
                assertReturnValueEquals(ROBOT_SCORE_TO_STRING_1_0)
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInSpeaker"),
                variable("autoScore").call("noteScoredInSpeaker"),
                variable("autoScore").call("toString")
            )
            .then(
                assertReturnValueEquals(ROBOT_SCORE_TO_STRING_2_0)
            ),
        new Koan(CLASS, MORE_OBJECT_S_FIELDS_MUTATIONS)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("frc.RobotAutoScore", "noteScoredInAmp")
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInAmp"),
                variable("autoScore").call("toString")
            )
            .then(
                assertReturnValueEquals(ROBOT_SCORE_TO_STRING_0_1)
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInAmp"),
                variable("autoScore").call("noteScoredInAmp"),
                variable("autoScore").call("toString")
            )
            .then(
                assertReturnValueEquals(ROBOT_SCORE_TO_STRING_0_2)
            ),
        new Koan(CLASS, COMPUTING_THE_TOTAL_SCORE_FOR_A_ROBOT)
            .beforeFirstTest(
                assertObjectMethodIsInvokable("frc.RobotAutoScore", "totalScore")
            )
            .when(
                newObject("frc.RobotAutoScore").call("totalScore")
            )
            .then(
                assertReturnValueEquals(0)
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInAmp"),
                variable("autoScore").call("totalScore")
            )
            .then(
                assertReturnValueEquals(2)
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInSpeaker"),
                variable("autoScore").call("totalScore")
            )
            .then(
                assertReturnValueEquals(5)
            )
            .when(
                assignVariable("autoScore", newObject("frc.RobotAutoScore")),
                variable("autoScore").call("noteScoredInAmp"),
                variable("autoScore").call("noteScoredInSpeaker"),
                variable("autoScore").call("totalScore")
            )
            .then(
                assertReturnValueEquals(7)
            ),
        new Koan(CLASS, COMPUTING_THE_TOTAL_SCORE_FOR_AN_ENTIRE_ALLIANCE)
            .beforeFirstTest(
                assertConstructorIsInvokable("frc.AllianceAutoScore", ROBOT_AUTO_SCORE, ROBOT_AUTO_SCORE, ROBOT_AUTO_SCORE),
                assertPrivateFinalField("frc.AllianceAutoScore", "robotAScore",  ROBOT_AUTO_SCORE),
                assertPrivateFinalField("frc.AllianceAutoScore", "robotBScore",  ROBOT_AUTO_SCORE),
                assertPrivateFinalField("frc.AllianceAutoScore", "robotCScore",  ROBOT_AUTO_SCORE),
                assertObjectMethodIsInvokable("frc.AllianceAutoScore", "totalScore")
            )
            .when(
                newObject(
                    "frc.AllianceAutoScore",
                    newObject("frc.RobotAutoScore"),
                    newObject("frc.RobotAutoScore"),
                    newObject("frc.RobotAutoScore")
                ).call("totalScore")
            )
            .then(
                assertReturnValueEquals(0)
            )
            .when(
                assignVariable("robot1Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot2Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot3Score", newObject("frc.RobotAutoScore")),
                variable("robot2Score").call("noteScoredInAmp"),
                newObject("frc.AllianceAutoScore", variable("robot1Score"), variable("robot2Score"), variable("robot3Score")).call("totalScore")
            )
            .then(
                assertReturnValueEquals(2)
            )
            .when(
                assignVariable("robot1Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot2Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot3Score", newObject("frc.RobotAutoScore")),
                variable("robot3Score").call("noteScoredInSpeaker"),
                newObject("frc.AllianceAutoScore", variable("robot1Score"), variable("robot2Score"), variable("robot3Score")).call("totalScore")
            )
            .then(
                assertReturnValueEquals(5)
            )
            .when(
                assignVariable("robot1Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot2Score", newObject("frc.RobotAutoScore")),
                assignVariable("robot3Score", newObject("frc.RobotAutoScore")),
                variable("robot1Score").call("noteScoredInAmp"),
                variable("robot2Score").call("noteScoredInSpeaker"),
                newObject("frc.AllianceAutoScore", variable("robot1Score"), variable("robot2Score"), variable("robot3Score")).call("totalScore")
            )
            .then(
                assertReturnValueEquals(7)
            )
    );
}
