package com.example.bomberm4n.Entities.Block.Items;

import com.almasb.fxgl.audio.Sound;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;
import javafx.scene.image.Image;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Player && !isPoweredUp())
        {
           // Sound.playSound("poweredUp");
            int newRate = getMap().getBomber().getBombRate() + 1;
            getMap().getBomber().setBombRate(newRate);
            Map.BOMB_AT_TIME++;
            setPoweredUp(true);
            remove = true;
        }
        return false;
    }
}