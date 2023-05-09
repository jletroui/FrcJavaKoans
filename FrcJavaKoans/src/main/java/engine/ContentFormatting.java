package engine;

public class ContentFormatting {
    public static void print(boolean silentMode, String template, Object... params) {
        if (!silentMode) {
            System.out.println(String.format(template, params));
        }
    }    
}
