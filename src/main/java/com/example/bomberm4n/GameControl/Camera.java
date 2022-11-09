package com.example.bomberm4n.GameControl;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Graphics.Sprite;

public class Camera {

    private int x;

    private int y;

    private final Map map;

    public Camera(Map map, int x, int y) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public void centerEntity(Entity entity) {
        x = entity.getX() - BomGame.WIDTH * Sprite.SCALED_SIZE / 4;
        y = entity.getY() - BomGame.HEIGHT * Sprite.SCALED_SIZE / 4;
        //center posX
        if (x < 0) {
            x = 0;
        } else if (x > map.getLevel().getPixelWidth() - BomGame.WIDTH * Sprite.SCALED_SIZE)  {
            x = map.getLevel().getPixelWidth() - BomGame.WIDTH * Sprite.SCALED_SIZE;
        }
        else {
            x = entity.getX() - BomGame.WIDTH * Sprite.SCALED_SIZE / 4;
        }

        //center posY
        if (y < 0) {
            y = 0;
        } else if (y > map.getLevel().getPixelHeight() - BomGame.HEIGHT * Sprite.SCALED_SIZE)  {
            y = map.getLevel().getPixelHeight() - BomGame.HEIGHT * Sprite.SCALED_SIZE;
        } else {
            y = entity.getY() - BomGame.HEIGHT * Sprite.SCALED_SIZE / 4;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}