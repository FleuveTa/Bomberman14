package com.example.bomberm4n;

import com.example.bomberm4n.GameControl.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class BomGame extends Application {

    public static final int GAME_OFFSET = 48;

    public static final int WIDTH = 31 / 2;

    public static final int HEIGHT = 13;

    public static int TIME_INIT;

    public static Stage gameStage;

    //private Menu menu;

    private Game game;

    public static void main(String[] args) {
        Application.launch(BomGame.class);
    }

    @Override
    public void start(Stage stage) {
        game = new Game();
        game.render();
        stage.setScene(game.getGameScene());
        gameStage = stage;
        loop();
        game.handleEvent();
        gameStage.show();
    }
    public void loop() {
        gameStage.setScene(game.getGameScene());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                game.render();
                game.update();
            }
        };
        timer.start();
    }
}