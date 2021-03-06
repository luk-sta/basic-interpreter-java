package hyperion.basic;

import java.io.Console;
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
        System.out.print("READY.");
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandsHolder commandsHolder = new CommandsHolder();

        String line;
        while ((line = console.readLine()) != null) {
            Matcher matcher = BEGIN_NUMBER.matcher(line);
            if (matcher.find()) {
                String lineNumberString = matcher.group();
                int lineNumber = Integer.parseInt(lineNumberString);
                String commandString = line.substring(lineNumberString.length());
                List<String> commands = splitCommandString(commandString);
                commandsHolder.add(lineNumber, commands);
            } else {
                List<String> commands = splitCommandString(line);
                commands.forEach(commandExecutor::exec);
            }
            System.out.print("READY.");
        }
    }

    private static List<String> splitCommandString(String commandString) {
        List<String> result = new LinkedList<>();
        String uCommandString = commandString.toUpperCase();
        StringBuilder sb = new StringBuilder();
        boolean insideQuoted = false;
        for (char c : uCommandString.toCharArray()) {
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
