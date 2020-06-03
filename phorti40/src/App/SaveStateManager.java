package App;

import model.GameMemento;
import model.SaveState;
import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

import static resources.Constants.SAVE_PATH;

//Caretaker class for Memento Pattern
public class SaveStateManager {
    public static Stack<GameMemento> gameHistory = new Stack<GameMemento>();

    //serialise and save our game state to .txt
    public static boolean SaveState(GameMemento memento){
        SaveState state = memento.getState();
        System.out.println("Saving...");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(SAVE_PATH,false);
            out = new ObjectOutputStream(fos);
            out.writeObject(state);
            out.close();
            System.out.println("Saved.");
            return true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    };

    //Deserialise from the .txt and return the loaded SaveState todo: should this create a Momemento?
    public static SaveState LoadState (){
        SaveState loadState = new SaveState(null, null, 0, 0);
        System.out.println("Loading...");
        try {
            FileInputStream fileInputStream = new FileInputStream(SAVE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loadState = (SaveState)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Loaded.");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Save file not found: " + ex.getLocalizedMessage());
        }
        catch(IOException ex) {
            System.out.println("IOException occurred: " + ex.getLocalizedMessage());
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException occurred: " + ex.getLocalizedMessage());
        }
        return loadState;
    };

    public static void SaveGameMemento(GameMemento memento){
        gameHistory.push(memento);
        System.out.println("Pushed this board: ");
        gameHistory.peek().getState().getGameBoard().printBoard();

        System.out.println("STACK: ");
        PrintStack();

    }

    public static GameMemento Undo(int numberOfUndo){
        System.out.println("Prev: ");
        gameHistory.peek().getState().getGameBoard().printBoard();
        return gameHistory.pop();
    }

    public static void PrintStack()
    {
        for (GameMemento i : gameHistory) {
            System.out.println("Turn: " + i.getStateId() + " - " + "Tile: " + i.getState().getGameBoard().getBoard()
                    + "Board: " + i.getState().getGameBoard());

            i.getState().getGameBoard().printBoard();
        }
    }
}
