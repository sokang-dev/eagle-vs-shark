package controller;

import App.SaveStateManager;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.SaveState;
import resources.Constants;
import resources.Utilities;
import view.MainMenuView;

public class MainMenuController {

    private MainMenuView mainMenuView;
    private Label errorMessage;

    public MainMenuController() {
        mainMenuView = new MainMenuView(this);
    }

    // Initialise the game controller and game thread, then change the scene
    public void handleNewGameButton(int timerInput, int boardSizeInput, int pieceCountInput, Event event) {
        if (boardSizeInput < Constants.MIN_BOARD_SIZE || boardSizeInput > Constants.MAX_BOARD_SIZE) {
            Alert boardSizeError = new Alert(Alert.AlertType.ERROR, "Please select a board size between 6-12.");
            boardSizeError.show();
        }
        else if (pieceCountInput < Constants.MIN_PIECE_COUNT || pieceCountInput > Constants.MAX_PIECE_COUNT) {
            Alert pieceCountError = new Alert(Alert.AlertType.ERROR, "Please select between 3-10 pieces.");
            pieceCountError.show();
        }
        else {
            GameController gameController = new GameController(timerInput, boardSizeInput, pieceCountInput);
            StartGame(gameController);
        }
}
    public void handleResumeGame(Event event) {
        SaveState state = SaveStateManager.LoadState();
        if (state.getGameBoard() == null) {
            //Create and show an alert
            Alert loadingError = new Alert(Alert.AlertType.ERROR, "Error loading save file.");
            loadingError.show();
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
