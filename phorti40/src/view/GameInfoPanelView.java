package view;

import controller.EndTurnButtonController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.GameInfoPanel;

public class GameInfoPanelView extends VBox {

    private GameInfoPanel gameInfoPanel;
    private Label playerTurn;
    private Label timeRemaining;
    private Label actionsRemaining;

    public GameInfoPanelView(GameInfoPanel gameInfoPanel) {
        super();
        this.gameInfoPanel = gameInfoPanel;
        drawGameInfoPanel();
    }

    private void drawGameInfoPanel(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setPrefWidth(200);
        this.setSpacing(50);
        playerTurn = new Label("Player Turn: " + gameInfoPanel.getCurrentPlayerName());
        timeRemaining = new Label("Timer: "+ gameInfoPanel.getTimeRemaining());
        actionsRemaining = new Label("Actions Left: " + gameInfoPanel.getActionsRemaining());

        Button endTurnButton = new Button("End Turn");
        endTurnButton.setOnAction(new EndTurnButtonController(this));
        this.getChildren().addAll(playerTurn, timeRemaining, actionsRemaining, endTurnButton);
    }

    public void updateGameInfoPanel(){
        Platform.runLater(() -> actionsRemaining.setText("Actions Left: " + gameInfoPanel.getActionsRemaining()));
        Platform.runLater(() -> playerTurn.setText("Player Turn: " + gameInfoPanel.getCurrentPlayerName()));
    }

    public GameInfoPanel getGameInfoPanel(){
        return gameInfoPanel;
    }
}

