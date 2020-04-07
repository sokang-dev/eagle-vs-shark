package model;

import java.util.HashSet;
import java.util.Set;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class Board {
    private Tile[][] boardMatrix;

    // Initialises board with initial piece positions
    public Board() {
        boardMatrix = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

        // Instaniate empty tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                boardMatrix[i][j] = new Tile(i, j);
            }
        }

        DummyShark dummyShark1 = new DummyShark(boardMatrix[5][4]);
        DummyEagle dummyEagle1 = new DummyEagle(boardMatrix[5][5]);
    }

    public Tile getTile(int x, int y) {
        return this.boardMatrix[x][y];
    }

    public Piece getPiece(int i, int j) {
        return this.boardMatrix[i][j].getPiece();
    }

    public int getWidth() {
        return BOARD_WIDTH;
    }

    public int getHeight() {
        return BOARD_HEIGHT;
    }

    // Utility
    public void printBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (this.getPiece(i, j) instanceof DummyEagle) {
                    System.out.print(" E ");
                } else if (this.getPiece(i, j) instanceof DummyShark) {
                    System.out.print(" S ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println("");
        }
    }
}
