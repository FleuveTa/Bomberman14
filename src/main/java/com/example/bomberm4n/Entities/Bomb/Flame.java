package com.example.bomberm4n.Entities.Bomb;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Flame extends Entity {

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    private final int direction;

    private boolean last = false;

    private int animate = 0;

    public Flame(int x, int y, Image img, Map map, int direction, boolean last) {
        super(x, y, img, map);
        this.direction = direction;
        this.last = last;
    }

    @Override
    public void render(GraphicsContext gc) {
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof  Flame) {
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        Entity e = getMap().getEntityAt(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, null);
        e.collision(this);
        animate();
    }

    public void animate() {
        if (animate < 7500) {
            animate ++;
        } else {
            animate = 0;
        }
        if (last) {
            if (direction == RIGHT) {
                this.setImg(Sprite.Animation(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        , Sprite.explosion_horizontal_right_last2, animate, 30).getFxImage());
            } else if (direction == LEFT) {
                this.setImg(Sprite.Animation(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        , Sprite.explosion_horizontal_left_last2, animate, 30).getFxImage());
            } else if (direction == DOWN) {
                this.setImg(Sprite.Animation(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        , Sprite.explosion_vertical_down_last2, animate, 30).getFxImage());
            } else if (direction == UP) {
                this.setImg(Sprite.Animation(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        , Sprite.explosion_vertical_top_last2, animate, 30).getFxImage());
            }
        } else {
            if (direction == RIGHT || direction == LEFT) {
                this.setImg(Sprite.Animation(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        , Sprite.explosion_horizontal2, animate, 30).getFxImage());
            } else if (direction == DOWN || direction == UP) {
                this.setImg(Sprite.Animation(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        , Sprite.explosion_vertical2, animate, 30).getFxImage());
            }
        }
    }
}