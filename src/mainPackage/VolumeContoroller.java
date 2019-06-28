package mainPackage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumeContoroller extends JSlider {
    VolumeContoroller volumeBar;
    public VolumeContoroller(){
        super();
        this.volumeBar = this;
        this.setBackground(new Color(58,58,58));
        this.setPreferredSize(new Dimension(150,50));
        Port.Info source = Port.Info.SPEAKER;
        if (AudioSystem.isLineSupported(source))
        {
            try
            {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                this.setValue((int)volumeControl.getValue()*100);
            }
            catch (LineUnavailableException ignored)
            {
            }
        }
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {

                if (AudioSystem.isLineSupported(source))
                {
                    try
                    {
                        Port outline = (Port) AudioSystem.getLine(source);
                        outline.open();
                        FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                        volumeControl.setValue(volumeBar.getValue()/100f);
                    }
                    catch (LineUnavailableException ignored)
                    {
                    }
                }
            }
        });
        this.setFocusable(false);
    }
}