package GameEngine;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundNow extends Thread{
    private String filename;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    enum Position{
        LEFT,RIGHT,NORMAL
    };
    public SoundNow(String wavfile){
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
    public SoundNow(String warfile,Position p){
        filename = warfile;
        curPosition = p;
    }
    public void run(){
        while(true){
            File soundFile = new File(filename);
            if(!soundFile.exists()){
                System.out.println("Wav not find"+ filename);
                return;
            }
            AudioInputStream audioInputStream = null;
            try{
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            }catch(UnsupportedAudioFileException el){
                el.printStackTrace();
                return;
            }catch(IOException el){
                el.printStackTrace();
                return;
            }
            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
            try{
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            }catch(LineUnavailableException e){
                e.printStackTrace();
                return;
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
            if(auline.isControlSupported(FloatControl.Type.PAN)){
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                if(curPosition == Position.RIGHT)
                 pan.setValue(1.0f);
                 else if(curPosition == Position.LEFT)
                  pan.setValue(-1.0f);
            }
            auline.start();
            int nBytesRead = 0;
            byte[] adData = new byte[EXTERNAL_BUFFER_SIZE];
            try{
                while(nBytesRead != -1){
                    nBytesRead =audioInputStream.read(adData,0,adData.length);
                    if(nBytesRead >=0)
                     auline.write(adData, 0, nBytesRead);

                }
            }catch(IOException e){
                e.printStackTrace();
                return;
            } finally{
                auline.drain();
                auline.close();
            }
        }
    }
}