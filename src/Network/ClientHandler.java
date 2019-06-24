package Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;


    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        writer = new ObjectOutputStream(client.getOutputStream());
        reader = new ObjectInputStream(client.getInputStream());
    }





    @Override
    public void run() {
        while (true) {


            try {
                Object object= reader.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }
}
