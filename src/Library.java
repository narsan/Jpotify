import java.io.File;
import java.util.ArrayList;

public class Library {
    private ArrayList<File> songs=new ArrayList<>();

    public void addSong(File file){
        songs.add(file);

    }

    public ArrayList<File> getSongs() {
        return songs;
    }
}
