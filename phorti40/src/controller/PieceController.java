package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import model.Enums.StatusType;
import model.interfaces.Piece;
import resources.Constants;
import view.TileView;

import java.util.HashSet;
import java.util.Set;

public class PieceController {
    private GridPane visualBoard;
    private GameController gameController;
    private GameInfoPanel gameInfoPanel;
    private Board board;
    private boolean pieceClicked = false;
    private Piece selectedPiece;
    private Set<Tile> validMoves;
    private Set<Tile> validAttacks;
    private boolean specialClicked = false;
    private Set<Tile> validSpecials;

    public PieceController(GridPane visualBoard, GameController gameController) {
        this.visualBoard = visualBoard;
        this.gameController = gameController;
        gameInfoPanel = gameController.getGameInfoPanel();
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

    public void handleSpecialButton(Event event) {
        if (selectedPiece == null) {
            System.out.println("No piece selected!");
            gameInfoPanel.setErrorMessage("No piece selected!");
            return;
        }

        if (validSpecials.isEmpty()) {
            gameInfoPanel.setErrorMessage("No valid specials available!");
            return;
        }

        // Toggle special
        specialClicked = !specialClicked;

        if (specialClicked)
            gameController.getBoardView().highlightTiles(this.validSpecials, Constants.VALID_SPECIAL_TILE_COLOR);
        else {
            gameController.getBoardView().highlightTiles(this.validSpecials, Constants.EMPTY_TILE_COLOR);
        }

    }

    public void handleTransformButton(Event event) {
        if (selectedPiece == null) {
            System.out.println("No piece selected!");
            gameInfoPanel.setErrorMessage("No piece selected!");
            return;
        }
        this.validSpecials.removeAll(validSpecials);
        selectedPiece = selectedPiece.transform();
        postActionBoardReset();
    }

    private void selectTile(Tile tile) {
        Piece piece = board.getPiece(tile.getX(), tile.getY());
        Player currentPlayer = gameController.getCurrentPlayer();

        // If tile is empty
        if (piece == null) {
            System.out.println("Non piece selected");
            gameInfoPanel.setErrorMessage("Non piece selected!");
        }
        // If tile contains a piece not belonging to player
        else if (!currentPlayer.isPlayerPiece(piece)) {
            System.out.println("Wrong piece dumbass.");
            gameInfoPanel.setErrorMessage("Wrong piece selected!");
        }
        // If piece is currently stunned
        else if (piece.getStatus(StatusType.Stun) != null) {
            System.out.println("This piece is currently stunned!");
            gameInfoPanel.setErrorMessage("This piece is stunned!");
        }
        else {
            // Store valid move and valid attacks
            storePieceAndValidMoves(piece);

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
        }

        // If valid move OR attack OR special
        if (validSpecials.contains(destinationTile) && specialClicked) {
            selectedPiece.special(destinationTile, board);
            postActionBoardReset();
        } else if (validAttacks.contains(destinationTile)) {
            selectedPiece.attack(destinationTile);
            postActionBoardReset();
        } else if (validMoves.contains(destinationTile)) {
            selectedPiece.move(board.getTile(destinationTile.getX(), destinationTile.getY()), board);
            postActionBoardReset();
        }  else {
            System.out.println("Invalid selection!");
            pieceReset();
        }
    }

    public void pieceReset() {
        gameController.getBoardView().highlightTiles(this.validMoves != null ? this.validMoves : new HashSet<>(), Constants.EMPTY_TILE_COLOR);
        gameController.getBoardView().highlightTiles(this.validAttacks != null ? this.validAttacks : new HashSet<>(), Constants.EMPTY_TILE_COLOR);
        gameController.getBoardView().highlightTiles(this.validSpecials != null ? this.validSpecials : new HashSet<>(), Constants.EMPTY_TILE_COLOR);

        selectedPiece = null;
        this.pieceClicked = false;
        this.specialClicked = false;

        gameInfoPanel.setErrorMessage("");
    }

    private void postActionBoardReset() {
        pieceReset();
        gameController.getBoardView().refreshBoard();

        if (board.hasNoPieces()) {
//            gameController.setGameIsOver(true);
            Platform.runLater(() -> gameInfoPanel.setActionsRemaining(0));
        }

        Platform.runLater(() -> gameInfoPanel.setActionsRemaining(gameInfoPanel.getActionsRemaining() - 1));
    }

    private void storePieceAndValidMoves(Piece selectedPiece) {
        // Store the selectedPiece and its valid moves
        this.pieceClicked = true;
        this.selectedPiece = selectedPiece;
        this.validMoves = selectedPiece.getValidMoves(selectedPiece.getTile(), selectedPiece.getBaseMovement(), board);
        this.validAttacks = selectedPiece.getValidAttacks(selectedPiece.getTile(), board);
        this.validSpecials = selectedPiece.calcValidSpecials(selectedPiece.getTile(), board);
        selectedPiece.setValidSpecials(validSpecials);
    }

    public void setBoard(Board board){
        this.board = board;
    }
}