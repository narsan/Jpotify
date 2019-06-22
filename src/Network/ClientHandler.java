package Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;


    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    }


    @Override
    public void run() {
        while (true) {
            try {

                String ip = reader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
