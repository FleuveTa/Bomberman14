package com.example.bomberm4n.Entities.Enemy;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Auto.AI;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Mobile;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public abstract class Enemy extends Mobile {
    protected int speed;

    protected  int MAX_STEPS;

    protected AI ai;

    protected int steps;

    public static final int POINT = 10;

    public Enemy(int x, int y, Image img, Map map, int speed) {
        super(x, y, img, map);
        this.speed = speed;
        MAX_STEPS = (Sprite.SCALED_SIZE / speed);
        steps = MAX_STEPS;
    }

    public static int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            return min + rn.nextInt(range);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMAX_STEPS(int MAX_STEPS) {
        this.MAX_STEPS = MAX_STEPS;
    }

    public Rectangle getBoundary() {
        return new Rectangle(x + 2, y + 2, Sprite.SCALED_SIZE - 4, Sprite.SCALED_SIZE - 4);
    }

    public abstract void kill();

    public abstract void afterKill();

    public void render(GraphicsContext gc) {
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }

    public abstract boolean collision(Entity e);

    public abstract void update();

    public abstract void move();
}
