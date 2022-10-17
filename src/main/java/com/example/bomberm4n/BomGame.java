package com.example.bomberm4n;

import com.example.bomberm4n.GameControl.Game;
import com.example.bomberm4n.scene.MainScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class BomGame extends Application {

    public static final int GAME_OFFSET = 48;

    public static final int WIDTH = 31 / 2;

    public static final int HEIGHT = 13;

    public static int TIME_INIT;


    @Override
    public void start(Stage stage) {
        try {
            MainScene manager = new MainScene();
            stage = manager.getMainStage();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}