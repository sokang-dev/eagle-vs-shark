package controller;

import App.SaveStateManager;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SaveState;
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
        StartGame(gameController);
    }

    // Initialise the game controller and game thread, then change the scene
    public void handleResumeGame(Event event) {
        SaveState state = SaveStateManager.LoadState();
        if (state.getGameBoard() == null)
        {
            //Create and show an alert
            Alert a1 = new Alert(Alert.AlertType.ERROR, "Error loading save file.");
            a1.show();
        }
        else {
            GameController gameController = new GameController(state);
            StartGame(gameController);
        }
    }

    private void StartGame(GameController gameController)
    {
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
