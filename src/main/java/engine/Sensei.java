package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import engine.Koan.KoanMethodCall;
import static engine.Texts.*;

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
        this.consolePrinter = new ConsolePrinter(locale);
        this.allKoans = koanSeries
        .stream()
        .flatMap((kl) -> kl.stream())
        .toList();
    }

    public void offerKoans() {
        for(int i = 0; i< allKoans.size(); i++) {
            if (!tryOffer(allKoans.get(i), i)) {
                return;
            }
        }

        consolePrinter.println();
        consolePrinter.println(MOUNTAINS_ARE_AGAIN_MERELY_MOUNTAINS);
        consolePrinter.println();
    }

    private boolean tryOffer(Koan koan, int successfulCount) {
        // Execute silently the first time.
        // We do not want to display all the outputs of the successful koans to the student.
        p = Printer.SILENT;
        var succeeded = offer(koan, successfulCount);

        if (!succeeded) {
            // If failed, execute verbosely the second time, in order to give feedback to the student.
            p = consolePrinter;
            return offer(koan, successfulCount);
        }

        return true;
    }

    private boolean offer(Koan koan, int successfulCount) {
        observe(koan);
        encourage();

        if (!koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }
        
        var success = false;
        try {
            success = executeCalls(koan);
        } catch (IllegalAccessException iae) {
            p.println(Color.red(EXPECTED_METHOD_TO_BE_PUBLIC), koan.methodName);
        } catch (IllegalArgumentException iae) {
            // Would be a bug in the Koan instances, since we are ensuring for the method with the right parameters.
            p.println(Color.red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR), koan.methodName);
        } catch (InvocationTargetException ite) {
            p.println(Color.red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR), koan.methodName,
                    ite.getCause().getMessage());
        } catch (NoSuchMethodException mnfe) {
            if (koan.methodParamTypes.length == 0) {
                p.println(
                    Color.red(EXPECTED_TO_FIND_MEHOD_NO_PARAMS),
                    koan.methodName,
                    koan.className(locale)
                );
            } else if (koan.methodParamTypes.length == 1) {
                p.println(
                    Color.red(EXPECTED_TO_FIND_MEHOD_ONE_PARAM),
                    koan.methodName,
                    koan.className(locale),
                    koan.methodParamTypes[0].getSimpleName()
                );
            } else {
                p.println(
                    Color.red(EXPECTED_TO_FIND_MEHOD_MANY_PARAMS),
                    koan.methodName,
                    koan.className(locale),
                    String.join(", ", Arrays.stream(koan.methodParamTypes).map(type -> "'" + type.getSimpleName() + "'").toArray(String[]::new))
                );
            }
        }

        p.println();
        p.println(
            PLEASE_MEDITATE_ON, 
            koan.methodName,
            koan.className(locale)
        );
        p.println("");

        showProgress(successfulCount);

        return success;
    }


    private boolean executeCalls(Koan koan) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        var method = koan.method(locale);

        for(var call: koan.calls) {
            var success = executeCall(method, koan, call);
            if (!success) {
                return false;
            }
        }

        return true;
    }

    private boolean executeCall(Method method, Koan koan, KoanMethodCall call) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (koan.usesConsole) {
            p.println();
            p.println(CONSOLE);
            p.println("---------");
            p.println();
        }

        var interceptionResult = StdStreamsInterceptor.capture(p == Printer.SILENT, () -> method.invoke(null, call.params), call.stdInInputs);

        var result = new KoanResult(
            koan,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue,
            call.params);

        if (koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }

        return executeAssertions(result, call.assertions);
    }

    private boolean executeAssertions(KoanResult result, Assertion[] assertions) {
        for (Assertion as : assertions) {
            if (!as.validate(locale, p, result)) {
                return false;
            }
        }
        return true;
    }

    private void encourage() {
        p.println();
        p.println(THE_MASTER_SAYS);
        p.println(Color.cyan(YOU_HAVE_NOT_REACHED_ENLIGHTMENT));
        p.println();
        p.println(THE_ANSWERS_YOU_SEEK);
    }

    private void observe(Koan koan) {
        p.println();
        p.println(THINKING, koan.className(locale));
        p.println(Color.red(HAS_DAMAGED_YOUR_KARMA), koan.className(locale), koan.methodName);
    }

    private void showProgress(int successfulCount) {
        if (successfulCount == 0) {
            // Let's not be discouraging...
            return;
        }

        var bar = String.format(
            "%s%s%s",
            Color.green(repeat(".", successfulCount)),
            Color.red("X"),
            Color.cyan(repeat("_", allKoans.size() - successfulCount - 1)));
        p.println(YOUR_PROGRESS_THUS_FAR, bar, successfulCount, allKoans.size());
        p.println();
    }

    private static String repeat(String s, int times) {
        return new String(new char[times]).replace("\0", s);
    }
}
