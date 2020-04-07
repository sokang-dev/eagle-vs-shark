package controller;

import javafx.collections.ListChangeListener;
import model.*;
import view.BoardView;

import java.util.Set;

public class GameController {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    // Logical representation of gameboard
    private Board gameBoard;
    private BoardView boardView;

    // Initialises board with initial piece positions
    public Board initialiseBoard() {
        Board gameBoard = new Board();
        this.gameBoard = gameBoard;
        return gameBoard;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void refreshBoard() {

    }

    /*
    public Set<Tile> getValidMoves(Piece piece) {
        Set<Tile> validMoves = piece.getValidMoves(piece.getTile(), piece.getBaseMovement(), this.getGameBoard());

        return validMoves;
    }
    */
}
