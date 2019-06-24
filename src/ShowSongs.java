import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ShowSongs extends JButton {

    public ShowSongs() {


        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setText("Songs");
        this.setBackground(Color.BLACK);
        this.setForeground(Color.white);
        this.setBorder(null);

    }

    public JPanel CreatButtonFromSongs() throws InvalidDataException, IOException, UnsupportedTagException {
        JPanel songsPanel = new JPanel();
        songsPanel.setVisible(true);
        GridBagLayout gridBagLayout=new GridBagLayout();
       // gridBagLayout.layoutContainer(songsPanel);
        songsPanel.setLayout(new FlowLayout());
        songsPanel.setPreferredSize(new Dimension(300,100));
        GridBagConstraints gbc = new GridBagConstraints();
        ArrayList<PausablePlayer> playedSongs=new ArrayList<PausablePlayer>();

        // songsPanel.setLayout(new FlowLayout());
        songsPanel.setBackground(new Color(58,58,58));
        JLabel Title = null;
        JButton songImage = null;
        int x=0;
        int y=0;




//            Iterator iterator=Library.getSongs().iterator();
        for (int i = 0; i < Library.getSongs().size(); i++) {
            File temp = Library.getSongs().get(i);
            Mp3File mp3File = new Mp3File(temp);

            if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getTitle()!=null) {
                ID3v1 id3v1 = mp3File.getId3v1Tag();
                Title = new JLabel();
                Title.setVisible(true);

                Title.setText(id3v1.getTitle());



                Title.setFont(new Font("Arial", Font.PLAIN, 13));
                Title.setForeground(Color.white);
            }

            else if (mp3File.hasId3v2Tag()&&mp3File.getId3v2Tag().getTitle()!=null){

                ID3v2 id3v2 = mp3File.getId3v2Tag();
                Title = new JLabel();
                Title.setVisible(true);

                Title.setText(id3v2.getTitle());

                Title.setFont(new Font("Arial", Font.PLAIN, 13));
                Title.setForeground(Color.white);

            }



            if (mp3File.hasId3v2Tag()) {


                ID3v2 id3v2 = mp3File.getId3v2Tag();
                ID3v1 id3v1=mp3File.getId3v1Tag();
                songImage = new JButton();
                songImage.setBorder(null);
                songImage.setVisible(true);


                songImage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel showPlayingSong = new JPanel();
                        JPanel cuurentPanel=null;
                        showPlayingSong.setLayout(new BorderLayout());
                        showPlayingSong.setBackground(Color.black);
                        byte[] imageData = id3v2.getAlbumImage();
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(imageData));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));

                        showPlayingSong.add(new JLabel(imageIcon), BorderLayout.WEST);
                        if(id3v2.getArtist()!=null){
                        JLabel Artist = new JLabel(id3v2.getArtist());
                            Artist.setForeground(Color.WHITE);
                            showPlayingSong.add(Artist, BorderLayout.EAST);
                        }

                        else if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getArtist()!=null){

                            JLabel Artist = new JLabel(id3v1.getArtist());
                            Artist.setForeground(Color.WHITE);
                            showPlayingSong.add(Artist, BorderLayout.EAST);

                        }

                        if(id3v2.getTitle()!=null){

                            JLabel Title = new JLabel(id3v2.getTitle());
                            Title.setForeground(Color.WHITE);
                            showPlayingSong.add(Title, BorderLayout.EAST);
                        }

                        else if(id3v1.getAlbum()!=null){

                            JLabel Title = new JLabel(id3v1.getTitle());
                            Title.setForeground(Color.WHITE);
                            showPlayingSong.add(Title, BorderLayout.CENTER);

                        }

                        if (id3v2.getAlbum()!=null){


                            JLabel album = new JLabel(id3v2.getAlbum());
                            album.setForeground(Color.WHITE);
                            showPlayingSong.add(album, BorderLayout.EAST);
                        }

                        else if (id3v1.getAlbum()!=null){
                            JLabel album = new JLabel(id3v1.getAlbum());
                            album.setForeground(Color.WHITE);
                            showPlayingSong.add(album, BorderLayout.EAST);

                        }
                        EmptyBorder border = new EmptyBorder(5, 5, 5, 80);
                        showPlayingSong.setBorder(border);
                        showPlayingSong.setBackground(new Color(58,58,58));
                        showPlayingSong.setPreferredSize(new Dimension(318,0));


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
                        try {
                            Playing playing=new Playing();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        Playing.plaiyingSongs.add(player);


                        try {
                            DownPanel.setPausablePlayer(player,temp);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        // PlayMusic playMusic1 = new PlayMusic(temp, player);
                        try {
                            Playing.Play();
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


                       /* FileInputStream in= null;
                        try {
                            in = new FileInputStream(temp);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }

                        PausablePlayer player= null;
                        try {
                            player = new PausablePlayer(in);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            if (playedSongs.size()!=0){
                                playedSongs.get(playedSongs.size()-1).close();
                                PlayMusic playMusic = new PlayMusic(temp,player);
                                DownPanel.addPlayingSongInfo(showPlayingSong);
                                DownPanel.downPanel.revalidate();
                            }

                            PlayMusic playMusic = new PlayMusic(temp,player);
                            DownPanel.addPlayingSongInfo(showPlayingSong);
                            player.play();


                            playedSongs.add(player);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        // playMusic.playThread.start();*/



                    }
                });

                byte[] imageData = id3v2.getAlbumImage();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                songImage.setIcon(imageIcon);
                String mimeType = id3v2.getAlbumImageMimeType();
                System.out.println(mimeType);
                Title.setText(id3v2.getTitle());

            }


            JPanel songData = new JPanel();
            songData.setBackground(Color.BLACK);
            songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
            songData.add(songImage);
            songData.add(Title);
            songData.setMinimumSize(new Dimension(200, 200));


            /*double[][] weights = gridBagLayout.getLayoutWeights();
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < weights[k].length; j++) {
                    weights[k][j] = 1;
                }
            }
            gridBagLayout.columnWeights = weights[0];
            gridBagLayout.rowWeights = weights[1];*/

/*            int top = 20;
            int left = 20;
            int bottom = 2;
            int right = 40;
            gbc.insets = new Insets(top, left, bottom, right);
            gridBagLayout.setConstraints(songData,gbc);*/






            songsPanel.add(songData,gbc);


        }

        songsPanel.setBackground(Color.BLACK);

        return songsPanel;
        // return test;

    }


}

