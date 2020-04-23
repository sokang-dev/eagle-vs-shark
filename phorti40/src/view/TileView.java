package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Piece;
import model.Tile;
import resources.Sprites;


import static resources.Constants.TILE_SIZE;

public class TileView extends StackPane {

    // private ImageView sprite;
    private Tile tile;
    private Rectangle tileBackground;
    private ImageView sprite;

    public TileView(Tile tile) {
        this.tile = tile;

        this.tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
        tileBackground.setFill(Color.AZURE);
        tileBackground.setStroke(Color.BLACK);

        this.getChildren().add(tileBackground);
    }

    public Tile getTile() {
        return tile;
    }


    public void setSprite() {
        if (tile.getPiece() != null) {
            sprite = this.tile.getPiece().getSprite();
            this.getChildren().add(sprite);
        }
    }

    public void removeSprite() {
        this.sprite.imageProperty().set(null);
    }

    public void highlightMovement(Color color) {
        this.tileBackground.setFill(color);
    }

}	
