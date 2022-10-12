package com.example.bomberm4n.Entities.Enemy;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Auto.random;
import com.example.bomberm4n.Entities.Bomb.Flame;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.image.Image;

public class Ballon extends Enemy {
    private int animate = 0;

    private int finalAnimation = 40;

    public Ballon(int x, int y, Image img, Map map, int speed) {
        super(x, y, img, map, speed);
        ai = new random();
        timeAfterKill = 20;
    }

    @Override
    public void kill() {
        if (!alive) return;
        alive = false;
        getMap().addPoint(POINT);
    }

    @Override
    public void afterKill() {
        if (timeAfterKill > 0) {
            setImg(Sprite.balloom_dead.getFxImage());
            animate = 0;
            timeAfterKill--;
        } else {
            if (finalAnimation > 0) {
                setImg(Sprite.Animation(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60).getFxImage());
                finalAnimation--;
            } else {
   //             Game.scorePlus.setText("");
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

    public void animation() {
        if (animate < 7500) {
            animate ++;
        } else {
            animate = 0;
        }
        if (alive) {
            if (direction == RIGHT) {
                setImg(Sprite.Animation(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 30).getFxImage());
            } else if (direction == LEFT) {
                setImg(Sprite.Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 30).getFxImage());
            } else if (direction == UP) {
                setImg(Sprite.Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 30).getFxImage());
            } else if (direction == DOWN) {
                setImg(Sprite.Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 30).getFxImage());
            } else {
                setImg(Sprite.balloom_left1.getFxImage());
            }
        }
    }
}
