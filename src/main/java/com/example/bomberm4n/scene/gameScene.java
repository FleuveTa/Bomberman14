package com.example.bomberm4n.scene;

import com.example.bomberm4n.GameControl.Game;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class gameScene {
    private Game game;

    private Stage menuStage;

    public static Stage gameStage;

    public gameScene() {
        game = new Game();
        game.render();
        gameStage = new Stage();
        gameStage.setScene(game.getGameScene());
        loop();
        game.handleEvent();
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

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
    }

}
