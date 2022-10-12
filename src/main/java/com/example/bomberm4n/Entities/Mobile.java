package com.example.bomberm4n.Entities;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Mobile extends Entity {

    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    // Trạng thái của các mobile : up left right down
    protected int direction;
    protected boolean alive = true;
    protected int timeAfterKill = 40;

    public Mobile(int x, int y, Image img, Map map) {
        super(x, y, img, map);
        direction = -1;
    }


    public abstract void kill();

    public abstract void afterKill();

    @Override
    public void render(GraphicsContext gc) {
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }

    public abstract boolean collision(Entity e);

    public abstract void update();

    public abstract void move();
}
