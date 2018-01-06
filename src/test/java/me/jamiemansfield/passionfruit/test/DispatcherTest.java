package me.jamiemansfield.passionfruit.test;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;
import me.jamiemansfield.passionfruit.CommandDispatcher;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public final class DispatcherTest {

    private static CommandDispatcher<TestCommandCaller> createDispatcher() {
        return new CommandDispatcher<>();
    }

    private static void registerSimpleCommand(final CommandDispatcher<TestCommandCaller> dispatcher, final String command, final String result) {
        dispatcher.register(command, (caller, args) -> caller.message(result));
    }

    private static final Map<String, String> COMMAND_TESTS = ImmutableMap.<String, String>builder()
            .put("test", "test")
            .build();

    private static final Map<String, Map<String, String>> SUBCOMMAND_TESTS = ImmutableMap.<String, Map<String, String>>builder()
            .put("example", ImmutableMap.<String, String>builder()
                    .put("test", "test")
                    .put("example", "example")
                    .build())
            .build();

    private static CommandDispatcher<TestCommandCaller> dispatcher;
    private static TestCommandCaller caller;

    @BeforeClass
    public static void initialise() {
        dispatcher = createDispatcher();

        COMMAND_TESTS.forEach((command, result) -> registerSimpleCommand(dispatcher, command, result));
        SUBCOMMAND_TESTS.forEach((rootCommand, childCommands) -> {
            final CommandDispatcher<TestCommandCaller> rootDispatcher = createDispatcher();
            childCommands.forEach((command, result) -> registerSimpleCommand(rootDispatcher, command, result));
            dispatcher.register(rootCommand, rootDispatcher);
        });

        caller = new TestCommandCaller();
    }

    @Test
    public void commandTest() {
        COMMAND_TESTS.forEach((command, expectedResult) -> {
            dispatcher.execute(caller, command);
            assertEquals(expectedResult, caller.getMessage());
        });
    }

    @Test
    public void subcommandTest() {
        SUBCOMMAND_TESTS.forEach((rootCommand, childCommands) -> childCommands.forEach((command, expectedResult) -> {
            dispatcher.execute(caller, rootCommand + " " + command);
            assertEquals(expectedResult, caller.getMessage());
        }));
    }

}
