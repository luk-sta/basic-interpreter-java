package hyperion.basic.variable;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class IntVar extends Variable<Integer> {
    public IntVar(String name, Integer value) {
        super(name, "%", value);
    }
}
