package mainPackage;

import javax.swing.*;
import java.awt.*;

public class CurrentPanel implements Scrollable {

    private JPanel currentPanel;
    private JScrollPane jScrollPane ;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;

    public CurrentPanel() {
        currentPanel = new JPanel();
        jScrollPane = new JScrollPane(currentPanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);

    }

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

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
