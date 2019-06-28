package Network;

import mainPackage.Library;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


public class Client  {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private ArrayList<String> ips = new ArrayList<>();
    private File file =null;
    ArrayList<File> songs=null;
    protected static Vector<Socket> sockets = new Vector<>();

    public void addIP(String ip) {
        ips.add(ip);
    }

    public void removeIP(String ip) {
        ips.remove(ip);
    }

    public void reciveSongs (){

        try {
            file = (File)reader.readObject();
            System.out.println("file = " + file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Client() {

        this.songs=songs;

        try {
           // for (String str : ips) {
                socket = new Socket("", 3504);
                sockets.add(socket);

                reader = new ObjectInputStream(socket.getInputStream());
                writer = new ObjectOutputStream(socket.getOutputStream());
           // }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.reciveSongs();

    }

}
