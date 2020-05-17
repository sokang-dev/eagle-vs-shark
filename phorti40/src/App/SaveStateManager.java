package App;

import model.SaveState;
import java.io.*;

import static resources.Constants.SAVE_PATH;

public class SaveStateManager {

    //serialise and save our game state to .txt
    public static boolean SaveState(SaveState state){
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
}
