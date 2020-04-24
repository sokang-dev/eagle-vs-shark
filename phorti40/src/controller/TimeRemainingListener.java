package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import resources.Utilities;
import view.GameInfoPanelView;

public class TimeRemainingListener implements ChangeListener {

    private GameInfoPanelView gameInfoPanelView;

    public TimeRemainingListener(GameInfoPanelView view) {
        gameInfoPanelView = view;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        Platform.runLater(() -> gameInfoPanelView.getTimeRemaining().setText("Timer: "+ Utilities.formatMilliseconds(gameInfoPanelView.getGameInfoPanel().getTimeRemaining())));
    }
}
