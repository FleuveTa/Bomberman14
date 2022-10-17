package com.example.bomberm4n.scene;

import com.example.bomberm4n.Constants.Constants;
import javafx.application.Application;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;

public class GameSound {
    private static AudioClip backgroundSound = new AudioClip(Constants.SOUND_URL[0]);

    private static AudioClip effectSound;

    public static void update() {
        MainScene.sound = !MainScene.sound;
        if(!MainScene.sound) {
            backgroundSound.stop();
        } else {
            backgroundSound.play();
        }
    }

    public static void playBackgroundSound(String url) {
        if(MainScene.sound) {
            GameSound.backgroundSound.stop();
            GameSound.backgroundSound = GameSound.playSound(url);
            GameSound.backgroundSound.setCycleCount(1000);
            GameSound.backgroundSound.play();
        }
    }

    private static AudioClip playSound(String url) {
        AudioClip audioClip = new AudioClip(url);
        return audioClip;
    }

    public static void playEffectSound(String url) {
        if(MainScene.sound) {
            effectSound = GameSound.playSound(url);
            effectSound.play();
        }
    }
}
