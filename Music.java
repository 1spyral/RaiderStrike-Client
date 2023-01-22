import java.io.*;
import javax.sound.sampled.*;

/*
 * Music player - does not yet loop song
 */
public class Music {
    private static final String PATH = "assets/BGM.wav";

    public static void play() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream); 
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
