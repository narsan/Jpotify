import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PlayMusic {

        JPanel playIcons = new JPanel();
        private File musicToPlay;
        Thread playThread;
        private Thread resumeThread;
        private JButton pause=new JButton();
        private JButton resume=new JButton();
        private JButton nextSong1=new JButton();
        private JButton previousSong =new JButton();
        private BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        private long totalLength;
        Player player;
        private long pause1;



        public PlayMusic(File file) {
            ImageIcon pause_Icon = new ImageIcon(new ImageIcon("src\\icons\\pause.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
            ImageIcon nextSong = new ImageIcon(new ImageIcon("src\\icons\\nextSong.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
            ImageIcon play = new ImageIcon(new ImageIcon("src\\icons\\play.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
            ImageIcon previous1 = new ImageIcon(new ImageIcon("src\\icons\\previousSong.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
            //playIcons.setLayout(new GridLayout(1,4));
            playIcons.setLayout(new GridBagLayout());
            playIcons.setBackground(Color.DARK_GRAY);
            nextSong1.setIcon(nextSong);
            pause.setIcon(pause_Icon);
            resume.setIcon(play);
            pause.setBorder(null);
            pause.setBackground(Color.DARK_GRAY);
            resume.setBorder(null);
            resume.setBackground(Color.DARK_GRAY);
            previousSong.setBorder(null);
            previousSong.setBackground(Color.DARK_GRAY);
            nextSong1.setBorder(null);
            nextSong1.setBackground(Color.DARK_GRAY);

            previousSong.setIcon(previous1);
            playIcons.add(previousSong);
            playIcons.add(resume);
            playIcons.add(pause);
            playIcons.add(nextSong1);

;
            DownPanel.addPauseAndResume(playIcons);


            musicToPlay = file;



            try {

                playThread = new Thread(new Runnable() {


                    @Override
                    public void run() {


                        try {
                            try {
                                fileInputStream=new FileInputStream(musicToPlay);
                                try {
                                    totalLength=fileInputStream.available();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            bufferedInputStream = new BufferedInputStream(fileInputStream);
                            player = new Player(bufferedInputStream);
                            player.play();

                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }

                    }


                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            pause.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pause1 = fileInputStream.available();
                        player.close();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            resumeThread=new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        fileInputStream=new FileInputStream(musicToPlay);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bufferedInputStream=new BufferedInputStream(fileInputStream);
                    try {
                        fileInputStream.skip(totalLength-pause1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        player.play();

                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }

                    try {


                        player.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }


                }
            });

            resume.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resumeThread.start();
                }
            });



        }
}
