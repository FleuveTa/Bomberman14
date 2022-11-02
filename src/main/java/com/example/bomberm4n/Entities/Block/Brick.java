package com.example.bomberm4n.Entities.Block;

import com.example.bomberm4n.Entities.Enemy.Kondoria;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Bomb.Flame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Brick extends Tile {
    public static ArrayList<Integer> brokenBrickX = new ArrayList<>();
    public static ArrayList<Integer> brokenBrickY = new ArrayList<>();

    private int animate = 0;

    private boolean isBroken = false;

    private int timeAfter = 20;

    private Tile under; // Các item khi phá Brick sẽ hiện ra

    public Brick(int x, int y, Image img, Map map, Tile under) {
        super(x, y, img, map);
        this.under = under;
    }

    public static void addBrokenBrickX(int x) {
        brokenBrickX.add(x);
    }

    public static void addBrokenBrickY(int y) {
        brokenBrickY.add(y);
    }

    @Override
    public void update() {
        if (isBroken) {
            if (animate < 7500) {
                animate++;
            } else {
                animate = 0;
            }
            this.setImg(Sprite.Animation(Sprite.brick_exploded, Sprite.brick_exploded1
                    , Sprite.brick_exploded2, animate, 30).getFxImage());
            if (timeAfter > 0) {
                timeAfter--;
            } else {
                remove = true;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        under.render(gc);
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Flame) {
            addBrokenBrickX(this.x / Sprite.SCALED_SIZE);
            addBrokenBrickY(this.y / Sprite.SCALED_SIZE);
            isBroken = true;
        }
        return !(e instanceof Kondoria);
    }

    public Tile getUnder() {
        return under;
    }
}
