package mainPackage;

import javax.swing.*;
import java.io.IOException;

public class UserFrame {
    JFrame Uframe = new JFrame();
    UserPanel userPanel = new UserPanel() ;
    String newName;


    public UserFrame() {


//        newName = JOptionPane.showInputDialog(Uframe, "enter new name");
//        System.out.println(newName);
//        userPanel.setName(newName);
        try {
            MainFrame mainFrame=new MainFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static void main(String[] args) {
        new UserFrame();
    }
}