package resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprites {

    public static Image AttackShark;
    public static Image Shark;
    public static Image UtilityShark;

    public static Image AttackEagle;
    public static Image Eagle;
    public static Image UtilityEagle;

    // Loads sprites into Image classes for use
    public Sprites()
    {
        this.Shark = new Image(getClass().getResource("shark.png").toExternalForm());
        this.AttackShark = new Image(getClass().getResource("shark_attack.png").toExternalForm());
        this.UtilityShark = new Image(getClass().getResource("shark_utility.png").toExternalForm());
        this.Eagle = new Image(getClass().getResource("eagle.png").toExternalForm());
        this.AttackEagle = new Image(getClass().getResource("eagle_attack.png").toExternalForm());
        this.UtilityEagle = new Image(getClass().getResource("eagle_utility.png").toExternalForm());
    }
}
