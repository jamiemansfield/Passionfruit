package me.jamiemansfield.passionfruit;

/**
 * An interface used to describe a command caller.
 */
public interface CommandCaller {

    /**
     * Sends a message to the command caller.
     *
     * @param message The message to send
     */
    void message(final String message);

}
