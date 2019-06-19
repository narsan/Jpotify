import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame {
        JFrame frame = new JFrame();
    private JPanel songsPanel=null;

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    private JPanel currentPanel;
    public MainFrame() throws IOException {
        frame.setLocation(100,100);
        frame.setSize(500,500);
        frame.setTitle("Jpotify");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        LeftPanel leftPanel = new LeftPanel();
        frame.add(leftPanel.getjScrollPane(),BorderLayout.WEST);
        ShowSongs showSongs=new ShowSongs();
        leftPanel.add(showSongs);
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\savedSongs.bin"));
            while (true)
            {
                Library.addSong((File) objectInputStream.readObject());

            }
        }
        catch (EOFException e)
        {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        showSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (songsPanel!=null)
                    frame.remove(songsPanel);
                try {
                    songsPanel=showSongs.CreatButtonFromSongs();
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

        CreatePlayList createPlayList=new CreatePlayList();
        leftPanel.add(createPlayList.getNewPlayList());
        /*createPlayList.getNewPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("there");

                ShowSongsToCreatePlayList showSongsToCreatePlayList=new ShowSongsToCreatePlayList();
                refresh(ShowSongsToCreatePlayList.showSongs);

            }
        });*/

        RightPanel rightPanel = new RightPanel();
        frame.add(rightPanel.getJScrollPane(),BorderLayout.EAST);

        DownPanel downPanel = new DownPanel();
        frame.add(downPanel.getDownPanel(),BorderLayout.PAGE_END);


        this.frame.pack();
    }

    public void refresh(JPanel jPanel){

        this.frame.add(jPanel,BorderLayout.CENTER);

        this.frame.repaint();

        this.frame.revalidate();


    }




    public static void main(String[] args) throws IOException {

        new MainFrame();
        System.out.println(" ");
    }
}
