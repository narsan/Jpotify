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

public class DownPanel {

    static JPanel downPanel = new JPanel();
    JPanel volume = new JPanel();
    private JButton pause = new JButton();
    private JButton resume = new JButton();
    private JButton nextSong1 = new JButton();
    private JButton previousSong = new JButton();
    JPanel playIcons = new JPanel();
   static JPanel downCenterPanel = new JPanel();
    static PausablePlayer pausablePlayer;
    private BufferedInputStream bufferedInputStream;
    FileInputStream fileInputStream;

   static JSlider currentSlider=null;

    static File musicToPlay=null;
     int index;
    static Playing playing=null;


    public  static void setPlaying(Playing playing1) {
        playing = playing1;
    }


    public static void setPausablePlayer(PausablePlayer Player,File music) throws InvalidDataException, IOException, UnsupportedTagException {

        pausablePlayer = Player;
        musicToPlay = music;
        Mp3File mp3File=new Mp3File(music);

    }

    public DownPanel() {

        ImageIcon pause_Icon = new ImageIcon(new ImageIcon("src\\icons\\pause.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon nextSong = new ImageIcon(new ImageIcon("src\\icons\\nextSong.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon play = new ImageIcon(new ImageIcon("src\\icons\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon previous1 = new ImageIcon(new ImageIcon("src\\icons\\previousSong.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playIcons.setLayout(new GridLayout(1, 2));
        //playIcons.setLayout(new GridBagLayout());
        playIcons.setBackground(new Color(58, 58, 58));
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

        previousSong.setIcon(previous1);
        playIcons.add(previousSong);
        playIcons.add(resume);
        playIcons.add(pause);
        playIcons.add(nextSong1);

        EmptyBorder border = new EmptyBorder(5, 0, 0, 0);
        downCenterPanel.setBorder(border);
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

        nextSong1.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            for (int i = 0; i <Library.getSongs().size() ; i++) {

                                                if (Library.getSongs().get(i).equals(musicToPlay)){

                                                    int temp=i+=1;
                                                    File file=Library.getSongs().get(temp);
                                                    Mp3File mp3File= null;
                                                    try {
                                                        mp3File = new Mp3File(file);
                                                    } catch (IOException e1) {
                                                        e1.printStackTrace();
                                                    } catch (UnsupportedTagException e1) {
                                                        e1.printStackTrace();
                                                    } catch (InvalidDataException e1) {
                                                        e1.printStackTrace();
                                                    }
                                                    try {
                                                        fileInputStream=new FileInputStream(file);
                                                    } catch (FileNotFoundException e1) {
                                                        e1.printStackTrace();
                                                    }

                                                    try {
                                                        PausablePlayer player=new PausablePlayer(fileInputStream);


                                                        try {
                                                            Playing.setFile(file);
                                                        } catch (InvalidDataException e1) {
                                                            e1.printStackTrace();
                                                        } catch (IOException e1) {
                                                            e1.printStackTrace();
                                                        } catch (UnsupportedTagException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        Playing.setPlayer(player);
                                                        Playing.plaiyingSongs.add(player);
                                                        try {
                                                            Playing.Play();
                                                        } catch (InvalidDataException e1) {
                                                            e1.printStackTrace();
                                                        } catch (IOException e1) {
                                                            e1.printStackTrace();
                                                        } catch (UnsupportedTagException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        DownPanel.addPlayingSongInfo(showSongs(mp3File));
                                                        try {
                                                            setPausablePlayer(player,file);
                                                        } catch (InvalidDataException e1) {
                                                            e1.printStackTrace();
                                                        } catch (IOException e1) {
                                                            e1.printStackTrace();
                                                        } catch (UnsupportedTagException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                    } catch (JavaLayerException e1) {
                                                        e1.printStackTrace();
                                                    }
                                                }


                                            }



                                        }


                                    });

        previousSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                for (int i = 0; i <Library.getSongs().size() ; i++) {

                    if (Library.getSongs().get(i).equals(musicToPlay)){

                        int temp=i-=1;
                        File file=Library.getSongs().get(temp);

                        try {
                            fileInputStream=new FileInputStream(file);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }

                        try {
                            PausablePlayer player=new PausablePlayer(fileInputStream);

                            try {
                                Mp3File  mp3File = new Mp3File(file);
                                Playing.setFile(file);
                                Playing.setPlayer(player);
                                Playing.plaiyingSongs.add(player);
                                Playing.Play();
                                DownPanel.addPlayingSongInfo(showSongs(mp3File));
                                setPausablePlayer(player,file);
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




        downPanel.setVisible(true);
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(new Color(58, 58, 58));
        volume.setBackground(new Color(58, 58, 58));
        volume.setPreferredSize(new Dimension(225, 0));
        downPanel.setPreferredSize(new Dimension(300, 65));
        JSlider jSlider = new JSlider();
        jSlider.setPreferredSize(new Dimension(100, 50));
        jSlider.setBackground(new Color(58, 58, 58));
        volume.add(jSlider, BorderLayout.PAGE_END);
        downPanel.add(volume, BorderLayout.EAST);
        downPanel.add(downCenterPanel, BorderLayout.CENTER);

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


    public JPanel showSongs(Mp3File mp3File) {
        JPanel showPlayingSong = new JPanel();

        ID3v2 id3v2 = mp3File.getId3v2Tag();
        ID3v1 id3v1 = mp3File.getId3v1Tag();

        showPlayingSong.setLayout(new BorderLayout());
        showPlayingSong.setBackground(Color.black);
        byte[] imageData = id3v2.getAlbumImage();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(imageData));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

        showPlayingSong.add(new JLabel(imageIcon), BorderLayout.WEST);
        if (id3v2.getArtist() != null) {
            JLabel Artist = new JLabel(id3v2.getArtist());
            Artist.setForeground(Color.WHITE);
            showPlayingSong.add(Artist, BorderLayout.PAGE_END);
        } else if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getArtist() != null) {

            JLabel Artist = new JLabel(id3v1.getArtist());
            Artist.setForeground(Color.WHITE);
            showPlayingSong.add(Artist, BorderLayout.PAGE_END);

        }

        if (id3v2.getTitle() != null) {

            JLabel Title = new JLabel(id3v2.getTitle());
            Title.setForeground(Color.WHITE);
            showPlayingSong.add(Title, BorderLayout.CENTER);
        } else if (id3v1.getAlbum() != null) {

            JLabel Title = new JLabel(id3v1.getTitle());
            Title.setForeground(Color.WHITE);
            showPlayingSong.add(Title, BorderLayout.CENTER);

        }

        if (id3v2.getAlbum() != null) {


            JLabel album = new JLabel(id3v2.getAlbum());
            album.setForeground(Color.WHITE);
            showPlayingSong.add(album, BorderLayout.NORTH);
        } else if (id3v1.getAlbum() != null) {
            JLabel album = new JLabel(id3v1.getAlbum());
            album.setForeground(Color.WHITE);
            showPlayingSong.add(album, BorderLayout.NORTH);

        }
        showPlayingSong.setBackground(new Color(58, 58, 58));
        showPlayingSong.setPreferredSize(new Dimension(318, 0));

        return showPlayingSong;
    }

    public  static void  addNewSlider(JSlider slider){

        if (currentSlider!=null){

            downCenterPanel.remove(currentSlider);
            downCenterPanel.add(slider,BorderLayout.PAGE_END);
            currentSlider=slider;
        }

        downCenterPanel.add(slider,BorderLayout.PAGE_END);
        currentSlider=slider;

    }

}
