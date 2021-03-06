package mainPackage;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShowAlbum extends JButton {

    private ArrayList<File> sameAlbum = new ArrayList<>();
    private ArrayList<ArrayList> albums = new ArrayList<>();
    private HashMap<ArrayList, String> albumsAndSongs = new HashMap<>();
    private JButton AlbumImage;


    public ShowAlbum() {


        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setText("Albums");
        this.setBackground(Color.BLACK);
        this.setForeground(Color.white);
        this.setBorder(null);
        AlbumImage = new JButton();
    }

    public CenterPanelScroller getAlbums() {


        ArrayList<String> AlbumNames = new ArrayList<>();
        boolean[] check = new boolean[Library.getSongs().size()];
        CenterPanelScroller Albums = new CenterPanelScroller();
        boolean flag = false;
        Albums.setBackground(Color.BLACK);
        Albums.setLayout(new GridLayout(0, Library.getSongs().size()));

        String albumName = "";
        for (int i = 0; i < Library.getSongs().size(); i++) {

            ArrayList<File> same = new ArrayList<>();
            same.add(Library.getSongs().get(i));


            for (int j = 0; j < Library.getSongs().size(); j++) {

                File file = Library.getSongs().get(i);

                try {
                    Mp3File mp3File = new Mp3File(file);
                    if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getAlbum() != null) {
                        ID3v2 id3v2 = mp3File.getId3v2Tag();

                        albumName = id3v2.getAlbum();

                    } else if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getAlbum() != null) {

                        ID3v1 id3v1 = mp3File.getId3v1Tag();

                        albumName = id3v1.getAlbum();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }


                File checkIfSame = Library.getSongs().get(j);

                try {
                    Mp3File mp3File = new Mp3File(checkIfSame);
                    if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getAlbum() != null) {

                        ID3v1 id3v1 = mp3File.getId3v2Tag();
                        ID3v1 id3v2 = mp3File.getId3v2Tag();

                        if (id3v2.getAlbum().equals(albumName)) {

                            if (!AlbumNames.contains(albumName) && i != j) {

                                same.add(checkIfSame);
                            }

                        }
                    } else if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getAlbum() != null) {

                        ID3v1 id3v1 = mp3File.getId3v2Tag();
                        ID3v1 id3v2 = mp3File.getId3v2Tag();

                        if (id3v1.getAlbum().equals(albumName)) {

                            if (!AlbumNames.contains(albumName) && i != j) {

                                same.add(checkIfSame);
                            }


                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }


            }

            if (same.size() >= 2) {

                albumsAndSongs.put(same, albumName);

                AlbumNames.add(albumName);
                JPanel AlbumInfo = showAlbum(same);
                Albums.add(AlbumInfo);
            }

        }


        return Albums;
    }

    public JButton getAlbumImage() {
        return AlbumImage;
    }

    public JPanel showAlbum(ArrayList<File> musicsFile) {
        JPanel showAlbumInfo = new JPanel();
        AlbumImage = new JButton();
        showAlbumInfo.setLayout(new BoxLayout(showAlbumInfo, BoxLayout.Y_AXIS));
        Mp3File mp3File = null;
        try {

            mp3File = new Mp3File(musicsFile.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getAlbum() != null) {

            ID3v2 id3v2 = mp3File.getId3v2Tag();
            String AlbumName = id3v2.getAlbum();
            JLabel AlbumTitle = new JLabel(AlbumName);
            AlbumTitle.setForeground(Color.WHITE);

            AlbumImage.setBorder(null);
            AlbumImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<File> songs = (ArrayList<File>) getKeyFromValue(albumsAndSongs, AlbumName);
                    try {
                        showSongsInAlbum(songs);
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            byte[] imageData = id3v2.getAlbumImage();
            BufferedImage img = null;
            try {
                img = ImageIO.read(new ByteArrayInputStream(imageData));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            showAlbumInfo.setBackground(Color.BLACK);
            AlbumImage.setIcon(imageIcon);
            showAlbumInfo.add(AlbumImage);
            showAlbumInfo.add(AlbumTitle);

        } else if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getAlbum() != null) {

            ID3v1 id3v1 = mp3File.getId3v1Tag();
            ID3v2 id3v2 = mp3File.getId3v2Tag();
            String AlbumName = id3v1.getAlbum();
            JLabel AlbumTitle = new JLabel(AlbumName);
            AlbumTitle.setForeground(Color.WHITE);

            AlbumImage.setBorder(null);
            AlbumImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<File> songs = (ArrayList<File>) getKeyFromValue(albumsAndSongs, AlbumName);
                    try {
                        showSongsInAlbum(songs);
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            byte[] imageData = id3v2.getAlbumImage();
            BufferedImage img = null;
            try {
                img = ImageIO.read(new ByteArrayInputStream(imageData));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            showAlbumInfo.setBackground(Color.BLACK);
            AlbumImage.setIcon(imageIcon);
            showAlbumInfo.add(AlbumImage);
            showAlbumInfo.add(AlbumTitle);


        }

        return showAlbumInfo;
    }

    public CenterPanelScroller showSongsInAlbum(ArrayList<File> songs) throws InvalidDataException, IOException, UnsupportedTagException {

        CenterPanelScroller songsPanel = new CenterPanelScroller();
        songsPanel.setVisible(true);
        GridBagLayout gridBagLayout = new GridBagLayout();

        songsPanel.setLayout(new FlowLayout());
        songsPanel.setPreferredSize(new Dimension(300, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        ArrayList<PausablePlayer> playedSongs = new ArrayList<PausablePlayer>();

        // songsPanel.setLayout(new FlowLayout());
        songsPanel.setBackground(new Color(58, 58, 58));
        JLabel Title = null;
        JButton songImage = null;

        Iterator iterator = albumsAndSongs.keySet().iterator();


        ArrayList<File> songsInAlbum = (ArrayList<File>) iterator.next();


        for (int j = 0; j < songs.size(); j++) {


            File temp = songs.get(j);
            Mp3File mp3File = new Mp3File(temp);

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





            ID3v2 id3v2 = mp3File.getId3v2Tag();
            ID3v1 id3v1 = mp3File.getId3v1Tag();
            songImage = new JButton();
            songImage.setBorder(null);
            songImage.setVisible(true);


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

                    try {
                        try {
                            try {
                                Playing.Play();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
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
                    Player player1 = null;
                    try {
                        player1 = new Player(in);
                    } catch (JavaLayerException e1) {
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
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            songImage.setIcon(imageIcon);
            String mimeType = id3v2.getAlbumImageMimeType();
            System.out.println(mimeType);
            if (id3v2.getTitle() != null) {

                Title.setText(id3v2.getTitle());
            } else if (id3v1.getTitle() != null) {

                Title.setText(id3v1.getTitle());

            }




            JPanel songData = new JPanel();
            songData.setBackground(Color.BLACK);
            songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
            songData.add(songImage);
            songData.add(Title);
            songData.setMinimumSize(new Dimension(200, 200));


            songsPanel.add(songData, gbc);


            songsPanel.setBackground(Color.black);

        }


        MainFrame.refresh(songsPanel);

        return songsPanel;


    }


    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}