package engine;

public class Type {
    private Class<?> clasz = null;
    private String className;
    private String simpleClassName;

    public Type(String className) {
        this.className = className;
        var lastDotIndex = className.lastIndexOf(".");
        this.simpleClassName = className.substring(lastDotIndex + 1);
    }

    public Type(Class<?> clasz) {
        this.clasz = clasz;
        this.className = clasz.getName();
        this.simpleClassName = clasz.getSimpleName();
    }

    public Class<?> resolve() throws ClassNotFoundException {
        if (clasz == null) {
            clasz = Class.forName(className);
        }
        return clasz;
    }

    public String simpleName() {
        return simpleClassName;
    }

    @Override
    public String toString() {
        return className;
    }
}
