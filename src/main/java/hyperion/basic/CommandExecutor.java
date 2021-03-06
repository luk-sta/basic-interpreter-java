package hyperion.basic;

import hyperion.basic.command.Command;
import hyperion.basic.command.List;
import hyperion.basic.command.New;
import hyperion.basic.command.Run;

import java.util.Iterator;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class CommandExecutor {
    private final ProgramLinesHolder programLinesHolder;
    private final VariablesField variablesField;
    private Iterator<Integer> lineNumbersIterator;

    public CommandExecutor(ProgramLinesHolder programLinesHolder, VariablesField variablesField) {
        this.programLinesHolder = programLinesHolder;
        this.variablesField = variablesField;
    }

    public boolean exec(Command command) {
        if (command instanceof List) {
            List cmd = (List) command;
            programLinesHolder.init();
            programLinesHolder.lineNumberStream(0)
                    .filter(cmd.getInsideInterval())
                    .map(programLinesHolder::get)
                    .forEach(l -> System.out.println(l.getRawText()));
            return true;
        }
        if (command instanceof Run) {
            programLinesHolder.init();
            variablesField.clear();
            lineNumbersIterator = programLinesHolder.lineNumberIterator(0);
            while (lineNumbersIterator.hasNext()) {
                Integer next = lineNumbersIterator.next();
                Line line = programLinesHolder.get(next);
                for (Command lineCommand : line.getCommands()) {
                    boolean cont = exec(lineCommand);
                    if (!cont) {
                        return false;
                    }
                }
            }
            return false;
        }
        if (command instanceof New) {
            variablesField.clear();
            programLinesHolder.clear();
            return false;
        }
        // TODO
        return true;
    }
}
