package mainPackage;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class UserPanel extends JPanel {

    private JLabel userAccount = new JLabel();
    protected static String name ;
    private String search;


    public  void setName(String name) {
        this.name = name;
        userAccount.setText(name);
    }


   public static String getName1(){

        return name;
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
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
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
            //TODO
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton songImage = null;
                JLabel Title = null;
                search = field.getText();
                CenterPanelScroller centerPanel = new CenterPanelScroller();
                centerPanel.setVisible(true);

                GridBagLayout gridBagLayout = new GridBagLayout();
                // gridBagLayout.layoutContainer(songsPanel);
                centerPanel.setLayout(new FlowLayout());
                centerPanel.setPreferredSize(new Dimension(300, 100));
                GridBagConstraints gbc = new GridBagConstraints();
                centerPanel.setBackground(new Color(58, 58, 58));

                for (int i = 0; i < Library.getSongs().size(); i++) {

                    if (Library.getSongs().get(i).getName().contains(search)) {

                        File temp = Library.getSongs().get(i);
                        Mp3File mp3File = null;
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
                                    in = new FileInputStream(temp);
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
                                    Playing.setFile(temp);
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
                                    DownPanel.setPausablePlayer(player, temp);
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


                       /* FileInputStream in= null;
                        try {
                            in = new FileInputStream(temp);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        mainPackage.PausablePlayer player= null;
                        try {
                            player = new mainPackage.PausablePlayer(in);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            if (playedSongs.size()!=0){
                                playedSongs.get(playedSongs.size()-1).close();
                                mainPackage.PlayMusic playMusic = new mainPackage.PlayMusic(temp,player);
                                mainPackage.DownPanel.addPlayingSongInfo(showPlayingSong);
                                mainPackage.DownPanel.downPanel.revalidate();
                            }
                            mainPackage.PlayMusic playMusic = new mainPackage.PlayMusic(temp,player);
                            mainPackage.DownPanel.addPlayingSongInfo(showPlayingSong);
                            player.play();
                            playedSongs.add(player);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        // playMusic.playThread.start();*/


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
                }

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
