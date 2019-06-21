import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class UpdateWorker  extends SwingWorker<Void, Integer>  {

    static   JSlider slider ;

        private int duration;

    public boolean isIspaused() {
        return ispaused;
    }

    public void setIspaused(boolean ispaused) {
        this.ispaused = ispaused;
    }

    private boolean ispaused=false;


        public UpdateWorker(int duration) {

            slider=new JSlider();
            this.duration = duration;
            slider.setBackground(Color.DARK_GRAY);
            slider.setMinimum(0);
            slider.setMaximum(duration);
            slider.setValue(0);
        }

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 1; i <= duration; i++) {

                if (ispaused==false){
                    publish(i);}
                Thread.sleep(1000);
                if (ispaused==true){

                    while (ispaused){
                        Thread.sleep(50);
                        continue;
                    }
                }


            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            slider.setValue(chunks.get(0));
        }

    public static JSlider getSlider() {
        return slider;
    }
}
