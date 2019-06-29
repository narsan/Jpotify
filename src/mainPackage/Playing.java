package mainPackage;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * @author shakiba , narges
 * this class is created for handle music thaht is playing
 * and for example privious music stop when new music is playing
 */

public class Playing {

    static HashMap<File, LocalDateTime> sortByTime = new HashMap<>();
    static UpdateWorker updateWorker = null;
    static boolean isPlayed = false;
    private static ObjectOutputStream out;

    public static UpdateWorker getUpdateWorker() {
        return updateWorker;
    }

    static ArrayList<PausablePlayer> plaiyingSongs = new ArrayList<>();
    static PausablePlayer player;
    static String title = "";
    static File file;


    public static String getTitle() {
        return title;
    }

    public static void setPlayer(PausablePlayer player) {
        Playing.player = player;

    }


    public static void setFile(File file) throws InvalidDataException, IOException, UnsupportedTagException {
        Playing.file = file;
    }

    public static void setTitle(String title) {
        Playing.title = title;
    }

    public static void Play() throws JavaLayerException, InvalidDataException, IOException, UnsupportedTagException, ParseException {
        if (updateWorker != null)
            updateWorker.cancel(true);
        isPlayed = true;

        Mp3File mp3File = new Mp3File(file);
        updateWorker = new UpdateWorker((int) mp3File.getLengthInSeconds());

        if (plaiyingSongs.size() == 1) {


            player.play();


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            sortByTime.put(file, now);

            ArrayList<File> sortedSong = new ArrayList<>(sortByTime.keySet());


            for (int i = 0; i < Library.getSongs().size(); i++) {

                if (!sortedSong.contains(Library.getSongs().get(i))) {


                    sortByTime.put(Library.getSongs().get(i), LocalDateTime.MIN);
                }
            }

            sortByTime = sortByValue(sortByTime);

            sortedSong = new ArrayList<>(sortByTime.keySet());

            out = new ObjectOutputStream(new FileOutputStream("src\\sorted.bin"));

            for (int j = sortedSong.size() - 1; j >= 0; j--) {

                out.writeObject(sortedSong.get(j));

            }

            setTitle(file.getName());


        }
       /* DownPanel.downCenterPanel.add(updateWorker.slider, BorderLayout.PAGE_END);
        DownPanel.downCenterPanel.add(updateWorker.getTime(), BorderLayout.EAST);
        DownPanel.downCenterPanel.add(updateWorker.getTotalTime(), BorderLayout.WEST);
        updateWorker.execute();*/


        if (plaiyingSongs.size() != 1) {


            plaiyingSongs.get(plaiyingSongs.size() - 2).stop();
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            sortByTime.put(file, now);

            ArrayList<File> sortedSong = new ArrayList<>(sortByTime.keySet());


            for (int i = 0; i < Library.getSongs().size(); i++) {

                if (!sortedSong.contains(Library.getSongs().get(i))) {


                    sortByTime.put(Library.getSongs().get(i), LocalDateTime.MIN);
                }
            }

            sortByTime = sortByValue(sortByTime);

            sortedSong = new ArrayList<>(sortByTime.keySet());


            out = new ObjectOutputStream(new FileOutputStream("src\\sorted.bin"));

            for (int j = sortedSong.size() - 1; j >= 0; j--) {

                System.out.println(sortedSong.get(j));

                out.writeObject(sortedSong.get(j));


                updateWorker.execute();
            }

            setTitle(file.getName());
        }


        DownPanel.downCenterPanel.add(updateWorker.slider, BorderLayout.PAGE_END);
        DownPanel.downCenterPanel.add(updateWorker.getTime(), BorderLayout.EAST);
        DownPanel.downCenterPanel.add(updateWorker.getTotalTime(), BorderLayout.WEST);
        updateWorker.execute();
    }


    public static void addTopalying(PausablePlayer player) {

        plaiyingSongs.add(player);

    }


    public static HashMap<File, LocalDateTime> sortByValue(HashMap<File, LocalDateTime> unsortMap) {


        // 1. Convert Map to List of Map
        List<HashMap.Entry<File, LocalDateTime>> list =
                new LinkedList<Map.Entry<File, LocalDateTime>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<HashMap.Entry<File, LocalDateTime>>() {
            public int compare(HashMap.Entry<File, LocalDateTime> o1,
                               HashMap.Entry<File, LocalDateTime> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        HashMap<File, LocalDateTime> sortedMap = new LinkedHashMap<File, LocalDateTime>();
        for (HashMap.Entry<File, LocalDateTime> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/
        return sortedMap;
    }
}








