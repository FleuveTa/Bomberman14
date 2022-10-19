package com.example.bomberm4n.Entities.Block.Items;

import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;
import javafx.scene.image.Image;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img, Map map) {
            super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Player && !isPoweredUp())
        {
          //  Sound.playSound("poweredUp");
            int newLength = getMap().getBomber().getFlameLength() + 1;
            getMap().getBomber().setFlameLength(newLength);
            Map.FLAME_LENGTH++;
            setPoweredUp(true);
            remove = true;
        }
        return false;
    }
}