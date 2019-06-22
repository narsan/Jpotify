import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Library {
    private static ArrayList<File> songs=new ArrayList<>();
    private static ArrayList<File> palyedSongs=new ArrayList<>();
    private static ArrayList<PlayList> playLists=new ArrayList<>();
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
}
