package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import model.Tile;
import resources.Sprites;

import static resources.Constants.TILE_SIZE;

public class TileView extends StackPane {

    private Tile tile;
    private Rectangle tileBackground;
    private ImageView sprite;

    public TileView(Tile tile) {
        this.tile = tile;
        this.sprite = new ImageView();

        setSprite();

        this.tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
        tileBackground.setFill(Color.AZURE);
        tileBackground.setStroke(Color.BLACK);

        this.getChildren().add(tileBackground);
        this.getChildren().add(sprite);
    }

    public Tile getTile() {
        return tile;
    }

    // Handles adding and removing Piece's sprite
    public void setSprite() {
        if (tile.getPiece() != null)
            sprite.imageProperty().set(this.tile.getPiece().getSprite());
        else
            sprite.imageProperty().set(null);
    }

    public void setTileBackgroundColor(Color color) {
        this.tileBackground.setFill(color);
    }
}	
