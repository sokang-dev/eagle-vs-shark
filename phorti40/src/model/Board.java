package model;

import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.UtilityEagle;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.UtilityShark;

public class Board {
    private Tile[][] board;

    // Initialises board with initial piece positions
    public Board(int boardSize) {
        board = new Tile[boardSize][boardSize];

        // Instaniate empty tiles
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        board[0][0].setPiece(new DummyShark());
        board[0][1].setPiece(new UtilityShark());
        board[0][2].setPiece(new AttackShark());

        board[9][9].setPiece(new DummyEagle());
        board[9][8].setPiece(new UtilityEagle());
        board[9][7].setPiece(new AttackEagle());

        printBoard();
    }

    // Used for debugging only
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
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
        if (x >= 0 && x < getSize() && y >= 0 && y < getSize())
            return this.board[x][y];

        // Return nothing if co-ords are out of bounds
        return null;
    }
    public Piece getPiece(int i, int j) {
        return this.board[i][j].getPiece();
    }
    public int getSize()  {
        return this.board.length;
    }
}
