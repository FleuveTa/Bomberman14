package com.example.bomberm4n.Entities.Auto;

import com.example.bomberm4n.Entities.Block.Brick;
import com.example.bomberm4n.Entities.Enemy.Enemy;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.GameControl.Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs extends AI{
    protected Player player;
    protected Enemy enemy;
    protected Map m;

    public ArrayList<Integer> path = new ArrayList<>();
    public int height;
    public int width;

    public int[][] node;
    public int numberOfNode;
    public int[][] mapNumMatrix;

    public Bfs(Player player, Enemy e , Map m) {
        this.player = player;
        this.enemy = e;
        this.m = m;
        height = m.getLevel().getHeight();
        width = m.getLevel().getWidth();
        node = new int[height*width][4];
        numberOfNode = height * width;
        mapNumMatrix = new int[height][width];
        getNumMatrix();
    }

    public void getNumMatrix() {
        int num = 1;
        char [][]map = m.getLevel().getMaps();
        for (int i = 0 ; i < height ; i++) {
            for ( int j = 0 ; j < width ; j++) {
                if (map[i][j] == '#') {
                    mapNumMatrix[i][j] = 0;
                }
                else if (map[i][j] =='*' || map[i][j] == 'x' || map[i][j] == 'f' || map[i][j] == 'b' || map[i][j] == 's') {
                    mapNumMatrix[i][j] = num * (-1);
                    num++;
                } else {
                    this.mapNumMatrix[i][j] = num;
                    num++;
                }
            }
        }
    }

    public void updateDestroy_Brick() {
        if (Brick.brokenBrickX.isEmpty()) return;
        for (int i = 0; i < Brick.brokenBrickX.size(); i++){
            int x = Brick.brokenBrickX.get(i);
            int y = Brick.brokenBrickY.get(i);
            if (mapNumMatrix [y][x] < 0){
                mapNumMatrix [y][x] =  mapNumMatrix [y][x] * (-1);
            }
        }
    }

    /** 4 node xung quanh node hiện tại
     * Nếu âm ~ không đi được thì cho về 0
     * */
    public void convertNearNodeMatrix() {
        for (int i = 1; i < height - 1; i++) {
            for ( int j = 1; j < width - 1; j++ ) {
                if (this.mapNumMatrix[i][j] > 0) {
                    this.node[this.mapNumMatrix[i][j]][0] = Math.max(this.mapNumMatrix[i][j - 1], 0);
                    this.node[this.mapNumMatrix[i][j]][1] = Math.max(this.mapNumMatrix[i][j + 1], 0);
                    this.node[this.mapNumMatrix[i][j]][2] = Math.max(this.mapNumMatrix[i - 1][j], 0);
                    this.node[this.mapNumMatrix[i][j]][3] = Math.max(this.mapNumMatrix[i + 1][j], 0);
                }
            }
        }
    }

    public void updateDetectBomMatrix(){
        int r = player.getFlameLength();
        int xe = this.enemy.getXTile();
        int ye = this.enemy.getYTile();
        for (int i = 0; i  < m.getBombs().size(); i++) {
            int xt =  m.getBombs().get(i).getXTile();
            int yt =  m.getBombs().get(i).getYTile();
            this.mapNumMatrix[yt][xt] *= -1;
            for (int j = 1; j <= r; j++) {
                if (this.mapNumMatrix[yt][xt + j] > 0 && yt != ye && xt + j != xe) {
                    this.mapNumMatrix[yt][xt + j] *= -1;
                } else {
                    break;
                }
            }
            for (int j = 1; j <= r; j++) {
                if (this.mapNumMatrix[yt][xt - j] > 0 && yt != ye && xt - j != xe) {
                    this.mapNumMatrix[yt][xt - j] *= -1;
                } else {
                    break;
                }
            }
            for (int j = 1; j <= r; j++) {
                if ( this.mapNumMatrix[yt + j][xt] > 0  && yt + j != ye && xt != xe ) {
                    this.mapNumMatrix[yt + j][xt] *= -1;
                } else {
                    break;
                }
            }
            for (int j = 1; j <= r; j++){
                if (this.mapNumMatrix[yt - j][xt] > 0 && yt - j != ye && xt != xe) {
                    this.mapNumMatrix[yt - j][xt] *= -1;
                } else {
                    break;
                }
            }
        }
    }

    public int bfs(int start , int end) throws IllegalStateException {
        Queue<Integer> qNode = new LinkedList<Integer>();
        int[] parent = new int[numberOfNode + 1];
        boolean[] visited = new boolean[numberOfNode + 1];
        if (start < 0) start *= -1;
        if (end < 0) end *= -1;
        visited[start] = false;
        parent[start] = -1;
        parent[end] = -1;
        qNode.add(start);
        while (!qNode.isEmpty()) {
            int currentNode = qNode.poll();
            for (int i = 0; i < 4; i++) {
                if (!visited[node[currentNode][i]] && node[currentNode][i]!=0) {
                    visited[node[currentNode][i]] = true;
                    parent[node[currentNode][i]] = currentNode;
                    qNode.add(node[currentNode][i]);
                }
            }
        }
        int p = parent[end];
        if (p != -1) {
            path.add(end);
            path.add(p);
            while (p != start){
                p = parent[p];
                path.add(p);
            }
            return path.get(path.size()-2);
        }
        return -1;
    }

    public int getDirection() {
        getNumMatrix();
        updateDetectBomMatrix();
        updateDestroy_Brick();
        convertNearNodeMatrix();
        int start = this.mapNumMatrix[enemy.getYTile()][enemy.getXTile()];
        int end = this.mapNumMatrix[player.getYTile()][player.getXTile()];
        int result = this.bfs(start, end);
        // no way
        if (result == -1) {
            return 1 + random.nextInt(4);
        }
        if (result - start == 1) return 1;
        if (start -  result == 1) return 2;
        if (start > result) return 3;
        if (start < result) return 4;
        return -1;
    }
}
