package view;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.AudioInputStream;

import java.io.File;

import javax.sound.sampled.Clip;


public class Audio {

    public static Clip vn;

    public static void play(String path, int type) {
        try {
            File file = new File(path);
            AudioInputStream ip = AudioSystem.getAudioInputStream(file);
            vn = AudioSystem.getClip();
            vn.open(ip);
            vn.start();
            if (type == 0) {
                vn.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void stop() {
        if (vn != null) {
            vn.stop();
        }
    }
}
