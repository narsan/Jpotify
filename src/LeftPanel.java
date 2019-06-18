import javax.swing.*;
import java.awt.*;

public class LeftPanel {
    private JPanel leftpanel = new JPanel();
    private JScrollPane jScrollPane ;

    public LeftPanel() {

        jScrollPane = new JScrollPane(leftpanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        leftpanel.setLayout(new BoxLayout(leftpanel,BoxLayout.Y_AXIS));
        leftpanel.setPreferredSize(new Dimension(300,40));

        JButton home = new JButton();
        home.setText("Home");
        home.setFont(new Font("Arial",Font.PLAIN,20));
        home.setBackground(Color.BLACK);
        home.setForeground(Color.white);
        home.setBorder(null);

        JLabel yourLibrary = new JLabel();
        yourLibrary.setPreferredSize(new Dimension(30,30));
        yourLibrary.setText("Your Library");
        yourLibrary.setFont(new Font("Arial",Font.PLAIN,20));
        yourLibrary.setForeground(Color.white);

        JButton songs = new JButton();
        songs.setFont(new Font("Arial",Font.PLAIN,20));
        songs.setText("Songs");
        songs.setBackground(Color.BLACK);
        songs.setForeground(Color.white);
        songs.setBorder(null);

        AddToLibrary addToLibrary = new AddToLibrary();
        leftpanel.add(home);
        leftpanel.add(yourLibrary);
        leftpanel.add(addToLibrary.getSongAdder());
        leftpanel.add(songs);
        leftpanel.setBackground(Color.CYAN);
        leftpanel.setVisible(true);

      //  songs.addActionListener();



    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public JPanel getLeftpanel() {
        return leftpanel;
    }
}
