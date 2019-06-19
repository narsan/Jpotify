import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame {
        JFrame frame = new JFrame();
    private JPanel songsPanel=null;
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
        System.out.println();
        ShowSongs showSongs=new ShowSongs();
        leftPanel.add(showSongs);
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\savedSongs.bin"));
            while (true)
            {
                Library.addSong((File) objectInputStream.readObject());
                System.out.println("here");
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
                songsPanel=showSongs.CreatButtonFromSongs();
                refresh(songsPanel);


            }
        });


        RightPanel rightPanel = new RightPanel();
        frame.add(rightPanel.getJScrollPane(),BorderLayout.EAST);

        DownPanel downPanel = new DownPanel();
        frame.add(downPanel.getDownPanel(),BorderLayout.PAGE_END);


        this.frame.pack();
    }

    public void refresh(JPanel jPanel){

        this.frame.add(jPanel,BorderLayout.CENTER);
        this.frame.revalidate();
        this.frame.repaint();

    }

    public static void main(String[] args) throws IOException {

        new MainFrame();
        System.out.println(" ");
    }
}
