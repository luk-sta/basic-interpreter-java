package hyperion.basic.command;

import hyperion.basic.error.GeneralException;

import java.lang.reflect.InvocationTargetException;

public enum CommandEnum {
    REM(Rem.class),
    PRINT(Print.class),
    RUN(Run.class),
    LET(Let.class),
    LIST(List.class),
    GOTO(Goto.class);

    private final Class<? extends Command> commandClass;

    CommandEnum(Class<? extends Command> commandClass) {
        this.commandClass = commandClass;
    }

    public Command create(String params) throws GeneralException {
        try {
            try {
                return commandClass.getConstructor(String.class).newInstance(params.trim());
            } catch (NoSuchMethodException e) {
                return commandClass.getConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof GeneralException) {
                throw (GeneralException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }
}
