package controller;

import model.*;

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
        return gameBoard;
    }

    public Set<Coord> getValidMoves(Piece piece) {
        Set<Coord> validMoves = piece.getValidMoves(piece.getCoord(), piece.getBaseMovement());
        Set<Coord> allPieceCoords = gameBoard.getAllPieceCoords();

        // Remove coordinates that contain pieces
        validMoves.removeAll(allPieceCoords);

        return validMoves;
    }
}
