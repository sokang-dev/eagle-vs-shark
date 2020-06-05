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
import model.interfaces.Prototype;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Board implements Serializable, Prototype {
    private Tile[][] board;

    public Board(Board board){
        Tile[][] clone = new Tile[10][10];
        for(int i=0; i<board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                clone[i][j] = (Tile)board.getBoard()[i][j].clone();
            }
        }
        this.board = clone;
    }
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

            board[x][y].setPiece(new NormDummyEagleDecorator(new Eagle()));
            extraEaglePieceCount--;
        }

        printBoard();
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

    // Used for debugging only
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                if(this.getTile(i,j).getTerrain() != null) {
                    System.out.print(" T ");
                }
                else if (this.getPiece(i,j) != null)
                    System.out.print(this.getPiece(i,j).toString());
                else
                    System.out.print(" 0 ");
            }
            System.out.println();
        }
        System.out.println();
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

    @Override
    public Prototype clone() {
        return new Board(this);
    }

    public Set<Piece> getPiecesByType(PieceType pieceType) {
        Set<Piece> pieces = new HashSet<>();

        for (Piece piece : getAllPieces()) {
            if (piece.getPieceType() == pieceType)
                pieces.add(piece);
        }

        return pieces;
    }

    public Set<Piece> getAdjacentPieces(Tile currentCoord) {
        Set<Piece> pieces = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (this.getTile(x, y) == null)
                    continue;

                if (getPiece(x, y) != null) {
                    pieces.add(getPiece(x, y));
                }
            }
        }

        return pieces;
    }

    public boolean hasNoPieces() {
        return getPiecesByType(PieceType.Shark).isEmpty() || getPiecesByType(PieceType.Eagle).isEmpty();
    }
}
