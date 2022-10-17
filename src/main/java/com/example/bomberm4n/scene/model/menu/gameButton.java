package com.example.bomberm4n.scene.model.menu;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;

public class gameButton extends Button {

    private static final String freeUrl;

    static {
        try {
            freeUrl = gameButton.class.getResource("/assets/mobs/button/normalButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String pressedUrl;

    static {
        try {
            pressedUrl = gameButton.class.getResource("/assets/mobs/button/hoverButton.png").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String PRESSED_BUTTON_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + pressedUrl + ")";
    private static final String FREE_BUTTON_FONT = "-fx-background-color: transparent; -fx-background-image: url(" + freeUrl + ")";

    private static final String TEXT_FONT;

    static {
        try {
            TEXT_FONT = Objects.requireNonNull(gameButton.class.getResource("/assets/mobs/button/Mr. JUNKER MSX.ttf")).toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public gameButton(String text) {
        setText(text);
        setTextFont();
        setTextFill(Color.WHITE);
        setPrefHeight(45);
        setPrefWidth(194);
        setStyle(FREE_BUTTON_FONT);
        initializeButtonListeners();
    }

    private void setTextFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(TEXT_FONT), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleaseStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null );
            }
        });
    }

    private void setButtonPressedStyle() {
        setStyle(PRESSED_BUTTON_FONT);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleaseStyle() {
        setStyle(FREE_BUTTON_FONT);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 4);
    }
}

