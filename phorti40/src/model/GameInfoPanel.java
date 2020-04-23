package model;

import javafx.beans.property.*;

public class GameInfoPanel {

    private StringProperty currentPlayer = new SimpleStringProperty();
    private IntegerProperty actionsRemaining = new SimpleIntegerProperty();
    private LongProperty timeRemaining = new SimpleLongProperty();

    public GameInfoPanel(String currentPlayerName, long timeRemaining) {
        currentPlayer.setValue(currentPlayerName);
        actionsRemaining.setValue(3);
        this.timeRemaining.setValue(timeRemaining);
    }

    public String getCurrentPlayerName() { return currentPlayer.get(); }
    public int getActionsRemaining() { return actionsRemaining.get(); }
    public long getTimeRemaining() { return timeRemaining.get(); }
    public IntegerProperty getActionsRemainingProperty() { return actionsRemaining; }
    public StringProperty getCurrentPlayerProperty() { return currentPlayer; }
    public LongProperty getTimeRemainingProperty() { return timeRemaining; }

    public void setActionsRemaining(int actionsRemaining) { this.actionsRemaining.setValue(actionsRemaining); }
    public void setCurrentPlayer(Player player) { currentPlayer.setValue(player.getPlayerName()); }
    public void setTimeRemaining(long timeRemaining) { this.timeRemaining.setValue(timeRemaining); }

}
