import javax.swing.*;
import java.awt.*;

public class MainFrame {
        JFrame frame = new JFrame();

    public MainFrame() {
        frame.setLocation(100,100);
        frame.setSize(500,500);
        frame.setTitle("Jpotify");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        LeftPanel leftPanel = new LeftPanel();
        frame.add(leftPanel.getjScrollPane(),BorderLayout.WEST);

        RightPanel rightPanel = new RightPanel();
        frame.add(rightPanel.getJScrollPane(),BorderLayout.EAST);

        DownPanel downPanel = new DownPanel();
        frame.add(downPanel.getDownPanel(),BorderLayout.PAGE_END);



    }

    public static void main(String[] args) {

        new MainFrame();

    }
}
