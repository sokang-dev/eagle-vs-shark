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
import resources.Sprites;

import static resources.Constants.TILE_SIZE;

public class BoardView extends Application {

    private static Sprites Sprites = new Sprites();

    // Grid of tiles to be displayed by the View
    GridPane visualBoard;

    private Parent createContent() {
        GameController gameController = new GameController();
        Board gameBoard = gameController.initialiseBoard();

        // Gridpane setup
        visualBoard = new GridPane();
        visualBoard.setPrefSize(gameBoard.getWidth() * TILE_SIZE,  gameBoard.getHeight() * TILE_SIZE);

        createUITiles(gameBoard);
		return visualBoard;
	}

	private void createUITiles(Board gameBoard)
    {
        for (int i = 0; i < gameBoard.getWidth(); i++) {
            for (int j = 0; j < gameBoard.getHeight(); j++) {
                TileView tile = new TileView(i,j);

                // Set the appropriate sprite
                // Note: best way to do this is probably a bunch of ifs
                if (gameBoard.getPiece(i, j) instanceof DummyShark)
                    tile.setSprite(new DummyShark(tile.getTile()), new ImageView(Sprites.Shark));

                if (gameBoard.getPiece(i, j) instanceof DummyEagle)
                    tile.setSprite(new DummyEagle(tile.getTile()), new ImageView(Sprites.Eagle));

                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                visualBoard.getChildren().addAll(tile);

                // System.out.println(visualBoard.getChildren().get(0));

                // do we need the TILE or the ENTIRE UI BOARD?
                PieceController pieceController = new PieceController(visualBoard, gameBoard, tile);
            }
        }
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
