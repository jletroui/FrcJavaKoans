package sensei;

import java.util.List;

import engine.Koan;

/**
 * All the widom of the master, materialized in the series of all available koans.
 */
public final class Wisdom {
    public static final List<List<Koan>> koans = List.of(
        AboutConsoleAndVariablesKoans.koans,
        AboutMethodsKoans.koans,
        AboutConditionsKoans.koans,
        AboutMoreMethodsKoans.koans,
        AboutDecimalNumbersKoans.koans,
        AboutLoopsKoans.koans,
        AboutNot7GameKoans.koans,
        AboutArraysKoans.koans,
        AboutClassesKoans.koans,
        AboutObjectsKoans.koans
    );
}
