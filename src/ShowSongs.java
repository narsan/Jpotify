import com.mpatric.mp3agic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ShowSongs extends JButton {

    public ShowSongs() {


        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setText("Songs");
        this.setBackground(Color.BLACK);
        this.setForeground(Color.white);
        this.setBorder(null);
    }

    public JPanel CreatButtonFromSongs() {
        JPanel songsPanel = new JPanel();
        songsPanel.setVisible(true);
        songsPanel.setLayout(new GridLayout(Library.getSongs().size(),0));

       // songsPanel.setLayout(new FlowLayout());
        songsPanel.setBackground(Color.black);
        JLabel Title=null;
        JButton songImage=null;


        for (int i = 0; i < Library.getSongs().size(); i++) {

            try {
                Mp3File mp3File = new Mp3File(Library.getSongs().get(i));
                File temp=Library.getSongs().get(i);
                System.out.println(Library.getSongs().size());
                if (mp3File.hasId3v1Tag()) {
                    ID3v1 id3v1 = mp3File.getId3v1Tag();
                    Title = new JLabel();
                    Title.setVisible(true);

                    //Title.setText(id3v1.getTitle());
                    System.out.println(id3v1.getTitle());


                    Title.setFont(new Font("Arial", Font.PLAIN, 10));
                    Title.setForeground(Color.white);
                }

                if (mp3File.hasId3v2Tag()) {



                    ID3v2 id3v2 = mp3File.getId3v2Tag();
                    songImage = new JButton();
                    songImage.setBorder(null);
                    songImage.setVisible(true);



                    songImage.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel showPlayingSong=new JPanel();
                            showPlayingSong.setLayout(new BorderLayout());
                            byte[] imageData = id3v2.getAlbumImage();
                            BufferedImage img = null;
                            try {
                                img = ImageIO.read(new ByteArrayInputStream(imageData));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));

                            showPlayingSong.add(new JLabel(imageIcon),BorderLayout.WEST);
                            JLabel Artist=new JLabel(id3v2.getArtist());
                            Artist.setForeground(Color.WHITE);
                            showPlayingSong.add(Artist,BorderLayout.CENTER);
                            JLabel Title=new JLabel(id3v2.getTitle());
                            Title.setForeground(Color.WHITE);
                            showPlayingSong.add(Title,BorderLayout.PAGE_END);
                            JLabel album=new JLabel(id3v2.getAlbum());
                            album.setForeground(Color.WHITE);
                            showPlayingSong.add(album,BorderLayout.NORTH);
                            DownPanel.addPlayingSongInfo(showPlayingSong);
                            showPlayingSong.setBackground(Color.BLACK);
                            PlayMusic playMusic=new PlayMusic(temp);
                            playMusic.playThread.start();
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


            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }


            JPanel songData = new JPanel();
            songData.setBackground(Color.black);
            songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
            songData.add(songImage);
            songData.add(Title);
            songData.setMinimumSize(new Dimension(200,200));
            songsPanel.add(songData);


        }

        return songsPanel;
        // return test;

    }


}

