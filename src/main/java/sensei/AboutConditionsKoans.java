package sensei;

import engine.Koan;
import koans.AboutConditions;

import static engine.Assertions.*;

import java.util.List;

public class AboutConditionsKoans {
    public static final List<Koan> koans = List.of(
        new Koan(AboutConditions.class, "sign", int.class)
            .whenCallingWith(2)
            .then(assertResultEquals("positive"))
            .whenCallingWith(0)
            .then(assertResultEquals("positive"))
            .whenCallingWith(-2)
            .then(assertResultEquals("negative"))
    );
}
