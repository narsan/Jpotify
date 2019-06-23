import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.util.ArrayList;

public class Playing  {

   static ArrayList<PausablePlayer >plaiyingSongs=new ArrayList<>();

     static PausablePlayer player;

    public static void setPlayer(PausablePlayer player) {
        Playing.player = player;
    }

    public static void setFile(File file) {
        Playing.file = file;
    }

    static File file;

    public  static void Play() throws JavaLayerException {

        if (plaiyingSongs.size()==1){


            player.play();
        }

        else if (plaiyingSongs.size()!=1){




            plaiyingSongs.get(plaiyingSongs.size()-2).stop();
            player.play();
        }


    }


    public void addTopalying(PausablePlayer player){

        plaiyingSongs.add(player);

    }


}
