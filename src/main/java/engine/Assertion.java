package engine;

public interface Assertion {
    boolean validate(boolean silentMode, KoanResult result);
}
