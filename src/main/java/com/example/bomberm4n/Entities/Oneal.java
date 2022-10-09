package com.example.bomberm4n.Entities;

import javafx.scene.image.Image;

public class Oneal extends Entity {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        y++;
    }
}
