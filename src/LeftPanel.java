import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public  class  LeftPanel extends JPanel implements Scrollable {

    private AddToLibrary addToLibrary;
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
    public LeftPanel() throws IOException {

        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200,40));

        JButton home = new JButton();
        home.setText("Home");
        home.setFont(new Font("Arial",Font.BOLD,16));
        home.setBackground(Color.BLACK);
        home.setForeground(Color.white);
        home.setBorder(null);

        JLabel yourLibrary = new JLabel();
        yourLibrary.setPreferredSize(new Dimension(30,30));
        yourLibrary.setText("Your Library");
        yourLibrary.setFont(new Font("Arial",Font.ITALIC,16));
        yourLibrary.setForeground(new Color(195,195,195));

        EmptyBorder border = new EmptyBorder(10, 0, 5, 20);
        yourLibrary.setBorder(border);


        addToLibrary = new AddToLibrary();

        this.add(home);
        this.add(yourLibrary);
        this.add(addToLibrary.getSongAdder());
        this.setBackground(Color.black);
        this.setVisible(true);


    }


    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public JPanel getLeftpanel() {
        return this;
    }

    public AddToLibrary getAddToLibrary() {
        return addToLibrary;
    }


}
