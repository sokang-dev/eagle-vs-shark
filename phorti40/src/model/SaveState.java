package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveState implements Serializable {
    private Board gameBoard;
//    private int currentTurn;
    private Player currentPlayer;
    private int timeLimit;

    public SaveState(Board gameBoard, Player currentPlayer, int timeLimit){
        this.gameBoard = gameBoard;
        this.currentPlayer = currentPlayer;
        this.timeLimit = timeLimit;
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
}
