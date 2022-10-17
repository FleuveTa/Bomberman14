package com.example.bomberm4n.GameControl;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Entities.Bomb.Boom;
import com.example.bomberm4n.Entities.Bomb.Flame;
import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.GameControl.Camera;
import com.example.bomberm4n.Entities.Mobile;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Level;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {

    public static int BOMB_AT_TIME = 1;

    public static int PLAYER_SPEED = 2;

    public static int FLAME_LENGTH = 10;

    private List<Mobile> mobiles = new ArrayList<>();

    private List<Boom> booms = new ArrayList<>();

    private Level level;

    private int score;

    private int time;

    private Camera camera;

    private boolean isWin = false;

    public static final int DEFAULT_TIME = 180;

    public Map() {
        level = new Level(this,1);
        level.createMap();
        score = 0;
        time = DEFAULT_TIME * 60;
        camera = new Camera(this, 0,0);
    }

    public void nextLevel() {
        if (!(level.getLevel() + 1 > Level.MAX_LEVEL)) {
            clear();
            level = new Level(this, level.getLevel() + 1);
            BomGame.TIME_INIT = 0;
            level.createMap();
            time = DEFAULT_TIME * 60;
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public int getScore() {
        return score;
    }

    public Level getLevel() {
        return level;
    }

    public void addPoint(int point) {
        score += point;
    }

    public int getTime() {
        return time;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public void addMobile(Mobile mobile) {
        mobiles.add(mobile);
    }

    public void addBomb(Boom bomb) {
        booms.add(bomb);
    }

    public void render(GraphicsContext gc) {
        level.render(gc);
        booms.forEach(g -> g.render(gc));
        mobiles.forEach(g -> g.render(gc));
    }

    public void update() {
        for (int i = 0; i < booms.size(); i++) {
            booms.get(i).update();
            if (booms.get(i).isRemove()) {
                booms.remove(i);
                i--;
            }
        }
        for (int i = 0; i < mobiles.size(); i++) {
            mobiles.get(i).update();
            if (mobiles.get(i).isRemove()) {
                mobiles.remove(i);
                i--;
            }
        }
        level.update();
        countDown();
    }

    private void countDown() {
        if (time != 0) {
            time--;
        }
    }

    public Player getBomber() {
        Iterator<Mobile> itr = mobiles.iterator();
        Entity cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur instanceof Player) {
                return (Player) cur;
            }
        }
        return null;
    }

    public Mobile getMobileAtExcluding(int x, int y, Mobile a) {
        Iterator<Mobile> itr = mobiles.iterator();
        Mobile cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }
            if (cur.getXTile() == x && cur.getYTile() == y) {
                if (a instanceof Enemy && cur instanceof Enemy) {
                    return null;
                }
                return cur;
            }
        }
        return null;
    }

    public void clear() {
        mobiles.clear();
        booms.clear();
    }

    public List<Boom> getBombs() {
        return booms;
    }

    public Boom getBombAt(int x, int y) {
        Iterator<Boom> bs = booms.iterator();
        Boom b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == x * Sprite.SCALED_SIZE && b.getY() == y * Sprite.SCALED_SIZE) {
                return b;
            }
        }
        return null;
    }

    public Flame getFlameAt(int x, int y) {
        Iterator<Boom> bs = booms.iterator();
        Boom b;
        while(bs.hasNext()) {
            b = bs.next();
            Flame e = b.flameAt(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
            if(e != null) {
                return e;
            }
        }
        return null;
    }

    public Entity getEntityAt(int x, int y, Mobile except) {
        Entity e = null;
        e = getBombAt(x, y);
        if (e != null) {
            return e;
        }
        e = getMobileAtExcluding(x, y, except);
        if (e != null) {
            return e;
        }
        e = level.getTileAt(x, y);
        return e;
    }

    public boolean hasEnemies() {
        for (Mobile mobile : mobiles) {
            if (mobile instanceof Enemy) return true;
        }
        return false;
    }

}
