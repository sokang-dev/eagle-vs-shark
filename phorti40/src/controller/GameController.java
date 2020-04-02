package controller;

import model.*;

import java.util.HashSet;
import java.util.Set;

public class GameController {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    // Logical representation of gameboard
    private Board gameBoard;

    // Initialises board with initial piece positions
    public Board initialiseBoard()
    {
        Board gameBoard = new Board();
        this.gameBoard = gameBoard;
        return gameBoard;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Set<Coord> getValidMoves(Piece piece) {
        Set<Coord> allPieceCoords = this.gameBoard.getAllPieceCoords();

        Set<Coord> validMoves = piece.getValidMoves(piece.getCoord(), piece.getBaseMovement(), allPieceCoords);

        return validMoves;
    }
}
