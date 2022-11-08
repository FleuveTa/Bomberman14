package com.example.bomberm4n.Entities.Auto;

import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.GameControl.Map;
import com.example.bomberm4n.Entities.Player;
import java.util.ArrayList;
import java.util.Arrays;

public class avoid extends AI {

    protected Player player;
    protected Enemy enemy;
    protected Map map;
    int r;

    public avoid(Map map, Enemy e) {
        this.player = map.getBomber();
        this.enemy = e;
        this.map = map;
    }

    public int calCol() {
        if(player.getXTile() < enemy.getXTile())
            return 2;
        else if(player.getXTile() > enemy.getXTile())
            return 1;
        return -1;
    }
    public int calRow() {
        if(player.getYTile() < enemy.getYTile())
            return 3;
        else if(player.getYTile() > enemy.getYTile())
            return 4;
        return -1;
    }

    public int detectBombinRanger(int  Xb  ,int  Yb ){
        r = player.getFlameLength();
        int Xe = this.enemy.getXTile();
        int Ye = this.enemy.getYTile();
        if ( Yb == Ye ){
            if ( (Xb- Xe) > 0 && ( Xb - Xe ) <= r + 1 ){
                return 0;
            }
            if( (Xe- Xb) > 0 && ( Xe - Xb ) <= r + 1 ){
                return 4;
            }
        }
        if  ( Xb == Xe  ){

            if ( (Yb- Ye) > 0 && ( Yb - Ye ) <= r + 1 ){
                return 6;
            }
            if( (Ye- Yb) > 0 && ( Ye - Yb ) <= r + 1 ){
                return 2;
            }
        }

        if ( (Ye - Yb > 0 ) && (Ye - Yb <= r + 1 )  ){
            if ( (Xb - Xe) > 0 && ( Xb- Xe) <= r + 1 ){
                return 1;
            }
            if ( (Xe - Xb) > 0 && ( Xe- Xb) <= r + 1 ){
                return 3;
            }
        }
        if ( (Yb - Ye > 0) && ( Yb - Ye <= r + 1) ){
            if ( (Xb - Xe) > 0 && ( Xb- Xe) <= r + 1){
                return 7;
            }
            if ( (Xe - Xb) > 0 && ( Xe- Xb) <= r + 1){
                return 5;
            }
        }
        if (Xe == Xb && Yb == Ye) {
            return 8;
        }
        return -1;
    }

    @Override
    public int getDirection() {
        int Xe = this.enemy.getXTile();
        int Ye = this.enemy.getYTile();
        boolean[] canGo = {true, true,true,true,true};

        ArrayList<Integer> way = new ArrayList<Integer>();

        int thread =0;

        // duyet toan bo list bom cua bang
        for (int i = 0; i < this.map.getBombs().size() ; i++){
            // phát hiện bom
            int Xb = this.map.getBombs().get(i).getXTile();
            int Yb = this.map.getBombs().get(i).getYTile();

            // xét những quả bom trong miền xét
            if ( this.detectBombinRanger(Xb, Yb)!=-1 ){
                thread++;
                // tùy trường hợp thì sẽ xét các hướng KHÔNG THỂ ĐI
                //  chú ý 4 thay cho -1;
                switch (this.detectBombinRanger(Xb, Yb)) {
                    case 0 -> canGo[4] = canGo[0] = false;
                    case 1 -> canGo[0] = canGo[2] = false;
                    case 2 -> canGo[4] = canGo[2] = false;
                    case 3 -> canGo[1] = canGo[2] = false;
                    case 4 -> canGo[4] = canGo[1] = false;
                    case 5 -> canGo[3] = canGo[1] = false;
                    case 6 -> canGo[4] = canGo[3] = false;
                    case 7 -> canGo[0] = canGo[3] = false;
                    case 8 -> Arrays.fill(canGo, false);
                }
            }
        }
        for ( int k =0  ; k < canGo.length ; k++){
            if (canGo[k]) {
                if ( k == 4 ){
                    way.add(-1); // chuyển 4 là -1
                }
                else{
                    way.add(k+1);
                }

            }
        }
        // nếu ko có nguy  hiểm
        if ( thread == 0 ){
            //  return -1;

            int vertical = random.nextInt(2);
            if(vertical == 1) {
                int v = calRow();
                if(v != -1)
                    return v;
                else
                    return calCol();

            } else {
                int h = calCol();

                if(h != -1)
                    return h;
                else
                    return calRow();
            }
        }

        // trường hợp không có đường đi hợp lý
        if (way.size() == 0 ) {
            return -1;
        }
        // tồn tại đường duy nhất
        if (way.size() == 1){
            return way.get(0);
        }
        return way.get(random.nextInt(way.size()));
    }
}
