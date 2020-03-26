package view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Board;

public class BoardView extends Application {

	public static final int WIDTH = 10;
	public static final int HEIGHT = 10; 
	public static final int TILE_SIZE = 75;
	
	Board board = new Board(); 
	
	private Parent createContent() {
		GridPane gameBoard = new GridPane();
		gameBoard.setPrefSize(WIDTH * TILE_SIZE,  HEIGHT * TILE_SIZE);
		
		// dummy generation 
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
				tile.setFill(Color.AZURE);
				tile.setStroke(Color.BLACK);

				
				
				GridPane.setRowIndex(tile, i);
				GridPane.setColumnIndex(tile, j);
				gameBoard.getChildren().addAll(tile);
			
			}
		}	

		
		return gameBoard;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Title");
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
