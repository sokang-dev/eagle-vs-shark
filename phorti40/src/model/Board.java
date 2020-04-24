package model;

import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.UtilityEagle;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.UtilityShark;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class Board {
    private Tile[][] board;

    // Initialises board with initial piece positions
    public Board() {
        board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

        // Instaniate empty tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        DummyShark dummyShark1 = new DummyShark(board[5][4]);
        DummyEagle dummyEagle1 = new DummyEagle(board[5][5]);
        AttackEagle attackEagle = new AttackEagle(board[3][3]);
        AttackShark attackShark = new AttackShark(board[0][0]);
        UtilityEagle utilityEagle = new UtilityEagle(board[2][2]);
        UtilityShark utilityShark = new UtilityShark(board[8][5]);
    }

    // Utility
    public void printBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (this.getPiece(i, j) instanceof DummyEagle) {
                    System.out.print(" E ");
                } else if (this.getPiece(i, j) instanceof DummyShark) {
                    System.out.print(" S ");
                } else if (this.getPiece(i, j) instanceof AttackEagle) {
                    System.out.print(" A ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public Tile[][] getBoard() {
        return this.board;
    }
    public Tile getTile(int x, int y) {
        return this.board[x][y];
    }
    public Piece getPiece(int i, int j) {
        return this.board[i][j].getPiece();
    }
}
