package me.jamiemansfield.passionfruit;

/**
 * An interface used to describe a command within Passionfruit.
 *
 * @param <C> The type of the command caller
 */
public interface Command<C extends CommandCaller> {

    /**
     * Executes the command, using the provided caller and arguments.
     *
     * @param caller The command caller
     * @param args The arguments, with the command name omitted
     */
    void execute(final C caller, final String[] args);

}
