package hyperion.basic;

import hyperion.basic.command.Command;

import java.util.List;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public final class Line {
    private int lineNumber;
    private List<Command> commands;
    private String rawText;

    public Line(int lineNumber, List<Command> commands, String rawText) {
        this.lineNumber = lineNumber;
        this.commands = commands;
        this.rawText = rawText;
    }
}
