package engine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Intercepts the StdOut output of koans so we can assert what the koan method does output to the console.
 * Also, allows to silent the output, so that successful koans are not polluting the console, and only the
 * first failing koan output is displayed.
 */
public class StdOutInterceptor {
    private static final PrintStream realOut = System.out;

    public static class InterceptionResult {
        public final String[] stdOutLines;
        public final Object returnValue;

        public InterceptionResult(String[] stdOutLines, Object returnValue) {
            this.stdOutLines = stdOutLines;
            this.returnValue = returnValue;
        }
    }

    public static interface ReflectionRunnable {
        Object run() throws IllegalAccessException, InvocationTargetException;
    }

    private static class OutputStreamMultiplexer extends OutputStream {
        private final OutputStream stream1;
        private final OutputStream stream2;
        
        public OutputStreamMultiplexer(OutputStream stream1, OutputStream stream2) {
            this.stream1 = stream1;
            this.stream2 = stream2;
        }

        @Override
        public void write(int b) throws IOException {
            stream1.write(b);
            stream2.write(b);
        }
    }

    public static InterceptionResult capture(boolean silent, ReflectionRunnable executeFunc) throws IllegalAccessException, InvocationTargetException {
        var bos = new ByteArrayOutputStream();
        var outputStream = silent? bos : new OutputStreamMultiplexer(bos, realOut);
        var printStream = new PrintStream(outputStream, true);

        System.setOut(printStream);
        Object returnValue;
        try {
            returnValue = executeFunc.run();
        }
        finally {
            System.setOut(realOut);
        }
        printStream.flush();

        return new InterceptionResult(bos.toString().split(System.lineSeparator()), returnValue);
    }
}
