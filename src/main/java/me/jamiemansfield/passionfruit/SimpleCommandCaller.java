package me.jamiemansfield.passionfruit;

import java.io.PrintStream;

/**
 * A simple implementation of {@link CommandCaller}.
 */
public class SimpleCommandCaller implements CommandCaller {

    private final PrintStream printStream;

    public SimpleCommandCaller(final PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void message(final String message) {
        this.printStream.println(message);
    }

}
