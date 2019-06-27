package mainPackage;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;


public class PlayingActionListener {
    private FileInputStream fileInputStream;
    public PlayingActionListener() {
        System.out.println(DownPanel.shufflesongs);
        if (DownPanel.shufflesongs == false) {
            for (int i = 0; i < Library.getSongs().size(); i++) {
                if (Library.getSongs().get(i).equals(DownPanel.musicToPlay)) {

                    int temp = i += 1;
                    if (temp >= Library.getSongs().size())
                        temp -= i;
                    File file = Library.getSongs().get(temp);
                    Mp3File mp3File = null;
                    try {
                        mp3File = new Mp3File(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        PausablePlayer player = new PausablePlayer(fileInputStream);


                        try {
                            Playing.setFile(file);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        Playing.setPlayer(player);
                        Playing.plaiyingSongs.add(player);

                        try {
                            try {
                                Playing.Play();
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        }
                        DownPanel.addPlayingSongInfo(DownPanel.showSongs(mp3File));

                        try {
                            DownPanel.setPausablePlayer(player, file);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        if (DownPanel.shufflesongs == true){
               // System.out.println( Library.getShuffledSings());
            for (int i = 0; i < Library.getShuffledSings().size(); i++) {
                if (Library.getShuffledSings().get(i).equals(DownPanel.musicToPlay)) {

                    int temp = i += 1;
                    if (temp >= Library.getShuffledSings().size())
                        temp -= i;
                    File file = Library.getShuffledSings().get(temp);
                    Mp3File mp3File = null;
                    try {
                        mp3File = new Mp3File(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        PausablePlayer player = new PausablePlayer(fileInputStream);


                        try {
                            Playing.setFile(file);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        Playing.setPlayer(player);
                        Playing.plaiyingSongs.add(player);

                        try {
                            try {
                                Playing.Play();
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        }
                        DownPanel.addPlayingSongInfo(DownPanel.showSongs(mp3File));

                        try {
                            DownPanel.setPausablePlayer(player, file);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }
    }
}
