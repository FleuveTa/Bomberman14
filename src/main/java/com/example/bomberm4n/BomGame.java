package com.example.bomberm4n;

import com.example.bomberm4n.Graphics.Sprite;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


import java.io.IOException;

public class BomGame extends Application {
    /** 4:3 ratio */
    public static final int WIDTH = 16;
    public static final int HEIGHT = 12;

    @Override
    public void start(Stage stage) throws IOException {

        // Create canvas
        Canvas canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Group group = new Group();
        group.getChildren().add(canvas);

        // Create scene
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Ver 1.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}