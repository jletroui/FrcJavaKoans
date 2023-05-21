package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import engine.Koan.KoanMethodCall;

public class Sensei {
    private final Printer consolePrinter = new ConsolePrinter();
    private Printer p = Printer.SILENT;

    @SafeVarargs
    public final void offerKoans(List<Koan>... koanLists) {
        var failingKoan = Arrays.stream(koanLists)
                .flatMap((kl) -> kl.stream())
                .filter((kl) -> !tryOffer(kl))
                .findFirst()
                .orElse(null);

        if (failingKoan == null) {
            consolePrinter.println();
            consolePrinter.println("Mountains are again merely mountains .");
            consolePrinter.println();
        }
    }

    private boolean tryOffer(Koan koan) {
        // Execute silently the first time
        p = Printer.SILENT;
        var succeeded = offer(koan);

        if (!succeeded) {
            // If failed, execute verbosely the second time
            p = consolePrinter;
            return offer(koan);
        }

        return true;
    }

    private boolean offer(Koan koan) {
        p.println();
        p.println("Thinking %s ...", koan.koanClass.getSimpleName());
        p.println("%s.%s has damaged your karma.", koan.koanClass.getSimpleName(), koan.methodName);
        p.println();
        p.println("The master says:");
        p.println("  You have not yet reached enlightment ...");

        if (!koan.usesConsole) {
            p.println();
            p.println("---------");
            p.println();
        }
        
        var success = false;
        try {
            success = executeCalls(koan);
        } catch (IllegalAccessException iae) {
            p.println("The method %s() appears to not be public. Koan methods must be public.", koan.methodName);
        } catch (IllegalArgumentException iae) {
            p.println("The method %s() appears to not accept TODO.", koan.methodName);
        } catch (InvocationTargetException ite) {
            p.println("The method %s() appears to produce an error: %s.", koan.methodName,
                    ite.getCause().getMessage());
        } catch (NoSuchMethodException mnfe) {
            if (koan.methodParamTypes.length == 0) {
                p.println(
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName()
                );
            } else if (koan.methodParamTypes.length == 1) {
                p.println(
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java with a '%s' parameter but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName(),
                    koan.methodParamTypes[0].getSimpleName()
                );
            } else {
                p.println(
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java with parameters of type %s but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName(),
                    String.join(", ", Arrays.stream(koan.methodParamTypes).map(type -> "'" + type.getSimpleName() + "'").toArray(String[]::new))
                );
            }
        }

        p.println();
        p.println(
            "Please meditate on %s in src/main/java/koans/%s.java", 
            koan.methodName,
            koan.koanClass.getSimpleName()
        );
        p.println("");

        return success;
    }

    private boolean executeCalls(Koan koan) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        var method = koan.koanClass.getMethod(koan.methodName, koan.methodParamTypes);

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
            p.println("Console:");
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
            if (!as.validate(p, result)) {
                return false;
            }
        }
        return true;
    }
}
