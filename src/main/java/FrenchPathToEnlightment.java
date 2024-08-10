import engine.Sensei;
import engine.text.Locale;
import sensei.Wisdom;

public class FrenchPathToEnlightment {
    public static void main(String[] args) {
        new Sensei(Locale.fr, Wisdom.koans).offerKoans();
    }
}
