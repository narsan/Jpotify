import javax.swing.*;
import java.awt.*;

public class DownPanel {

     static JPanel downPanel=new JPanel();

    public DownPanel() {

        downPanel.setVisible(true);
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(Color.DARK_GRAY);
        downPanel.setPreferredSize(new Dimension(300,65));
        JSlider jSlider = new JSlider();
        jSlider.setPreferredSize(new Dimension(700,10));
        jSlider.setBackground(Color.darkGray);

       // downPanel.add(jSlider);

    }

    public JPanel getDownPanel() {
        return downPanel;
    }

    public static void addPlayingSongInfo(JPanel jPanel) {

        downPanel.add(jPanel, BorderLayout.WEST);
        downPanel.revalidate();
        downPanel.repaint();
    }
    public static void addPauseAndResume(JPanel jPanel){

        downPanel.add(jPanel,BorderLayout.CENTER);
        downPanel.revalidate();
        downPanel.repaint();
    }

}
