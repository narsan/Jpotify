import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
       // songsPanel.setLayout(new GridBagLayout());
        songsPanel.setLayout(new BoxLayout(songsPanel,BoxLayout.X_AXIS));
        songsPanel.setLayout(new GridLayout(0,Library.getSongs().size()));
        ArrayList<PausablePlayer> playedSongs=new ArrayList<PausablePlayer>();
        ArrayList<PlayMusic> playMusics=new ArrayList<>();


        // songsPanel.setLayout(new FlowLayout());
        songsPanel.setBackground(new Color(58,58,58));
        //songsPanel.setBackground(Color.BLACK);
        JLabel Title = null;
        JButton songImage = null;
        PlayMusic playMusic=null;




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

                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

                        showPlayingSong.add(new JLabel(imageIcon), BorderLayout.WEST);
                        if(id3v2.getArtist()!=null){
                        JLabel Artist = new JLabel(id3v2.getArtist());
                            Artist.setForeground(Color.WHITE);
                            showPlayingSong.add(Artist, BorderLayout.PAGE_END);
                        }

                        else if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getArtist()!=null){

                            JLabel Artist = new JLabel(id3v1.getArtist());
                            Artist.setForeground(Color.WHITE);
                            showPlayingSong.add(Artist, BorderLayout.PAGE_END);

                        }

                        if(id3v2.getTitle()!=null){

                            JLabel Title = new JLabel(id3v2.getTitle());
                            Title.setForeground(Color.WHITE);
                            showPlayingSong.add(Title, BorderLayout.CENTER);
                        }

                        else if(id3v1.getAlbum()!=null){

                            JLabel Title = new JLabel(id3v1.getTitle());
                            Title.setForeground(Color.WHITE);
                            showPlayingSong.add(Title, BorderLayout.CENTER);

                        }

                        if (id3v2.getAlbum()!=null){


                            JLabel album = new JLabel(id3v2.getAlbum());
                            album.setForeground(Color.WHITE);
                            showPlayingSong.add(album, BorderLayout.NORTH);
                        }

                        else if (id3v1.getAlbum()!=null){
                            JLabel album = new JLabel(id3v1.getAlbum());
                            album.setForeground(Color.WHITE);
                            showPlayingSong.add(album, BorderLayout.NORTH);

                        }
                        showPlayingSong.setBackground(new Color(58,58,58));
                        showPlayingSong.setPreferredSize(new Dimension(318,0));

                        FileInputStream in= null;
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


                            Playing.setFile(temp);
                            Playing.setPlayer(player);
                            Playing.plaiyingSongs.add(player);
                            PlayMusic playMusic1=new PlayMusic(temp,player);
                        try {
                            Playing.Play();
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }

                        if (cuurentPanel!=null){

                            cuurentPanel.setVisible(false);
                            DownPanel.downPanel.remove(cuurentPanel);
                            cuurentPanel=showPlayingSong;
                            DownPanel.addPlayingSongInfo(showPlayingSong);
                            DownPanel.downPanel.revalidate();
                        }

                        else {

                            cuurentPanel=showPlayingSong;

                            DownPanel.addPlayingSongInfo(showPlayingSong);
                            DownPanel.downPanel.revalidate();

                        }




                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = new Date();
                            File file=Library.file;
                            PrintWriter writer= null;
                            try {
                                writer = new PrintWriter(file);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }


                        /*try {
                           // player.play();
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }*/

                        writer.println(dateFormat.format(date)+"  "+temp);

                            playedSongs.add(player);



                    }
                });

                byte[] imageData = id3v2.getAlbumImage();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                songImage.setIcon(imageIcon);
                String mimeType = id3v2.getAlbumImageMimeType();
                System.out.println(mimeType);
                Title.setText(id3v2.getTitle());

            }


            JPanel songData = new JPanel();
            songData.setBackground(Color.black);
            songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
            songData.add(songImage);
            songData.add(Title);
            songData.setMinimumSize(new Dimension(200, 200));
            songsPanel.add(songData);


        }

        return songsPanel;
        // return test;

    }


}

