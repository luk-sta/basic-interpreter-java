package hyperion.basic.command;

import hyperion.basic.error.SyntaxError;

import java.util.function.Predicate;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class List implements Command {
    private final Predicate<Integer> insideInterval;

    public List(String params) throws SyntaxError {
        String[] split = params.split("-", 2);
        String s0 = split[0].trim();
        try {
            if (split.length < 2) {
                if (s0.isEmpty()) {
                    insideInterval = l -> true;
                } else {
                    int lineNum = Integer.parseInt(s0);
                    insideInterval = l -> l == lineNum;
                }
            } else {
                Predicate<Integer> p1 = (s0.isEmpty()) ? l -> true : l -> l >= Integer.parseInt(s0);
                String s1 = split[1].trim();
                Predicate<Integer> p2 = (s1.isEmpty()) ? l -> true : l -> l <= Integer.parseInt(s1);
                insideInterval = p1.and(p2);
            }
        } catch (NumberFormatException e) {
            throw new SyntaxError();
        }
    }

    public Predicate<Integer> getInsideInterval() {
        return insideInterval;
    }
}
