package com.example.bomberm4n.scene.model.pause;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.model.menu.gameButton;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameOverScene {
    private AnchorPane gameOverPane;

    private Scene gameOverScene;

    private Stage gameOverStage;

    private gameButton menuButton;

    private static final int LAYOUT_X = 130;
    private static final int LAYOUT_Y = 100;

    private List<gameButton> gameOverButton;


    private static final int WIDTH = BomGame.WIDTH * Sprite.SCALED_SIZE;

    private static final int HEIGHT = BomGame.HEIGHT * Sprite.SCALED_SIZE;

    public GameOverScene() {
        gameOverButton = new ArrayList<>();
        gameOverPane = new AnchorPane();
        gameOverScene = new Scene(gameOverPane, WIDTH, HEIGHT);
        gameOverStage = new Stage();
        gameOverStage.setScene(gameOverScene);
        createButton();
        createGameOverBackground();
    }

    public void createGameOverBackground() {
        try {
            String url = PauseScene.class.getResource("/assets/menu/PauseBackground.jpg").toURI().toString();
            Image backgroundImage = new Image(url, 480, 464, true, false);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            gameOverPane.setBackground(new Background(background));
        } catch (Exception e) {

        }
    }

    public void createButton(){
        menuButton = new gameButton("Return Menu");
        addMenuButton(menuButton);
    }

    public void addMenuButton(gameButton button) {
        button.setLayoutX(LAYOUT_X);
        button.setLayoutY(LAYOUT_Y + gameOverButton.size()*70);
        gameOverButton.add(button);
        gameOverPane.getChildren().add(button);
    }

    public Stage getGameOverStage() {
        return gameOverStage;
    }
}
