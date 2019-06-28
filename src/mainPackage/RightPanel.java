package mainPackage;

import Network2.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RightPanel extends JPanel implements Scrollable {


    private JScrollPane jScrollPane ;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;
    static ArrayList<String> friendsName=new ArrayList<>();
    static String playingMusic;

    public static void setPlayingMusic(String playing) {
        playingMusic = playing;
        System.out.println(playingMusic+"&&&&");
    }

    public static String getPlayingMusic() {
        return playingMusic;
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
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
       private JButton friendsbtn = new JButton();

    public RightPanel() {

        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 30, 500, 50);
        jScrollPane.setBorder(null);


        this.setPreferredSize(new Dimension(210, 40));
        this.setVisible(true);
        this.setBackground(Color.black);
        this.setLayout( new BorderLayout());



        EmptyBorder border = new EmptyBorder(5, 30, 5, 5);
        JLabel friends = new JLabel();
        JPanel panel = new JPanel();
        JPanel activityPanel = new JPanel();
        activityPanel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Color.black);
        //panel.setPreferredSize(new Dimension(500,40));
        //activityPanel.setPreferredSize(new Dimension(500,40));

        this.setBorder(border);
        ImageIcon friendIcon = new ImageIcon(new ImageIcon("src\\icons\\friend.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        friendsbtn.setIcon(friendIcon);
        friendsbtn.setBackground(Color.BLACK);
        friendsbtn.setBorder(null);
        friends.setText("Friends activity");
        friends.setFont(new Font("Arial", Font.BOLD, 15));
        friends.setBackground(Color.black);
        friends.setForeground(Color.white);




        friendsbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                JFrame friendsNumber=new JFrame();
                JFrame friendsName=new JFrame();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.Y_AXIS));



                String friendsNumber1=JOptionPane.showInputDialog(friendsNumber,"Enter number of your friends..");
                int number=Integer.parseInt(friendsNumber1);
                for (int i = 0; i <number ; i++) {
                    JTextField Field = new JTextField(5);
                    myPanel.add(new JLabel("your friends name"));
                    myPanel.add(Field);
                    int result=JOptionPane.showConfirmDialog(null, myPanel,
                            "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                    String Text=Field.getText();
                    System.out.println(Text);
                    Client.addFriends(Text);
                    RightPanel.friendsName.add(Text);

                }


                activityPanel.setLayout(new GridLayout(RightPanel.friendsName.size(),1));
                for (String str:RightPanel.friendsName) {
                    JButton button = new JButton();
                    button.setBorder(null);
                    activityPanel.add(button);
                    button.setBackground(Color.black);
                    JLabel label=new JLabel(str);
                    JLabel label1=new JLabel(getPlayingMusic());
                    label.setFont(new Font("Arial", Font.BOLD, 15));
                    label.setForeground(Color.white);
                    label1.setFont(new Font("Arial", Font.BOLD, 10));
                    label1.setForeground(Color.white);
                    System.out.println(getPlayingMusic()+"***");
                    label.setForeground(Color.BLACK);
                    button.setLayout(new BoxLayout(button,BoxLayout.Y_AXIS));
                    button.add(label);
                    button.add(label1);


                }



            }
        });

        this.revalidate();





        panel.add(friendsbtn);
        panel.add(friends);
        this.add(panel,BorderLayout.NORTH);
        this.add(activityPanel);
    }

    public JScrollPane getJScrollPane() {

        return jScrollPane;

    }

}
