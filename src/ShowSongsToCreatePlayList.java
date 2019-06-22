import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class ShowSongsToCreatePlayList  implements ItemListener {

    static JPanel showSongs = new JPanel();

    private static   HashSet<File> songsInPlaylist;
    private ArrayList<JCheckBox> checkBoxes;
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
        checkBoxes=new ArrayList<>();
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
            songs.add(Library.getSongs().get(i));


        }

        JFrame setName = new JFrame();
        String name1 = JOptionPane.showInputDialog(setName, "Enter your playList name");
        playList=new PlayList(name1);

        for (int i = 0; i < songs.size(); i++) {

            JCheckBox checkBox = new JCheckBox();
            checkBox.setBorder(null);
            checkBox.setBackground(Color.BLACK);
            checkBox.setForeground(Color.WHITE);
            checkBox.setLayout(new GridLayout(songs.size(),songs.size()));
            try {
                Mp3File mp3File=new Mp3File(songs.get(i));
                checkBox.setText(mp3File.getId3v2Tag().getTitle());
                checkBox.addItemListener(this);
                int finalI1 = i;


                checkBoxes.add(checkBox);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }


            showSongs.add(checkBox);





        }



        return showSongs;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        for (int i = 0; i <checkBoxes.size() ; i++) {

            if (checkBoxes.get(i).isSelected()){

               // System.out.println("selected");

                playList.addSongToPlayList(Library.getSongs().get(i));
            }

        }

        System.out.println(playList.getPlayListSongs().size());
    }

    public  HashSet<File> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void addToPlayList(File file) {

        songsInPlaylist.add(file);
    }




}



