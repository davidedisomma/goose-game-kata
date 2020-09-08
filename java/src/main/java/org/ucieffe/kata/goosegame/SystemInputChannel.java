package org.ucieffe.kata.goosegame;

import java.io.BufferedReader;
import java.io.IOException;

public class SystemInputChannel implements InputChannel{

    private final BufferedReader inputStream;

    public SystemInputChannel(BufferedReader inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String read() {
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
