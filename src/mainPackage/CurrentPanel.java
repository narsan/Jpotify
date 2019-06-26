package mainPackage;

import javax.swing.*;
import java.awt.*;

public class CurrentPanel  {

    private CenterPanelScroller currentPanel;
    private JPanel jPanel;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public CurrentPanel() {
        currentPanel = new CenterPanelScroller();


    }



    public void setCurrentPanel(CenterPanelScroller currentPanel) {
        this.currentPanel = currentPanel;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

}
