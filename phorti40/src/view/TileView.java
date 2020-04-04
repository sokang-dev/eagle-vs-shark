package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Coord;
import model.Piece;
import model.Tile;


import static resources.Constants.TILE_SIZE;

public class TileView extends StackPane {

    private ImageView sprite;
    private Tile tile;

    public TileView(int i, int j) {
        tile = new Tile(new Coord(i,j));

        Rectangle tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
        tileBackground.setFill(Color.AZURE);
        tileBackground.setStroke(Color.BLACK);

        this.getChildren().add(tileBackground);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(Piece piece, ImageView sprite) {
        tile.setPiece(piece);

        this.sprite = sprite;
        this.getChildren().add(sprite);


    }
}	
