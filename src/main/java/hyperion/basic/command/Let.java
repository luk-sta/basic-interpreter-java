package hyperion.basic.command;

import hyperion.basic.error.SyntaxError;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class Let implements Command {
    private static final String ASSIGNMENT_CHAR = "=";
    private final String variableName;
    private final String valueExpression;

    public Let(String params) throws SyntaxError {
        String[] split = params.split(ASSIGNMENT_CHAR, 2);
        if (split.length != 2) {
            throw new SyntaxError();
        }
        variableName = split[0].trim();
        valueExpression = split[1].trim();
    }
}
