package view;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.Set;

import model.*;
import resources.Constants;

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
                tileView.setHP();

                boardView[x][y] = tileView;

                GridPane.setRowIndex(tileView, x);
                GridPane.setColumnIndex(tileView, y);
                this.getChildren().addAll(tileView);
            }
        }
    }

    public void refreshBoard() {
        for (Tile[] tileArr : gameBoard.getBoard()) {
            for (Tile tile : tileArr) {
                int x = tile.getX();
                int y = tile.getY();

                boardView[x][y].setSprite();
                boardView[x][y].setHP();
            }
        }
        gameBoard.printBoard();
    }

    public void highlightTiles(Set<Tile> tiles, Color color) {
        for (Tile t : tiles) {
            TileView selectedTile;

            int validX = t.getX();
            int validY = t.getY();

            for (Node node : this.getChildren()) {
                if (GridPane.getRowIndex(node) == validX && GridPane.getColumnIndex(node) == validY) {
                    selectedTile = (TileView) node;
                    selectedTile.setTileBackgroundColor(color);
                }
            }
        }
    }



}
