package com.example.bomberm4n.Entities.Auto;

import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Player;

public class follow extends AI{

    protected  Player player;
    protected  Enemy enemy;
    protected  Map map;

    public follow(Map map, Enemy enemy) {
        player = map.getBomber();
        this.enemy = enemy;
        this.map = map;
    }

    @Override
    public int getDirection() {
         return findDirection();
    }

    public int findDirection() {
        if (AI.getDistance(enemy, player) > 5) {
            return 1 + random.nextInt(4);
        } else {
            int ver = random.nextInt(2);
        if(ver == 1) {
            int v = RowDirec();
            if(v != -1) {
                if (enemy.getYTile() % 2 == 0) {
                    enemy.setSpeed(1);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE);
                } else {
                    enemy.setSpeed(2);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 2);
                }
                return v;
            }
            else {
                return ColDirec();
            }

        } else {
            int h = ColDirec();
            if(h != -1) {
                if(enemy.getXTile() % 2 == 0) {
                    enemy.setSpeed(1);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE);
                } else {
                    enemy.setSpeed(2);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 2);
                }
                return h;
            } else {
                return RowDirec();
            }
        }

    }}

    public int ColDirec() {
        if(player.getXTile() < enemy.getXTile())
            return 2;
        else if(player.getXTile() > enemy.getXTile())
            return 1;
        if(player.getYTile() < enemy.getYTile())
            return 3;
        else if(player.getYTile() > enemy.getYTile())
            return 4;
        return -1;
    }

    public  int RowDirec() {

        return -1;
    }
}
