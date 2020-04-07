package controller;

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
    GameController gameController;
    Board board;

    boolean tileSelected = false;
    Piece selectedPiece;
    Set<Tile> validMoves;

    public PieceController(GridPane visualBoard, GameController gameController) {
        this.visualBoard = visualBoard;
        this.gameController = gameController;
        this.board = gameController.getGameBoard();

        visualBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (Node node : visualBoard.getChildren()) {
                    // Get the Tile at specific RowIndex and ColumnIndex of mouse click
                    if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                        TileView tile = (TileView) node;

                        if (!tileSelected) {
                            selectTile(tile);
                        } else {
                            selectMovementTile(tile.getTile());
                        }
                    }
                }
            }
        });
    }

    private void calculateValidMoves(TileView tile) {
        int x = tile.getTile().getX();
        int y = tile.getTile().getY();

        this.selectedPiece = board.getPiece(x, y);
        this.validMoves = selectedPiece.getValidMoves(tile.getTile(), selectedPiece.getBaseMovement(), board);
    }

    private void selectTile(TileView tile) {
        calculateValidMoves(tile);
        updateMovementTiles(Color.ORANGE);
        this.tileSelected = true;
    }

    private void selectMovementTile(Tile destinationTile) {
        // If the selected destinationTile is another piece
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) instanceof Piece) {
            updateMovementTiles(Color.AZURE);
            this.tileSelected = false;
        }

        // TODO: if destinationTile is NOT in ValidMoves -> updateMovementTiles(Color.AZURE); AND this.tileSelected = false;

        // If destinationTile is in validMoves
        else {
            for (Tile t : validMoves) {
                int validX = t.getX();
                int validY = t.getY();

                if (destinationTile.getX() == validX && destinationTile.getY() == validY) {
                    // Move the selected piece to the destination tile's coordinates
                    selectedPiece.move(t);

                    // TODO: Refresh board
                    gameController.refreshBoard();

                    updateMovementTiles(Color.AZURE);
                    selectedPiece = null;
                    this.tileSelected = false;
                }
            }
        }
    }

    private void updateMovementTiles(Color color) {
        // Looks at the validMoves of the selectedPiece and highlights them
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
}
