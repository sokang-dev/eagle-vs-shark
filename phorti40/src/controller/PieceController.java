package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.*;
import resources.Constants;
import view.TileView;

import java.util.Set;

public class PieceController {
    private GridPane visualBoard;
    private GameController gameController;
    private Board board;
    private boolean pieceClicked = false;
    private Piece selectedPiece;
    private Set<Tile> validMoves;
    private Set<Tile> validAttacks;

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
        Player currentPlayer = gameController.getCurrentPlayer();

        // If tile is empty
        if (piece == null) {
            System.out.println("Non piece selected");
        }
        // If tile contains a piece not belonging to player
        else if (!currentPlayer.isPlayerPiece(piece)) {
            System.out.println("Wrong piece dumbass.");
        } else {
            // Store valid move and valid attacks
            storePieceAndValidMoves(piece);
            this.pieceClicked = true;

            // Highlight validMoves tiles
            gameController.getBoardView().highlightTiles(this.validMoves, Constants.VALID_MOVE_TILE_COLOR);

            // Highlight valid attacks
            gameController.getBoardView().highlightTiles(this.validAttacks, Constants.VALID_ATTACK_TILE_COLOR);
        }
    }

    private void selectMovementTile(Tile destinationTile) {
        /* If the selected destinationTile is another piece
         * Unselect piece
         * Unhighlight validMoves tiles */
        if (board.getPiece(destinationTile.getX(), destinationTile.getY()) != null) {
            gameController.getBoardView().highlightTiles(this.validMoves, Constants.EMPTY_TILE_COLOR);
            gameController.getBoardView().highlightTiles(this.validAttacks, Constants.EMPTY_TILE_COLOR);

            this.pieceClicked = false;
            board.printBoard(); // console printing board for debugging
        }

        // If valid move OR attack
        if (validAttacks.contains(destinationTile)) {
            destinationTile.getPiece().takeDamage();
            postActionBoardReset();
        }
        else if (validMoves.contains(destinationTile)) {
            selectedPiece.move(board.getTile(destinationTile.getX(), destinationTile.getY()));
            postActionBoardReset();

            board.printBoard(); // console printing board for debugging
        } else {
            System.out.println("Can't move there :|");
        }
    }

    private void postActionBoardReset() {
        gameController.getBoardView().highlightTiles(this.validMoves, Constants.EMPTY_TILE_COLOR);
        gameController.getBoardView().highlightTiles(this.validAttacks, Constants.EMPTY_TILE_COLOR);

        gameController.getBoardView().refreshBoard();

        selectedPiece = null;
        this.pieceClicked = false;
        Platform.runLater(() -> gameController.getGameInfoPanel().setActionsRemaining(gameController.getGameInfoPanel().getActionsRemaining() - 1));
    }

    private void storePieceAndValidMoves(Piece selectedPiece) {
        // Store the selectedPiece and it's valid moves
        this.selectedPiece = selectedPiece;
        this.validMoves = selectedPiece.getValidMoves(selectedPiece.getTile(), selectedPiece.getBaseMovement(), board);
        this.validAttacks = selectedPiece.getValidAttacks(selectedPiece.getTile(), board);
    }
}