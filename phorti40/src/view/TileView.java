package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Piece;
import model.Tile;


import static resources.Constants.TILE_SIZE;

public class TileView extends StackPane {

    // private ImageView sprite;
    private Tile tile;
    private Rectangle tileBackground;

    public TileView(int i, int j) {
        tile = new Tile(i, j);

        this.tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
        tileBackground.setFill(Color.AZURE);
        tileBackground.setStroke(Color.BLACK);

        this.getChildren().add(tileBackground);
    }

    public Tile getTile() {
        return tile;
    }


    public void setSprite(Piece piece) {
        this.getChildren().add(piece.get);
    }

    /*
    public void removeSprite() {
        this.sprite.imageProperty().set(null);
    }
     */

    public void highlightMovement(Color color) {
        this.tileBackground.setFill(color);
    }

}	
