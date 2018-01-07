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

package me.jamiemansfield.passionfruit.util;

import me.jamiemansfield.passionfruit.CommandCaller;

import java.io.PrintStream;

/**
 * A simple implementation of {@link CommandCaller}, that messages to a
 * {@link PrintStream} - such as {@link System#out}.
 */
public class SimpleCommandCaller implements CommandCaller {

    private final PrintStream printStream;

    /**
     * Creates a command caller from the provided print stream.
     *
     * @param printStream The print stream
     */
    public SimpleCommandCaller(final PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void message(final String message) {
        this.printStream.println(message);
    }

}
