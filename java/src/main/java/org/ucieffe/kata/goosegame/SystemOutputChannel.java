package org.ucieffe.kata.goosegame;

import java.io.IOException;
import java.io.OutputStream;

public class SystemOutputChannel implements OutputChannel {

    private final OutputStream out;

    public SystemOutputChannel(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(String text) {
        try {
            out.write(text.getBytes());
            out.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
