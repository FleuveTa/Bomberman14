package com.example.bomberm4n.scene.model.menu;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.example.bomberm4n.BomGame.GAME_OFFSET;

public class NextScene {
    private static final int  WIDTH = BomGame.WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = BomGame.HEIGHT * Sprite.SCALED_SIZE + GAME_OFFSET;

    private AnchorPane nextPane;
    private Scene nextScene;
    private Stage nextStage;

    public NextScene(String path) {
        nextPane = new AnchorPane();
        nextScene = new Scene(nextPane, WIDTH, HEIGHT);
        nextStage = new Stage();
        nextStage.setScene(nextScene);
        createBackground(path);
    }

    private void createBackground(String path) {
        Image backgroundImage = new Image(path, 480, 464, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        nextPane.setBackground(new Background(background));
    }

    public Stage getNextStage() {
        return nextStage;
    }
}
