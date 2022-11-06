package com.example.bomberm4n.Entities.Block;

import com.example.bomberm4n.Entities.Enemy.Kondoria;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Tile {
    public Wall(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public void update() {}
    @Override
    public boolean collision(Entity e) {
        return true;
    }
}
