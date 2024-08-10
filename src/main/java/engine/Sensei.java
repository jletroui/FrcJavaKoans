package engine;

import java.util.List;

import engine.console.Fmt;
import engine.console.Printer;
import engine.console.Style;
import engine.script.Expression;
import engine.text.Locale;

import static engine.Texts.*;
import static engine.console.Fmt.code;
import static engine.console.Fmt.cyan;
import static engine.console.Fmt.green;
import static engine.console.Fmt.normal;
import static engine.console.Fmt.red;
import static engine.console.Fmt.sameStyle;
import static engine.console.Fmt.sequence;
import static engine.console.Fmt.strong;
import static engine.console.Fmt.strongRed;

/**
 * The main engine class, executing the series of koans.
 */
public class Sensei {
    private final Locale locale;
    private final Printer consolePrinter;
    private Printer p = Printer.SILENT;
    private List<Koan> allKoans;

    public Sensei(Locale locale, List<List<Koan>> koanSeries) {
        this.locale = locale;
        this.consolePrinter = Printer.console(locale);
        this.allKoans = koanSeries
            .stream()
            .flatMap(kl -> kl.stream())
            .toList();
    }

    public void offerKoans() {
        for(int i = 0; i< allKoans.size(); i++) {
            if (!tryOfferKoan(allKoans.get(i), i)) {
                return;
            }
        }

        consolePrinter.println();
        consolePrinter.println(MOUNTAINS_ARE_AGAIN_MERELY_MOUNTAINS);
        consolePrinter.println();
    }

    private boolean tryOfferKoan(Koan koan, int successfulCount) {
        for(final var test: koan.tests) {
            if (!tryOfferTest(test, successfulCount)) {
                return false;
            }
        }
        return true;
    }

    private boolean tryOfferTest(KoanTest test, int successfulCount) {
        // Execute silently the first time.
        // We do not want to display all the outputs of the successful koans to the student.
        p = Printer.SILENT;
        final var succeeded = offerTest(test, successfulCount);

        if (!succeeded) {
            // If failed, execute verbosely the second time, in order to give feedback to the student.
            p = consolePrinter;
            offerTest(test, successfulCount);
            return false;
        }

        return true;
    }

    private boolean offerTest(KoanTest test, int successfulCount) {
        final Koan koan = test.koan;        

        encourage(koan);

        final boolean success = executeCall(test);

        offerToMeditate(koan);
        showProgress(successfulCount);

        return success;
    }

    private boolean executeCall(final KoanTest test) {
        final boolean preparationSucceeded = test.prepare(p, locale);
        if (!preparationSucceeded) {
            return false;
        }

        introduceConsole(test);

        try {
            return test.execute(p, locale, p == Printer.SILENT).succeeded();
        }
        finally {
            concludeConsole(test.koan);
        }
    }

    private void introduceConsole(final KoanTest test) {
        final Fmt testedExpression = code(test.script[test.script.length - 1].formatSourceCode(locale));

        if (test.script.length > 1 || test.koan.showStdInInputs) {
            p.println(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN); // Seulement si code de prep ou stdin input
            if (test.koan.showStdInInputs) {
                p.println(normal(WHEN_ANSWERING, sequence(test.stdInInputs(locale), Style.Inherit)));
            }
            if (test.script.length > 1) {
                p.println(WHEN_EXECUTING);
                p.println();
                p.println(code(Expression.formatPreparationSourceCode(test.script, locale, "    ")));
                p.println(normal(AND_FINALLY_LOOKING_THE_RESULT_OF, testedExpression));
            } else {
                p.println(normal(WHEN_LOOKING_THE_RESULT_OF, testedExpression));
            }
        } else {
            p.println(normal(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN_LOOKING_AT, testedExpression));
        }

        p.println();
        if (test.koan.usesConsole) {
            p.println();
            p.println(CONSOLE);
            p.println("---------");
            p.println();
        }
    }

    private void concludeConsole(final Koan koan) {
        if (koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }
    }

    private void encourage(final Koan koan) {
        p.println();
        p.println(THINKING, koan.koanClass.get(locale).simpleClassName);
        p.println(red(HAS_DAMAGED_YOUR_KARMA, strongRed(koan.koanName)));
        p.println();
        p.println(THE_MASTER_SAYS);
        p.println(cyan(YOU_HAVE_NOT_REACHED_ENLIGHTMENT));
        p.println();
        p.println("---------");
        p.println();
        p.println(THE_ANSWERS_YOU_SEEK);
        p.println();
    }

    private void offerToMeditate(final Koan koan) {
        p.println();
        p.println(normal(
            PLEASE_MEDITATE_ON, 
            strong(koan.koanName),
            sameStyle(koan.koanClass.get(locale).simpleClassName)
        ));
        p.println();
    }

    private void showProgress(final int successfulCount) {
        if (successfulCount == 0) {
            // Let's not be discouraging...
            return;
        }

        p.println(green(
            YOUR_PROGRESS_THUS_FAR,
            green(repeat(".", successfulCount)),
            red("X"),
            cyan(repeat("_", allKoans.size() - successfulCount - 1)),
            normal(String.format("%s/%s", successfulCount, allKoans.size()))
        ));
        p.println();
    }

    private static String repeat(final String s, final int times) {
        return new String(new char[times]).replace("\0", s);
    }
}
