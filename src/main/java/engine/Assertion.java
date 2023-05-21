package engine;

public interface Assertion {
    boolean validate(Printer p, KoanResult result);
}
