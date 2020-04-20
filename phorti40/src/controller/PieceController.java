package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.*;
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
        // If tile is empty
        Piece tilePiece=board.getPiece(tile.getTile().getX(), tile.getTile().getY());
        if (tilePiece == null) {
            System.out.println("Non piece selected");
        }
        else if ((gameController.getCurrentPlayer().getPieceType()=="shark" && tilePiece instanceof DummyEagle)
        || gameController.getCurrentPlayer().getPieceType()=="eagle" && tilePiece instanceof DummyShark) {
            System.out.println("Wrong piece dumbass.");
        }
        else {
            calculateValidMoves(tile);
            gameController.getBoardView().updateMovementTiles(this.validMoves, Color.ORANGE);
            this.pieceClicked = true;
        }
    }

    private void selectMovementTile(Tile destinationTile) {
        // If the selected destinationTile is another piece
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) instanceof Piece) {
            gameController.getBoardView().updateMovementTiles(this.validMoves, Color.AZURE);
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

                    // Move the piece in the Model
                    selectedPiece.move(board.getTile(t.getX(), t.getY()));
                    // Update the View
                    gameController.getBoardView().refreshBoard(board, originX, originY, t.getX(), t.getY(), validMoves);
                    selectedPiece = null;
                    this.pieceClicked = false;
                    Platform.runLater(() -> gameController.getGameInfoPanel().deductActionsRemaining());
                    gameController.getGameInfoPanelView().updateGameInfoPanel();
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
}
