import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class PlayList {

    protected JButton playList = new JButton();
    protected String playListName;
    protected HashSet<File> playListSongs=new HashSet<>();
    private ObjectOutputStream out;



    JButton deleteSong = new JButton();
    JButton addNewSong = new JButton();
    JButton delete = new JButton();
    JButton setNewName = new JButton();

    public HashSet<File> getPlayListSongs() {
        return playListSongs;
    }

    public void setPlayListSongs(HashSet<File> songs) {

        ArrayList<File> temp=new ArrayList<>(songs);
        for (int i = 0; i <temp.size() ; i++) {

            //System.out.println(temp.get(i));

        }
        this.playListSongs = songs;

        temp=new ArrayList<>(songs);

        for (int i = 0; i <temp.size() ; i++) {

            //System.out.println(temp.get(i));

        }

    }

    public JButton getPlayList() {
        return playList;
    }


    public PlayList(String playListName) throws IOException {

        this.playListName = playListName;
        playList.setText(playListName);
        playList.setFont(new Font("Arial", Font.PLAIN, 16));
        playList.setBackground(Color.BLACK);
        playList.setForeground(Color.white);
        playList.setBorder(null);
        playList.setVisible(true);

    }


    public JPanel showSongsInPlayList() {


        JPanel songsPanel = new JPanel();
        JPanel option=new JPanel();
        option.setBackground(Color.BLACK);
        option.setLayout(new GridLayout(5,0));
        songsPanel.setBackground(Color.black);
        songsPanel.setVisible(true);
        songsPanel.setLayout( new FlowLayout());
        ArrayList<PausablePlayer> playedSongs = new ArrayList<PausablePlayer>();

        // songsPanel.setLayout(new FlowLayout());
        //songsPanel.setBackground(Color.DARK_GRAY);
        JLabel Title = null;
        JButton songImage = null;
        ArrayList<File> temp = new ArrayList<>();
        Iterator iterator = playListSongs.iterator();
        while (iterator.hasNext()) {

            temp.add((File) iterator.next());
        }


//            Iterator iterator=Library.getSongs().iterator();
        for (int i = 0; i < temp.size(); i++) {


            File temp1 = temp.get(i);
            Mp3File mp3File = null;
            try {
                mp3File = new Mp3File(temp1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }



            if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getTitle()!=null) {
                ID3v1 id3v1 = mp3File.getId3v1Tag();
                Title = new JLabel();
                Title.setVisible(true);

                Title.setText(id3v1.getTitle());
                //System.out.println(id3v1.getTitle());


                Title.setFont(new Font("Arial", Font.PLAIN, 13));
                Title.setForeground(Color.white);
            }

            else if (mp3File.hasId3v2Tag()&&mp3File.getId3v2Tag().getTitle()!=null){

                ID3v2 id3v2 = mp3File.getId3v2Tag();
                Title = new JLabel();
                Title.setVisible(true);

                Title.setText(id3v2.getTitle());
                //System.out.println(id3v1.getTitle());


                Title.setFont(new Font("Arial", Font.PLAIN, 13));
                Title.setForeground(Color.white);


            }

            if (mp3File.hasId3v2Tag()) {


                ID3v2 id3v2 = mp3File.getId3v2Tag();
                ID3v1 id3v1=mp3File.getId3v1Tag();
                songImage = new JButton();
                songImage.setBorder(null);
                songImage.setVisible(true);


                songImage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel showPlayingSong = new JPanel();
                        showPlayingSong.setLayout(new BorderLayout());
                        JPanel playingSongImg = new JPanel();
                        playingSongImg.setBackground(new Color(58,58,58));
                        playingSongImg.setPreferredSize(new Dimension(100,80));
                        JPanel playingSonglabel = new JPanel();
                        playingSonglabel.setLayout(new BoxLayout(playingSonglabel,BoxLayout.Y_AXIS));
                        playingSonglabel.setBackground(new Color(58,58,58));
                        JPanel cuurentPanel=null;
                        showPlayingSong.setLayout(new BorderLayout());
                        showPlayingSong.setBackground(new Color(58,58,58));
                        byte[] imageData = id3v2.getAlbumImage();
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(imageData));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));

                        playingSongImg.add(new JLabel(imageIcon));
                        if (id3v2.getArtist()!=null){

                            JLabel Artist = new JLabel(id3v2.getArtist());
                            Artist.setForeground(Color.WHITE);
                            Artist.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(Artist);

                        }

                        else if (id3v1.getArtist()!=null){

                            JLabel Artist = new JLabel(id3v2.getArtist());
                            Artist.setForeground(Color.WHITE);
                            Artist.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(Artist);


                        }

                        if (id3v2.getTitle()!=null){

                            JLabel Title = new JLabel(id3v2.getTitle());
                            Title.setForeground(Color.WHITE);
                            Title.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(Title);

                        }

                        else if (id3v1.getTitle()!=null){

                            JLabel Title = new JLabel(id3v2.getTitle());
                            Title.setForeground(Color.WHITE);
                            Title.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(Title);


                        }

                        if (id3v2.getAlbum()!=null){


                            JLabel album = new JLabel(id3v2.getAlbum());
                            album.setForeground(Color.WHITE);
                            album.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(album);
                        }

                        else if (id3v1.getAlbum()!=null){

                            JLabel album = new JLabel(id3v2.getAlbum());
                            album.setForeground(Color.WHITE);
                            album.setFont(new Font("Arial",Font.PLAIN,10));
                            playingSonglabel.add(album);


                        }
                        EmptyBorder border2 = new EmptyBorder(5, 50, 0, 0);
                        playingSonglabel.setBorder(border2);
                        showPlayingSong.add(playingSongImg,BorderLayout.WEST);
                        showPlayingSong.add(playingSonglabel,BorderLayout.EAST);
                        EmptyBorder border = new EmptyBorder(0, 0, 0, 80);
                        showPlayingSong.setBorder(border);
                        showPlayingSong.setBackground(new Color(58,58,58));
                        showPlayingSong.setPreferredSize(new Dimension(318,0));

                        FileInputStream in = null;
                        try {
                            in = new FileInputStream(temp1);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }

                        PausablePlayer player = null;

                        try {
                            player=new PausablePlayer(in);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }


                        try {
                            Playing.setFile(temp1);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        Playing.setPlayer(player);
                        Playing.playingSongs.add(player);
                        try {
                            DownPanel.setPausablePlayer(player,temp1);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        //  PlayMusic playMusic1 = new PlayMusic(temp1, player);
                        try {
                            try {
                                Playing.Play();
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
                        Player player1= null;
                        try {
                            player1 = new Player(in);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }

                        /*FileInputStream in = null;
                        try {
                            in = new FileInputStream(temp1);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        PausablePlayer player = null;
                        try {
                            player = new PausablePlayer(in);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        Thread thread= null;
                       /* try {
                            //thread = new Thread( new newPalyer(new Player(in)));
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                        thread.start();*/


                        DownPanel.addPlayingSongInfo(showPlayingSong);
                        DownPanel.downPanel.revalidate();


                    }
                });

                byte[] imageData = id3v2.getAlbumImage();
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                songImage.setIcon(imageIcon);
                String mimeType = id3v2.getAlbumImageMimeType();
                System.out.println(mimeType);
                if (mp3File.hasId3v1Tag()&&mp3File.getId3v1Tag().getTitle()!=null){

                    Title.setText(id3v1.getTitle());
                }

                else if (mp3File.hasId3v2Tag()&&mp3File.getId3v2Tag().getTitle()!=null){


                    Title.setText(mp3File.getId3v2Tag().getTitle());
                }

            }


            JPanel songData = new JPanel();
            songData.setBackground(Color.black);
            songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
            songData.add(songImage);
            songData.add(Title);
            songData.setMinimumSize(new Dimension(200, 200));
            songsPanel.add(songData);


        }

        AddNewSongToPlayList addNewSongToPlayList=new AddNewSongToPlayList(this);
        DeleteSongFromPlayList deleteSongFromPlayList=new DeleteSongFromPlayList(this);
        ModifySongsOrderInPlayList modify=new ModifySongsOrderInPlayList(this);


        delete.setText("delete this playList");
        delete.setBorder(null);
        delete.setBackground(Color.black);
        delete.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        delete.setForeground(Color.WHITE);


        setNewName.setText("rename this playList");
        setNewName.setBorder(null);
        setNewName.setBackground(Color.black);
        setNewName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        setNewName.setForeground(Color.WHITE);




        option.add(delete);
        option.add(setNewName);
        option.add(addNewSongToPlayList.getAddNewSong());
        option.add(deleteSongFromPlayList.getDeleteSong());
        option.add(modify.getModify());

        songsPanel.add(option);



        /*songsPanel.add(delete);
        songsPanel.add(setNewName);
        songsPanel.add(addNewSongToPlayList.getAddNewSong());
        songsPanel.add(deleteSongFromPlayList.getDeleteSong());
      //  songsPanel.add(modify.getModify());*/



        PlayList thisPlayList = this;

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("./src/playlists/"+playListName+".bin");
                file.delete();
                Library.deletePlayList(thisPlayList);
                thisPlayList.playList.setVisible(false);
                songsPanel.setVisible(false);
                System.out.println();

            }
        });

        setNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame enterName=new JFrame();
                String newName=JOptionPane.showInputDialog(enterName,"enter new name");
                thisPlayList.playList.setText(newName);
                thisPlayList.setPlayListName(newName);
            }
        });

        return songsPanel;


    }

    public void addSongToPlayList(File file)  {

        playListSongs.add(file);

    }
    public void writeSongs() throws IOException {

        File path=new File("./src/playlists/"+playListName+".bin");
        out = new ObjectOutputStream(new FileOutputStream(path));




        try {
            for (File f:playListSongs) {
                out.writeObject(f);
            }
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayListName(String playListName) {

        this.playListName = playListName;
        //./src/playlists/
        // File file2=new File("./src/playlists/"+playListName+".bin");

        /*if ( path.renameTo(file2)){
            System.out.println("renamed");
        }*/

    }

    public void removeSongFromPlayList(File file) throws IOException, ClassNotFoundException {

        /*ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));
        File file1= (File) in.readObject();
        if (file1.equals(file)){
            System.out.println("equal");
        }*/

        playListSongs.remove(file);
        // path.delete();

        //writeSongs();
    }


}