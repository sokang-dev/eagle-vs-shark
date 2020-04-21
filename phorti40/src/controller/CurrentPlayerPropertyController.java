package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.GameInfoPanel;
import view.GameInfoPanelView;

public class CurrentPlayerPropertyController implements ChangeListener {

    private GameInfoPanelView gameInfoPanelView;
    private GameInfoPanel gameInfoPanel;

    public CurrentPlayerPropertyController(GameInfoPanelView view, GameInfoPanel gameInfoPanel)
    {
        gameInfoPanelView = view;
        this.gameInfoPanel = gameInfoPanel;
    }
    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        Platform.runLater(() -> gameInfoPanelView.getPlayerTurn().setText("Player Turn: " + gameInfoPanel.getCurrentPlayerName()));
    }
}
