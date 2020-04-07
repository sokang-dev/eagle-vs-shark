package view;

import controller.GameController;
import controller.PieceController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import resources.Constants;
import resources.Sprites;

import static resources.Constants.TILE_SIZE;

public class BoardView extends Application {

    private static Sprites Sprites = new Sprites();

    // Grid of tiles to be displayed by the View
    GridPane visualBoard;
    GameController gameController;

    private Parent createContent() {
        this.gameController = new GameController();
        Board gameBoard = gameController.initialiseBoard();

        // Gridpane setup
        visualBoard = new GridPane();
        visualBoard.setPrefSize(Constants.BOARD_WIDTH * TILE_SIZE, Constants.BOARD_HEIGHT * TILE_SIZE);

        createUITiles(gameBoard);
        return visualBoard;
    }

    private void createUITiles(Board gameBoard) {
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
                visualBoard.getChildren().addAll(tile);
            }
        }
        PieceController pieceController = new PieceController(visualBoard, gameController);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("4040 OOSP");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
