package com.example.bomberm4n.Constants;

import java.net.URISyntaxException;

public class Constants {
    public static String[] SOUND_URL;

    static {
        try {
            SOUND_URL = new String[]{
                Constants.class.getResource("/assets/sound/backgroundSound.mp3").toURI().toString(), //0
                Constants.class.getResource("/assets/sound/gameSound.mp3").toURI().toString(), //1
                Constants.class.getResource("/assets/sound/PauseSound.mp3").toURI().toString(), //2 pause
                Constants.class.getResource("/assets/sound/Die.wav").toURI().toString(), //3 die
                Constants.class.getResource("/assets/sound/EnemyDie.wav").toURI().toString(), //4 enemy die
                Constants.class.getResource("/assets/sound/Explosion.wav").toURI().toString()
            };
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
