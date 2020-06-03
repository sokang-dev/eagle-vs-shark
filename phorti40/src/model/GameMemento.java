package model;

public class GameMemento {
    private SaveState state;
    private int Id;

    public GameMemento(SaveState state, int id){
        this.state = state;
        this.Id = id;
    }
    public SaveState getState(){
        return state;
    }

    public int getStateId() {return Id;}
}
