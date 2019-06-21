import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.RadioButton;

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

    private static   HashSet<File> songsInPlaylist = new HashSet<>();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    PlayList playList;

    public PlayList getPlayList() {
        return playList;
    }

    public JPanel getShowSongs() {
        return showSongs;
    }

    public JPanel songsName() {

        HashSet<Mp3File> songsInPlaylist1 = new HashSet<Mp3File>();
        ArrayList<File> songs = new ArrayList<>();
        ArrayList<JCheckBox> checkBoxes=new ArrayList<>();
        JButton ok=new JButton("Ok");
        showSongs.add(ok);


        showSongs.setBackground(Color.black);
        showSongs.setVisible(true);
        JFrame setName = new JFrame();
        String name1 = JOptionPane.showInputDialog(setName, "Enter your playList name");
        playList=new PlayList(name1);
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
            songs.add(Library.getSongs().get(i));


        }


        for (int i = 0; i < songs.size(); i++) {

            JCheckBox checkBox = new JCheckBox();
            checkBox.setBorder(null);
            checkBox.setBackground(Color.BLACK);
            checkBox.setForeground(Color.WHITE);
            checkBox.setLayout(new GridLayout(songs.size(),songs.size()));
            try {
                Mp3File mp3File=new Mp3File(songs.get(i));
                checkBox.setText(mp3File.getId3v2Tag().getTitle());
                checkBoxes.add(checkBox);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }

            showSongs.add(checkBox);
            int finalI = i;

            playList.addSongToPlayList(songs.get(finalI));

            /*checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {


                        playList.addSongToPlayList(songs.get(finalI));






                }
            });
*/




        }



        return showSongs;
    }


    public  HashSet<File> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void addToPlayList(File file) {

        songsInPlaylist.add(file);
    }




}



