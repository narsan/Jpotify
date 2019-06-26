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

    public RightPanel() {

        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        this.setPreferredSize(new Dimension(210, 40));
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));



        JLabel friends = new JLabel();
        EmptyBorder border = new EmptyBorder(5, 50, 5, 5);
        this.setBorder(border);


        friends.setText("Friends activity");
        friends.setFont(new Font("Arial", Font.BOLD, 15));
        friends.setBackground(Color.black);
        friends.setForeground(Color.white);
        friends.setBorder(null);
        friends.setVisible(true);

        this.add(friends);
    }

    public JScrollPane getJScrollPane() {

        return jScrollPane;

    }

}
