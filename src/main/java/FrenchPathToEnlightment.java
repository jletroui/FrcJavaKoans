import engine.Locale;
import engine.Sensei;
import sensei.Wisdom;

public class FrenchPathToEnlightment {
    public static void main(String[] args) {
        new Sensei(Locale.fr, Wisdom.koans).offerKoans();
    }
}
