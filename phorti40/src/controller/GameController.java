package controller;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import resources.Constants;
import resources.Sprites;
import view.BoardView;
import view.TileView;

public class GameController {
    // Logical representation of gameboard
    private Board gameBoard;

    // Visual board
    private BoardView boardView;

    public GameController() {
        this.gameBoard = new Board();
        this.boardView = new BoardView();

        initialiseBoard();
    }

    private void initialiseBoard() {
        for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                TileView tile = new TileView(i, j);

                // Set the appropriate sprite
                // Note: best way to do this is probably a bunch of ifs
                if (gameBoard.getPiece(i, j) instanceof DummyShark)
                    tile.setSprite(new DummyShark(tile.getTile()), new ImageView(Sprites.Shark));

                if (gameBoard.getPiece(i, j) instanceof DummyEagle)
                    tile.setSprite(new DummyEagle(tile.getTile()), new ImageView(Sprites.Eagle));

                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                boardView.getChildren().addAll(tile);
            }
        }
        PieceController pieceController = new PieceController(boardView, this);
    }

    public Board getGameBoard() {
        return this.gameBoard;
    }

    public BoardView getBoardView() {
        return this.boardView;
    }

}
