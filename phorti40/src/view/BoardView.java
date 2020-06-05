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
        this.setPrefSize(gameBoard.getSize() * TILE_SIZE, gameBoard.getSize() * TILE_SIZE);
        initialiseBoardView();
    }

    private void initialiseBoardView() {
        boardView = new TileView[gameBoard.getSize()][gameBoard.getSize() ];
        AddTileViewsToBoard(boardView);
    }

    private void AddTileViewsToBoard(TileView[][] board){
        for (Tile[] tileArr : gameBoard.getBoard()) {
            for (Tile tile : tileArr) {
                int x = tile.getX();
                int y = tile.getY();

                TileView tileView = new TileView(tile);
                tileView.setSprite();
                tileView.setHP();

                board[x][y] = tileView;

                GridPane.setRowIndex(tileView, x);
                GridPane.setColumnIndex(tileView, y);
                this.getChildren().addAll(tileView);
            }
        }
    }

    public TileView[][] RestoreTileView(){
        this.getChildren().removeAll(this.getChildren());
        TileView[][] tileViews = new TileView[gameBoard.getSize()][gameBoard.getSize()];
        AddTileViewsToBoard(tileViews);
        return tileViews;
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

    public void setBoard(Board board){
        this.gameBoard=board;
    }

    public void setBoardView(TileView[][] boardView){
        this.boardView=boardView;
    }
}
