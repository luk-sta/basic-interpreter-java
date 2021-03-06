package hyperion.basic;

import hyperion.basic.command.Command;
import hyperion.basic.command.CommandEnum;
import hyperion.basic.command.Let;
import hyperion.basic.command.Rem;
import hyperion.basic.error.GeneralException;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class CommandFactory {
    public static Command create(String commandString) throws GeneralException {
        commandString = commandString.trim();
        
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandString.startsWith(commandEnum.name())) {
                String params = commandString.substring(commandEnum.name().length());
                return commandEnum.create(params);
            }
        }
        if (commandString.isEmpty()) {
            return new Rem();
        }
        return new Let(commandString);
    }
}
