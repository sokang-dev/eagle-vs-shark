package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameInfoPanel {

    private StringProperty currentPlayer = new SimpleStringProperty();
    private IntegerProperty actionsRemaining = new SimpleIntegerProperty();
    private int timeRemaining;

    public GameInfoPanel(String currentPlayerName) {
        currentPlayer.setValue(currentPlayerName);
        actionsRemaining.setValue(3);
        timeRemaining = 60;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.get();
    }
    public int getActionsRemaining(){
        return actionsRemaining.get();
    }
    public void deductActionsRemaining(){ actionsRemaining.setValue(getActionsRemaining() - 1); }
    public void setActionsRemaining (int actionsRemaining){this.actionsRemaining.setValue(actionsRemaining); }
    public int getTimeRemaining(){ return timeRemaining;}
    public void setCurrentPlayer(Player player)
    {
        currentPlayer.setValue(player.getPlayerName());
    }
    public IntegerProperty getActionsRemainingProperty(){
        return actionsRemaining;
    }
    public StringProperty getCurrentPlayerProperty(){
        return currentPlayer;
    }

}
