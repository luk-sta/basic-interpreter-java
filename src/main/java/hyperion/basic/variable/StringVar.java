package hyperion.basic.variable;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class StringVar extends Variable<String> {
    public static final String NAME_SUFFIX = "$";

    public StringVar(String name, String value) {
        super(name, NAME_SUFFIX, value);
    }
}
