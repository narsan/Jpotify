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

public class ModifySongsOrderInPlayList   {

    private PlayList playList;
    private JButton modify = new JButton();
    private   ArrayList<String> songsName = new ArrayList<>();


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
        //modify.addActionListener(this);



        }
}
