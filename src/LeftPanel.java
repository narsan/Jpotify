import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public  class  LeftPanel extends JPanel  {

    private AddToLibrary addToLibrary;

    public LeftPanel() throws IOException {



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


    public JPanel getLeftpanel() {
        return this;
    }

    public AddToLibrary getAddToLibrary() {
        return addToLibrary;
    }


}