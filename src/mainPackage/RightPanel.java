package mainPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RightPanel extends JPanel implements Scrollable {


    private JScrollPane jScrollPane ;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(VP_WIDTH, VP_HEIGHT);
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
        // TODO Consider improving
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
        // TODO Consider improving
        return 0;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
       private JButton friendsbtn = new JButton();

    public RightPanel() {

        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        this.setPreferredSize(new Dimension(210, 40));
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout( new BorderLayout());



        EmptyBorder border = new EmptyBorder(5, 30, 5, 5);
        JLabel friends = new JLabel();
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(210,40));

        this.setBorder(border);
        ImageIcon friendIcon = new ImageIcon(new ImageIcon("src\\icons\\friend.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        friendsbtn.setIcon(friendIcon);
        friendsbtn.setBackground(Color.BLACK);
        friendsbtn.setBorder(null);
        friends.setText("Friends activity");
        friends.setFont(new Font("Arial", Font.BOLD, 15));
        friends.setBackground(Color.black);
        friends.setForeground(Color.white);

        //friends.setVisible(true);
        //friends.setBorder(null);
        panel.add(friendsbtn);
        panel.add(friends);
        this.add(panel,BorderLayout.NORTH);
    }

    public JScrollPane getJScrollPane() {

        return jScrollPane;

    }

}
