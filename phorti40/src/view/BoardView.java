package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Board;
import resources.Sprites;

public class BoardView extends Application {

    private static Sprites Sprites = new Sprites();

    private Parent createContent() {
        GameController gameController = new GameController();

        // creates model
        gameController.initialiseBoard();
        // creates visual board
        GridPane visualBoard = gameController.createBoard();
        // populates visual board
        gameController.populateUITiles();

        return visualBoard;
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
