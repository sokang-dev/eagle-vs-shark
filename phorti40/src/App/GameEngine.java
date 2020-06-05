package App;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resources.Utilities;

public class GameEngine extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Save a reference to the primary stage for later use
        Utilities.setPrimaryStage(primaryStage);
        primaryStage.setTitle("4040 OOSP");

        MainMenuController mainMenuController = new MainMenuController();
        Scene menuScene = mainMenuController.createMainMenu();
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
}
