package controller;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resources.Utilities;
import view.MainMenuView;

public class MainMenuController {

    private MainMenuView mainMenuView;

    public MainMenuController() {
        mainMenuView = new MainMenuView(this);
    }

    // Initialise the game controller and game thread, then change the scene
    public void handleNewGameButton(int timerInput, Event event) {
        GameController gameController = new GameController(timerInput);

        BorderPane pane = new BorderPane();
        pane.setCenter(gameController.getBoardView());
        pane.setRight(gameController.getGameInfoPanelView());

        Stage primaryStage = Utilities.getPrimaryStage();
        primaryStage.hide();
        primaryStage.setScene(new Scene(pane));
        primaryStage.setOnShown(e -> new Thread(() -> {
            gameController.GameStart();
        }).start());
        primaryStage.show();
    }

    public MainMenuView getMainMenuView() { return mainMenuView; }
}
