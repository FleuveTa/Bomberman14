package com.example.bomberm4n.scene;

import com.example.bomberm4n.Constants.Constants;
import com.example.bomberm4n.GameControl.Game;
import com.example.bomberm4n.scene.model.menu.BomSubScene;
import com.example.bomberm4n.scene.model.pause.GameOverScene;
import com.example.bomberm4n.scene.model.pause.PauseScene;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class gameScene {
    private Game game;

    private Stage menuStage;

    private static Stage gameStage;

    private AnimationTimer timer;

    private PauseScene pauseScene;

    public gameScene() throws URISyntaxException {
        game = new Game();
        setGameButton();
        game.render();
        gameStage = new Stage();
        game.handleEvent();
        gameStage.setScene(game.getGameScene());
        gameStage.setResizable(false);
        pauseScene = new PauseScene();
        pauseScene.getPauseStage().setResizable(false);
        GameSound.playBackgroundSound(Constants.SOUND_URL[1]);
        setPauseScene();
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                game.render();
                game.update();
            }
        };
        timer.start();
    }

    public void setGameButton() {
        game.getPauseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timer.stop();
                gameStage.hide();
                pauseScene.getPauseStage().show();
            }
        });

        game.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameSound.update();
            }
        });
    }

    public void setPauseScene() {
        setResumeButton();
        setExit();
        setReturnMenuButton();
    }

    public void setResumeButton() {
        pauseScene.getResumeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timer.start();
                pauseScene.getPauseStage().hide();
                gameStage.show();
            }
        });
    }

    public void setReturnMenuButton() {
        pauseScene.getMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pauseScene.getPauseStage().hide();
                menuStage.show();
                GameSound.playBackgroundSound(Constants.SOUND_URL[0]);
            }
        });
    }

    public void setExit() {
        pauseScene.getPlayAgainButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pauseScene.getPauseStage().close();
            }
        });
    }
}
