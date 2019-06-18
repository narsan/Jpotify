import com.mpatric.mp3agic.Mp3File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddToLibrary extends Library {
    private JButton songAdder = new JButton();
    private File music;

    public JButton getSongAdder() {
        return songAdder;
    }

    public AddToLibrary() {
        songAdder.setText("add song");
        songAdder.setFont(new Font("Arial",Font.PLAIN,20));
        songAdder.setBackground(Color.BLACK);
        songAdder.setForeground(Color.white);
        songAdder.setBorder(null);

        songAdder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int res = jFileChooser.showOpenDialog(null);
                Mp3File mp3File;
                if(res==JFileChooser.APPROVE_OPTION){
                    music = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                }
              addSong(music);
            }
        });



    }
}
