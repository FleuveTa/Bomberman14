package com.example.bomberm4n.Entities.Auto;

import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Entities.Player;

import java.util.Random;

public abstract class AI {
    protected static Random random = new Random();
    protected Enemy enemy;
    protected Player player;

    public static int getDistance(Enemy enemy, Player player) {
        return (int) Math.sqrt(
                (enemy.getXTile() - player.getXTile())
                        * (enemy.getXTile() - player.getXTile())
                        + (enemy.getYTile() - player.getYTile())
                        * (enemy.getYTile() - player.getYTile()));
    }

    public abstract int getDirection();
}
