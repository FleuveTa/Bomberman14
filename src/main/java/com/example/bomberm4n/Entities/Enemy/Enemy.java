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

    public void move() {
        if (steps <= 0) {
            direction = ai.getDirection();;
            steps = MAX_STEPS;
        }
        if (direction == RIGHT) {
            int tx = (int) (speed + getBoundary().getX() + getBoundary().getWidth()) / Sprite.SCALED_SIZE;
            if (!(getMap().getEntityAt(tx, (int) (getBoundary().getY()) / Sprite.SCALED_SIZE, this).collision(this))
                    && !(getMap().getEntityAt(tx, (int) (getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE, this).collision(this))) {
                x += speed;
                steps -= 1;
            } else {
                x = (int) (tx * Sprite.SCALED_SIZE - getBoundary().getWidth() - 2 - 1);
                steps = 0;
            }
        } else if (direction == LEFT) {
            int tx = (int) (-speed + getBoundary().getX()) / Sprite.SCALED_SIZE;
            if (!(getMap().getEntityAt(tx, (int) (getBoundary().getY()) / Sprite.SCALED_SIZE, this).collision(this))
                    && !(getMap().getEntityAt(tx, (int) (getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE, this).collision(this))) {
                x -= speed;
                steps -= 1;
            } else {
                x = (tx * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE - 2);
                steps = 0;
            }
        } else if (direction == UP) {
            int ty = (int) (- speed + getBoundary().getY()) / Sprite.SCALED_SIZE;
            if (!(getMap().getEntityAt((int) getBoundary().getX() / Sprite.SCALED_SIZE, ty, this).collision(this))
                    && !(getMap().getEntityAt((int) (getBoundary().getX() + getBoundary().getWidth()) / Sprite.SCALED_SIZE, ty, this).collision(this))) {
                y -= speed;
                steps -= 1;
            } else {
                y = (int) (ty * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE - 2);
                steps = 0;
            }
        } else if (direction == DOWN) {
            int ty = (int) (speed + getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE;
            if (!(getMap().getEntityAt((int) getBoundary().getX() / Sprite.SCALED_SIZE, ty, this).collision(this))
                    && !(getMap().getEntityAt((int) (getBoundary().getX() + getBoundary().getWidth()) / Sprite.SCALED_SIZE, ty, this).collision(this))) {
                y += speed;
                steps -= 1;
            } else {
                y = (int) (ty * Sprite.SCALED_SIZE - getBoundary().getHeight() - 2 - 1);
                steps = 0;
            }
        } else {
            steps = 0;
        }
    }
}
