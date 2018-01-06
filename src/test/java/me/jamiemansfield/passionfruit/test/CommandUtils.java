package me.jamiemansfield.passionfruit.test;

import me.jamiemansfield.passionfruit.Command;
import me.jamiemansfield.passionfruit.CommandDispatcher;

import java.util.Map;

public final class CommandUtils {

    public static CommandDispatcher<TestCommandCaller> createDispatcher() {
        return new CommandDispatcher<>();
    }

    public static Command<TestCommandCaller> createSimpleCommand(final String result) {
        return (caller, args) -> caller.message(result);
    }

    public static CommandDispatcher<TestCommandCaller> register(
            final CommandDispatcher<TestCommandCaller> dispatcher,
            final Map<String, String> commands) {
        commands.forEach((command, result) -> dispatcher.register(command, createSimpleCommand(result)));
        return dispatcher;
    }

    public static CommandDispatcher<TestCommandCaller> register(final Map<String, String> commands) {
        return register(createDispatcher(), commands);
    }

    public static CommandDispatcher<TestCommandCaller> subregister(
            final CommandDispatcher<TestCommandCaller> dispatcher,
            final Map<String, Map<String, String>> commandTree) {
        commandTree.forEach((rootCommand, childCommand) -> dispatcher.register(rootCommand, register(childCommand)));
        return dispatcher;
    }

    private CommandUtils() {
    }

}
