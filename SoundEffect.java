import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {
    private Clip clip;

    public SoundEffect(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.stop(); // Hentikan jika sedang diputar
            clip.setFramePosition(0); // Kembali ke awal
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
