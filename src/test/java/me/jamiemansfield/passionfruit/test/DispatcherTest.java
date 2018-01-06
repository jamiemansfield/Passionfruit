package me.jamiemansfield.passionfruit.test;

import static me.jamiemansfield.passionfruit.test.CommandUtils.register;
import static me.jamiemansfield.passionfruit.test.CommandUtils.subregister;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;
import me.jamiemansfield.passionfruit.CommandDispatcher;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public final class DispatcherTest {

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
        dispatcher = subregister(register(COMMAND_TESTS), SUBCOMMAND_TESTS);
        caller = new TestCommandCaller();
    }

    @Test
    public void commandTest() {
        COMMAND_TESTS.forEach(this::test);
    }

    @Test
    public void subcommandTest() {
        SUBCOMMAND_TESTS.forEach((rootCommand, childCommands) ->
                childCommands.forEach((childCommand, expectedResult) -> this.test(rootCommand, childCommand, expectedResult)));
    }

    private void test(final String parent, final String child, final String expectedResult) {
        this.test(parent + " " + child, expectedResult);
    }

    private void test(final String command, final String expectedResult) {
        dispatcher.execute(caller, command);
        assertEquals(expectedResult, caller.getMessage());
    }

}
