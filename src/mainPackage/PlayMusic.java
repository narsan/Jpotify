package mainPackage;

import javazoom.jl.player.Player;

import java.io.*;

public class PlayMusic {

   /* JPanel playIcons = new JPanel();
    JPanel downCenterPanel = new JPanel();*/

    private File musicToPlay;
    int index;
    /* private JButton pause= new JButton();
     private JButton resume= new JButton();
     private JButton nextSong1= new JButton() ;
     private JButton previousSong= new JButton() ;*/
    private BufferedInputStream bufferedInputStream;
    FileInputStream fileInputStream;
    Player player;
    private UpdateWorker updateWorker;
    private PausablePlayer pausablePlayer;


    public PlayMusic() {

        //index = mainPackage.Library.getSongs().indexOf(file);




        /*ImageIcon pause_Icon = new ImageIcon(new ImageIcon("src\\icons\\pause.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
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
        downCenterPanel.add(playIcons, BorderLayout.NORTH);*/

        //  try {

           /* Mp3File mp3File = new Mp3File(file);
            JLabel totalTime=new JLabel();
            totalTime.setText("Total:"+mp3File.getLengthInSeconds());
            totalTime.setForeground(Color.pink);
            totalTime.setFont(new Font("Arial",Font.PLAIN,13));
            updateWorker = new mainPackage.UpdateWorker((int) mp3File.getLengthInSeconds());
            //downCenterPanel.add(mainPackage.UpdateWorker.getSlider(), BorderLayout.PAGE_END);*/
        //downCenterPanel.add(totalTime,BorderLayout.CENTER);
        //downCenterPanel.add(updateWorker.getTime(),BorderLayout.EAST);
            /*updateWorker.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }*/

        //mainPackage.DownPanel.addPauseAndResume(downCenterPanel);


       /* pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausablePlayer.pause();
                updateWorker.setIspaused(true);
            }
        });*/


       /* resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWorker.setIspaused(false);
                pausablePlayer.resume();
            }
        });
        nextSong1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = index + 1;
                mainPackage.PausablePlayer player1 = null;
                try {
                    FileInputStream in = null;
                    try {
                        in = new FileInputStream(mainPackage.Library.getSongs().get(temp));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    player1 = new mainPackage.PausablePlayer(in);
                    mainPackage.PlayMusic playMusic=new mainPackage.PlayMusic(mainPackage.Library.getSongs().get(temp),player1);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                pausablePlayer.close();
                try {
                    try {
                        Mp3File mp3File = new Mp3File(mainPackage.Library.getSongs().get(temp));
                        mainPackage.DownPanel.addPlayingSongInfo(showSongs(mp3File));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    }
                    player1.play();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });
        previousSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(index);
                int temp = index - 1;
                mainPackage.PausablePlayer player1 = null;
                try {
                    FileInputStream in = null;
                    try {
                        in = new FileInputStream(mainPackage.Library.getSongs().get(temp));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    player1 = new mainPackage.PausablePlayer(in);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                pausablePlayer.close();
                try {
                    try {
                        Mp3File mp3File = new Mp3File(mainPackage.Library.getSongs().get(temp));
                        mainPackage.DownPanel.addPlayingSongInfo(showSongs(mp3File));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    }
                    player1.play();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });
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
        return showPlayingSong;*/
    }
}