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

import me.jamiemansfield.passionfruit.CommandArgs;

import java.util.NoSuchElementException;

/**
 * This class is to commands, what {@link java.util.Scanner} is
 * to command line reading.
 */
public class CommandArgsReader {

    private final CommandArgs args;
    private int index = -1;

    /**
     * Creates a new command args reader, from the given command args.
     *
     * @param args The command args
     */
    public CommandArgsReader(final CommandArgs args) {
        this.args = args;
    }

    /**
     * Establishes whether there is a remaining argument to read.
     *
     * @return {@code true} if there is another argument;
     *         {@code false} otherwise
     */
    public boolean hasNext() {
        return this.args.getArgs().size() > this.index + 1;
    }

    /**
     * Gets the next argument, if available.
     *
     * @return The next argument
     * @throws NoSuchElementException Should no available argument be remaining to be read
     */
    public String next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("There are no arguments left!");
        }
        return this.args.getArgs().get(++this.index);
    }

}
