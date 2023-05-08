import java.util.List;

import engine.KoanExecutor;
import koans.OnSimpleCommands;

public class App {
    public static void main(String[] args) throws Exception {
        var executor = new KoanExecutor(OnSimpleCommands.class, List.of("askANameInConsole"));
        executor.execute();
    }
}
