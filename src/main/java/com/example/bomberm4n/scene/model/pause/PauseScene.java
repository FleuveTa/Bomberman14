package com.example.bomberm4n.scene.model.pause;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.GameControl.Game;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.gameScene;
import com.example.bomberm4n.scene.model.menu.gameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PauseScene {
    private AnchorPane pausePane;

    private Scene pauseScene;

    private Stage pauseStage;

    private gameButton resumeButton;
    private gameButton menuButton;
    private gameButton exitButton;

    private static final int LAYOUT_X = 130;
    private static final int LAYOUT_Y = 100;

    private List<gameButton> pauseButton;


    private static final int WIDTH = BomGame.WIDTH * Sprite.SCALED_SIZE;

    private static final int HEIGHT = BomGame.HEIGHT * Sprite.SCALED_SIZE;

    public PauseScene() {
        pauseButton = new ArrayList<>();
        pausePane = new AnchorPane();
        pauseScene = new Scene(pausePane, WIDTH, HEIGHT);
        pauseStage = new Stage();
        pauseStage.setScene(pauseScene);
        createButton();
        createPauseBackground();
    }

    public void createPauseBackground() {
        try {
            String url = PauseScene.class.getResource("/assets/menu/PauseBackground.jpg").toURI().toString();
            Image backgroundImage = new Image(url, 480, 464, true, false);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            pausePane.setBackground(new Background(background));
        } catch (Exception e) {

        }
    }

    public void createButton(){
        resumeButton = new gameButton("Resume");
        addMenuButton(resumeButton);
        menuButton = new gameButton("Return Menu");
        addMenuButton(menuButton);
        exitButton = new gameButton("EXIT");
        addMenuButton(exitButton);
    }

    public void addMenuButton(gameButton button) {
        button.setLayoutX(LAYOUT_X);
        button.setLayoutY(LAYOUT_Y + pauseButton.size()*70);
        pauseButton.add(button);
        pausePane.getChildren().add(button);
    }

    public gameButton getResumeButton() {
        return resumeButton;
    }

    public gameButton getMenuButton() {
        return menuButton;
    }

    public gameButton getPlayAgainButton() {
        return exitButton;
    }

    public Stage getPauseStage() {
        return pauseStage;
    }
}
