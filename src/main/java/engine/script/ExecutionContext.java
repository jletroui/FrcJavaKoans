package engine.script;

import java.util.HashMap;
import java.util.Map;

import engine.Locale;
import engine.Localizable;

public final class ExecutionContext {
    public final Class<?> koanClass;
    public final Locale locale;
    private final Map<String, Object> variables = new HashMap<>();

    public ExecutionContext(final Localizable<Type> declaredKoanClass, final Locale locale) {
        this.koanClass = declaredKoanClass.get(locale).unsafeResolve();
        this.locale = locale;
    }

    public Object getVariableValue(final String name) {
        return variables.get(name);
    }

    public void setVariableValue(final String name, final Object value) {
        variables.put(name, value);
    }
}
