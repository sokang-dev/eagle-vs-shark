package resources;

import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

public final class Utilities {

    // Initial stage given by JavaFX
    private static Stage primaryStage;

    // Returns milliseconds in Minute:Second format
    public static String formatMilliseconds(long milliseconds)
    {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds - TimeUnit.MINUTES.toMillis(minutes));

        return String.format("%02d:%02d", minutes, seconds);
    }

    public static Stage getPrimaryStage() { return primaryStage; }

    public static void setPrimaryStage(Stage stage) { primaryStage = stage; }
}
