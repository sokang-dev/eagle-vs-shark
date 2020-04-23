package view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.Set;

import model.*;
import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.UtilityEagle;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.UtilityShark;
import resources.Constants;
import resources.Sprites;

import static resources.Constants.*;

public class BoardView extends GridPane {

    private Board gameBoard;
    private TileView[][] boardView;

    public BoardView(Board gameBoard) {
        super();
        this.gameBoard = gameBoard;
        this.setPrefSize(Constants.BOARD_WIDTH * TILE_SIZE, Constants.BOARD_HEIGHT * TILE_SIZE);
        initialiseBoardView();
    }

    private void initialiseBoardView() {
        boardView = new TileView[BOARD_WIDTH][BOARD_HEIGHT];

        for (Tile[] tileArr : gameBoard.getBoard()) {
            for (Tile tile : tileArr) {
                int x = tile.getX();
                int y = tile.getY();

                TileView tileView = new TileView(tile);
                tileView.setSprite();
                boardView[x][y] = tileView;
                GridPane.setRowIndex(tileView, x);
                GridPane.setColumnIndex(tileView, y);
                this.getChildren().addAll(tileView);
            }
        }
    }

    public void refreshBoard(Set<Tile> validMoves) {
        for (Tile[] tileArr : gameBoard.getBoard()) {
            for (Tile tile : tileArr) {
                int x = tile.getX();
                int y = tile.getY();

                boardView[x][y].setSprite();
            }
        }
        updateMovementTiles(validMoves, Color.AZURE);
    }

//    public void refreshBoard(Board gameBoard, int originX, int originY, int destinationX, int destinationY, Set<Tile> validMoves) {
//        for (Node node : this.getChildren()) {
//            TileView selectedTileView;
//
//            // Remove sprite at the original coordinate
//            if (GridPane.getRowIndex(node) == originX && GridPane.getColumnIndex(node) == originY) {
//                selectedTileView = (TileView) node;
//                selectedTileView.removeSprite();
//            }
//
//            if (GridPane.getRowIndex(node) == destinationX && GridPane.getColumnIndex(node) == destinationY) {
//                selectedTileView = (TileView) node;
//                int x = selectedTileView.getTile().getX();
//                int y = selectedTileView.getTile().getY();
//
//                if (gameBoard.getPiece(x, y) instanceof DummyShark)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Shark));
//                if (gameBoard.getPiece(x, y) instanceof DummyEagle)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Eagle));
//
//                if (gameBoard.getPiece(x,y) instanceof AttackEagle)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.AttackEagle));
//                if (gameBoard.getPiece(x,y) instanceof AttackShark)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.AttackShark));
//
//                if (gameBoard.getPiece(x,y) instanceof UtilityEagle)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.UtilityEagle));
//                if (gameBoard.getPiece(x,y) instanceof UtilityShark)
//                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.UtilityShark));
//            }
//        }
//        updateMovementTiles(validMoves, Color.AZURE);
//    }

    public void updateMovementTiles(Set<Tile> tiles, Color color) {
        // Looks at the validMoves of the selectedPiece and highlights them
        if (tiles.size() > 0) {
            for (Tile t : tiles) {
                TileView selectedTile;

                int validX = t.getX();
                int validY = t.getY();

                for (Node node : this.getChildren()) {
                    if (GridPane.getRowIndex(node) == validX && GridPane.getColumnIndex(node) == validY) {
                        selectedTile = (TileView) node;
                        selectedTile.highlightMovement(color);
                    }
                }
            }
        }
    }



}
