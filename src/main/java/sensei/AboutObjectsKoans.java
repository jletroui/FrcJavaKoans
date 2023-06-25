package sensei;

import static engine.Assertions.assertFieldIsFinal;
import static engine.Assertions.assertFieldIsPrivate;
import static engine.Assertions.assertFieldType;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueStringRepresentationEquals;
import static engine.Factories.global;
import static engine.Factories.localClass;
import static engine.Factories.type;
import static engine.Factories.value;

import java.util.List;

import engine.Koan;
import engine.Local;
import engine.Type;

public class AboutObjectsKoans {
    private static final Type DOUBLE = type(double.class);

    private static final Type POINT = type("geom.Point");

    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutObjects.class)
        .fr(koans.french.AboutObjects.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "toString")
            .inClass("geom.Point", DOUBLE, DOUBLE)
            .beforeCalling(
                assertFieldIsPrivate("x"),
                assertFieldIsFinal("x"),
                assertFieldType("x", DOUBLE),
                assertFieldIsPrivate("y"),
                assertFieldIsFinal("y"),
                assertFieldType("y", DOUBLE)
            )
            .withObjectConstructedWith(2.0, 2.0)
            .whenCalling(),
        new Koan(CLASS, "toString")
            .inClass("geom.Point", DOUBLE, DOUBLE)
            .withObjectConstructedWith(2.0, 2.0)
            .whenCalling()
            .then(
                assertReturnValueEquals(global("Point(2.0, 2.0)"))
            ),
        new Koan(CLASS, "translate", double.class, double.class)
            .inClass("geom.Point", DOUBLE, DOUBLE)
            .withObjectConstructedWith(2.0, 2.0)
            .whenCallingWith(0.0, 0.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(2.0, 2.0)"), "geom.Point")
            )
            .withObjectConstructedWith(2.0, 2.0)
            .whenCallingWith(1.0, -2.5)
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(3.0, -0.5)"), "geom.Point")
            )
            .withObjectConstructedWith(-2.0, 2.0)
            .whenCallingWith(1.0, 1.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Point(-1.0, 3.0)"), "geom.Point")
            ),
        new Koan(CLASS, "toString")
            .inClass("geom.Triangle", POINT, POINT, POINT)
            .beforeCalling(
                assertFieldIsPrivate("a"),
                assertFieldIsFinal("a"),
                assertFieldType("a", POINT),
                assertFieldIsPrivate("b"),
                assertFieldIsFinal("b"),
                assertFieldType("b", POINT),
                assertFieldIsPrivate("c"),
                assertFieldIsFinal("c"),
                assertFieldType("c", POINT)
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
            .whenCalling(),
        new Koan(CLASS, "toString")
            .inClass("geom.Triangle", POINT, POINT, POINT)
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
            .whenCalling()
            .then(
                assertReturnValueEquals(global("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))"))
            ),
        new Koan(CLASS, "translate", double.class, double.class)
            .inClass("geom.Triangle", POINT, POINT, POINT)
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
            .whenCallingWith(0.0, 0.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(2.0, 2.0), Point(0.0, 0.0), Point(2.0, -1.0))"), "geom.Triangle")
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
            .whenCallingWith(1.0, -2.5)
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, -0.5), Point(1.0, -2.5), Point(3.0, -3.5))"), "geom.Triangle")
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(POINT, 0.0, 0.0), value(POINT, 2.0, -1.0))
            .whenCallingWith(1.0, 1.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Triangle(Point(3.0, 3.0), Point(1.0, 1.0), Point(3.0, 0.0))"), "geom.Triangle")
            ),                
        new Koan(CLASS, "toString")
            .inClass("geom.Circle", POINT, type(double.class))
            .beforeCalling(
                assertFieldIsPrivate("center"),
                assertFieldIsFinal("center"),
                assertFieldType("center", POINT),
                assertFieldIsPrivate("radius"),
                assertFieldIsFinal("radius"),
                assertFieldType("radius", type(double.class))
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
            .whenCalling()
            .then(
                assertReturnValueEquals(global("Circle(Point(2.0, 2.0), 1.7)"))
            ),
        new Koan(CLASS, "translate", double.class, double.class)
            .inClass("geom.Circle", POINT, type(double.class))
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
            .whenCallingWith(0.0, 0.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(2.0, 2.0), 1.7)"), "geom.Circle")
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
            .whenCallingWith(1.0, -2.5)
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, -0.5), 1.7)"), "geom.Circle")
            )
            .withObjectConstructedWith(value(POINT, 2.0, 2.0), value(1.7))
            .whenCallingWith(1.0, 1.0)
            .then(
                assertReturnValueStringRepresentationEquals(global("Circle(Point(3.0, 3.0), 1.7)"), "geom.Circle")
            )                
    );
}
