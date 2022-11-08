package com.example.bomberm4n.Entities.Block.Items;

import com.example.bomberm4n.Constants.Constants;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.scene.GameSound;
import javafx.scene.image.Image;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img, Map map) {
        super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Player)
        {
            GameSound.playEffectSound(Constants.SOUND_URL[7]);
            int newSpeed = getMap().getBomber().getSpeed() + 1;
            getMap().getBomber().setSpeed(newSpeed);
            remove = true;
        }
        return false;
    }
}
