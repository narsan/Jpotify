import javax.swing.*;
import java.awt.*;

public class DownPanel {

    private JPanel downPanel=new JPanel();

    public DownPanel() {

        downPanel.setVisible(true);
        downPanel.setBackground(Color.DARK_GRAY);
        downPanel.setPreferredSize(new Dimension(300,65));
        JSlider jSlider = new JSlider();
        jSlider.setPreferredSize(new Dimension(700,10));
        jSlider.setBackground(Color.darkGray);

        downPanel.add(jSlider);
    }

    public JPanel getDownPanel() {
        return downPanel;
    }
}
