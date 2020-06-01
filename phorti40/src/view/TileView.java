package view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Tile;
import resources.Constants;

import static resources.Constants.TILE_SIZE;

public class TileView extends StackPane {

    private Tile tile;
    private Rectangle tileBackground;
    private ImageView sprite;
    private Text hpText = new Text("");

    public TileView(Tile tile) {
        this.tile = tile;
        this.sprite = new ImageView();

        setSprite();

        this.tileBackground = new Rectangle(TILE_SIZE, TILE_SIZE);
        tileBackground.setFill(Color.AZURE);
        tileBackground.setStroke(Color.BLACK);

        this.getChildren().add(tileBackground);
        this.getChildren().add(sprite);

        // shows hp
        hpText.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        hpText.setFill(Constants.HP_TEXT_COLOR);
        StackPane.setAlignment(hpText, Pos.TOP_LEFT);
        this.getChildren().add(hpText);
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

    public void setHP() {
        if (tile.getPiece() != null)
            this.hpText.setText(" HP: " + String.valueOf(this.tile.getPiece().getHealth()));
        else
            this.hpText.setText("");
    }
}	
