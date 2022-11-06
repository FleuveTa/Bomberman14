package com.example.bomberm4n.Entities;

import com.example.bomberm4n.GameControl.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.shape.Rectangle;

public abstract class Entity {

    protected int x;

    protected int y;
    protected Image img;

    protected boolean remove = false;

    private final Map map;

    public Entity(int x, int y, Image img, Map map) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.map = map;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getYTile() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int getXTile() {
        return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public void remove() {
        remove = true;
    }

    public boolean isRemove() {
        return remove;
    }

    public Map getMap() {
        return map;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Rectangle getBoundary() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public abstract void render(GraphicsContext gc);

    public abstract boolean collision(Entity e);

    public abstract void update();
}
