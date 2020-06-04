package model;

public class GameMemento {
    private SaveState state;

    public GameMemento(SaveState state){
        this.state = state;
    }
    public SaveState getState(){
        return state;
    }
}
