package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import resources.Sprites;

public class BoardView extends Application {

    public static final int TILE_SIZE = 75;
    private static Sprites Sprites = new Sprites();

    // Grid of tiles to be displayed by the View
    GridPane visualBoard;

    private Parent createContent() {

        GameController gameController = new GameController();
        Board gameBoard = gameController.initialiseBoard();

        // Gridpane setup
        visualBoard = new GridPane();
        visualBoard.setPrefSize(gameBoard.getWidth() * TILE_SIZE,  gameBoard.getHeight() * TILE_SIZE);

        refreshBoardView(gameBoard);
		
		return visualBoard;
	}

	private void refreshBoardView(Board gameBoard)
    {
        for (int i = 0; i < gameBoard.getWidth(); i++) {
            for (int j = 0; j < gameBoard.getHeight(); j++) {
                StackPane tile = new StackPane();

                Rectangle tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
                tileBackground.setFill(Color.AZURE);
                tileBackground.setStroke(Color.BLACK);

                tile.getChildren().add(tileBackground);

                // Set the appropriate sprite
                // Note: best way to do this is probably a bunch of ifs
                if (gameBoard.getPiece(i, j) instanceof DummyShark)
                    tile.getChildren().add(new ImageView(Sprites.Shark));

                if (gameBoard.getPiece(i, j) instanceof DummyEagle)
                    { /*TODO*/ }


                GridPane.setRowIndex(tile, gameBoard.getHeight() - j);
                GridPane.setColumnIndex(tile, i);
                visualBoard.getChildren().addAll(tile);
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
