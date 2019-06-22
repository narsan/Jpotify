package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private boolean isRun = true;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(3504);

    }

    public void run() {

        try {

            while (isRun) {
                System.out.println("waiting for client ...");
                Socket client = this.serverSocket.accept();
                System.out.println("client connected");
                ClientHandler clientHandler = new ClientHandler(client);
                Thread thread = new Thread(clientHandler);
                thread.start();
//                System.out.println(" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

        try {
            Server srv = new Server();
            Thread t = new Thread(srv);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}