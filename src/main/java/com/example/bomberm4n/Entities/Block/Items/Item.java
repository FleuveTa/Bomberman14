package com.example.bomberm4n.Entities.Block.Items;

import com.example.bomberm4n.Entities.Block.Tile;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item extends Tile {

   // private boolean isPoweredUp = false;

    public Item(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public void render(GraphicsContext gc) {
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }
}
