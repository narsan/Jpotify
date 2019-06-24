import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ID3VData {

    public void addData(ID3v2 id3v2, ID3v1 id3v1, Mp3File mp3File, File temp) {

        JPanel showPlayingSong = new JPanel();
        showPlayingSong.setLayout(new BorderLayout());
        JPanel playingSongImg = new JPanel();
        playingSongImg.setBackground(new Color(58,58,58));
        playingSongImg.setPreferredSize(new Dimension(100,80));
        JPanel playingSonglabel = new JPanel();
        playingSonglabel.setLayout(new BoxLayout(playingSonglabel,BoxLayout.Y_AXIS));
        playingSonglabel.setBackground(new Color(58,58,58));
        JPanel cuurentPanel=null;
        showPlayingSong.setLayout(new BorderLayout());
        showPlayingSong.setBackground(new Color(58,58,58));
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
        if(id3v2.getArtist()!=null){
            JLabel Artist = new JLabel(id3v2.getArtist());
            Artist.setForeground(Color.WHITE);
            playingSonglabel.add(Artist);

        }

        else if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getArtist()!=null){

            JLabel Artist = new JLabel(id3v1.getArtist());
            Artist.setForeground(Color.WHITE);
            playingSonglabel.add(Artist);


        }

        if(id3v2.getTitle()!=null){

            JLabel Title = new JLabel(id3v2.getTitle());
            Title.setForeground(Color.WHITE);
            playingSonglabel.add(Title);
        }

        else if(id3v1.getAlbum()!=null){

            JLabel Title = new JLabel(id3v1.getTitle());
            Title.setForeground(Color.WHITE);
            playingSonglabel.add(Title);

        }

        if (id3v2.getAlbum()!=null){


            JLabel album = new JLabel(id3v2.getAlbum());
            album.setForeground(Color.WHITE);
            playingSonglabel.add(album);
        }

        else if (id3v1.getAlbum()!=null){
            JLabel album = new JLabel(id3v1.getAlbum());
            album.setForeground(Color.WHITE);
            playingSonglabel.add(album);

        }
        EmptyBorder border2 = new EmptyBorder(5, 30, 0, 0);
        playingSonglabel.setBorder(border2);
        showPlayingSong.add(playingSongImg,BorderLayout.WEST);
        showPlayingSong.add(playingSonglabel,BorderLayout.EAST);
        EmptyBorder border = new EmptyBorder(0, 0, 0, 80);
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


        Playing.setFile(temp);
        Playing.setPlayer(player);
        Playing.plaiyingSongs.add(player);
        PlayMusic playMusic1 = new PlayMusic(temp, player);
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

    }

}



