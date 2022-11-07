package com.example.bomberm4n.Entities.Auto;

import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;

public class Distance {
    protected Enemy enemy;
    protected Player player;
    protected Map gameMap;

    public static int getDistance(Enemy enemy, Player player) {
        return (int) Math.sqrt(
                (enemy.getXTile() - player.getXTile())
                        * (enemy.getXTile() - player.getXTile())
                        + (enemy.getYTile() - player.getYTile())
                        * (enemy.getYTile() - player.getYTile()));
    }
}
