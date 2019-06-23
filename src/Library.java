import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Library {
    private static ArrayList<File> songs=new ArrayList<>();
    private static HashMap<String,String> sortByTime=new HashMap<>();
    private static ArrayList<PlayList> playLists=new ArrayList<>();
     static File file=new File("src\\sortByDate.txt");
//    private static HashSet<File> songs=new HashSet<>();



    public static void addSong(File file){
        songs.add(file);

    }

    public static void addNewPlayList(PlayList playList){

        playLists.add(playList);
    }

    public static ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public static void deletePlayList(PlayList playList){

        playLists.remove(playList);
    }

    public static ArrayList<File> getSongs() {
        return songs;
    }

    public static  void addToSortedHashMap(String file,String date){

        sortByTime.put(file,date);
    }
}
