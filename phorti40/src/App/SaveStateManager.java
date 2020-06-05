package App;

import model.GameMemento;
import model.SaveState;
import resources.Constants;

import java.io.*;
import java.util.EmptyStackException;
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

    //Deserialise from the .txt and return the loaded SaveState
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
        //only keep stack to a size that we actually need.
        if (gameHistory.size() > Constants.GAME_HISTORY_MAX_SIZE)
        {
            gameHistory.removeElement(gameHistory.firstElement());
        }
        gameHistory.push(memento);

        //For debug
        System.out.println("STACK: ");
        PrintStack();
    }

    public static GameMemento Undo(int numberOfUndo){
        try {
            if (!canGameHistoryUndo(numberOfUndo))
            {
                throw new EmptyStackException();
            }
            for (int i = 0; i < numberOfUndo ; i++) {
                gameHistory.pop();
            }
            return gameHistory.pop();
        } catch (EmptyStackException ex)
        {
            throw new EmptyStackException();
        }
    }

    private static boolean canGameHistoryUndo(int numberOfUndo){
        return gameHistory.size() > numberOfUndo;
    }

    private static void PrintStack()
    {
        for (GameMemento i : gameHistory) {
            System.out.println("Tile: " + i.getState().getGameBoard().getBoard() + "Board: " + i.getState().getGameBoard());
        }
    }
}
