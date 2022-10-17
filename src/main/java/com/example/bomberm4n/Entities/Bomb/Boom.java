package com.example.bomberm4n.Entities.Bomb;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Mobile;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Boom extends Entity {
    private boolean isExploded = false;
    private int timeToExplode = 150;
    private int timeAfter = 20;
    private int animate;
    private final List<Flame> flames = new ArrayList<>();
    private boolean canPass = true;

    public Boom(int xUnit, int yUnit, Image img, Map map) {
        super(xUnit, yUnit, img, map);
    }

    @Override
    public void update() {
        if (animate < 7500) {
            animate ++;
        } else {
            animate = 0;
        }
        if (timeToExplode > 0) {
            timeToExplode--;
            this.setImg(Sprite.Animation(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 30).getFxImage());
        }
        else {
            if(!isExploded) {
                canPass = true;
                isExploded = true;
                Mobile a = getMap().getMobileAtExcluding(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, null);
                if (a != null)  {
                    a.kill();
                }
                addFlame();
            }
            else {
                flames.forEach(Flame::update);
                this.setImg(Sprite.Animation(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 30).getFxImage());
            }
            if (timeAfter > 0) {
                timeAfter--;
            } else {
                remove = true;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        flames.forEach(g -> g.render(gc));
        int xOffset = getMap().getCamera().getX();
        int yOffset = getMap().getCamera().getY();
        gc.drawImage(img, x - xOffset, y - yOffset + BomGame.GAME_OFFSET );
    }

    @Override
    public boolean collision(Entity e) {
//        if (e instanceof Player) {
//            int diffX = e.getX() - this.getX();
//            int diffY = e.getY() - this.getY();
//            if (!(diffX > -e.getBoundary().getWidth() - 6 - 1 && diffX < Sprite.SCALED_SIZE - 6
//                    && diffY > -e.getBoundary().getHeight() - 6 - 1 && diffY < Sprite.SCALED_SIZE - 6)) {
//                canPass = true;
//            }
//            return canPass;
//        }
        if (e instanceof Flame) {
            timeToExplode = 0;
        }
//        if (e instanceof Boom) {
//            if (((Boom) e).isExploded) {
//                timeToExplode = 0;
//            }
//        }
        return true;
    }

    public void addFlame() {
        int length = getMap().getBomber().getFlameLength();
        for (int i = 1; i <= length; i++) {
            if (i == length) {
                Entity e = getMap().getEntityAt((x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE , y / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x + Sprite.SCALED_SIZE * i, y,
                        Sprite.explosion_horizontal_right_last.getFxImage(), getMap(), Flame.RIGHT, true);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            } else {
                Entity e = getMap().getEntityAt((x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE , y / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x + Sprite.SCALED_SIZE * i, y,
                        Sprite.explosion_horizontal.getFxImage(), getMap(), Flame.RIGHT, false);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i <= length; i++) {
            if (i == length) {
                Entity e = getMap().getEntityAt((x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE , y / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x - Sprite.SCALED_SIZE * i, y,
                        Sprite.explosion_horizontal_left_last.getFxImage(), getMap(), Flame.LEFT, true);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            } else {
                Entity e = getMap().getEntityAt((x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE , y / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x - Sprite.SCALED_SIZE * i, y,
                        Sprite.explosion_horizontal.getFxImage(), getMap(), Flame.LEFT, false);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i <= length; i++) {
            if (i == length) {
                Entity e = getMap().getEntityAt(x / Sprite.SCALED_SIZE , (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x, y + Sprite.SCALED_SIZE * i,
                        Sprite.explosion_vertical_down_last.getFxImage(), getMap(), Flame.DOWN, true);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            } else {
                Entity e = getMap().getEntityAt(x / Sprite.SCALED_SIZE , (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x, y + Sprite.SCALED_SIZE * i,
                        Sprite.explosion_vertical.getFxImage(), getMap(), Flame.DOWN, false);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i <= length; i++) {
            if (i == length) {
                Entity e = getMap().getEntityAt(x / Sprite.SCALED_SIZE , (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x, y - Sprite.SCALED_SIZE * i,
                        Sprite.explosion_vertical_top_last.getFxImage(), getMap(), Flame.UP, true);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            } else {
                Entity e = getMap().getEntityAt(x / Sprite.SCALED_SIZE , (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, null);
                Flame flame = new Flame(x, y - Sprite.SCALED_SIZE * i,
                        Sprite.explosion_vertical.getFxImage(), getMap(), Flame.UP, false);
                if (!e.collision(flame)) {
                    flames.add(flame);
                } else {
                    break;
                }
            }
        }
    }

    public Flame flameAt(int x, int y) {
        for (Flame flame : flames) {
            if (flame.getX() == x && flame.getY() == y)
                return flame;
        }
        return null;
    }
}
