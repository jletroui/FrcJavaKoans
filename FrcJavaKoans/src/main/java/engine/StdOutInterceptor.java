package engine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class StdOutInterceptor {
    private final PrintStream realOut = System.out;

    private final class OutputStreamMultiplexer extends OutputStream {
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

    public interface ReflectionRunnable {
        void run() throws IllegalAccessException, InvocationTargetException;
    }

    public String capture(ReflectionRunnable runnable) throws IllegalAccessException, InvocationTargetException {
        var bos = new ByteArrayOutputStream();
        var multiplexer = new OutputStreamMultiplexer(bos, realOut);
        var printStream = new PrintStream(multiplexer, true);

        System.setOut(printStream);
        try {
            runnable.run();
        }
        finally {
            System.setOut(realOut);
        }
        printStream.flush();
        return bos.toString();
    }
}
