import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.util.ArrayList;

public class Library {
    private static ArrayList<File> songs=new ArrayList<>();



    public static void addSong(File file){
        songs.add(file);




    }

    public static ArrayList<File> getSongs() {
        return songs;
    }
}
