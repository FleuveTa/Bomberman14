package com.example.bomberm4n.Entities.Enemy;

import com.example.bomberm4n.Constants.Constants;
import com.example.bomberm4n.Entities.Auto.follow;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Auto.random;
import com.example.bomberm4n.Entities.Bomb.Flame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.GameSound;
import javafx.scene.image.Image;

public class Oneal extends Enemy {

    private int animate = 0;

    private int finalAnimation = 40;
    public Oneal(int x, int y, Image img, Map map, int speed) {
        super(x, y ,img, map, speed);
        ai = new follow(map, this);
        timeAfterKill = 20;
    }

    @Override
    public void kill() {
        if(!alive) return;
        alive = false;
        getMap().addPoint(POINT);
        GameSound.playEffectSound(Constants.SOUND_URL[3]);
    }

    @Override
    public void afterKill() {
        if(timeAfterKill > 0) {
            setImg(Sprite.oneal_dead.getFxImage());
            animate = 0;
            timeAfterKill--;
        } else {
            if (finalAnimation > 0) {
                setImg(Sprite.Animation(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60). getFxImage());
                finalAnimation--;
            } else {
                remove();
            }
        }

    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Flame) {
            kill();
            return false;
        }
        if (e instanceof Player) {
            ((Player) e).kill();
            return false;
        }
        return false;
    }

    @Override
    public void update() {
        if (alive) {
            move();
        } else {
            afterKill();
        }
        animation();
    }

    public void animation() {
        if (animate < 7500) {
        animate ++;
    } else {
        animate = 0;
    }
        if (alive) {
            if (direction == RIGHT) {
                setImg(Sprite.Animation(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 30).getFxImage());
            } else if (direction == LEFT) {
                setImg(Sprite.Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 30).getFxImage());
            } else if (direction == UP) {
                setImg(Sprite.Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 30).getFxImage());
            } else if (direction == DOWN) {
                setImg(Sprite.Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 30).getFxImage());
            } else {
                setImg(Sprite.oneal_left1.getFxImage());
            }
        }
    }
}
