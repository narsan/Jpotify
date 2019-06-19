import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ShowSongsToCreatePlayList  {

    static JPanel showSongs=new JPanel();

    public JPanel getShowSongs() {
        return showSongs;
    }

    public JPanel songsName() {

        showSongs.setLayout(new BoxLayout(showSongs, BoxLayout.Y_AXIS));
        showSongs.setBackground(Color.BLACK);
        for (int i = 0; i < Library.getSongs().size(); i++) {


            JButton songsName = new JButton();
            songsName.setBorder(null);
            songsName.setBackground(Color.BLACK);
            songsName.setForeground(Color.white);
            try {
                Mp3File mp3File = new Mp3File(Library.getSongs().get(i));
                if (mp3File.hasId3v1Tag()) {


                    songsName.setText(mp3File.getId3v1Tag().getTitle());
                    System.out.println(mp3File.getId3v1Tag().getTitle());

                    showSongs.add(songsName);
                    showSongs.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }

        }

        return showSongs;


    }

}
