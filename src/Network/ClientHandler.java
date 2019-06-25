package Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private ArrayList<File> songs=null;


    public ClientHandler(Socket client, ArrayList<File> songs) throws IOException {
        this.songs=songs;
        this.client = client;
        writer = new ObjectOutputStream(client.getOutputStream());
        reader = new ObjectInputStream(client.getInputStream());
    }





    @Override
    public void run() {
        while (true) {
            try {

                for (int i = 0; i <songs.size() ; i++) {

                    writer.writeObject(songs.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
