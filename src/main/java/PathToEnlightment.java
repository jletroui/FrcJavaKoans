import engine.Sensei;
import sensei.AboutConsoleAndVariablesKoans;
import sensei.AboutMethodsKoans;

public class PathToEnlightment {
    public static void main(String[] args) {
        Sensei.offerKoans(
            AboutConsoleAndVariablesKoans.koans,
            AboutMethodsKoans.koans
        );
    }
}
