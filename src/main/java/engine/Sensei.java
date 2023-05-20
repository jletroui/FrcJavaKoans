package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import engine.Koan.KoanMethodCall;

import static engine.ContentFormatting.print;

public class Sensei {
    @SafeVarargs
    public static void offerKoans(List<Koan>... koanLists) {
        var failingKoan = Arrays.stream(koanLists)
                .flatMap((kl) -> kl.stream())
                .filter((kl) -> !Sensei.offer(kl))
                .findFirst()
                .orElse(null);

        if (failingKoan == null) {
            print(false, "");
            print(false, "Mountains are again merely mountains .");
            print(false, "");
        }
    }

    private static boolean offer(Koan koan) {
        // Execute silently the first time
        var succeeded = offer(true, koan);

        if (!succeeded) {
            // If failed, execute verbosely the second time
            return offer(false, koan);
        }

        return true;
    }

    private static boolean offer(boolean silent, Koan koan) {
        print(silent, "");
        print(silent, "Thinking %s ...", koan.koanClass.getSimpleName());
        print(silent, "%s.%s has damaged your karma.", koan.koanClass.getSimpleName(), koan.methodName);
        print(silent, "");
        print(silent, "The master says:");
        print(silent, "  You have not yet reached enlightment ...");

        if (!koan.usesConsole) {
            print(silent, "");
            print(silent, "---------");
            print(silent, "");
        }
        
        var success = false;
        try {
            success = executeCalls(silent, koan);
        } catch (IllegalAccessException iae) {
            print(silent, "The method %s() appears to not be public. Koan methods must be public.", koan.methodName);
        } catch (IllegalArgumentException iae) {
            print(silent, "The method %s() appears to not accept TODO.", koan.methodName);
        } catch (InvocationTargetException ite) {
            print(silent, "The method %s() appears to produce an error: %s.", koan.methodName,
                    ite.getCause().getMessage());
        } catch (NoSuchMethodException mnfe) {
            if (koan.methodParamTypes.length == 0) {
                print(
                    silent,
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName()
                );
            } else if (koan.methodParamTypes.length == 1) {
                print(
                    silent,
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java with a '%s' parameter but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName(),
                    koan.methodParamTypes[0].getSimpleName()
                );
            } else {
                print(
                    silent,
                    "Expected to find a method called '%s' in src/main/java/koans/%s.java with parameters of type %s but did not find it.",
                    koan.methodName,
                    koan.koanClass.getSimpleName(),
                    String.join(", ", Arrays.stream(koan.methodParamTypes).map(type -> "'" + type.getSimpleName() + "'").toArray(String[]::new))
                );
            }
        }

        print(silent, "");
        print(
            silent,
            "Please meditate on %s in src/main/java/koans/%s.java", 
            koan.methodName,
            koan.koanClass.getSimpleName()
        );
        print(silent, "");

        return success;
    }

    private static boolean executeCalls(boolean silent, Koan koan) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        var method = koan.koanClass.getMethod(koan.methodName, koan.methodParamTypes);

        for(var call: koan.calls) {
            var success = executeCall(silent, method, koan, call);
            if (!success) {
                return false;
            }
        }

        return true;
    }

    
    private static boolean executeCall(boolean silent, Method method, Koan koan, KoanMethodCall call) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (koan.usesConsole) {
            print(silent, "");
            print(silent, "Console:");
            print(silent, "---------");
            print(silent, "");
        }

        var interceptionResult = StdStreamsInterceptor.capture(silent, () -> method.invoke(null, call.params), call.stdInInputs);

        var result = new KoanResult(
            koan,
            interceptionResult.stdOutLines,
            interceptionResult.stdInLines,
            interceptionResult.returnValue,
            call.params);

        if (koan.usesConsole) {
            print(silent, "");
            print(silent, "---------");
            print(silent, "");
        }

        return executeAssertions(silent, result, call.assertions);
    }

    private static boolean executeAssertions(boolean silent, KoanResult result, Assertion[] assertions) {
        for (Assertion as : assertions) {
            if (!as.validate(silent, result)) {
                return false;
            }
        }
        return true;
    }
}
