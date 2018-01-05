package me.jamiemansfield.passionfruit.test;

import static org.junit.Assert.assertEquals;

import me.jamiemansfield.passionfruit.CommandDispatcher;
import org.junit.BeforeClass;
import org.junit.Test;

public final class DispatcherTest {

    private static CommandDispatcher<TestCommandCaller> createDispatcher() {
        return new CommandDispatcher<>();
    }

    private static CommandDispatcher<TestCommandCaller> dispatcher;
    private static TestCommandCaller caller;

    @BeforeClass
    public static void initialise() {
        dispatcher = createDispatcher()
            .register("test", (caller, args) -> caller.message("test"))
            .register("example", createDispatcher()
                    .register("test", (caller, args) -> caller.message("test"))
                    .register("example", (caller, args) -> caller.message("example"))
            );
        caller = new TestCommandCaller();
    }

    @Test
    public void commandTest() {
        dispatcher.execute(caller, "test");
        assertEquals("test", caller.getMessage());
    }

    @Test
    public void subcommandTest() {
        dispatcher.execute(caller, "example example");
        assertEquals("example", caller.getMessage());
    }

}
