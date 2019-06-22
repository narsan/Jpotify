package Network;

import java.io.*;
import java.net.Socket;

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

                String command = reader.readLine();
                writer.write("ddd  "+command);
                // System.out.println("Answer is : "+sum);
                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
