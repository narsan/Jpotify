import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ShowSongsToCreatePlayList {

    static JPanel showSongs = new JPanel();

    private  HashSet<Mp3File> songsInPlaylist = new HashSet<>();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();


    public JPanel getShowSongs() {
        return showSongs;
    }

    public JPanel songsName() {

        HashSet<Mp3File> songsInPlaylist1 = new HashSet<Mp3File>();
        ArrayList<Mp3File> songs = new ArrayList<>();

        showSongs.setBackground(Color.black);
        showSongs.setVisible(true);
        //TODO

        for (int i = 0; i < Library.getSongs().size(); i++) {
            Mp3File mp3File = null;
            try {
                mp3File = new Mp3File(Library.getSongs().get(i));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
            songs.add(mp3File);


        }


        for (int i = 0; i < songs.size(); i++) {

            JCheckBox checkBox = new JCheckBox();
            checkBox.setBorder(null);
            checkBox.setBackground(Color.BLACK);
            checkBox.setForeground(Color.WHITE);
            checkBox.setLayout(new GridLayout(songs.size(),songs.size()));
            checkBox.setText(songs.get(i).getId3v1Tag().getTitle());

            showSongs.add(checkBox);
            int finalI = i;
            songsInPlaylist.add(songs.get(finalI));
           /* checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {

                }
            });*/

        }

        return showSongs;
    }


    public  HashSet<Mp3File> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void addToPlayList(Mp3File mp3File) {

        songsInPlaylist.add(mp3File);
    }


}



