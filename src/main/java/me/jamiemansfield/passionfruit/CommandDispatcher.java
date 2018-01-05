package me.jamiemansfield.passionfruit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Dispatches commands to the correct {@link Command}.
 *
 * @param <C> The command caller type
 */
public class CommandDispatcher<C extends CommandCaller> implements Command<C> {

    /**
     * A psuedo-command used for when commands are not found.
     */
    private final Command<C> COMMAND_NOT_FOUND = (caller, args) -> caller.message("Command not found!");

    private final Map<String, Command<C>> registry;

    public CommandDispatcher() {
        this.registry = new HashMap<>();
    }

    /**
     * Registers the command.
     *
     * @param name The name of the command
     * @param command The command
     * @return {@code this} for chaining
     */
    public CommandDispatcher<C> register(final String name, final Command<C> command) {
        this.registry.put(name, command);
        return this;
    }

    @Override
    public void execute(final C caller, final String[] args) {
        final String commandName = args[0];

        // Get the command from the registry
        final Command<C> command = this.registry.getOrDefault(commandName, COMMAND_NOT_FOUND);

        // Copy args without the first element
        final String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        // Execute the command
        command.execute(caller, newArgs);
    }

    /**
     * Executes the command, using the provided caller and arguments.
     *
     * @param caller The command caller
     * @param line The command line
     */
    public void execute(final C caller, final String line) {
        this.execute(caller, line.split(" "));
    }

}
