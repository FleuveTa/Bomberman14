package com.example.bomberm4n;

import com.example.bomberm4n.Entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.fxml.FXML;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BomGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public Scene scene;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private static char[][] mapMatrix = null;

    // Dùng để gọi các ảnh không cắt ra từ sheet
    Sprite tile = new Sprite(Sprite.DEFAULT_SIZE);


    public static void main(String[] args) {
        Application.launch(BomGame.class);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException, IOException {
        // Tao Canvas
        canvas = new Canvas( Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        Player bomberman = new Player(1, 1, Sprite.player_down_1.getFxImage());
        //move
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W -> {
                        bomberman.moveUp();
                    }
                    case S -> {
                        bomberman.moveDown();
                    }
                    case A -> {
                        bomberman.moveLeft();
                    }
                    case D -> {
                        bomberman.moveRight();
                    }
                    case SPACE -> {
                        if (bomberman.canPut) {
                        bomberman.putBomCheck();
                        Entity object = new Boom(bomberman.getX()/Sprite.SCALED_SIZE,bomberman.getY()/Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                        stillObjects.add(object);
                        }
                    }
                }
            }
        });

        // Add scene vao stage
        stage.setScene(scene);
        stage.setTitle("Ver 1.0");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        entities.add(bomberman);
    }

    public void createMap() throws FileNotFoundException, IOException {
       try {
           Reader reader = new FileReader("src/main/resources/maptext/map1.txt");
           BufferedReader bufferedReader = new BufferedReader(reader);
           String Fline = bufferedReader.readLine();
           String [] tokens = Fline.split("\\s");
           int level = Integer.parseInt(tokens[0]);
           int row = Integer.parseInt(tokens[1]);
           int column = Integer.parseInt(tokens[2]);
           mapMatrix = new char[row][column];

           for (int i = 0; i < row; i++) {
               String s = bufferedReader.readLine();
               for (int j = 0; j < column; j++) {
                   char x = s.charAt(j);
                   mapMatrix[i][j] = x;
           }
       }
           for (int i = 0; i < row; i++) {
               for (int j = 0; j < column; j++) {
                   char mapChar = mapMatrix[i][j];
                   switch (mapChar) {
                       case '#' -> {
                           Entity object = new Wall(j, i, tile.walll);
                           stillObjects.add(object);
                           break;
                       }
                       case '1' -> {
                           Entity object = new Grass(j, i, tile.grasss);
                           stillObjects.add(object);
                           Entity object1 = new Ballon(j, i, Sprite.balloom_left1.getFxImage());
                           entities.add(object1);
                           break;
                       }
                       case '2' -> {
                           Entity object = new Grass(j, i, tile.grasss);
                           stillObjects.add(object);
                           Entity object1 = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                           entities.add(object1);
                           break;
                       }
                       case '*' -> {
                           Entity object = new Grass(j, i, tile.grasss);
                           stillObjects.add(object);
                           Entity object1 = new Brick(j, i, tile.brickk);
                           stillObjects.add(object1);
                       }
                       default -> {
                           Entity object = new Grass(j, i, tile.grasss);
                           stillObjects.add(object);
                       }
                   }
               }
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}