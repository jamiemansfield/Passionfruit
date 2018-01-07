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
