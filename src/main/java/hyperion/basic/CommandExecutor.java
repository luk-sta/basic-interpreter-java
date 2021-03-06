package hyperion.basic;

import hyperion.basic.command.Command;
import hyperion.basic.command.List;
import hyperion.basic.command.Run;

import java.util.stream.Collectors;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class CommandExecutor {
    private final ProgramLinesHolder programLinesHolder;
    private final VariablesField variablesField;

    public CommandExecutor(ProgramLinesHolder programLinesHolder, VariablesField variablesField) {
        this.programLinesHolder = programLinesHolder;
        this.variablesField = variablesField;
    }

    public void exec(Command command) {
        if (command instanceof List) {
            List cmd = (List) command;
            programLinesHolder.listLines()
                    .filter(l -> cmd.getInsideInterval().test(l.getLineNumber()))
                    .forEach(l -> System.out.println(l.getRawText()));
            return;
        }
        if (command instanceof Run) {
            variablesField.clear();
            for (Line line : programLinesHolder.listLines().collect(Collectors.toList())) {
                for (Command lineCommand : line.getCommands()) {
                    exec(lineCommand);
                }
            }
            return;
        }
    }
}
