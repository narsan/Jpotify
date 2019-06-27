package mainPackage;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserPanel extends JPanel {

    private JLabel userAccount = new JLabel();
    protected String name ;
    private String search;

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
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.BLACK);
        searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(200, 40));
        JTextField field = new RoundJTextField(15);
        JButton searchBtn = new JButton();
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("src\\icons\\search.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        searchBtn.setIcon(searchIcon);
        searchBtn.setBackground(Color.BLACK);
        searchBtn.setBorder(null);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search = field.getText();
                System.out.println(search);
            }
        });

        userAccount.setText(name);
        userAccount.setFont(new Font("Arial", Font.PLAIN, 16));
        userAccount.setBackground(Color.BLACK);
        userAccount.setForeground(Color.white);
        userAccount.setBorder(null);
        userAccount.setVisible(true);
        EmptyBorder border = new EmptyBorder(5, 250, 5, 250);
        searchPanel.add(field);
        searchPanel.add(searchBtn);
        this.add(searchPanel,BorderLayout.WEST);
        this.setBorder(border);
        this.add(userAccount, BorderLayout.EAST);

    }

}
