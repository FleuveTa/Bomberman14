package com.example.bomberm4n.Entities.Block;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Game;
import com.example.bomberm4n.GameControl.Level;
import com.example.bomberm4n.GameControl.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Portal extends Tile {

    public Portal(int x, int y, Image image, Map map) {
        super(x, y, image, map);
    }

    @Override
    public void render(GraphicsContext gc) {
        Rectangle rect = this.getBoundary();
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.fillRect(rect.getX() - xOffset,
                rect.getY() - yOffset + BomGame.GAME_OFFSET,
                rect.getWidth(),
                rect.getHeight());
        gc.setFill(Color.BLACK);
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof Player) {
            if(!getMap().hasEnemies())
                getMap().nextLevel();
            if (getMap().getLevel().getLevel() == Level.MAX_LEVEL) getMap().setWin(true);
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
