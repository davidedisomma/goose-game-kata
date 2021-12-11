package org.ucieffe.kata.goosegame;

public interface GooseGameCommand {
    void handle(String commandText);
    boolean isTriggered(String commandText);
}
