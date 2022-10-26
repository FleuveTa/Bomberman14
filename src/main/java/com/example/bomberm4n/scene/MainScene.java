package com.example.bomberm4n.scene;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Constants.Constants;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.model.menu.BomSubScene;
import com.example.bomberm4n.scene.model.menu.gameButton;
import com.example.bomberm4n.scene.model.pause.PauseScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.bomberm4n.BomGame.GAME_OFFSET;

public class MainScene {

    private static final int  WIDTH = BomGame.WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = BomGame.HEIGHT * Sprite.SCALED_SIZE + GAME_OFFSET;

    private static final int MENU_BUTTON_START_X = 30;

    private static final int MENU_BUTTON_START_Y = 80;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private List<gameButton> menuButton;

    public static boolean sound;


    private BomSubScene helpScene;
    private BomSubScene creditScene;


    public MainScene() {
        sound = true;
        menuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        GameSound.playBackgroundSound(Constants.SOUND_URL[0]);
        createBackground();
        createButton();
        createSubScene();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createBackground()  {
        try {
            String url = Objects.requireNonNull(MainScene.class.getResource("/assets/menu/img.png")).toURI().toString();
            Image backgroundImage = new Image(url, 480, 464, false, true);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            mainPane.setBackground(new Background(background));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMenuButton(gameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButton.size()*70);
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }


    private void createSubScene() {
        createHelpScene();
        creditScene = new BomSubScene();
        mainPane.getChildren().add(creditScene);
    }

    private Label tutorialLabel(String text) {
        Label help = new Label();
        help.setTextAlignment(TextAlignment.LEFT);
        help.setLineSpacing(10);
        help.setText(text);
        help.setPrefHeight(49);
        help.setPrefWidth(130);
        String s = "-fx-background-color: transparent; -fx-background-image: url(" + Constants.BACKGROUND_URL[0] + ")";
        help.setStyle(s);
        return help;
    }

    private void createHelpScene() {
        helpScene = new BomSubScene();
        mainPane.getChildren().add(helpScene);
        GridPane helpGrid = new GridPane();
        helpGrid.setLayoutX(40);
        helpGrid.setLayoutY(20);
        helpGrid.setHgap(20);
        helpGrid.setVgap(20);

        ImageView bomber_down = new ImageView(new Image(Constants.TUTORIAL_URL[0], 32, 32, true, false));
        Label bomber_down_t = tutorialLabel("   S to move down");
        ImageView bomber_up = new ImageView(new Image(Constants.TUTORIAL_URL[3], 32, 32, true, false));
        Label bomber_up_t = tutorialLabel("    W to move up");
        ImageView bomber_right = new ImageView(new Image(Constants.TUTORIAL_URL[1], 32, 32, true, false));
        Label bomber_right_t = tutorialLabel("  A to move right");
        ImageView bomber_left = new ImageView(new  Image(Constants.TUTORIAL_URL[2], 32, 32, true, false));
        Label bomber_left_t = tutorialLabel("   D to move left");
        ImageView bomber_space = new ImageView(new Image(Constants.TUTORIAL_URL[4], 32, 32, true, false));
        Label bomber_space_t = tutorialLabel("  Space to place bomb");
        ImageView portal = new ImageView(new Image(Constants.TUTORIAL_URL[5], 32, 32, true, false));
        Label portal_t = tutorialLabel("    go to next stage");
        ImageView balloon = new ImageView(new Image(Constants.TUTORIAL_URL[6], 32, 32, true, false));
        Label balloon_t = tutorialLabel("   Enemy Balloon");
        ImageView oneal = new ImageView(new Image(Constants.TUTORIAL_URL[7], 32, 32, true, false));
        Label oneal_t = tutorialLabel("    Enemy Oneal");
        helpGrid.add(bomber_down, 0, 1);
        helpGrid.add(bomber_down_t, 1, 1);
        helpGrid.add(bomber_up, 0, 2);
        helpGrid.add(bomber_up_t, 1, 2);
        helpGrid.add(bomber_right, 0, 3);
        helpGrid.add(bomber_right_t, 1, 3);
        helpGrid.add(bomber_left, 0, 4);
        helpGrid.add(bomber_left_t, 1, 4);
        helpGrid.add(bomber_space, 2, 1);
        helpGrid.add(bomber_space_t, 3, 1);
        helpGrid.add(portal, 2, 2);
        helpGrid.add(portal_t, 3, 2);
        helpGrid.add(balloon, 2, 3);
        helpGrid.add(balloon_t, 3, 3);
        helpGrid.add(oneal, 2, 4);
        helpGrid.add(oneal_t, 3, 4);

        helpScene.getPane().getChildren().addAll(helpGrid);
    }

    private void createButton() {
        createPlayButton();
        createHelpButton();
        createCreditButton();
        createSoundButton();
        createExitButton();
    }

    private void createPlayButton() {
        gameButton playButton = new gameButton("PLAY");
        addMenuButton(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameScene gameManager = null;
                try {
                    gameManager = new gameScene();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                gameManager.createNewGame(mainStage);
            }
        });
    }

    private void createHelpButton() {
        gameButton helpButton = new gameButton("TUTORIAL");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    helpScene.moveScene();
            }
        });
    }

    private void createCreditButton() {
        gameButton creditButton = new gameButton("CREDIT");
        addMenuButton(creditButton);

        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    creditScene.moveScene();
            }
        });
    }
    private void createExitButton() {
        gameButton exitButton = new gameButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createSoundButton() {
        gameButton soundButton = new gameButton("SOUND");
        addMenuButton(soundButton);

        soundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameSound.update();
            }
        });
    }


}
