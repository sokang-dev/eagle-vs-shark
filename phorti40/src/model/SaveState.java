package model;

import java.io.Serializable;

public class SaveState implements Serializable {
    private Board gameBoard;
    private Player currentPlayer;
    private int timeLimit;
    private int actionsRemaining;

    public SaveState(Board gameBoard, Player currentPlayer, int timeLimit, int actionsRemaining){
        this.gameBoard = gameBoard;
        this.currentPlayer = currentPlayer;
        this.timeLimit = timeLimit;
        this.actionsRemaining = actionsRemaining;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getActionsRemaining() {
        return actionsRemaining;
    }
}
