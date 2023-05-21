import engine.Sensei;
import sensei.AboutConditionsKoans;
import sensei.AboutConsoleAndVariablesKoans;
import sensei.AboutMethodsKoans;

public class PathToEnlightment {
    public static void main(String[] args) {
        new Sensei().offerKoans(
            AboutConsoleAndVariablesKoans.koans,
            AboutMethodsKoans.koans,
            AboutConditionsKoans.koans
        );
    }
}
