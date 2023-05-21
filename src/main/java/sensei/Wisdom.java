package sensei;

import java.util.List;

import engine.Koan;

public final class Wisdom {
    public static final List<List<Koan>> koans = List.of(
        AboutConsoleAndVariablesKoans.koans,
        AboutMethodsKoans.koans,
        AboutConditionsKoans.koans
    );
}
