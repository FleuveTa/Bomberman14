package com.example.bomberm4n.Entities;

import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Bomb.Boom;
import com.example.bomberm4n.Entities.Bomb.Flame;
import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Player extends Mobile {

    private int flameLength;  //Độ dài khi bom nổ

    private int bombAtTime; //Số lượng bom đặt được cùng lúc

    private int speed;

    private int animate = 0;

    private int live;

    public Player(int x, int y, Image img, Map map) {
        super(x, y, img, map);
        direction = -1;
        alive = true;
        live = 3;
        this.flameLength = Map.FLAME_LENGTH;
        this.bombAtTime = Map.BOMB_AT_TIME;
        this.speed = Map.PLAYER_SPEED;
    }

    public int getLive() {
        return live;
    }

    public int getTimeAfterKill() {
        return timeAfterKill;
    }


    @Override
    public void kill() {
        if (!alive) return;
        alive = false;
        live--;
    }

    @Override
    public void afterKill() {
        if (timeAfterKill > 0) {
            setImg(Sprite.Animation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 60).getFxImage());
            timeAfterKill--;
        } else {
            reset();
        }
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

    /** Hình chữ nhật bao ngoài player */
    @Override
    public Rectangle getBoundary() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE - 26, Sprite.SCALED_SIZE - 14);
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof Flame) {
            kill();
            return false;
        }
        if(e instanceof Enemy) {
            kill();
            return false;
        }
        return false;
    }

    /** test **/
    private void reset() {
        x = Sprite.SCALED_SIZE;
        y = Sprite.SCALED_SIZE;
        setImg(Sprite.player_right.getFxImage());
        speed = 2;
        bombAtTime = 1;
        flameLength = 1;
        Map.PLAYER_SPEED = 3;
        Map.BOMB_AT_TIME = 1;
        Map.FLAME_LENGTH = 1;
        alive = true;
        timeAfterKill = 40;
        direction = -1;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public void setBombRate(int bombRate) {
        this.bombAtTime = bombRate;
    }

    public int getBombRate() {
        return bombAtTime;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void animation() {
        if (animate < 7500) {
            animate ++;
        } else {
            animate = 0;
        }
        if (alive) {
            if (direction == UP) {
                setImg(Sprite.Animation(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 30).getFxImage());
            } else if (direction == DOWN) {
                setImg(Sprite.Animation(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 30).getFxImage());
            } else if (direction == LEFT) {
                setImg(Sprite.Animation(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 30).getFxImage());
            } else if (direction == RIGHT) {
                setImg(Sprite.Animation(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 30).getFxImage());
            }
        }
    }

    public boolean canGoRight() {
        int px = (int) (speed + getBoundary().getX()) / Sprite.SCALED_SIZE;
        return !(getMap().getEntityAt(px, (int) (getBoundary().getY()) / Sprite.SCALED_SIZE, this).collision(this))
                && !(getMap().getEntityAt(px, (int) (getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE , this).collision(this));
    }

    public boolean canGoLeft() {
        int px = (int) (-speed + getBoundary().getX()) / Sprite.SCALED_SIZE;
        return !(getMap().getEntityAt(px, (int) (getBoundary().getY()) / Sprite.SCALED_SIZE, this).collision(this))
                && !(getMap().getEntityAt(px, (int) (getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE , this).collision(this));
    }

    public boolean canGoUp() {
        int py = (int) (- speed + getBoundary().getY()) / Sprite.SCALED_SIZE;
        return !(getMap().getEntityAt((int) getBoundary().getX() / Sprite.SCALED_SIZE, py, this).collision(this))
                && !(getMap().getEntityAt((int) (getBoundary().getX() + getBoundary().getWidth()) / Sprite.SCALED_SIZE, py, this).collision(this));
    }

    public boolean canGoDown() {
        int py = (int) (speed + getBoundary().getY() + getBoundary().getHeight()) / Sprite.SCALED_SIZE;
        return !(getMap().getEntityAt((int) getBoundary().getX() / Sprite.SCALED_SIZE, py, this).collision(this))
                && !(getMap().getEntityAt((int) (getBoundary().getX() + getBoundary().getWidth()) / Sprite.SCALED_SIZE, py, this).collision(this));
    }
    @Override
    public void move() {
        if (direction == RIGHT) {
            if (canGoRight()) {
                x += speed;
            } else {
                return;
            }
        } else if (direction == LEFT) {
            if (canGoLeft()) {
                x -= speed;
            } else {
                return;
            }
        } else if (direction == UP) {
            if (canGoUp()) {
                y -= speed;
            } else {
                return;
            }
        } else if (direction == DOWN) {
            if (canGoDown()) {
                y += speed;
            } else {
                return;
            }
        }
    }

    /** Di chuyển player bằng bàn phím */
    public void eventHandler(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (event.getCode()) {
                case W -> {
                    direction = UP;
                    break;
                }
                case S -> {
                    direction = DOWN;
                    break;
                }
                case A -> {
                    direction = LEFT;
                    break;
                }
                case D -> {
                    direction = RIGHT;
                    break;
                }
                case SPACE -> {
                    int xB = (int) ((double) x) / Sprite.SCALED_SIZE;
                    int yB = (int) ((double) y) / Sprite.SCALED_SIZE;
                    if (!(getMap().getBombs().size() == bombAtTime)) {
                        getMap().addBomb(new Boom(xB * Sprite.SCALED_SIZE, yB * Sprite.SCALED_SIZE, Sprite.bomb.getFxImage(), getMap()));
                    }
                    break;
                }
            }
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            switch (event.getCode()) {
                case W -> {
                    direction = -1;
                    setImg(Sprite.player_up.getFxImage());
                    break;
                }
                case S -> {
                    direction = -1;
                    setImg(Sprite.player_down.getFxImage());
                    break;
                }
                case A -> {
                    direction = -1;
                    setImg(Sprite.player_left.getFxImage());
                    break;
                }
                case D -> {
                    direction = -1;
                    setImg(Sprite.player_right.getFxImage());
                    break;
                }
            }
        }
    }
}
