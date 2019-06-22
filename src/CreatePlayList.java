import com.mpatric.mp3agic.Mp3File;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class CreatePlayList {

    JButton newPlayList=new JButton();

    private HashSet<Mp3File> playList =new HashSet<>();
    private String name;




    public String getName() {
        return name;
    }

    public HashSet<Mp3File> getPlayList() {
        return playList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayList(HashSet<Mp3File> playList) {
        this.playList = playList;
    }


    public JButton getNewPlayList() {
        return newPlayList;
    }

    public CreatePlayList(){

        newPlayList.setText("Create playlist");
        newPlayList.setFont(new Font("Arial",Font.BOLD,16));
        newPlayList.setBackground(Color.BLACK);
        newPlayList.setForeground(Color.white);
        newPlayList.setBorder(null);
    }



}
