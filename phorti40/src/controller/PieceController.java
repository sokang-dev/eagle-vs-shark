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

    boolean pieceClicked = false;
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

                        if (!pieceClicked) {
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
        try {
            calculateValidMoves(tile);
            updateMovementTiles(Color.ORANGE);
            this.pieceClicked = true;
        } catch (Exception e) {
            System.out.printf("Non piece selected!\n");
        }
    }

    private void selectMovementTile(Tile destinationTile) {
        // If the selected destinationTile is another piece
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) instanceof Piece) {
            updateMovementTiles(Color.AZURE);
            this.pieceClicked = false;

            board.printBoard();
        }

        // TODO: if destinationTile is NOT in ValidMoves -> updateMovementTiles(Color.AZURE); AND this.tileSelected = false;

        // If destinationTile is in validMoves
        else {
            for (Tile t : validMoves) {
                if (destinationTile.getX() == t.getX() && destinationTile.getY() == t.getY()) {
                    int originX = selectedPiece.getTile().getX();
                    int originY = selectedPiece.getTile().getY();

                    // Move the selected piece to the destination tile's coordinates
                    selectedPiece.move(board.getTile(t.getX(), t.getY()));
                    refreshBoard(originX, originY, t.getX(), t.getY());

                    board.printBoard();
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
                TileView selectedTile;

                int validX = t.getX();
                int validY = t.getY();

                for (Node node : visualBoard.getChildren()) {
                    if (GridPane.getRowIndex(node) == validX && GridPane.getColumnIndex(node) == validY) {
                        selectedTile = (TileView) node;
                        selectedTile.highlightMovement(color);
                    }
                }
            }
        }
    }

    private void refreshBoard(int originX, int originY, int destinationX, int destinationY) {
        for (Node node : visualBoard.getChildren()) {
            TileView selectedTileView;

            // Remove sprite at the origin
            if (GridPane.getRowIndex(node) == originX && GridPane.getColumnIndex(node) == originY) {
                selectedTileView = (TileView) node;
                selectedTileView.removeSprite();
            }

            // The coordinates of the selectedTileView
            if (GridPane.getRowIndex(node) == destinationX && GridPane.getColumnIndex(node) == destinationY) {
                selectedTileView = (TileView) node;
                int x = selectedTileView.getTile().getX();
                int y = selectedTileView.getTile().getY();

                if (board.getPiece(x, y) instanceof DummyShark)
                    selectedTileView.setSprite(board.getPiece(x, y), new ImageView(Sprites.Shark));
                if (board.getPiece(x, y) instanceof DummyEagle)
                    selectedTileView.setSprite(board.getPiece(x, y), new ImageView(Sprites.Eagle));
            }
        }
        updateMovementTiles(Color.AZURE);
        selectedPiece = null;
        this.pieceClicked = false;
    }

    // TODO: Make convenience method??
    public void getTileView() {

    }
}
