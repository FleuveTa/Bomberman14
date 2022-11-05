package com.example.bomberm4n.Constants;

import java.net.URISyntaxException;

public class Constants {
    public static String[] SOUND_URL;

    static {
        try {
            SOUND_URL = new String[]{
                    Constants.class.getResource("/assets/sound/backgroundSound.mp3").toURI().toString(), //0
                    Constants.class.getResource("/assets/sound/howl.mp3").toURI().toString(), //1
                    Constants.class.getResource("/assets/sound/PauseSound.mp3").toURI().toString(), //2 pause
                    Constants.class.getResource("/assets/sound/Die.wav").toURI().toString(), //3 die
                    Constants.class.getResource("/assets/sound/EnemyDie.wav").toURI().toString(), //4 enemy die
                    Constants.class.getResource("/assets/sound/Explosion.wav").toURI().toString(),  //5 bomb explosion
                    Constants.class.getResource("/assets/sound/ItemAppears.wav").toURI().toString(), // 6
                    Constants.class.getResource("/assets/sound/PowerUp.wav").toURI().toString(), // 7
                    Constants.class.getResource("/assets/sound/Oops.wav").toURI().toString(), // 8 game Over
                    Constants.class.getResource("/assets/sound/Victory.wav").toURI().toString()
            };
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] TUTORIAL_URL;

    static {
        try {
            TUTORIAL_URL = new String[]{
                    Constants.class.getResource("/assets/bom/player_down.png").toURI().toString(),
                    Constants.class.getResource("/assets/bom/player_right.png").toURI().toString(),
                    Constants.class.getResource("/assets/bom/player_left.png").toURI().toString(),
                    Constants.class.getResource("/assets/bom/player_up.png").toURI().toString(),
                    Constants.class.getResource("/assets/bom/bom1.png").toURI().toString(),
                    Constants.class.getResource("/assets/bom/portal.png").toURI().toString(),
                    Constants.class.getResource("/assets/mobs/balloom/mob1.png").toURI().toString(),
                    Constants.class.getResource("/assets/mobs/oneal/mob1.png").toURI().toString()
            };
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] BACKGROUND_URL;

    static {
        try {
            BACKGROUND_URL = new String[]{
                    Constants.class.getResource("/assets/mobs/button/grey_button05.png").toURI().toString(),
                    Constants.class.getResource("/assets/background/win.png").toURI().toString(),
                    Constants.class.getResource("/assets/background/level.png").toURI().toString(),
                    Constants.class.getResource("/assets/background/lose.png").toURI().toString(),

            };
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
