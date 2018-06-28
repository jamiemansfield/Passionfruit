/*
 * This file is part of Passionfruit, licensed under the MIT License (MIT).
 *
 * Copyright (c) Jamie Mansfield <https://www.jamierocks.uk/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.jamiemansfield.passionfruit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An object used for dispatching commands, implementing
 * {@link Command} as an easy means of implementing sub-commands.
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
     * Registers the command to the registry under the given name.
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
    public void execute(final C caller, final CommandArgs args) {
        final String commandName = args.getRawArgs()[0];

        // Get the command from the registry
        final Command<C> command = this.registry.getOrDefault(commandName, COMMAND_NOT_FOUND);

        // Copy args without the first element
        final String[] newArgs = Arrays.copyOfRange(args.getRawArgs(), 1, args.getRawArgs().length);

        // Execute the command
        command.execute(caller, new CommandArgs(newArgs));
    }

    /**
     * Executes the command, using the provided caller and arguments.
     *
     * @param caller The command caller
     * @param line The command line
     */
    public void execute(final C caller, final String line) {
        this.execute(caller, new CommandArgs(line.split(" ")));
    }

}
