package model;

import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.Eagle;
import model.Eagle.UtilityEagle;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.Shark;
import model.Shark.UtilityShark;
import java.lang.Math;
import java.util.ArrayList;

public class Board {
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
            Shark[] sharks = new Shark[] {new DummyShark(), new UtilityShark(), new AttackShark()};
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


            // Set Eagles
            Eagle[] eagles = new Eagle[] {new DummyEagle(), new UtilityEagle(), new AttackEagle()};
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

            board[x][y].setPiece(new DummyShark());
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

            board[x][y].setPiece(new DummyEagle());
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
    public int getSize()  {
        return this.board.length;
    }
}
