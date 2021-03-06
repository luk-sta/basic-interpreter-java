package hyperion.basic.variable;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public abstract class Variable<T> {
    private final String name;
    private T value;

    protected Variable(String name, String nameSuffix, T value) {
        this.name = name.substring(0, 2) + nameSuffix;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }
}
