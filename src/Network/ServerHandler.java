package Network;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket server;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;


    public ServerHandler(Socket server) throws IOException {
        this.server = server;
        writer = new ObjectOutputStream(server.getOutputStream());
        reader = new ObjectInputStream(this.server.getInputStream());
    }


    @Override
    public void run() {
        while (true){
           /* try {

                try {
                    String ip = reader.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
