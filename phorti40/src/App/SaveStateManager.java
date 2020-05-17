package App;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Board;
import model.Player;
import model.SaveState;

import javax.imageio.ImageIO;
import java.io.*;

import static resources.Constants.SAVE_PATH;

public class SaveStateManager {

    public static boolean SaveState(SaveState state){
        System.out.println("Saving...");
        //serialise it
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

    public static SaveState LoadState (){

        SaveState loadState = new SaveState(null, null, 0, 0);
        System.out.println("Loading...");
        try
        {
            FileInputStream fileInputStream = new FileInputStream(SAVE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            loadState = (SaveState)objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Loaded.");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Save file not found.");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return loadState;
    };

    public static SaveState CreateSaveState(Board gameBoard, Player currentPlayer, int timeLimit, int actionsRemaining){
        return new SaveState(gameBoard, currentPlayer, timeLimit, actionsRemaining);
    }
}
