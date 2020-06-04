package model;

import javafx.beans.property.*;

public class GameInfoPanel {

    private StringProperty currentPlayer = new SimpleStringProperty();
    private IntegerProperty actionsRemaining = new SimpleIntegerProperty();
    private LongProperty timeRemaining = new SimpleLongProperty();
    private StringProperty errorMessage = new SimpleStringProperty();

    public GameInfoPanel(String currentPlayerName, long timeRemaining, int actionsRemaining) {
        currentPlayer.setValue(currentPlayerName);
        this.actionsRemaining.setValue(actionsRemaining);
        this.timeRemaining.setValue(timeRemaining);
        errorMessage.setValue("");
    }

    public String getCurrentPlayerName() { return currentPlayer.get(); }
    public int getActionsRemaining() { return actionsRemaining.get(); }
    public long getTimeRemaining() { return timeRemaining.get(); }
    public String getErrorMessage() { return errorMessage.get(); }

    public IntegerProperty getActionsRemainingProperty() { return actionsRemaining; }
    public StringProperty getCurrentPlayerProperty() { return currentPlayer; }
    public LongProperty getTimeRemainingProperty() { return timeRemaining; }
    public StringProperty getErrorMessageProperty() { return errorMessage; }

    public void setActionsRemaining(int actionsRemaining) { this.actionsRemaining.setValue(actionsRemaining); }
    public void setCurrentPlayer(Player player) { currentPlayer.setValue(player.getPlayerName()); }
    public void setTimeRemaining(long timeRemaining) { this.timeRemaining.setValue(timeRemaining); }
    public void setErrorMessage(String errorMessage) { this.errorMessage.setValue(errorMessage); }

    public void decrementTimeRemaining(long time) { this.timeRemaining.setValue(this.timeRemaining.getValue() - time); }

}
