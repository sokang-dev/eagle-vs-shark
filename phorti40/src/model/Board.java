package model;

import model.Eagle.Eagle;
import model.Eagle.NormAttackEagleDecorator;
import model.Eagle.NormDummyEagleDecorator;
import model.Eagle.NormUtilityEagleDecorator;
import model.Shark.NormAttackSharkDecorator;
import model.Shark.NormDummySharkDecorator;
import model.Shark.NormUtilitySharkDecorator;
import model.Shark.Shark;
import model.interfaces.Piece;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class Board {
    private Tile[][] board;

    // Initialises board with initial piece positions
    public Board() {
        board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

        // Instantiate empty tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        // board[0][0].setPiece(new NormDummySharkDecorator());
        // board[0][1].setPiece(new NormUtilitySharkDecorator());
        board[0][2].setPiece(new NormDummySharkDecorator(new Shark()));
        board[1][2].setPiece(new NormUtilitySharkDecorator(new Shark()));
        board[4][4].setPiece(new NormAttackSharkDecorator(new Shark()));
        board[4][5].setPiece(new NormDummySharkDecorator(new Shark()));
        board[4][6].setPiece(new NormDummySharkDecorator(new Shark()));

        // board[9][9].setPiece(new NormDummyEagleDecorator());
        board[1][3].setPiece(new NormUtilityEagleDecorator(new Eagle()));
        board[0][3].setPiece(new NormDummyEagleDecorator(new Eagle()));
        board[5][4].setPiece(new NormAttackEagleDecorator(new Eagle()));
        board[5][5].setPiece(new NormDummyEagleDecorator(new Eagle()));
        board[5][6].setPiece(new NormDummyEagleDecorator(new Eagle()));

//        printBoard();
    }

    // Used for debugging only
//    public void printBoard() {
//        for (int i = 0; i < BOARD_WIDTH; i++) {
//            for (int j = 0; j < BOARD_HEIGHT; j++) {
//                Piece piece = this.getPiece(i, j);
//                if (piece != null)
//                    System.out.print(piece.toString());
//                else
//                    System.out.print(" 0 ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

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
