package hyperion.basic.command;

import hyperion.basic.error.SyntaxError;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class List implements Command {
    public static final int MIN_LINE_NUMBER = 0;
    public static final int MAX_LINE_NUMBER = 63999;
    private final int from;
    private final int to;

    public List(String params) throws SyntaxError {
        String[] split = params.split("-", 2);
        if (split.length < 2) {
            from = MIN_LINE_NUMBER;
            to = MAX_LINE_NUMBER;
        } else {
            from = (split[0].isEmpty()) ? MIN_LINE_NUMBER : Integer.parseInt(split[0]);
            to = (split[1].isEmpty()) ? MAX_LINE_NUMBER : Integer.parseInt(split[1]);
            if (from < MIN_LINE_NUMBER || from > MAX_LINE_NUMBER || to < MIN_LINE_NUMBER || to > MAX_LINE_NUMBER) {
                throw new SyntaxError();
            }
        }
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
