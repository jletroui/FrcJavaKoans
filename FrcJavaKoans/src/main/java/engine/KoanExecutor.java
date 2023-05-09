package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static engine.ContentFormatting.print;

public class KoanExecutor {
    @SafeVarargs
    public static void executeKoanLists(List<Koan>... koanLists) {
        Arrays.stream(koanLists)
            .flatMap((kl) -> kl.stream())
            .filter((kl) -> !KoanExecutor.execute(kl))
            .findFirst()
            .orElse(null);
    }

    private static boolean execute(Koan koan) {
        // Execute silently the first time
        var succeeded = execute(true, koan);

        if (!succeeded) {
            // If failed, execute verbosely the second time
            return execute(false, koan);
        }

        return true;
    }

    private static boolean execute(boolean silent, Koan koan) {
        try {
            print(silent, "Looking for wisdom in the %s koan of the %s class in the src/main/java/koans folder", koan.methodName, koan.koanClass.getSimpleName());
            print(silent, "");
            print(silent, "---------");
            print(silent, "");
            var method = koan.koanClass.getMethod(koan.methodName);
            try {
                var interceptionResult = StdOutInterceptor.capture(silent, () -> method.invoke(null));

                var result = new KoanResult(interceptionResult.stdOutLines, new String[0], interceptionResult.returnValue, new String[0]);

                print(silent, "");
                print(silent, "---------");
                print(silent, "");

                return executeAssertions(silent, result, koan.assertions);
            }
            catch(IllegalAccessException iae) {
                print(silent, "Koan lesson: the method %s() appears to not be public. Koan methods must be public.", koan.methodName);
                return false;
            }
            catch(IllegalArgumentException iae) {
                print(silent, "Koan lesson: the method %s() appears to not accept TODO.", koan.methodName);
                return false;
            }
            catch(InvocationTargetException ite) {
                print(silent, "Koan lesson: the method %s() appears to produce an error: %s.", koan.methodName, ite.getCause().getMessage());
                return false;
            }
        }
        catch(NoSuchMethodException mnfe) {
            throw new RuntimeException("Oops, something bad happen", mnfe);
        }
    }

    private static boolean executeAssertions(boolean silent, KoanResult result, Assertion[] assertions) {
        for(Assertion as: assertions) {
            if (!as.validate(silent, result)) {
                return false;
            }
        }
        return true;
    }
}
