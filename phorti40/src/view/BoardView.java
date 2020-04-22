package view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import model.*;
import resources.Constants;
import resources.Sprites;
import java.util.Set;

import static resources.Constants.TILE_SIZE;

public class BoardView extends GridPane {

    private static Sprites Sprites = new Sprites();

    public BoardView() {
        super();
        this.setPrefSize(Constants.BOARD_WIDTH * TILE_SIZE, Constants.BOARD_HEIGHT * TILE_SIZE);
    }

    public void refreshBoard(Board gameBoard, int originX, int originY, int destinationX, int destinationY, Set<Tile> validMoves) {
        for (Node node : this.getChildren()) {
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

                if (gameBoard.getPiece(x, y) instanceof DummyShark)
                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Shark));
                if (gameBoard.getPiece(x, y) instanceof DummyEagle)
                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Eagle));

                if (gameBoard.getPiece(x,y) instanceof AttackEagle)
                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Eagle));
                if (gameBoard.getPiece(x,y) instanceof AttackShark)
                    selectedTileView.setSprite(gameBoard.getPiece(x, y), new ImageView(Sprites.Shark));
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
