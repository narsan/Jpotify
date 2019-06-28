package mainPackage;

import com.mpatric.mp3agic.Mp3File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddToLibrary extends Library {
    private JButton songAdder = new JButton();
    private File music;
    private ObjectOutputStream out;


    public JButton getSongAdder() {
        return songAdder;
    }

    public AddToLibrary() throws IOException {
        songAdder.setText("Add song");
        songAdder.setFont(new Font("Arial", Font.BOLD, 16));
        songAdder.setBackground(Color.BLACK);
        songAdder.setForeground(Color.white);
        songAdder.setBorder(null);
        songAdder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    out = new ObjectOutputStream(new FileOutputStream("src\\sorted.bin"));
                    if (Library.getSongs().size()!=0)
                    {
                        for (int i=0;i<=Library.getSongs().size()-1;i++) {
                            try {
                                out.writeObject(Library.getSongs().get(i));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setMultiSelectionEnabled(true);
                int res = jFileChooser.showOpenDialog(null);
                Mp3File mp3File;
                if (res == JFileChooser.APPROVE_OPTION) {
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    File[] files = jFileChooser.getSelectedFiles();
                    for (int i = 0; i < files.length; i++) {
                        music = files[i];
                        if (!Library.getSongs().contains(music)) {
                            System.out.println("writing");
                            try {
                                out.writeObject(music);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            System.out.println("done");
                            try {
                                out.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            addSong(music);
                        }
                    }
                }
            }
        });

    }
}
