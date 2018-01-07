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

package me.jamiemansfield.passionfruit.test;

import static me.jamiemansfield.passionfruit.util.Commands.createDispatcher;

import me.jamiemansfield.passionfruit.Command;
import me.jamiemansfield.passionfruit.CommandDispatcher;

import java.util.Map;

public final class CommandUtils {

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
