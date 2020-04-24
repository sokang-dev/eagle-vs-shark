package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import model.Eagle.Eagle;
import model.Enums.PieceType;
import model.Shark.Shark;
import resources.Constants;
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
                        TileView tileView = (TileView) node;
                        if (!pieceClicked) {
                            selectTile(tileView.getTile());
                        } else {
                            selectMovementTile(tileView.getTile());
                        }
                    }
                }
            }
        });
    }

    private void selectTile(Tile tile) {
        Piece piece = board.getPiece(tile.getX(), tile.getY());
        PieceType currentPlayerPieceType = gameController.getCurrentPlayer().getPieceType();

        // If tile is empty
        if (piece == null) {
            System.out.println("Non piece selected");
        }
        // If tile contains a piece not belonging to player
        else if ((currentPlayerPieceType == PieceType.Shark && piece instanceof Eagle)
        || currentPlayerPieceType == PieceType.Eagle && piece instanceof Shark) {
            System.out.println("Wrong piece dumbass.");
        }
        else {
            storePieceAndValidMoves(piece);
            gameController.getBoardView().highlightPieceValidMoves(this.validMoves, Constants.VALID_MOVE_TILE_COLOR);
            this.pieceClicked = true;
        }
    }

    private void selectMovementTile(Tile destinationTile) {
        /* If the selected destinationTile is another piece
         * Unselect piece
         * Unhighlight validMoves tiles */
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) != null) {
            gameController.getBoardView().highlightPieceValidMoves(this.validMoves, Constants.EMPTY_TILE_COLOR);
            this.pieceClicked = false;
            board.printBoard(); // console printing board for debugging
        }

        // If destinationTile is in validMoves
        if (validMoves.contains(destinationTile)) {
            // Move the piece in the Model
            selectedPiece.move(board.getTile(destinationTile.getX(), destinationTile.getY()));

            // Update the View
            gameController.getBoardView().highlightPieceValidMoves(this.validMoves, Constants.EMPTY_TILE_COLOR);
            gameController.getBoardView().refreshBoard();

            selectedPiece = null;
            this.pieceClicked = false;
            Platform.runLater(() -> gameController.getGameInfoPanel().setActionsRemaining(gameController.getGameInfoPanel().getActionsRemaining()-1));
            board.printBoard(); // console printing board for debugging
        }
        else
            System.out.println("Can't move there :|");
    }

    private void storePieceAndValidMoves(Piece selectedPiece) {
        // Store the selectedPiece and it's valid moves
        this.selectedPiece = selectedPiece;
        this.validMoves = selectedPiece.getValidMoves(selectedPiece.getTile(), selectedPiece.getBaseMovement(), board);
    }
}
