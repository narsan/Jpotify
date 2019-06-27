package mainPackage;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ModifySongsOrderInPlayList {

    private PlayList playList;
    private JButton modify = new JButton();

    public JButton getModify() {
        return modify;
    }

    public ModifySongsOrderInPlayList(PlayList playList) {
        this.playList = playList;


        modify.setText("modify order");
        modify.setBorder(null);
        modify.setBackground(Color.black);
        modify.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        modify.setForeground(Color.WHITE);


        ArrayList<String> songsName = new ArrayList<>();

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Iterator iterator = playList.playListSongs.iterator();
                Mp3File mp3File;
                while (iterator.hasNext())

                    try {
                        mp3File = new Mp3File((File) iterator.next());
                        songsName.add(mp3File.getId3v2Tag().getTitle());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    }


                JFrame modify = new JFrame();

                JList list = new JList(songsName.toArray());
                JOptionPane.showMessageDialog(modify, list, "modify order", JOptionPane.PLAIN_MESSAGE);
                int[] selected;
                selected = list.getSelectedIndices();
                ArrayList<File> temp = new ArrayList<>(playList.getPlayListSongs());
                File file1 = temp.get(selected[0]);
                File file2 = temp.get(selected[1]);
                int index1 = temp.indexOf(file1);
                int index2 = temp.indexOf(file2);

                //temp.add(index1, file2);
                //temp.add(index2, file1);
                Collections.swap(temp,index1,index2);

                //playList.playListSongs.clear();
                HashSet<File> modified = new HashSet<>(temp);
                playList.setPlayListSongs(modified);


            }


        });


    }
}