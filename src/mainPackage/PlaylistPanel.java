package mainPackage;

import javax.swing.*;
import java.awt.*;

public class PlaylistPanel extends JPanel implements Scrollable {
    private JScrollPane jScrollPane ;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
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

    public PlaylistPanel() {
        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(50,10));
        this.setBackground(Color.BLACK);
        this.setVisible(true);

    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}
