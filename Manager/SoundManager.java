package Manager;

import GameEngine.SoundNow;

public class SoundManager {
    private static SoundNow introSound = new SoundNow ("Media/music.wav");

    public static void playIntroSound(){
        introSound.start();
    }

    public static void pauseIntro(){
        introSound.suspend();
    }

    public static void resumeIntro(){
        introSound.resume();
    }
}
