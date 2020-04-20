package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.GameInfoPanel;
import view.GameInfoPanelView;

public class EndTurnButtonController implements EventHandler {

    private GameInfoPanelView gameInfoPanelView;

    public EndTurnButtonController (GameInfoPanelView gameInfoPanelView)
    {
        this.gameInfoPanelView = gameInfoPanelView;
    }
    @Override
    public void handle(Event event) {
        Platform.runLater(() -> gameInfoPanelView.getGameInfoPanel().setActionsRemaining(0));
    }
}
