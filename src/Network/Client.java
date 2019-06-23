/*package Network;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Client implements Runnable {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private ArrayList<String> ips = new ArrayList<>();
    protected static Vector<Socket> sockets = new Vector<>();

    public void addIP(String ip) {
        ips.add(ip);
    }

    public void removeIP(String ip) {
        ips.remove(ip);
    }


    public void sendRequestToServer(String ip) {
        try {
            System.out.println(ip);
            //writer.writeObject();
            writer.flush();
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                try {
                  //  = reader.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Client() {

        try {
            for (String str : ips) {
                socket = new Socket(str, 3504);
                sockets.add(socket);
                ServerHandler serverHandler = new ServerHandler(socket);
                Thread thread = new Thread(serverHandler);
                thread.start();

                reader = new ObjectInputStream(socket.getInputStream());
                writer = new ObjectOutputStream(socket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Thread tr = new Thread(client);
        tr.start();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.next();
            client.sendRequestToServer(input);
        }
    }

}
*/