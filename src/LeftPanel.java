import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeftPanel extends JPanel {

    private JScrollPane jScrollPane ;
    private AddToLibrary addToLibrary;
    public LeftPanel() throws IOException {

        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(300,40));

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
