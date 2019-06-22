import javax.swing.*;
import java.awt.*;

public class RightPanel {

    private JScrollPane jScrollPane ;
    private JPanel rightPanel = new JPanel();

    public RightPanel() {

        jScrollPane = new JScrollPane(rightPanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        rightPanel.setPreferredSize(new Dimension(210, 40));
        rightPanel.setVisible(true);
        rightPanel.setBackground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setText("Friends activity");
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setBackground(Color.black);
        textField.setForeground(Color.white);
        textField.setBorder(null);
        textField.setVisible(true);

        rightPanel.add(textField, BorderLayout.PAGE_START);
    }

    public JScrollPane getJScrollPane() {

        return jScrollPane;

    }

    public JPanel getRightPanel() {
        return rightPanel;
    }
}
