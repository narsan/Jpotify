import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Playing  {

   static UpdateWorker updateWorker=null;

    public static UpdateWorker getUpdateWorker() {
        return updateWorker;
    }

    static ArrayList<PausablePlayer >plaiyingSongs=new ArrayList<>();

     static PausablePlayer player;

    public static void setPlayer(PausablePlayer player) {
        Playing.player = player;

    }


    public static void setFile(File file) throws InvalidDataException, IOException, UnsupportedTagException {
        Playing.file = file;
    }

    static File file;

    public  static void Play() throws JavaLayerException, InvalidDataException, IOException, UnsupportedTagException {


        Mp3File mp3File=new Mp3File(file);
        updateWorker=new UpdateWorker((int)mp3File.getLengthInSeconds());

        if (plaiyingSongs.size()==1){


            player.play();
            //DownPanel.downCenterPanel.add(updateWorker.slider, BorderLayout.PAGE_END);
            DownPanel.addNewSlider(updateWorker.slider);
            DownPanel.downCenterPanel.add(updateWorker.getTime(),BorderLayout.EAST);
            DownPanel.downCenterPanel.add(updateWorker.getTotalTime(),BorderLayout.WEST);
            updateWorker.execute();
        }

        else if (plaiyingSongs.size()!=1){


            plaiyingSongs.get(plaiyingSongs.size()-2).stop();
            player.play();
            updateWorker.execute();
        }


    }


    public void addTopalying(PausablePlayer player){

        plaiyingSongs.add(player);

    }


}
