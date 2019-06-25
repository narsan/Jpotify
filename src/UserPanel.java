import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserPanel extends JPanel {

    private JLabel userAccount = new JLabel();
    protected String name ;

    @Override
    public void setName(String name) {
        this.name = name;
        userAccount.setText(name);
    }

    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }

    public UserPanel() {

        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(200, 40));
        JTextField searchBar = new JTextField();
        searchBar.setColumns(10);
        searchBar.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        searchBar.setText("search...");
        searchBar.setEditable(true);
        searchBar.setBackground(Color.white);
        searchBar.setForeground(Color.pink);

        userAccount.setText(name);
        userAccount.setFont(new Font("Arial", Font.PLAIN, 16));
        userAccount.setBackground(Color.BLACK);
        userAccount.setForeground(Color.white);
        userAccount.setBorder(null);
        userAccount.setVisible(true);
        EmptyBorder border = new EmptyBorder(5, 250, 5, 250);
        this.setBorder(border);
        this.add(userAccount, BorderLayout.EAST);
        this.add(searchBar, BorderLayout.WEST);

    }

}
