package org.ucieffe.kata.goosegame;

import java.util.List;

public abstract sealed class GooseGameEvent permits InvalidCommandEvent, AddPlayerEvent, PlayerAlreadyPresentEvent, BounceBackEvent, WinningEvent, MoveEvent{
}

final class InvalidCommandEvent extends GooseGameEvent {
}

final class AddPlayerEvent extends GooseGameEvent {

    public final List<String> playerNames;

    public AddPlayerEvent(List<String> playerNames) {
        this.playerNames = playerNames;
    }
}
final class PlayerAlreadyPresentEvent extends GooseGameEvent {

    public final String playerName;

    public PlayerAlreadyPresentEvent(String playerName) {
        this.playerName = playerName;
    }
}
final class BounceBackEvent extends GooseGameEvent {

    public final String playerName;
    public final Integer firstDice;
    public final Integer secondDice;
    public final String lastPosition;
    public final String currentPosition;

    public BounceBackEvent(String playerName, Integer firstDice, Integer secondDice, String lastPosition, String currentPosition) {
        this.playerName = playerName;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.lastPosition = lastPosition;
        this.currentPosition = currentPosition;
    }
}
final class WinningEvent extends GooseGameEvent {

    public final String playerName;
    public final Integer firstDice;
    public final Integer secondDice;
    public final String lastPosition;
    public final String currentPosition;

    public WinningEvent(String playerName, Integer firstDice, Integer secondDice, String lastPosition, String currentPosition) {
        this.playerName = playerName;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.lastPosition = lastPosition;
        this.currentPosition = currentPosition;
    }
}
final class MoveEvent extends GooseGameEvent {

    public final String playerName;
    public final Integer firstDice;
    public final Integer secondDice;
    public final String lastPosition;
    public final String currentPosition;

    public MoveEvent(String playerName, Integer firstDice, Integer secondDice, String lastPosition, String currentPosition) {
        this.playerName = playerName;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.lastPosition = lastPosition;
        this.currentPosition = currentPosition;
    }
}

