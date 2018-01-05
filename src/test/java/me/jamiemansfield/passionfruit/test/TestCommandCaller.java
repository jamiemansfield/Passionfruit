package me.jamiemansfield.passionfruit.test;

import me.jamiemansfield.passionfruit.CommandCaller;

public class TestCommandCaller implements CommandCaller {

    private String message;

    @Override
    public void message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
