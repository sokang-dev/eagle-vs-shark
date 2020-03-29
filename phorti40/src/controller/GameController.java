package controller;

import model.Board;
import model.DummyEagle;
import model.DummyShark;
import model.Tile;

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
}
