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
import model.interfaces.Prototype;

import java.io.Serializable;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class Board implements Serializable, Prototype {
    private Tile[][] board;

    public Board(Board board){
        Tile[][] clone = new Tile[10][10];
        for(int i=0; i<board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                clone[i][j] = (Tile)board.getBoard()[i][j].clone();
               // if (clone[i][j].getPiece() != null){
             //       clone[i][j].getPiece().setTile(clone[i][j]);
             //   }
            }
        }
        this.board = clone;
    }
    // Initialises board with initial piece positions
    public Board() {
        board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

        // Instantiate empty tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        board[0][2].setPiece(new NormDummySharkDecorator(new Shark()));
        board[1][2].setPiece(new NormUtilitySharkDecorator(new Shark()));
        board[4][4].setPiece(new NormAttackSharkDecorator(new Shark()));
        board[4][5].setPiece(new NormDummySharkDecorator(new Shark()));
        board[4][6].setPiece(new NormDummySharkDecorator(new Shark()));

        board[1][3].setPiece(new NormUtilityEagleDecorator(new Eagle()));
        board[0][3].setPiece(new NormDummyEagleDecorator(new Eagle()));
        board[5][4].setPiece(new NormAttackEagleDecorator(new Eagle()));
        board[5][5].setPiece(new NormDummyEagleDecorator(new Eagle()));
        board[5][6].setPiece(new NormDummyEagleDecorator(new Eagle()));
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

    public void updatePiecesOnRestore(){
        Tile[][] tiles = board;
        for(int i=0; i<tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile current = tiles[i][j];
                if (current.getPiece()==null)
                    continue;
                current.getPiece().setTile(current);

            }
        }
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

    @Override
    public Prototype clone() {
        return new Board(this);
    }
}
