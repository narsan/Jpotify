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
                        ID3VData id3VData = new ID3VData();
                        id3VData.addData(id3v2,id3v1,mp3File,temp);
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

