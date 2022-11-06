package com.example.bomberm4n.Entities.Block;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {

    /** Đây là các entity không di chuyển */
    public Tile(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        return true;
    }

    @Override
    public void render(GraphicsContext gc) {
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }
    @Override
    public void update() {}
}
