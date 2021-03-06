package hyperion.basic.command;

import hyperion.basic.error.SyntaxError;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class Goto implements Command {
    private final int lineNum;

    public Goto(String params) throws SyntaxError {
        try {
            lineNum = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new SyntaxError();
        }
    }

    public int getLineNum() {
        return lineNum;
    }
}
