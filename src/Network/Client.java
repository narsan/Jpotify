package Network;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ArrayList<String> ips = new ArrayList<>();

    public void addIP(String ip) {
        ips.add(ip);
    }

    public void removeIP(String ip) {
        ips.remove(ip);
    }


    public void sendRequestToServer(String ip) {
        try {
            System.out.println(ip);
            writer.println(ip);
            writer.flush();
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        String sum;
        while (true) {
            try {
                sum = reader.readLine();
                System.out.println(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Client() {

        try {
            for (String str : ips) {
                socket = new Socket(str, 3504);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
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
