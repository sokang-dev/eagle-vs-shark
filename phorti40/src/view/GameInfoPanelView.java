package view;

import controller.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.GameInfoPanel;
import resources.Utilities;

public class GameInfoPanelView extends VBox {

    private GameInfoPanel gameInfoPanel;
    private GameController gameController;

    private Label playerTurn;
    private Label timeRemaining;
    private Label actionsRemaining;
    private Label saveStatusLabel;
    private Label errorMessage;

    public GameInfoPanelView(GameInfoPanel gameInfoPanel, GameController gameController) {
        super();
        this.gameController = gameController;
        this.gameInfoPanel = gameInfoPanel;
        drawGameInfoPanel();

        gameInfoPanel.getActionsRemainingProperty().addListener(new ActionsRemainingListener(this));
        gameInfoPanel.getCurrentPlayerProperty().addListener(new CurrentPlayerListener(this, gameInfoPanel));
        gameInfoPanel.getTimeRemainingProperty().addListener(new TimeRemainingListener(this));
        gameInfoPanel.getErrorMessageProperty().addListener(new ErrorMessageListener(this, gameInfoPanel));
    }

    private void drawGameInfoPanel(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setPrefWidth(200);
        this.setSpacing(50);
        playerTurn = new Label("Player Turn: " + gameInfoPanel.getCurrentPlayerName());
        timeRemaining = new Label("Timer: "+ Utilities.formatMilliseconds(gameInfoPanel.getTimeRemaining()));
        actionsRemaining = new Label("Actions Left: " + gameInfoPanel.getActionsRemaining());

        Button specialButton = new Button("Special");
        specialButton.setOnAction(gameController.getPieceController()::handleSpecialButton);

        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);

        Button endTurnButton = new Button("End Turn");
        endTurnButton.setOnAction(gameController::handleEndTurnButton);

        saveStatusLabel = new Label();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(gameController::handleSaveButton);
        this.getChildren().addAll(playerTurn, timeRemaining, actionsRemaining, specialButton, errorMessage, endTurnButton, saveStatusLabel, saveButton);
    }

    public GameInfoPanel getGameInfoPanel(){
        return gameInfoPanel;
    }

    public Label getActionsRemaining() {
        return actionsRemaining;
    }

    public Label getPlayerTurn() {
        return playerTurn;
    }

    public Label getTimeRemaining() {
        return timeRemaining;
    }

    public void setSaveStatusLabel(String message) {
        saveStatusLabel.setText(message);
    }

    public Label getSaveStatusLabel(){
        return saveStatusLabel;
    }

    public Label getErrorMessage() { return errorMessage; }
}

