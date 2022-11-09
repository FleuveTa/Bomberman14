package com.example.bomberm4n.scene.model.GameTool;

import com.example.bomberm4n.scene.model.menu.gameButton;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URISyntaxException;

public class SoundButton extends Button {
    private static final String freeUrl;

    static {
        try {
            freeUrl = gameButton.class.getResource("/assets/mobs/button/FreeSoundButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String FREE_CLOSE_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + freeUrl + ")";

    private static final String clickUrl;

    static {
        try {
            clickUrl = gameButton.class.getResource("/assets/mobs/button/ClickSoundButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String CLICK_CLOSE_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + clickUrl + ")";

    private static final String enterUrl;

    static {
        try {
            enterUrl = gameButton.class.getResource("/assets/mobs/button/EnterSoundButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String ENTER_CLOSE_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + enterUrl + ")";

    public SoundButton() {
        setStyle(FREE_CLOSE_FONT);
        setPrefHeight(30);
        setPrefWidth(30);
        setLayoutX(50);
        setLayoutY(10);
        initialSoundButton();
    }

    public void initialSoundButton() {
        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
                setStyle(ENTER_CLOSE_FONT);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null );
                setStyle(FREE_CLOSE_FONT);
            }
        });


    }

    private void setButtonReleasedStyle() {
        setStyle(FREE_CLOSE_FONT);
    }

    private void setButtonPressedStyle() {
        setStyle(CLICK_CLOSE_FONT);
    }
}
