package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import view.GameInfoPanelView;

public class ActionsRemainingListener implements ChangeListener {

    private GameInfoPanelView gameInfoPanelView;

    public ActionsRemainingListener(GameInfoPanelView view)
    {
        gameInfoPanelView = view;
    }
    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        Platform.runLater(() -> gameInfoPanelView.getActionsRemaining().setText("Actions Left: " + gameInfoPanelView.getGameInfoPanel().getActionsRemaining()));
    }
}
