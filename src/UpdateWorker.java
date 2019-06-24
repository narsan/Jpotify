import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UpdateWorker  extends SwingWorker<Void, Integer>  {

    static   JSlider slider ;

    private int duration;

    public JLabel getTime() {
        return time;
    }

    private JLabel time=new JLabel();
    private JLabel totalTime=new JLabel();

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

    private boolean ispaused=false;


        public UpdateWorker(int duration) {

            this.duration = duration;
            slider=new JSlider();
            slider.setBackground(new Color(58,58,58));
            slider.setMinimum(0);
            slider.setMaximum(duration);
            slider.setValue(0);
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

                    time.setText("passed :"+String.valueOf(i));


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

    public static JSlider getSlider() {
        return slider;
    }
}
