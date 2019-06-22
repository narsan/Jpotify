package Network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private InputStreamReader in;
    BufferedWriter writer;
    BufferedReader reader;

    public Client() throws IOException {

        socket=new Socket(InetAddress.getByName("localhost"),6801);
        out=new PrintWriter(socket.getOutputStream());
        writer=new BufferedWriter(out);
        in=new InputStreamReader(socket.getInputStream());
        reader=new BufferedReader(in);

    }


    @Override
    public void run() {

        String input= null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server :  "+input);




    }

    public static void main(String[] args) {
        Client client= null;
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread(client);
        thread.start();
    }
}
