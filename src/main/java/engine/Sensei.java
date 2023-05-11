package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

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
            print(false, "Mountains are again merely mountains .");
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
        print(silent, "Console:");
        print(silent, "---------");
        print(silent, "");
        
        var success = false;
        try {
            var method = koan.koanClass.getMethod(koan.methodName);

            var interceptionResult = StdStreamsInterceptor.capture(silent, () -> method.invoke(null), koan.stdInInputs);

            var result = new KoanResult(
                interceptionResult.stdOutLines,
                interceptionResult.stdInLines,
                interceptionResult.returnValue,
                new String[0]);

            print(silent, "");
            print(silent, "---------");
            print(silent, "");
            print(silent, "The master says:");
            print(silent, "  You have not yet reached enlightment ...");
            print(silent, "");

            success = executeAssertions(silent, result, koan.assertions);
        } catch (IllegalAccessException iae) {
            print(silent, "The method %s() appears to not be public. Koan methods must be public.", koan.methodName);
        } catch (IllegalArgumentException iae) {
            print(silent, "The method %s() appears to not accept TODO.", koan.methodName);
        } catch (InvocationTargetException ite) {
            print(silent, "The method %s() appears to produce an error: %s.", koan.methodName,
                    ite.getCause().getMessage());
        } catch (NoSuchMethodException mnfe) {
            print(silent, "The method %s() seems to have disappeared.", koan.methodName);
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

    private static boolean executeAssertions(boolean silent, KoanResult result, Assertion[] assertions) {
        for (Assertion as : assertions) {
            if (!as.validate(silent, result)) {
                return false;
            }
        }
        return true;
    }
}
