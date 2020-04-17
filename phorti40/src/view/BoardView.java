package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;
import model.DummyEagle;
import model.DummyShark;
import model.Tile;
import resources.Sprites;

import java.util.Set;

public class BoardView extends GridPane {

    private static Sprites Sprites = new Sprites();
    GameController gameController;
    GridPane visualBoard;
    Board board;

    public BoardView() {
        this.gameController = new GameController(this);
        // creates model
        gameController.initialiseBoard();
        // creates visual board
        this.visualBoard = gameController.createBoard();
        // populates visual board
        gameController.populateUITiles();

        this.board = gameController.getGameBoard();
    }

    private Parent createContent() {
        return visualBoard;
    }

    public void refreshBoard(Board gameBoard, int originX, int originY, int destinationX, int destinationY, Set<Tile> validMoves) {
        for (Node node : visualBoard.getChildren()) {
            TileView selectedTileView;

            // Remove sprite at the original coordinate
            if (GridPane.getRowIndex(node) == originX && GridPane.getColumnIndex(node) == originY) {
                selectedTileView = (TileView) node;
                selectedTileView.removeSprite();
            }

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
        updateMovementTiles(validMoves, Color.AZURE);
    }

    public void updateMovementTiles(Set<Tile> tiles, Color color) {
        // Looks at the validMoves of the selectedPiece and highlights them
        if (tiles.size() > 0) {
            for (Tile t : tiles) {
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

}
