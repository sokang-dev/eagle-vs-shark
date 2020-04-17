import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.BoardView;

public class GameEngine extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("4040 OOSP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createContent() {
        BorderPane pane = new BorderPane();
        BoardView boardView = new BoardView();

        pane.setCenter(boardView);

        return pane;
    }
}
