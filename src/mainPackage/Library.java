package mainPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Library {
    private static ArrayList<File> songs=new ArrayList<>();
    private static ArrayList<File> shuffleArrey = new ArrayList<>();

    private static HashMap<String,String> sortByTime=new HashMap<>();
    private static ArrayList<PlayList> playLists=new ArrayList<>();


    public static void addSong(File file){

        if (!songs.contains(file)){

        songs.add(file);

        }
    }

    public static void  shuffle(){
        shuffleArrey = (ArrayList<File>) songs.clone();
        System.out.println("songs" + songs);
        Collections.shuffle(shuffleArrey);
        System.out.println("what "+shuffleArrey);


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
    public  static ArrayList<File> getShuffledSings(){
        return shuffleArrey;
    }

    public static ArrayList<File> getSongs() {
        return songs;
    }

}