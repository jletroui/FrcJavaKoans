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
            .whenCalling()
    );
}
