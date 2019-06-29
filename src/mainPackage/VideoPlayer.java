package mainPackage;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class VideoPlayer{
    public void playVideo(){

    }

    public static void main(String[] args) {

        try {
            Desktop.getDesktop().open(new File("src\\video_2019-06-28_19-25-00.mp4"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}