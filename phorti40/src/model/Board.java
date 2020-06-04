package model;

import model.Eagle.Eagle;
import model.Eagle.NormAttackEagleDecorator;
import model.Eagle.NormDummyEagleDecorator;
import model.Eagle.NormUtilityEagleDecorator;
import model.Enums.PieceType;
import model.Shark.NormAttackSharkDecorator;
import model.Shark.NormDummySharkDecorator;
import model.Shark.NormUtilitySharkDecorator;
import model.Shark.Shark;
import model.interfaces.Piece;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Board implements Serializable {
    private Tile[][] board;

    // Initialises board with initial piece positions
    public Board(int boardSize, int pieceCount) {
        board = new Tile[boardSize][boardSize];

        // Instaniate empty tiles
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        int mainPieceCount = (int) Math.floor((double) pieceCount / 3);

        while(mainPieceCount > 0)
        {
            // Set Sharks
            Piece[] sharks = new Piece[] {
                    new NormDummySharkDecorator(new Shark()),
                    new NormUtilitySharkDecorator(new Shark()),
                    new NormAttackSharkDecorator(new Shark())
            };
            int sharkAmount = 0;

            while(sharkAmount < sharks.length)
            {
                int x = (int) (Math.random() * boardSize / 2);
                int y = (int) (Math.random() * boardSize);

                if(board[x][y].getPiece() != null)
                    continue;

                board[x][y].setPiece(sharks[sharkAmount]);
                sharkAmount++;
            }

            board[1][3].setPiece(new NormUtilityEagleDecorator(new Eagle()));
            board[0][3].setPiece(new NormDummyEagleDecorator(new Eagle()));
            board[5][4].setPiece(new NormAttackEagleDecorator(new Eagle()));
            // Set Eagles
            Piece[] eagles = new Piece[] {
                    new NormUtilityEagleDecorator(new Eagle()),
                    new NormDummyEagleDecorator(new Eagle()),
                    new NormAttackEagleDecorator(new Eagle())
            };
            int eagleAmount = 0;

            while(eagleAmount < eagles.length)
            {
                int x = (int) (Math.random() * boardSize / 2) + boardSize / 2;
                int y = (int) (Math.random() * boardSize);

                if(board[x][y].getPiece() != null)
                    continue;

                board[x][y].setPiece(eagles[eagleAmount]);
                eagleAmount++;
            }

            mainPieceCount--;
        }

        int extraSharkPieceCount = pieceCount % 3;
        while (extraSharkPieceCount > 0)
        {
            // Extra Sharks
            int x = (int) (Math.random() * boardSize / 2);
            int y = (int) (Math.random() * boardSize);

            if(board[x][y].getPiece() != null)
                continue;

            board[x][y].setPiece(new NormDummySharkDecorator(new Shark()));
            extraSharkPieceCount--;
        }

        int extraEaglePieceCount = pieceCount % 3;
        while (extraEaglePieceCount > 0)
        {
            // Extra eagles
            int x = (int) (Math.random() * boardSize / 2) + boardSize / 2;
            int y = (int) (Math.random() * boardSize);

            if(board[x][y].getPiece() != null)
                continue;

            board[x][y].setPiece(new NormDummySharkDecorator(new Shark()));
            extraEaglePieceCount--;
        }


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

    public int getSize() {
        return this.board.length;
    }

    public Set<Piece> getAllPieces() {
        Set<Piece> pieces = new HashSet<>();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (getPiece(i, j) != null) pieces.add(getPiece(i, j));
            }
        }

        return pieces;
    }

    public Set<Piece> getPiecesByType(PieceType pieceType) {
        Set<Piece> pieces = new HashSet<>();

        for (Piece piece : getAllPieces()) {
            if (piece.getPieceType() == pieceType)
                pieces.add(piece);
        }

        return pieces;
    }
}
