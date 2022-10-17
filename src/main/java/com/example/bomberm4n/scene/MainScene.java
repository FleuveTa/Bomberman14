package com.example.bomberm4n.scene;

import com.example.bomberm4n.BomGame;
import com.example.bomberm4n.Graphics.Sprite;
import com.example.bomberm4n.scene.model.menu.BomSubScene;
import com.example.bomberm4n.scene.model.menu.gameButton;
import com.example.bomberm4n.scene.model.pause.PauseScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


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


    private BomSubScene helpScene;
    private BomSubScene creditScene;


    public MainScene() {
        menuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
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
        helpScene = new BomSubScene();
        mainPane.getChildren().add(helpScene);
        creditScene = new BomSubScene();
        mainPane.getChildren().add(creditScene);
    }

    private void createButton() {
        createPlayButton();
        createHelpButton();
        createCreditButton();
        createExitButton();
    }

    private void createPlayButton() {
        gameButton playButton = new gameButton("PLAY");
        addMenuButton(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameScene gameManager = new gameScene();
                gameManager.createNewGame(mainStage);
            }
        });
    }

    private void createHelpButton() {
        gameButton helpButton = new gameButton("HELP");
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


}
