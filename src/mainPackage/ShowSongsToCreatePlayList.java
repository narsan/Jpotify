package mainPackage;

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


public class ShowSongsToCreatePlayList implements ItemListener   {

     CenterPanelScroller showSongs =null;

    private static HashSet<File> songsInPlaylist;
    private ArrayList<JCheckBox> checkBoxes;
    PlayList playList;
    boolean flag=false;

    public PlayList getPlayList() {
        return playList;
    }



    /*public JPanel getShowSongs() {
        return showSongs;
    }*/

    public CenterPanelScroller songsName() {

        showSongs=new CenterPanelScroller();

        HashSet<Mp3File> songsInPlaylist1 = new HashSet<Mp3File>();
        ArrayList<File> songs = new ArrayList<>();
        checkBoxes = new ArrayList<>();
        showSongs.setLayout(new FlowLayout());
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
        if (name1!=null&&name1.length()>0) {
            try {
                playList = new PlayList(name1);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (flag == true) {

                showSongs.removeAll();
                System.out.println();
            }


            for (int i = 0; i < songs.size(); i++) {


                JCheckBox checkBox = new JCheckBox();
                checkBox.setBorder(null);
                checkBox.setBackground(Color.BLACK);
                checkBox.setForeground(Color.WHITE);
                checkBox.setLayout(new GridLayout(songs.size(), songs.size()));
                try {
                    Mp3File mp3File = new Mp3File(songs.get(i));
                    if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getTitle() != null) {

                        checkBox.setText(mp3File.getId3v1Tag().getTitle());
                        checkBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
                        checkBox.addItemListener(this);
                    } else if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getTitle() != null) {

                        checkBox.setText(mp3File.getId3v2Tag().getTitle());
                        checkBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
                        checkBox.addItemListener(this);


                    /*checkBox.setText(mp3File.getId3v2Tag().getTitle());
                    //checkBox.addItemListener(this);
                    int finalI = i;
                    checkBox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {


                            try {
                                playList.addSongToPlayList(songs.get(finalI));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


                            try {
                                playList.writeSongs();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }


                    });*/

                    }


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
        }

        flag=true;


        return showSongs;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {


        for (int i = 0; i < checkBoxes.size(); i++) {

            if (checkBoxes.get(i).isSelected()) {


                try {

                    if (!playList.playListSongs.contains(Library.getSongs().get(i))){
                    playList.addSongToPlayList2(Library.getSongs().get(i));


                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                try {
                    playList.writeSongs();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }




    public HashSet<File> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void addToPlayList(File file) {

        songsInPlaylist.add(file);
    }


    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }




}

