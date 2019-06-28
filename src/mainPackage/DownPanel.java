package mainPackage;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import mainPackage.PausablePlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.util.Collections;

public class DownPanel {

    static JPanel downPanel = new JPanel();
    JPanel volume = new JPanel();
    JPanel emptyPanel = new JPanel();
    private JButton pause = new JButton();
    private JButton resume = new JButton();
    private JButton nextSong1 = new JButton();
    private JButton previousSong = new JButton();
    private JButton shuffle1 = new JButton();
    private JButton repeat1 = new JButton();
    private JButton reset = new JButton();
    private JButton soundBtn = new JButton();
    JPanel playIcons = new JPanel();
    static JPanel downCenterPanel = new JPanel();
    static PausablePlayer pausablePlayer;
    private BufferedInputStream bufferedInputStream;
    FileInputStream fileInputStream;


    static JSlider currentSlider = null;

    static File musicToPlay = null;
    int index;
    private ImageIcon shuffle;
    private ImageIcon repeat;
    static Playing playing = null;
    static boolean shufflesongs = false;


    public static void setPlaying(Playing playing1) {
        playing = playing1;
    }


    public static void setPausablePlayer(PausablePlayer Player, File music) throws InvalidDataException, IOException, UnsupportedTagException {

        pausablePlayer = Player;
        musicToPlay = music;
        Mp3File mp3File = new Mp3File(music);

    }

    public DownPanel() {

        ImageIcon pause_Icon = new ImageIcon(new ImageIcon("src\\icons\\pause.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ImageIcon nextSong = new ImageIcon(new ImageIcon("src\\icons\\nextSong.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ImageIcon play = new ImageIcon(new ImageIcon("src\\icons\\play.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ImageIcon previous1 = new ImageIcon(new ImageIcon("src\\icons\\previousSong.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ImageIcon sound1 = new ImageIcon(new ImageIcon("src\\icons\\sound.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        shuffle = new ImageIcon(new ImageIcon("src\\icons\\shuffle.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        repeat = new ImageIcon(new ImageIcon("src\\icons\\repeat.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        playIcons.setLayout(new GridLayout(1, 5));
        playIcons.setBackground(new Color(58, 58, 58));
        soundBtn.setIcon(sound1);
        soundBtn.setBackground(new Color(58,58,58));
        soundBtn.setBorder(null);
        reset.setText("reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 10));
        reset.setBorder(null);
        reset.setBackground(new Color(58, 58, 58));
        reset.setForeground(Color.pink);
        reset.setPreferredSize(new Dimension(5, 2));
        nextSong1.setIcon(nextSong);
        pause.setIcon(pause_Icon);
        resume.setIcon(play);
        pause.setBorder(null);
        pause.setBackground(new Color(58, 58, 58));
        resume.setBorder(null);
        resume.setBackground(new Color(58, 58, 58));
        previousSong.setBorder(null);
        previousSong.setBackground(new Color(58, 58, 58));
        nextSong1.setBorder(null);
        nextSong1.setBackground(new Color(58, 58, 58));
        shuffle1.setBorder(null);
        shuffle1.setBackground(new Color(58, 58, 58));
        repeat1.setBorder(null);
        repeat1.setBackground(new Color(58, 58, 58));

        shuffle1.setIcon(shuffle);
        repeat1.setIcon(repeat);
        previousSong.setIcon(previous1);
        playIcons.add(shuffle1);
        playIcons.add(previousSong);
        playIcons.add(resume);
        playIcons.add(pause);
        playIcons.add(nextSong1);
        playIcons.add(repeat1);
        playIcons.add(reset);

        downCenterPanel.setLayout(new BorderLayout());
        downCenterPanel.setBackground(new Color(58, 58, 58));
        downCenterPanel.add(playIcons, BorderLayout.NORTH);

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Playing.updateWorker.setIspaused(true);

                pausablePlayer.pause();
            }
        });

        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Playing.updateWorker.setIspaused(false);
                pausablePlayer.resume();
            }
        });

        shuffle1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffle = new ImageIcon(new ImageIcon("src\\icons\\shuffle1.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                shuffle1.setIcon(shuffle);
                Library.shuffle();
                shufflesongs = true;

            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shufflesongs = false;
                shuffle = new ImageIcon(new ImageIcon("src\\icons\\shuffle.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                shuffle1.setIcon(shuffle);
            }
        });


//        nextSong1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                for (int i = 0; i < Library.getSongs().size(); i++) {
//
//                    if (Library.getSongs().get(i).equals(musicToPlay)) {
//
//                        int temp = i += 1;
//                        File file = Library.getSongs().get(temp);
//                        Mp3File mp3File = null;
//                        try {
//                            mp3File = new Mp3File(file);
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        } catch (UnsupportedTagException e1) {
//                            e1.printStackTrace();
//                        } catch (InvalidDataException e1) {
//                            e1.printStackTrace();
//                        }
//                        try {
//                            fileInputStream = new FileInputStream(file);
//                        } catch (FileNotFoundException e1) {
//                            e1.printStackTrace();
//                        }
//
//                        try {
//                            PausablePlayer player = new PausablePlayer(fileInputStream);
//
//
//                            try {
//                                Playing.setFile(file);
//                            } catch (InvalidDataException e1) {
//                                e1.printStackTrace();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            } catch (UnsupportedTagException e1) {
//                                e1.printStackTrace();
//                            }
//                            Playing.setPlayer(player);
//                            Playing.plaiyingSongs.add(player);
//
//                            try {
//                                try {
//                                    Playing.Play();
//                                } catch (ParseException ex) {
//                                    ex.printStackTrace();
//                                }
//                            } catch (InvalidDataException ex) {
//                                ex.printStackTrace();
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            } catch (UnsupportedTagException ex) {
//                                ex.printStackTrace();
//                            }
//
//                            DownPanel.addPlayingSongInfo(showSongs(mp3File));
//                            try {
//                                setPausablePlayer(player, file);
//                            } catch (InvalidDataException e1) {
//                                e1.printStackTrace();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            } catch (UnsupportedTagException e1) {
//                                e1.printStackTrace();
//                            }
//                        } catch (JavaLayerException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//
//
//                }
//
//
//            }
//
//
//        });
        System.out.println("here");

        nextSong1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayingActionListener();
            }
        });
        System.out.println("here");
        previousSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                for (int i = 0; i < Library.getSongs().size(); i++) {

                    if (Library.getSongs().get(i).equals(musicToPlay)) {

                        int temp = i -= 1;
                        if (temp < 0) {
                            System.out.println(temp);
                            System.out.println(Library.getSongs().size());
                            temp += Library.getSongs().size();
                            System.out.println(temp);
                            System.out.println(Library.getSongs());
                        }
                        System.out.println(temp);
                        System.out.println(Library.getSongs().get(temp));
                        File file = Library.getSongs().get(temp);

                        try {
                            fileInputStream = new FileInputStream(file);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }

                        try {
                            PausablePlayer player = new PausablePlayer(fileInputStream);

                            try {
                                Mp3File mp3File = new Mp3File(file);
                                Playing.setFile(file);
                                Playing.setPlayer(player);
                                Playing.plaiyingSongs.add(player);
                                try {
                                    Playing.Play();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                                DownPanel.addPlayingSongInfo(showSongs(mp3File));
                                setPausablePlayer(player, file);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            }


                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                    }


                }


            }
        });
        VolumeContoroller volumeContoroller= new VolumeContoroller();


        downPanel.setVisible(true);
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(new Color(58, 58, 58));
        emptyPanel.setBackground(new Color(58, 58, 58));
        emptyPanel.setPreferredSize(new Dimension(250, 85));
        downPanel.add(emptyPanel, BorderLayout.WEST);
        volume.setBackground(new Color(58, 58, 58));
        volume.setPreferredSize(new Dimension(200, 0));
        volume.setLayout(new BoxLayout(volume,BoxLayout.X_AXIS));
        downPanel.setPreferredSize(new Dimension(200, 85));
        JSlider jSlider = new JSlider();
        jSlider.setPreferredSize(new Dimension(100, 50));
        jSlider.setBackground(new Color(58, 58, 58));
        volume.add(soundBtn);
        EmptyBorder b = new EmptyBorder(0,0,0,5);
        volume.setBorder(b);
        volume.add(volumeContoroller);
        downPanel.add(downCenterPanel, BorderLayout.CENTER);
        downPanel.add(volume, BorderLayout.EAST);
        //downPanel.add(volumeContoroller, BorderLayout.EAST);

    }

