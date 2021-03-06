package hyperion.basic.error;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class SyntaxError extends GeneralException {
    public SyntaxError() {
        super("?SYNTAX ERROR");
    }

    public SyntaxError(String lineNumberString) {
        super("?SYNTAX ERROR IN " + lineNumberString);
    }
}
