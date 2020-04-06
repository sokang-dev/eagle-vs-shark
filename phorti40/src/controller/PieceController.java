package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Board;
import model.Piece;
import model.Tile;
import view.TileView;

import java.util.Set;

public class PieceController {
    GridPane visualBoard;
    Board board;
    TileView tile;

    Piece selectedPiece;
    Set<Tile> validMoves;

    public PieceController(GridPane visualBoard, Board board, TileView tile) {
        this.visualBoard = visualBoard;
        this.board = board;
        this.tile = tile;

        tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                selectTile();
            }
        });
    }

    private void selectTile() {
        this.validMoves = calculateValidMoves();
        updateMovementTiles(Color.ORANGE);
    }

    private void selectMovementTile(Tile tile) {
         Tile destinationTile = tile;

         // If origin equals destination (clicking on same tile)
         if (this.selectedPiece.getTile().equals(tile)) {
             updateMovementTiles(Color.AZURE);
             selectedPiece = null;
         }

         // if destinationTile is not in ValidMoves
         // updateMovementTiles(Color.AZURE);

        // if destinationTile is in validMoves
        // update model
        // updateMovementTiles(Color.AZURE);
        // refresh board
    }

    private void updateMovementTiles(Color color){
        // Gets the valid moves of the selectedPiece and highlights them
        if (validMoves.size() > 0) {
            for (Tile t : validMoves) {
                TileView validTile;

                int validX = t.getX();
                int validY = t.getY();

                for (Node node : visualBoard.getChildren()) {
                    if (GridPane.getRowIndex(node) == validX && GridPane.getColumnIndex(node) == validY) {
                        validTile = (TileView) node;
                        validTile.highlightMovement(color);
                    }
                }
            }
        }
    }

    private Set<Tile> calculateValidMoves() {
        int x = tile.getTile().getX();
        int y = tile.getTile().getY();

        this.selectedPiece = board.getPiece(x, y);
        this.validMoves = selectedPiece.getValidMoves(tile.getTile(), selectedPiece.getBaseMovement(), board);

        return validMoves;
    }
}
