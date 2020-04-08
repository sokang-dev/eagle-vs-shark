package controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.*;
import resources.Sprites;
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

    private void selectTile(TileView tile) {
        // TODO: catch error for when a non Piece is selected

        calculateValidMoves(tile);
        updateMovementTiles(Color.ORANGE);
        this.tileSelected = true;
    }

    private void selectMovementTile(Tile destinationTile) {
        // If the selected destinationTile is another piece
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) instanceof Piece) {
            updateMovementTiles(Color.AZURE);
            this.tileSelected = false;

            board.printBoard();
        }

        // TODO: if destinationTile is NOT in ValidMoves -> updateMovementTiles(Color.AZURE); AND this.tileSelected = false;

        // If destinationTile is in validMoves
        else {
            for (Tile t : validMoves) {
                int validX = t.getX();
                int validY = t.getY();

                if (destinationTile.getX() == validX && destinationTile.getY() == validY) {
                    board.printBoard();

                    // Move the selected piece to the destination tile's coordinates
                    int originX = selectedPiece.getTile().getX();
                    int originY = selectedPiece.getTile().getY();

                    selectedPiece.move(board.getTile(validX, validY));
                    updateSprites(originX, originY, validX, validY);

                    // Utility stuff
                    System.out.println("Moved from " + originX + ", " + originY);
                    System.out.println("To " + validX + ", " + validY + "\n\n");
                    board.printBoard();

                    updateMovementTiles(Color.AZURE);
                    selectedPiece = null;
                    this.tileSelected = false;
                }
            }
        }
    }

    private void calculateValidMoves(TileView selectedTile) {
        int x = selectedTile.getTile().getX();
        int y = selectedTile.getTile().getY();

        // Store the selectedPiece and it's valid moves
        this.selectedPiece = board.getPiece(x, y);
        this.validMoves = selectedPiece.getValidMoves(selectedTile.getTile(), selectedPiece.getBaseMovement(), board);
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

    private void updateSprites(int originX, int originY, int destinationX, int destinationY) {
        for (Node node : visualBoard.getChildren()) {
            TileView selectedTileView;

            if (GridPane.getRowIndex(node) == originX && GridPane.getColumnIndex(node) == originY) {
                // Remove sprite at the origin
                selectedTileView = (TileView) node;
                selectedTileView.removeSprite();
            }

            if (GridPane.getRowIndex(node) == destinationX && GridPane.getColumnIndex(node) == destinationY) {
                selectedTileView = (TileView) node;
                // The coordinates of the selectedTileView
                int x = selectedTileView.getTile().getX();
                int y = selectedTileView.getTile().getY();

                if (board.getPiece(x, y) instanceof DummyShark)
                    selectedTileView.setSprite(board.getPiece(x, y), new ImageView(Sprites.Shark));
                if (board.getPiece(x, y) instanceof DummyEagle)
                    selectedTileView.setSprite(board.getPiece(x, y), new ImageView(Sprites.Eagle));
            }
        }
    }

    // TODO: Make convenience method??
    public void getTileView() {

    }

}
