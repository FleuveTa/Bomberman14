package com.example.bomberm4n.Entities.Block.Items;

import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;
import javafx.scene.image.Image;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Player && !isPoweredUp())
        {
         //   Sound.playSound("poweredUp");
            int newSpeed = getMap().getBomber().getSpeed() + 1;
            getMap().getBomber().setSpeed(newSpeed);
            Map.PLAYER_SPEED += 40;
            setPoweredUp(true);
            remove = true;
        }
        return false;
    }
}
