package com.example.bomberm4n.Entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Player extends Entity {

    public boolean canPut = true;
    private LocalDateTime timePutStart;
    private LocalDateTime timePutStop;
    private int speed = 10;

    public Player(int x, int y, Image image) throws URISyntaxException {
        super(x, y, image);
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveRight() {
        this.setLocation(x + speed, y);
    }
    public void moveDown() {
        this.setLocation(x, y + speed);
    }
    public void moveLeft() {
        this.setLocation(x - speed, y);
    }
    public void moveUp() {
        this.setLocation(x, y - speed);
    }


    public void putBomCheck() {
        if (!canPut) {
            timePutStop = LocalDateTime.now();
            if (Duration.between(timePutStart, timePutStop).toMillis() > 250) {
                canPut = true;
            }
        }
    }


    @Override
    public void update() {}
}
