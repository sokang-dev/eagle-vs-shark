package resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class Sprites {

    public static Image AttackShark = new Image(Sprites.class.getResourceAsStream("shark_attack.png"));
    public static Image Shark = new Image(Sprites.class.getResourceAsStream("shark.png"));
    public static Image UtilityShark = new Image(Sprites.class.getResourceAsStream("shark_utility.png"));

    public static Image AttackEagle = new Image(Sprites.class.getResourceAsStream("eagle_attack.png"));
    public static Image Eagle = new Image(Sprites.class.getResourceAsStream("eagle.png"));
    public static Image UtilityEagle = new Image(Sprites.class.getResourceAsStream("eagle_utility.png"));
}
