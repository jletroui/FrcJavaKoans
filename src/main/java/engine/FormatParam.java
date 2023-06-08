package engine;

/**
 * A functional interface offering the possibility to format a piece of feedback to the user given the koan's result. 
 */
public interface FormatParam {
    String format(KoanResult res);

    /**
     * Creates a FormatParam taking the stdIn input for the given index, convert it to an int, and increment it by the given increment.
     * 
     * Ex: if input 0 contains "3", then paramPlus(0, 10) will return "13".
     */
    static FormatParam addToStdInInput(int inputIndex, int increment) { 
        return res -> 
            res.inputLine(inputIndex)
                .map(line -> {
                    try {
                        int value = Integer.parseInt(line);
                        return  String.valueOf(value + increment);
                    } catch(NumberFormatException nfe) {
                        // Ignore
                    }
                    return "";
                })
                .orElse("");
    }

    static FormatParam stdInInput(int inputIndex) {
        return res -> res.inputLine(inputIndex).orElse("");
    }
}
