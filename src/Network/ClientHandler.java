package Network;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    public void send(){

           writer.write("hello client");
           writer.flush();
    }

    @Override
    public void run() {





    }
}
