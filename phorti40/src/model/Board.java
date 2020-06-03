package model;

import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.UtilityEagle;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.UtilityShark;

import java.io.Serializable;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class Board implements Serializable, Prototype {
    private Tile[][] board;

    public Board(Board board){
        Tile[][] clone = board.getBoard().clone();
        this.board = clone;
    }
    // Initialises board with initial piece positions
    public Board() {
        board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

        // Instaniate empty tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        // board[0][0].setPiece(new DummyShark());
        // board[0][1].setPiece(new UtilityShark());
        board[0][2].setPiece(new DummyShark());
        board[1][2].setPiece(new UtilityShark());
        board[4][4].setPiece(new AttackShark());
        board[4][5].setPiece(new DummyShark());
        board[4][6].setPiece(new DummyShark());

        // board[9][9].setPiece(new DummyEagle());
        board[1][3].setPiece(new UtilityEagle());
        board[0][3].setPiece(new DummyEagle());
        board[5][4].setPiece(new AttackEagle());
        board[5][5].setPiece(new DummyEagle());
        board[5][6].setPiece(new DummyEagle());

        printBoard();
    }

    // Used for debugging only
    public void printBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                Piece piece = this.getPiece(i, j);
                if (piece != null)
                    System.out.print(piece.toString());
                else
                    System.out.print(" 0 ");
            }
            System.out.println();
        }
        System.out.println();
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

    @Override
    public Prototype clone() {
        return new Board(this);
    }
}
