package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static engine.Texts.*;

/**
 * The main engine class, executing the series of koans.
 */
public class Sensei {
    private static final long TIMEOUT_INFINITE_LOOPS_MS = 2000;
    private final Locale locale;
    private final Printer consolePrinter;
    private Printer p = Printer.SILENT;
    private List<Koan> allKoans;

    public Sensei(Locale locale, List<List<Koan>> koanSeries) {
        this.locale = locale;
        this.consolePrinter = new ConsolePrinter(locale);
        this.allKoans = koanSeries
            .stream()
            .flatMap(kl -> kl.stream())
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
        for(var test: koan.tests) {
            var success = tryOffer(test, successfulCount);
            if (!success) {
                return false;
            }
        }
        return true;
    }

    private boolean tryOffer(KoanTest test, int successfulCount) {
        // Execute silently the first time.
        // We do not want to display all the outputs of the successful koans to the student.
        p = Printer.SILENT;
        var succeeded = offer(test, successfulCount);

        if (!succeeded) {
            // If failed, execute verbosely the second time, in order to give feedback to the student.
            p = consolePrinter;
            offer(test, successfulCount);
            return false;
        }

        return true;
    }

    private boolean offer(KoanTest test, int successfulCount) {
        var koan = test.koan;
        observe(koan);
        encourage();
        
        AtomicBoolean success = new AtomicBoolean(false);

        var thread = new Thread(() -> {
            try {
                success.set(executeCall(test));
            } catch (IllegalArgumentException iae) {
                // Special case: since the executeCall() method did not complete, the console conclusion was not displayed.
                concludeConsole(koan);
                // Would be a bug in the Koan instances, since we are ensuring for the method with the right parameters.
                p.println(Color.red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR), koan.methodName, iae.getMessage());
            } catch (InvocationTargetException ite) {
                // Special case: since the executeCall() method did not complete, the console conclusion was not displayed.
                concludeConsole(koan);
                if (ite.getTargetException() instanceof StackOverflowError) {
                    p.println(Color.red(THE_METHOD_SEEMS_TO_RECURSE_INFINITELY), koan.methodName);
                } else {
                    p.println(Color.red(THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR), koan.methodName, ite.getCause().getMessage());
                }
            } catch (KoanBugException kbe) {
                // Special case: since the executeCall() method did not complete, the console conclusion was not displayed.
                concludeConsole(koan);
                p.println(Color.red(BUG_FOUND), kbe.getMessage());
            }
        });

        thread.setDaemon(true);
        thread.start();
        try {
            // thread.join(TIMEOUT_INFINITE_LOOPS_MS);
            thread.join();
        }
        catch(InterruptedException ie) {
            throw new IllegalStateException("Something very weird happened. We should not have been interrupted.");
        }

        if (thread.isAlive()) {
            StdStreamsInterceptor.reset();
            concludeConsole(koan);
            p.println(Color.red(THE_METHOD_SEEMS_TO_NOT_FINISH), koan.methodName);
        }

        offerToMeditate(koan);
        showProgress(successfulCount);

        return success.get();
    }

    private boolean executeCall(KoanTest test) throws InvocationTargetException {

        test.setupRandomForKoan();
        if (!test.koan.executeBeforeAssertions(p, locale)) {
            return false;
        }

        final var targetMethod = test.resolveTargetMethod(locale);
        introduceConsole(targetMethod);

        for(var prepCall: targetMethod.koanTest.objectPrepCalls) {
            prepCall.invoke(targetMethod.object, locale);
        }

        final var interceptionResult = StdStreamsInterceptor.capture(
            p == Printer.SILENT,
            targetMethod::invoke,
            test.stdInInputs(locale)
        );

        final var result = new KoanResult(
            locale,
            targetMethod,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue
        );

        concludeConsole(test.koan);

        return result.executeAssertions(p);
    }

    private void introduceConsole(KoanTargetMethod targetMethod) {
        if (targetMethod.koanTest.koan.usesConsole) {
            if (targetMethod.koanTest.koan.showStdInInputs || targetMethod.koanTest.objectPrepCalls.length > 0) {
                p.println();
                p.println(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN);
                if (targetMethod.koanTest.koan.showStdInInputs) {
                    p.println(WHEN_ANSWERING, Helpers.formatSequence(locale, targetMethod.koanTest.stdInInputs(locale)));
                }
                for(var prepCall: targetMethod.koanTest.objectPrepCalls) {
                    p.println(WHEN_CALLING, prepCall);
                }
                p.println();
            }

            p.println();
            p.println(CONSOLE, targetMethod);
            p.println("---------");
            p.println();
        } else if (targetMethod.koanTest.objectPrepCalls.length > 0) {
            p.println();
            p.println(THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN);
            for(var prepCall: targetMethod.koanTest.objectPrepCalls) {
                p.println(WHEN_CALLING, prepCall);
            }
            p.println();             
        }
    }

    private void concludeConsole(Koan koan) {
        if (koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }
    }

    private void encourage() {
        p.println();
        p.println(THE_MASTER_SAYS);
        p.println(Color.cyan(YOU_HAVE_NOT_REACHED_ENLIGHTMENT));
        p.println();
        p.println("---------");
        p.println();
        p.println(THE_ANSWERS_YOU_SEEK);
        p.println();
    }

    private void observe(Koan koan) {
        p.println();
        p.println(THINKING, koan.koanClass.get(locale).simpleClassName);
        p.println(Color.red(HAS_DAMAGED_YOUR_KARMA), koan.methodName);
    }

    private void offerToMeditate(Koan koan) {
        p.println();
        p.println(
            PLEASE_MEDITATE_ON, 
            koan.methodName,
            koan.koanClass.get(locale).className
        );
        p.println();
    }

    private void showProgress(int successfulCount) {
        if (successfulCount == 0) {
            // Let's not be discouraging...
            return;
        }

        final var bar = String.format(
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
