package engine;

/**
 * A functional interface offering the possibility to format a piece of feedback to the user given the koan's result. 
 */
public interface FormatParam {
    String format(KoanResult res);
}
