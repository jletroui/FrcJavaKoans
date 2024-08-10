import engine.Sensei;
import engine.text.Locale;
import sensei.Wisdom;

public class EnglishPathToEnlightment {
    public static void main(String[] args) {
        new Sensei(Locale.en, Wisdom.koans).offerKoans();
    }
}
