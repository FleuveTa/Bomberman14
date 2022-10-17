package com.example.bomberm4n.GameControl;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.model.GameTool.PauseButton;
import com.example.bomberm4n.scene.model.GameTool.SoundButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.bomberm4n.BomGame.*;

public class Game {
    private Map map;
    private Canvas canvas;

    private GraphicsContext gc;
    private Group root;
    private Scene gameScene;
    private List<Text> textList;

    private PauseButton pauseButton;
    private SoundButton soundButton;

    public Game() {
        map = new Map();
        // Tao Canvas
        canvas = new Canvas(BomGame.WIDTH * Sprite.SCALED_SIZE,
                BomGame.HEIGHT * Sprite.SCALED_SIZE + GAME_OFFSET);
        gc = canvas.getGraphicsContext2D();
        textList = new ArrayList<>();
        root = new Group();

        root.getChildren().add(canvas);
        root.getChildren().addAll(textList);
        setButton(root);

        // Tao root container


        // Tao scene
        gameScene = new Scene(root);

        gameScene.setFill(Color.web("010017", 1.0));
    }

    public Scene getGameScene() {
        return gameScene;
    }


    public void handleEvent() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                map.getBomber().eventHandler(keyEvent);
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                map.getBomber().eventHandler(keyEvent);
            }
        });
    }

    public void update() {
        map.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.getCamera().centerEntity(map.getBomber());
        map.render(gc);
    }

    public Map getBoard() {
        return map;
    }

    public void setButton(Group root) {
        pauseButton = new PauseButton();
        pauseButton.setFocusTraversable(false);
        soundButton = new SoundButton();
        soundButton.setFocusTraversable(false);
        root.getChildren().add(pauseButton);
        root.getChildren().add(soundButton);
    }

    public PauseButton getPauseButton() {
        return pauseButton;
    }

    public SoundButton getSoundButton() {
        return soundButton;
    }
}
