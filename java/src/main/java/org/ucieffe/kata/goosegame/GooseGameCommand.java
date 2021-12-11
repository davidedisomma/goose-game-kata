package org.ucieffe.kata.goosegame;

public interface GooseGameCommand {
    GooseGameEvent handle(String commandText);
    boolean isTriggered(String commandText);
}
