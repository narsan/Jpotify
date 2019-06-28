package mainPackage;

import Network2.Client;

import javax.swing.*;
import java.io.IOException;

public class UserFrame {
    JFrame Uframe = new JFrame();
    UserPanel userPanel = new UserPanel() ;
    String newName;


    public UserFrame() {

        try {
            MainFrame mainFrame=new MainFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    public static void main(String[] args) {
        new UserFrame();
        new Client();

    }
}