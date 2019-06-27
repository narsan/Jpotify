package mainPackage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;

public class VolumeContoroller {

    public static void main(String[] args) {
        Port.Info source = Port.Info.SPEAKER;
        //        source = Port.Info.LINE_OUT;
        //        source = Port.Info.HEADPHONE;

        if (AudioSystem.isLineSupported(source)) {
            try {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                System.out.println("       volume: " + volumeControl.getValue());
                float v = 0.33F;
                volumeControl.setValue(v);
                System.out.println("   new volume: " + volumeControl.getValue());
                v = 0.73F;
                volumeControl.setValue(v);
                System.out.println("newest volume: " + volumeControl.getValue());
            } catch (LineUnavailableException ex) {
                System.err.println("source not supported");
                ex.printStackTrace();
            }
        }
    }
}