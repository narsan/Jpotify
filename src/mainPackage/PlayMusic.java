package mainPackage;

import javazoom.jl.player.Player;

import java.io.*;

public class PlayMusic {


    private File musicToPlay;
    int index;

    private BufferedInputStream bufferedInputStream;
    FileInputStream fileInputStream;
    Player player;
    private UpdateWorker updateWorker;
    private PausablePlayer pausablePlayer;


    public PlayMusic() {

    }
}