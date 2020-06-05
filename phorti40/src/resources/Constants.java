package resources;

import javafx.scene.paint.Color;

public final class Constants {
    public static final int TILE_SIZE = 75;

    public static final int MIN_BOARD_SIZE = 6;
    public static final int MAX_BOARD_SIZE = 12;
    public static final int MIN_PIECE_COUNT = 3;
    public static final int MAX_PIECE_COUNT = 10;

    public static final Color EMPTY_TILE_COLOR = Color.AZURE;
    public static final Color VALID_MOVE_TILE_COLOR = Color.ORANGE;
    public static final String SAVE_PATH = "./save.txt";
    public static final int DEFAULT_ACTIONS_REMAINING = 3;
    public static final Color VALID_ATTACK_TILE_COLOR = Color.RED;

    public static final Color VALID_SPECIAL_TILE_COLOR = Color.HOTPINK;
    public static final Color HP_TEXT_COLOR = Color.DARKBLUE;
}
