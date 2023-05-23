import engine.Locale;
import engine.Sensei;
import sensei.Wisdom;

public class EnglishPathToEnlightment {
    public static void main(String[] args) {
        new Sensei(Locale.en, Wisdom.koans).offerKoans();
    }
}
