package sensei;

import engine.Koan;

import java.util.List;

import koans.english.AboutNot7Game;

import static engine.Assertions.*;
import engine.Local;
import static engine.Localizable.*;

public class AboutNot7GameKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutNot7Game.class)
        .fr(AboutNot7Game.class); // TODO

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "die6")
            .whenCalling()
            .then(
                assertResultWithRandomEquals(rand -> die(6, rand))
            ),
        new Koan(CLASS, "die6Sum", int.class)
            .whenCallingWith(2)
            .then(
                assertResultWithRandomEquals(2, rand -> dieSum(6, rand))
            )
            .whenCallingWith(3)
            .then(
                assertResultWithRandomEquals(3, rand -> dieSum(6, rand))
            )

    );

    private static int die(int faces, double rand) {
        return (int)Math.ceil(faces * rand);
    }

    private static int dieSum(int faces, double[] rand) {
        int sum = 0;
        for(int i = 0; i< rand.length; i++) {
            sum += die(faces, rand[i]);
        }
        return sum;
    }
}
