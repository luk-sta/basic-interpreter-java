package hyperion.basic;

import hyperion.basic.command.Command;
import hyperion.basic.error.GeneralException;
import hyperion.basic.error.SyntaxError;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class BasicInterpreter {
    private static final Pattern BEGIN_NUMBER = Pattern.compile("^[1-9][0-9]");
    private static final char COMMAND_SEPARATOR = ':';

    public static void main(String[] args) {
        Console console = System.console();
        System.out.println("READY.");
        ProgramLinesHolder programLinesHolder = new ProgramLinesHolder();
        VariablesField variablesField = new VariablesField();
        CommandExecutor commandExecutor = new CommandExecutor(programLinesHolder, variablesField);

        String line;
        while ((line = console.readLine()) != null) {
            line = line.toUpperCase();
            try {
                Matcher matcher = BEGIN_NUMBER.matcher(line);
                if (matcher.find()) {
                    String lineNumberString = matcher.group();
                    int lineNumber = Integer.parseInt(lineNumberString);
                    String commandsString = line.substring(lineNumberString.length());
                    try {
                        List<Command> commands = createCommands(commandsString);
                        programLinesHolder.add(lineNumber, commands, line);
                    } catch (SyntaxError e) {
                        throw new SyntaxError(lineNumberString);
                    }
                } else {
                    List<Command> commands = createCommands(line);
                    commands.forEach(commandExecutor::exec);
                }
            } catch (GeneralException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("READY.");
        }
    }

    private static List<Command> createCommands(String commandsString) throws GeneralException {
        List<String> commandStrings = splitCommandsString(commandsString);
        List<Command> commands = new ArrayList<>(commandStrings.size());
        for (String commandString : commandStrings) {
            Command command = CommandFactory.create(commandString);
            commands.add(command);
        }
        return commands;
    }

    private static List<String> splitCommandsString(String commandsString) {
        List<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean insideQuoted = false;
        for (char c : commandsString.toCharArray()) {
            switch (c) {
                case '"':
                    insideQuoted = !insideQuoted;
                    sb.append(c);
                    break;
                case COMMAND_SEPARATOR:
                    if (insideQuoted) {
                        sb.append(c);
                    } else {
                        if (sb.length() > 0) {
                            result.add(sb.toString());
                            sb = new StringBuilder();
                        }
                    }
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }
}
