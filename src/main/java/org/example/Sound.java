package org.example;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {

    public static void Sound(String path) {
        try {
            // טוען את הקובץ מתוך תיקיית המשאבים (resources)
            InputStream audioSrc = Sound.class.getResourceAsStream(path);

            // עטיפה בקלט נתונים עם תמיכה ב-buffering
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

}
