import javax.swing.*;
import java.awt.*;

public class CreatePlayList {

    JButton newPlayList=new JButton();
    public JButton getNewPlayList() {
        return newPlayList;
    }

    public CreatePlayList(){

        newPlayList.setText("Create playList");
        newPlayList.setFont(new Font("Arial",Font.PLAIN,20));
        newPlayList.setBackground(Color.BLACK);
        newPlayList.setForeground(Color.white);
        newPlayList.setBorder(null);
    }



}
