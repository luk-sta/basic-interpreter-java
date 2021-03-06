package hyperion.basic;

import hyperion.basic.command.Command;
import hyperion.basic.command.List;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class CommandExecutor {
    private final ProgramLinesHolder programLinesHolder;

    public CommandExecutor(ProgramLinesHolder programLinesHolder) {
        this.programLinesHolder = programLinesHolder;
    }

    public void exec(Command command) {
        if (command instanceof List) {
            List cmd = (List) command;
            programLinesHolder.listLines()
                    .filter(l -> l.getLineNumber() >= cmd.getFrom())
                    .filter(l -> l.getLineNumber() <= cmd.getTo())
                    .forEach(l -> System.out.println(l.getRawText()));
        }
    }
}
