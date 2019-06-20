import javax.swing.*;
import java.awt.*;

public class DownPanel {

     static JPanel downPanel=new JPanel();
     JPanel volume=new JPanel();


    public DownPanel() {

        downPanel.setVisible(true);
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(Color.black);
        volume.setBackground(Color.ORANGE);
        volume.setPreferredSize(new Dimension(210,0));
        downPanel.setPreferredSize(new Dimension(300,65));
        JSlider jSlider = new JSlider();
        jSlider.setPreferredSize(new Dimension(700,10));
        jSlider.setBackground(Color.black);

       // downPanel.add(jSlider);

        downPanel.add(volume,BorderLayout.EAST);

    }

    public JPanel getDownPanel() {
        return downPanel;
    }

    public static void addPlayingSongInfo(JPanel jPanel) {

        downPanel.add(jPanel, BorderLayout.WEST);
        downPanel.revalidate();
        //downPanel.repaint();
    }
    public static void addPauseAndResume(JPanel jPanel){

        downPanel.add(jPanel,BorderLayout.CENTER);
        downPanel.revalidate();
        //downPanel.repaint();
    }

}
