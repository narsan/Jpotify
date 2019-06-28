package mainPackage;

import Network2.Client;
import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class RightPanel extends JPanel implements Scrollable {


    private JScrollPane jScrollPane ;
    private static final int PREF_W = 900;
    private static final int PREF_H = 800;
    private static final int VP_WIDTH = 200;
    private static final int VP_HEIGHT = 448;
    static ArrayList<String> friendsName=new ArrayList<>();
    static String playingMusic;
    static String from;

    public static String getPlayingMusic() {
        return playingMusic;
    }


    public static void setPlayingMusic(String playing,String str) {
        playingMusic = playing;
        from=str;
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
        jScrollPane.setBounds(50, 30, 300, 50);
        jScrollPane.setBorder(null);


        this.setPreferredSize(new Dimension(250, 40));
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout( new BorderLayout());



        EmptyBorder border = new EmptyBorder(5, 30, 5, 5);
        JLabel friends = new JLabel();
        JPanel panel = new JPanel();
        JPanel activityPanel = new JPanel();
        activityPanel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Color.black);
      //  panel.setPreferredSize(new Dimension(210,40));

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
                ArrayList<JButton> buttons=new ArrayList<>();



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
                            "Please enter your friends", JOptionPane.OK_CANCEL_OPTION);
                    String Text=Field.getText();
                    System.out.println(Text);
                    Client.addFriends(Text);
                    RightPanel.friendsName.add(Text);

                }


                activityPanel.setLayout(new GridLayout(RightPanel.friendsName.size(),1));
                ArrayList<File> songsInShare=new ArrayList<>();
                for (String str:RightPanel.friendsName) {
                    //JButton button = new JButton();

                    JButton button = new JButton();
                    buttons.add(button);
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


                    label.setForeground(Color.white);

                    button.setLayout(new BoxLayout(button,BoxLayout.Y_AXIS));
                    button.add(label);
                    button.add(label1);


                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            JButton songImage = null;
                            JLabel Title = null;
                            CenterPanelScroller centerPanel = new CenterPanelScroller();
                            centerPanel.setVisible(true);
                            GridBagConstraints gbc = new GridBagConstraints();

                            centerPanel.setLayout(new FlowLayout());
                            centerPanel.setPreferredSize(new Dimension(300, 100));
                            centerPanel.setBackground(new Color(58, 58, 58));
                            //File filesFolder = new File(str);
                            JFileChooser jFileChooser = new JFileChooser();
                            jFileChooser.setMultiSelectionEnabled(true);
                            int res = jFileChooser.showOpenDialog(null);
                            Mp3File mp3File = null;
                            File temp=null;
                            if (res == JFileChooser.APPROVE_OPTION) {
                              temp = jFileChooser.getSelectedFile();
                                //songsInShare.add(path);
                            }




                                try {
                                    mp3File = new Mp3File(temp);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedTagException e1) {
                                    e1.printStackTrace();
                                } catch (InvalidDataException e1) {
                                    e1.printStackTrace();
                                }

                                if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getTitle() != null) {
                                    ID3v1 id3v1 = mp3File.getId3v1Tag();
                                    Title = new JLabel();
                                    Title.setVisible(true);

                                    Title.setText(id3v1.getTitle());


                                    Title.setFont(new Font("Arial", Font.PLAIN, 13));
                                    Title.setForeground(Color.white);
                                } else if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getTitle() != null) {

                                    ID3v2 id3v2 = mp3File.getId3v2Tag();
                                    Title = new JLabel();
                                    Title.setVisible(true);

                                    Title.setText(id3v2.getTitle());

                                    Title.setFont(new Font("Arial", Font.PLAIN, 13));
                                    Title.setForeground(Color.white);

                                }


                                // if (mp3File.hasId3v2Tag()) {


                                ID3v2 id3v2 = mp3File.getId3v2Tag();
                                ID3v1 id3v1 = mp3File.getId3v1Tag();
                                songImage = new JButton();
                                songImage.setBorder(null);
                                songImage.setVisible(true);


                                Mp3File finalMp3File = mp3File;
                            File finalTemp = temp;
                            songImage.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JPanel showPlayingSong = new JPanel();
                                        showPlayingSong.setLayout(new BorderLayout());
                                        JPanel playingSongImg = new JPanel();
                                        playingSongImg.setBackground(new Color(58, 58, 58));
                                        playingSongImg.setPreferredSize(new Dimension(100, 80));
                                        JPanel playingSonglabel = new JPanel();
                                        playingSonglabel.setLayout(new BoxLayout(playingSonglabel, BoxLayout.Y_AXIS));
                                        playingSonglabel.setBackground(new Color(58, 58, 58));
                                        JPanel cuurentPanel = null;
                                        showPlayingSong.setLayout(new BorderLayout());
                                        showPlayingSong.setBackground(new Color(58, 58, 58));
                                        byte[] imageData = id3v2.getAlbumImage();
                                        BufferedImage img = null;
                                        try {
                                            img = ImageIO.read(new ByteArrayInputStream(imageData));
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }

                                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));

                                        playingSongImg.add(new JLabel(imageIcon));
                                        EmptyBorder border1 = new EmptyBorder(0, 2, 5, 2);
                                        playingSongImg.setBorder(border1);
                                        if (id3v2.getArtist() != null) {
                                            JLabel Artist = new JLabel(id3v2.getArtist());
                                            Artist.setForeground(Color.WHITE);
                                            Artist.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(Artist);
                                        } else if (finalMp3File.hasId3v1Tag() && finalMp3File.getId3v1Tag().getArtist() != null) {

                                            JLabel Artist = new JLabel(id3v1.getArtist());
                                            Artist.setForeground(Color.WHITE);
                                            Artist.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(Artist);


                                        }

                                        if (id3v2.getTitle() != null) {

                                            JLabel Title = new JLabel(id3v2.getTitle());
                                            Title.setForeground(Color.WHITE);
                                            Title.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(Title);
                                        } else if (id3v1.getAlbum() != null) {

                                            JLabel Title = new JLabel(id3v1.getTitle());
                                            Title.setForeground(Color.WHITE);
                                            Title.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(Title);


                                        }

                                        if (id3v2.getAlbum() != null) {

                                            JLabel album = new JLabel(id3v2.getAlbum());
                                            album.setForeground(Color.WHITE);
                                            album.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(album);
                                        } else if (id3v1.getAlbum() != null) {

                                            JLabel album = new JLabel(id3v1.getAlbum());
                                            album.setForeground(Color.WHITE);
                                            album.setFont(new Font("Arial", Font.PLAIN, 10));
                                            playingSonglabel.add(album);

                                        }
                                        showPlayingSong.add(playingSongImg, BorderLayout.WEST);
                                        showPlayingSong.add(playingSonglabel, BorderLayout.EAST);
                                        showPlayingSong.setBackground(new Color(58, 58, 58));
                                        showPlayingSong.setPreferredSize(new Dimension(250, 0));

                                        FileInputStream in = null;
                                        try {
                                            in = new FileInputStream(finalTemp);
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }

                                        PausablePlayer player = null;
                                        try {
                                            player = new PausablePlayer(in);
                                        } catch (JavaLayerException e1) {
                                            e1.printStackTrace();
                                        }


                                        try {
                                            Playing.setFile(finalTemp);
                                        } catch (InvalidDataException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        } catch (UnsupportedTagException e1) {
                                            e1.printStackTrace();
                                        }
                                        Playing.setPlayer(player);

                                        Playing.addTopalying(player);


                                        try {
                                            DownPanel.setPausablePlayer(player, finalTemp);
                                        } catch (InvalidDataException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        } catch (UnsupportedTagException e1) {
                                            e1.printStackTrace();
                                        }
                                        // mainPackage.PlayMusic playMusic1 = new mainPackage.PlayMusic(temp, player);


                                        try {
                                            Playing.Play();
                                            // System.out.println("title"+Playing.getTitle());

                                        } catch (Exception e1) {
                                            e1.printStackTrace();
                                        }


                                        if (cuurentPanel != null) {

                                            cuurentPanel.setVisible(false);
                                            DownPanel.downPanel.remove(cuurentPanel);
                                            cuurentPanel = showPlayingSong;
                                            DownPanel.addPlayingSongInfo(showPlayingSong);
                                            DownPanel.downPanel.revalidate();
                                        } else {

                                            cuurentPanel = showPlayingSong;

                                            DownPanel.addPlayingSongInfo(showPlayingSong);
                                            DownPanel.downPanel.revalidate();

                                        }




                                    }
                                });

                                byte[] imageData = id3v2.getAlbumImage();
                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                                songImage.setIcon(imageIcon);
                                String mimeType = id3v2.getAlbumImageMimeType();
                                System.out.println(mimeType);
                                if (id3v2.getTitle() != null) {

                                    Title.setText(id3v2.getTitle());
                                } else if (id3v1.getTitle() != null) {

                                    Title.setText(id3v1.getTitle());
                                }

                                //    }


                                JPanel songData = new JPanel();
                                songData.setBackground(Color.BLACK);
                                songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
                                songData.add(songImage);
                                songData.add(Title);
                                songData.setMinimumSize(new Dimension(200, 200));

                                centerPanel.add(songData, gbc);
                                centerPanel.setBackground(Color.BLACK);
                                MainFrame.refresh(centerPanel);







                        }
                    });
                   /* activityPanel.add(button);
                    button.setBackground(Color.cyan);
                    JLabel label=new JLabel(str);
                    JLabel label1=new JLabel(playingMusic);
                    System.out.println(playingMusic+"***");
                    label.setForeground(Color.BLACK);
                    button.add(label);
                    button.add(label1);*/

                }


              /*  JLabel label1=new JLabel(playingMusic);
                int index=RightPanel.friendsName.indexOf(from);
                JButton button=buttons.get(index);
                button.add(label1);*/




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
