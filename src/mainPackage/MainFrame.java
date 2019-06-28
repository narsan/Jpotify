package mainPackage;

import Network2.Client;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame {
    static JFrame frame = new JFrame();
    private CenterPanelScroller songsPanel = null;
    static CenterPanelScroller currentPanel;
    PlaylistPanel playlistPanel = new PlaylistPanel();


 /*   public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
    public JPanel getCurrentPanel() {
        return currentPanel;
    }*/

    //private static JPanel currentPanel;

    public MainFrame() throws IOException {
//        currentPanel = new CurrentPanel();
        frame.setLocation(100, 100);
        frame.setTitle("Jpotify");
        frame.setLayout(new BorderLayout());
        ImageIcon jpotify = new ImageIcon("src\\icons\\jpotify.jpg");
        frame.setIconImage(jpotify.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);


//        JTextField searchBar=new JTextField("search...");
//        searchBar.setBackground(Color.white);
//        searchBar.setForeground(Color.pink);
//        searchBar.setColumns(10);
//        searchBar.setBorder(BorderFactory.createCompoundBorder(
//                new CustomeBorder(),
//                new EmptyBorder(new Insets(15, 25, 15, 25))));
        UserPanel userPanel = new UserPanel();
        JFrame Uframe = new JFrame();
        String newName = JOptionPane.showInputDialog(Uframe, "enter new name");
        System.out.println(newName);
        userPanel.setName(newName);
        frame.add(userPanel, BorderLayout.NORTH);


        LeftPanel leftPanel = new LeftPanel();
        frame.add(leftPanel, BorderLayout.WEST);
        ShowSongs showSongs = new ShowSongs();

        ShowAlbum showAlbum = new ShowAlbum();
        leftPanel.add(showAlbum);

        CreatePlayList createPlayList = new CreatePlayList();
        leftPanel.add(showSongs);
        leftPanel.add(createPlayList.getNewPlayList());

        JLabel playLists = new JLabel();
        playLists.setPreferredSize(new Dimension(30, 30));
        playLists.setText("Your Playlists");
        playLists.setFont(new Font("Arial", Font.ITALIC, 16));
        playLists.setForeground(new Color(195, 195, 195));
        leftPanel.add(playLists);
        leftPanel.add(playlistPanel.getjScrollPane());

        FavoriteSong favoriteSong = new FavoriteSong();
        SharedPlayList sharedPlayList = new SharedPlayList();
        Client.setSharedPlayList(sharedPlayList);
        playlistPanel.add(favoriteSong.playList);
        playlistPanel.add(sharedPlayList.playList);

        try {
            File file = new File("src\\sorted.bin");
            //File file=new File("src\\sorted.bin");

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\sorted.bin"));
            while (true) {

                Library.addSong((File) objectInputStream.readObject());
            }

        } catch (EOFException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(Library.getSongs().size() + "  size");
        File temp = new File("src\\playlists");
        if (temp.exists()) {
            for (File file : temp.listFiles()) {

                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

                String name = file.getName().replace(".bin", "");
                System.out.println(name+"   name");
                if (!name .equals("Favorite songs") &&!name .equals("Shared playlist")) {

                    PlayList playList = new PlayList(name);
                    try {
                        while (true) {
                            System.out.println("-");
                            playList.addSongToPlayList((File) in.readObject());
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (EOFException e) {
                        System.out.println(playList.getPlayListSongs());
                        in.close();
                    }
                    ShowSongsToCreatePlayList showSongsToCreatePlayList = new ShowSongsToCreatePlayList();
                    showSongsToCreatePlayList.setPlayList(playList);
                    Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                    playlistPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                    showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                        }
                    });
                }

                else if (name.equals("Favorite songs")){

                    System.out.println("lll");

                    //FavoriteSong favoriteSong1=new FavoriteSong();

                    try {
                        while (true) {
                            System.out.println("-");
                            favoriteSong.addSongToPlayList((File) in.readObject());
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (EOFException e) {
                        System.out.println(favoriteSong.getPlayListSongs());
                        in.close();
                    }
                    ShowSongsToCreatePlayList showSongsToCreatePlayList = new ShowSongsToCreatePlayList();
                    showSongsToCreatePlayList.setPlayList(favoriteSong);
                    Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                    playlistPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                    showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                        }
                    });
                }

                else if (name.equals("Shared playlist")){


                    //SharedPlayList sharedPlayList1=new SharedPlayList();

                    try {
                        while (true) {
                            System.out.println("-");
                            sharedPlayList.addSongToPlayList((File) in.readObject());
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (EOFException e) {
                        System.out.println(sharedPlayList.getPlayListSongs());
                        in.close();
                    }
                    ShowSongsToCreatePlayList showSongsToCreatePlayList = new ShowSongsToCreatePlayList();
                    showSongsToCreatePlayList.setPlayList(sharedPlayList);
                    Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                    playlistPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                    showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                        }
                    });

                }
            }

        }
        showSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (songsPanel != null)
                    frame.remove(songsPanel);
                try {
                    songsPanel = showSongs.CreatButtonFromSongs();
                } catch (InvalidDataException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedTagException ex) {
                    ex.printStackTrace();
                }

                refresh(songsPanel);


            }
        });
        showAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (songsPanel != null) {
                    frame.remove(songsPanel);
                    songsPanel = showAlbum.getAlbums();
                    refresh(songsPanel);
                }
            }
        });
        ShowSongsToCreatePlayList showSongsToCreatePlayList = new ShowSongsToCreatePlayList();
        createPlayList.getNewPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refresh(showSongsToCreatePlayList.songsName());


                Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                playlistPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                    }
                });


            }
        });

        favoriteSong.playList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh(favoriteSong.showSongsInPlayList());
            }
        });


        sharedPlayList.playList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh(sharedPlayList.showSongsInPlayList());
            }
        });

        RightPanel rightPanel = new RightPanel();
        frame.add(rightPanel.getJScrollPane(), BorderLayout.EAST);
        DownPanel downPanel = new DownPanel();
        frame.add(downPanel.getDownPanel(), BorderLayout.PAGE_END);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void refresh(CenterPanelScroller jPanel) {
        if (currentPanel != null) {
            currentPanel.setVisible(false);
            frame.remove(currentPanel.getjScrollPane());
        }
        currentPanel=(jPanel);
        frame.add(jPanel.getjScrollPane(), BorderLayout.CENTER);
        frame.revalidate();
    }
}
