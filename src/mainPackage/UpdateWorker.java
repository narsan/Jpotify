package mainPackage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class UpdateWorker  extends SwingWorker<Void, Integer>  {

    private int duration;
    private static JLabel time=new JLabel();
    private JLabel totalTime=new JLabel();
    private boolean ispaused=false;
    JSlider slider =null;




    public JLabel getTime() {
        return time;
    }

    public void TotalTime(){

        totalTime.setText(String.valueOf(duration));
    }

    public JLabel getTotalTime() {
        return totalTime;
    }

    public boolean isIspaused() {
        return ispaused;
    }

    public void setIspaused(boolean ispaused) {
        this.ispaused = ispaused;
    }


    public UpdateWorker(int duration) {

        this.duration = duration;
        slider=new JSlider();
        slider.setValue(0);
        slider.setBackground(new Color(58,58,58));
        slider.setMinimum(0);
        slider.setMaximum(duration);
        addchangelistener();
    }
    public void addchangelistener() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(slider.getValue()==slider.getMaximum())
                {

                    Playing.isPlayed=false;
                    slider.setValue(0);
                    new PlayingActionListener();
                }
            }
        });
    }

    public void setDuration(int duration) {
        this.duration = duration;

    }

    @Override
    protected Void doInBackground() throws Exception {

        time.setForeground(Color.pink);

        time.setFont(new Font("Arial",Font.PLAIN,13));


        for (int i = 1; i <= duration; i++) {

            if (ispaused==false){

                publish(i);

                time.setText("passed :"+ String.valueOf(i));


            }
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

    public  JSlider getSlider() {
        return slider;
    }

}