package com.example.bomberm4n.GameControl;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.MainScene;
import com.example.bomberm4n.scene.model.GameTool.PauseButton;
import com.example.bomberm4n.scene.model.GameTool.SoundButton;
import com.example.bomberm4n.scene.model.menu.BomSubScene;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.bomberm4n.BomGame.*;

public class Game {
    private static Map map;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Scene gameScene;
    private final List<Text> textList;
    private boolean isWin;
    private Text score;
    private Text score2;
    private Text time;
    private Text time2;

    private boolean alive;
    private int score3;
    Text live;
    private PauseButton pauseButton;
    private SoundButton soundButton;
    public static final int fontSize2 = 20;


    public Game() throws URISyntaxException {
        map = new Map();
        alive = true;
        // Tao Canvas
        canvas = new Canvas(BomGame.WIDTH * Sprite.SCALED_SIZE,
                BomGame.HEIGHT * Sprite.SCALED_SIZE + GAME_OFFSET);
        gc = canvas.getGraphicsContext2D();
        textList = new ArrayList<>();
        initScoreBar();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().addAll(textList);
        setButton(root);
        // Tao scene
        gameScene = new Scene(root);
        gameScene.setFill(Color.web("010017", 1.0));
        isWin = false;
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
        score.setText(String.valueOf(map.getScore()));
        score2.setText(String.valueOf(map.getScore()));
        time.setText(String.valueOf(map.getTime() / 60));
        time2.setText(String.valueOf(map.getTime() / 60));
        live.setFill(Color.web("a0331b", 1.0));
        score3 = map.getScore();
        if (map.getBomber().getLive() == 3) live.setText("❤❤❤");
        else if (map.getBomber().getLive() == 2) live.setText("❤❤");
        else if (map.getBomber().getLive() == 1) live.setText("❤");
        else alive = false;
        if(map.isWin()) {
            isWin = true;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWin() {
        return isWin;
    }

    public int getScore3() {
        return score3;
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.getCamera().centerEntity(map.getBomber());    //camera following player
        map.render(gc);
    }

    public void setButton(Group root) {
        pauseButton = new PauseButton();
        pauseButton.setFocusTraversable(false);
        soundButton = new SoundButton();
        soundButton.setFocusTraversable(false);
        root.getChildren().add(pauseButton);
        root.getChildren().add(soundButton);
    }

    public void initScoreBar() {
        Text scoreField = new Text(100, 30, "Score ");
        scoreField.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        scoreField.setFill(Color.web("350924", 1.0));
        textList.add(scoreField);

        Text scoreField2 = new Text(100, 30, "Score ");
        scoreField2.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        scoreField2.setFill(Color.web("007257", 1.0));
        textList.add(scoreField2);

        score = new Text(160, 30, String.valueOf(map.getScore()));
        score.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        score.setFill(Color.web("350924", 1.0));
        textList.add(score);

        score2 = new Text(160, 30, String.valueOf(map.getScore()));
        score2.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        score2.setFill(Color.web("007257", 1.0));
        textList.add(score2);

        Text timeField = new Text(200, 30, "Time");
        timeField.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        timeField.setFill(Color.web("350924", 1.0));
        textList.add(timeField);

        Text timeField2 = new Text(200, 30, "Time");
        timeField2.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        timeField2.setFill(Color.web("007257", 1.0));
        textList.add(timeField2);

        time = new Text(240, 30, String.valueOf(map.getTime() / 60));
        time.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        time.setFill(Color.web("350924", 1.0));
        textList.add(time);

        time2 = new Text(240, 30, String.valueOf(map.getTime() / 60));
        time2.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        time2.setFill(Color.web("007257", 1.0));
        textList.add(time2);

        Text liveField = new Text(320, 30, "Live");
        liveField.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        liveField.setFill(Color.web("350924", 1.0));
        textList.add(liveField);

        Text liveField2 = new Text(320, 30, "Live");
        liveField2.setFont(Font.loadFont(BomSubScene.FONT_PATH, fontSize2));
        liveField2.setFill(Color.web("007257", 1.0));
        textList.add(liveField2);

        live = new Text(380, 35,  "❤❤❤");
        live.setFont(new Font("Arial", 25));
        textList.add(live);
    }


    public PauseButton getPauseButton() {
        return pauseButton;
    }

    public SoundButton getSoundButton() {
        return soundButton;
    }

    public static Map getMap() {
        return map;
    }
}
