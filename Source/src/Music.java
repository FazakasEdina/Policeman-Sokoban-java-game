//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
   private final Clip audioClip;

    public Music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File audiofile = new File ("Paint it, Black.wav");
        AudioInputStream as = AudioSystem.getAudioInputStream(audiofile);
        AudioFormat format = as.getFormat();

        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(as);
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void start(){
        audioClip.start();
    }
    public void stop(){
        audioClip.stop();
    }

}
