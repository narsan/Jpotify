package Network;

import mainPackage.Library;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    ArrayList<File> songs=new ArrayList<>();

    public Server(ArrayList<File> songs) throws IOException {

        this.songs=songs;
        this.serverSocket = new ServerSocket(3504);

    }

    public void run() {

        try {

            while (true) {
                System.out.println("waiting for client ...");
                Socket client = this.serverSocket.accept();
                System.out.println("client connected");
                ClientHandler clientHandler = new ClientHandler(client,songs);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws IOException {

        ArrayList<File> musics=new ArrayList<>();


        File file= new File("src\\02.-Calum-Scott-What-I-Miss-Most.mp3");
        File file1= new File("src\\Calum Scott - You Are the Reason - MP3 320.mp3");
        musics.add(file);
        musics.add(file1);

       Server server=new Server(musics);
       Thread thread=new Thread(server);
       thread.start();

    }
}