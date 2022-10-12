package com.example.bomberm4n.GameControl;

import com.example.bomberm4n.Entities.Block.Tile;
import com.example.bomberm4n.Entities.Block.*;
import com.example.bomberm4n.Entities.Enemy.Ballon;
import com.example.bomberm4n.Entities.Player;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Level {

    public static final int MAX_LEVEL = 4;

    private int width, height;

    private int level;

    private char [][] mapMatrix;

    private Map map;

    private Tile[][] tiles;

    public Level(Map map, int level) {
        this.level = level;
        this.map = map;
        loadFromFile();
    }

    public void loadFromFile() {
        try {
            String path = "src/main/resources/maptext/map" + level + ".txt";
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = buffReader.readLine().trim();
            String[] tokens = line.split(" ");
            width = Integer.parseInt(tokens[2]);
            height = Integer.parseInt(tokens[1]);
            mapMatrix = new char[height][width];
            tiles = new Tile[height][width];

            for (int i = 0; i < height; i++) {
                line = buffReader.readLine();
                if (line != null) {
                    for (int j = 0; j < width; j++) {
                        mapMatrix[i][j] = line.charAt(j);
                    }
                }
            }
            fileReader.close();
            buffReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void createMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (mapMatrix[i][j]) {
                    case '#': {
                        tiles[i][j] = new Wall(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.wall.getFxImage(), map);
                        break;
                    }
                    case 'p': {
                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), map);
                        map.addMobile(new Player(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.player_right.getFxImage(), map));
                        break;
                    }
                    case '*': {
                        tiles[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), map,
                                new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), map));
                        break;
                    }
//                    case 'x': {
//                        tiles[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
//                                new Portal(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.portal.getFxImage(), board));
//                        break;
//                    }
//                    case 'f': {
//                        tiles[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
//                                new FlameItem(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.powerup_flames.getFxImage(), board));
//                        break;
//                    }
//                    case 'b': {
//                        tiles[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
//                                new BombItem(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.powerup_bombs.getFxImage(), board));
//                        break;
//                    }
//                    case 's': {
//                        tiles[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
//                                new SpeedItem(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.powerup_speed.getFxImage(), board));
//                        break;
//                    }
                    case '1': {
                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), map);
                        map.addMobile(new Ballon(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage(), map, 1));
                        break;
                    }
//                    case '2': {
//                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), board);
//                        board.addCharacter(new Oneal(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage(), board, 2));
//                        break;
//                    }
//                    case '3': {
//                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), board);
//                        board.addCharacter(new Kondoria(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage(), board, 1));
//                        break;
//                    }
//                    case '4': {
//                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), board);
//                        board.addCharacter(new Doll(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage(), board, 2));
//                        break;
//                    }
//                    case '5': {
//                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), board);
//                        board.addCharacter(new Pontan(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.pontan_left1.getFxImage(), board, 2));
//                        break;
//                    }
                    default: {
                        tiles[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), map);
                        break;
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public int getRealWidth() {
        return width * Sprite.SCALED_SIZE;
    }

    public int getRealHeight() {
        return height * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j].render(gc);
            }
        }
    }

    public void update() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j] instanceof Brick) {
                    if (((Brick) tiles[i][j]).isRemove()) {
                        tiles[i][j] = ((Brick) tiles[i][j]).getUnder();
                    }
                }
                if (tiles[i][j] instanceof Item) {
                    if (((Item) tiles[i][j]).isRemove()) {
                        tiles[i][j] = new Grass(tiles[i][j].getX(), tiles[i][j].getY(), Sprite.grass.getFxImage(), map);
                    }
                }
                tiles[i][j].update();
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) {
            return null;
        }
        return tiles[y][x];
    }

    public char[][] getMaps() {
        return mapMatrix;
    }
}
