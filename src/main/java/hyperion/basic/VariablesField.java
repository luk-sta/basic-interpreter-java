package hyperion.basic;

import hyperion.basic.variable.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class VariablesField {
    private final Map<String, Variable<?>> nameToVariableMap = new HashMap<>();

    public void put(Variable<?> var) {
        nameToVariableMap.put(var.getName(), var);
    }

    public Variable<?> get(String name) {
        return nameToVariableMap.get(name);
    }

    public void clear() {
        nameToVariableMap.clear();
    }
}
