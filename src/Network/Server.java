package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<String> ips = new ArrayList<>();
    private PrintWriter out;
    private InputStreamReader in;

    public Server() throws IOException {

        serverSocket = new ServerSocket(6801);
        System.out.println("server waiting for client");
    }

    public void run() {

        while (true) {

            Socket client = null;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client accepted");

            ClientHandler clientHandler= null;
            try {

                clientHandler = new ClientHandler(client);
                while (true)
                 clientHandler.send();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread thread=new Thread(clientHandler);
            thread.start();



        }

    }

    public static void main(String[] args) {

        Server server= null;
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread(server);
        thread.start();
    }
}