    public JPanel getDownPanel() {
        return downPanel;
    }

    public static void addPlayingSongInfo(JPanel jPanel) {

        downPanel.add(jPanel, BorderLayout.WEST);
        downPanel.revalidate();
        //downPanel.repaint();
    }

    public void addPauseAndResume(JPanel jPanel) {

        downPanel.add(jPanel, BorderLayout.CENTER);
        downPanel.revalidate();
        //downPanel.repaint();
    }


    static JPanel showSongs(Mp3File mp3File) {
        JPanel showPlayingSong = new JPanel();

        ID3v2 id3v2 = mp3File.getId3v2Tag();
        ID3v1 id3v1 = mp3File.getId3v1Tag();

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

        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

        playingSongImg.add(new JLabel(imageIcon));
        EmptyBorder border1 = new EmptyBorder(0, 5, 5, 5);
        playingSongImg.setBorder(border1);
        if (id3v2.getArtist() != null) {
            JLabel Artist = new JLabel(id3v2.getArtist());
            Artist.setForeground(Color.WHITE);
            Artist.setFont(new Font("Arial", Font.PLAIN, 10));
            playingSonglabel.add(Artist);

        } else if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getArtist() != null) {

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
        EmptyBorder border2 = new EmptyBorder(5, 50, 0, 0);
        playingSonglabel.setBorder(border2);
        showPlayingSong.add(playingSongImg, BorderLayout.WEST);
        showPlayingSong.add(playingSonglabel, BorderLayout.EAST);
        showPlayingSong.setBackground(new Color(58, 58, 58));
        showPlayingSong.setPreferredSize(new Dimension(250, 0));

        return showPlayingSong;
    }

    public static void addNewSlider(JSlider slider) {

        if (currentSlider != null) {

            downCenterPanel.remove(currentSlider);
            downCenterPanel.add(slider, BorderLayout.PAGE_END);
            currentSlider = slider;
        }

        downCenterPanel.add(slider, BorderLayout.PAGE_END);
        currentSlider = slider;

    }
}