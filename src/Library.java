import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Library {
    private static ArrayList<File> songs=new ArrayList<>();
//    private static HashSet<File> songs=new HashSet<>();



    public static void addSong(File file){
        songs.add(file);




    }

    public static ArrayList<File> getSongs() {
        return songs;
    }
}
