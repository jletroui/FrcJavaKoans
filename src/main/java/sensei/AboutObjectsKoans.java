package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutObjects;
import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutObjectsKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutObjects.class)
        .fr(AboutObjects.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "toString")
            .inClass("geom.Point", double.class, double.class)
            .beforeCalling(
                assertFieldIsPrivate("x"),
                assertFieldIsFinal("x"),
                assertFieldType("x", double.class),
                assertFieldIsPrivate("y"),
                assertFieldIsFinal("y"),
                assertFieldType("y", double.class)
            )
            .withObjectConstructedWith(2.0, 2.0)
            .whenCalling(),
        new Koan(CLASS, "toString")
            .inClass("geom.Point", double.class, double.class)
            .withObjectConstructedWith(2.0, 2.0)
            .whenCalling()
            .then(
                assertReturnValueEquals(global("Point(2.0, 2.0)"))
            ),
        new Koan(CLASS, "translate", double.class, double.class)
            .inClass("geom.Point", double.class, double.class)
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
            )    
    );
}
