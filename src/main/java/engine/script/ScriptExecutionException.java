package engine.script;

public final class ScriptExecutionException extends RuntimeException {
    public final String methodName;
    public ScriptExecutionException(final Throwable cause, final String methodName) {
        super(cause);
        this.methodName = methodName;
    }
}
