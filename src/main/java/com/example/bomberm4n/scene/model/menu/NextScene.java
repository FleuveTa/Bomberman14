package com.example.bomberm4n.scene.model.menu;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.MainScene;
import com.example.bomberm4n.scene.gameScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bomberm4n.BomGame.GAME_OFFSET;

public class NextScene {
    private static final int  WIDTH = BomGame.WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = BomGame.HEIGHT * Sprite.SCALED_SIZE + GAME_OFFSET;

    private AnchorPane nextPane;
    private Scene nextScene;
    private Stage nextStage;

    private MainScene newGame;

    private boolean canContinue;

    private List<Text> textList;

    private gameButton continueButton;

    public NextScene(String path, int z) {
        nextPane = new AnchorPane();
        nextScene = new Scene(nextPane, WIDTH, HEIGHT);
        nextStage = new Stage();
        nextStage.setScene(nextScene);
        createBackground(path);
        continueButton = new gameButton("CONTINUE");
        textList = new ArrayList<>();
        canContinue = false;
        if(z == 1) {
            createScoreText();
            nextPane.getChildren().addAll(textList);
            createMenuButton();

        }else if(z == 2){
            createScoreText();
            nextPane.getChildren().addAll(textList);
            createMenuButton();
            createPlayButton();
        }
    }

    private void createPlayButton() {
        continueButton.setLayoutX(250);
        continueButton.setLayoutY(250);
        nextPane.getChildren().add(continueButton);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                canContinue = true;
            }
        });
    }

    private void createScoreText() {
        Text scoreField = new Text(320, 180, String.valueOf(gameScene.getScore()));
        scoreField.setFont(Font.loadFont(BomSubScene.FONT_PATH, 120));
        scoreField.setFill(Color.GRAY);
        textList.add(scoreField);
    }

    private void createMenuButton() {
        gameButton menuButton = new gameButton("MENU");
        menuButton.setLayoutX(250);
        menuButton.setLayoutY(300);
        nextPane.getChildren().add(menuButton);

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGame = new MainScene();
                nextStage.close();
                newGame.getMainStage().show();
            }
        });
    }

    private void createBackground(String path) {
        Image backgroundImage = new Image(path, 480, 464, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        nextPane.setBackground(new Background(background));
    }

    public Stage getNextStage() {
        return nextStage;
    }

    public gameButton getContinueButton() {
        return continueButton;
    }

}
