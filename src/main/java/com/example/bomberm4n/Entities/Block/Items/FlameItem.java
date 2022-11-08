package com.example.bomberm4n.Entities.Block.Items;

import com.example.bomberm4n.Constants.Constants;
import com.example.bomberm4n.Entities.Entity;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.scene.GameSound;
import javafx.scene.image.Image;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img, Map map) {
            super(x, y, img, map);
    }

    @Override
    public boolean collision(Entity e) {
        if (e instanceof Player)
        {
            GameSound.playEffectSound(Constants.SOUND_URL[7]);
            int newLength = getMap().getBomber().getFlameLength() + 1;
            getMap().getBomber().setFlameLength(newLength);
            Map.FLAME_LENGTH++;
            remove = true;
        }
        return false;
    }
}