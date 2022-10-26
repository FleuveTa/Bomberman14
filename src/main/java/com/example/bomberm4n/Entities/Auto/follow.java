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
        if(player == null)
            return random.nextInt(4);

        int ver = random.nextInt(2);
        if(ver == 1) {
            int v = RowDirec();
            if(v != -1) {
                if (enemy.getYTile() % 2 == 0) {
                    enemy.setSpeed(2);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 2);
                } else {
                    enemy.setSpeed(4);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 4);
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
                    enemy.setSpeed(2);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 2);
                } else {
                    enemy.setSpeed(4);
                    enemy.setMAX_STEPS(Sprite.SCALED_SIZE / 4);
                }
                return h;
            } else {
                return RowDirec();
            }
        }

    }

    public int ColDirec() {
        if(player.getXTile() < enemy.getXTile())
            return 1;
        else if(player.getXTile() > enemy.getXTile())
            return 0;
        return -1;
    }

    public  int RowDirec() {
        if(player.getYTile() < enemy.getYTile())
            return 2;
        else if(player.getYTile() > enemy.getYTile())
            return 3;
        return -1;
    }
}
