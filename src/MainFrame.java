import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public  class MainFrame {
    JFrame frame = new JFrame();
    private JPanel songsPanel = null;

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    private JPanel currentPanel;

    public MainFrame() throws IOException {
        frame.setLocation(100, 100);
        frame.setTitle("Jpotify");
        frame.setLayout(new BorderLayout());
        ImageIcon jpotify = new ImageIcon("src\\icons\\jpotify.jpg");
        frame.setIconImage(jpotify.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);


        LeftPanel leftPanel = new LeftPanel();
        frame.add(leftPanel.getjScrollPane(), BorderLayout.WEST);
        ShowSongs showSongs = new ShowSongs();


        CreatePlayList createPlayList = new CreatePlayList();
        leftPanel.add(showSongs);
        leftPanel.add(createPlayList.getNewPlayList());


        JLabel playLists = new JLabel();
        playLists.setPreferredSize(new Dimension(30, 30));
        playLists.setText("Your Playlists");
        playLists.setFont(new Font("Arial", Font.ITALIC, 16));
        playLists.setForeground( new Color(195,195,195));
        leftPanel.add(playLists);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\savedSongs.bin"));
            while (true) {
                Library.addSong((File) objectInputStream.readObject());

            }
        } catch (EOFException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        File temp=new File("src\\playlists");
        if (temp.exists()){
            for(File file:temp.listFiles()){

                ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));

                String name=file.getName().replace(".bin","");
                PlayList playList=new PlayList(name);
                try {
                    while (true) {
                        System.out.println("-");
                        playList.addSongToPlayList((File) in.readObject());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (EOFException e)
                {
                    System.out.println(playList.getPlayListSongs());
                    in.close();
                }
                ShowSongsToCreatePlayList showSongsToCreatePlayList = new ShowSongsToCreatePlayList();
                showSongsToCreatePlayList.setPlayList(playList);
                Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                leftPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                    }
                });
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
        ShowSongsToCreatePlayList showSongsToCreatePlayList=new ShowSongsToCreatePlayList();
        createPlayList.getNewPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                refresh(showSongsToCreatePlayList.songsName());

                Library.addNewPlayList(showSongsToCreatePlayList.getPlayList());

                leftPanel.add(showSongsToCreatePlayList.playList.getPlayList());

                showSongsToCreatePlayList.getPlayList().getPlayList().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        refresh(showSongsToCreatePlayList.getPlayList().showSongsInPlayList());
                    }
                });



            }
        });


        //FavoriteSong favoriteSong=new FavoriteSong();
        //SharedPlayList sharedPlayList=new SharedPlayList();
        /*leftPanel.add(favoriteSong.playList);
        leftPanel.add(sharedPlayList.playList);
        favoriteSong.playList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refresh(favoriteSong.showSongsInPlayList());


            }
        });*/

        RightPanel rightPanel = new RightPanel();
        frame.add(rightPanel.getJScrollPane(), BorderLayout.EAST);

        DownPanel downPanel = new DownPanel();
        frame.add(downPanel.getDownPanel(), BorderLayout.PAGE_END);

        frame.setMinimumSize(new Dimension(1200,700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    public  void refresh(JPanel jPanel) {

        if (currentPanel!=null){

            currentPanel.setVisible(false);

        this.frame.remove(currentPanel);

        }
        currentPanel=jPanel;

        this.frame.add(jPanel, BorderLayout.CENTER);

        //this.frame.repaint();

        this.frame.revalidate();
       // this.frame.validate();


    }


    public static void main(String[] args) throws IOException {

        new MainFrame();

    }
}
