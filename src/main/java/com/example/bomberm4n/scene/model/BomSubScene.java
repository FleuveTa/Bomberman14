package com.example.bomberm4n.scene.model;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URISyntaxException;

public class BomSubScene extends SubScene {
    private static final String FONT_PATH = "C:\\Users\\USER\\Documents\\GitHub\\Bomberman14\\src\\main\\resources\\assets\\mobs\\button\\Mr. JUNKER MSX.ttf";

    private static final String PANEL_STYLE = "C:\\Users\\USER\\Documents\\GitHub\\Bomberman14\\src\\main\\resources\\assets\\mobs\\SubScene\\Panel 5.png";

    private boolean isHidden;

    private Button closeButton;

    private static final String closeUrl;

    static {
        try {
            closeUrl = BomSubScene.class.getResource("/assets/mobs/SubScene/B_Button41.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String closeClickUrl;

    static {
        try {
            closeClickUrl = BomSubScene.class.getResource("/assets/mobs/SubScene/B_Button42.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String enterCloseUrl;

    static {
        try {
            enterCloseUrl = BomSubScene.class.getResource("/assets/mobs/SubScene/EnterButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String CLOSE_BUTTON_CLICK_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + closeClickUrl + ")";

    private static final String CLOSE_BUTTON_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + closeUrl + ")";

    private static final String ENTER_CLOSE_BUTTON_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + enterCloseUrl + ")";

    public BomSubScene() {
        super(new AnchorPane(), 455, 298);
        prefHeight(455);
        prefWidth(298);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(PANEL_STYLE, 455, 298, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(backgroundImage));

        isHidden = true;

        closeButton = new Button();
        createCloseButton(closeButton);
        root2.getChildren().add(closeButton);

        setLayoutX(480);
        setLayoutY(50);
    }

    public void moveScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden) {
            transition.setToX(-460);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }

        transition.play();
    }

    public void createCloseButton(Button button) {
        button.setPrefHeight(30);
        button.setPrefWidth(30);
        button.setLayoutX(410);
        button.setLayoutY(5);
        button.setStyle(CLOSE_BUTTON_FONT);
        initializeButtonListeners(button);
    }

    private void initializeButtonListeners(Button button) {
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle(button);
                    moveScene();
                }
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleaseStyle(button);
                }
            }
        });

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setEffect(new DropShadow());
                button.setStyle(ENTER_CLOSE_BUTTON_FONT);
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setEffect(null );
                button.setStyle(CLOSE_BUTTON_FONT);
            }
        });
    }

    private void setButtonPressedStyle(Button button) {
        button.setStyle(CLOSE_BUTTON_CLICK_FONT);
    }

    private void setButtonReleaseStyle(Button button) {
        button.setStyle(CLOSE_BUTTON_FONT);
    }


    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }




}
