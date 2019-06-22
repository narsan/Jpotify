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

        serverSocket = new ServerSocket(5478);
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
            try {
                out = new PrintWriter(client.getOutputStream());
                in=new InputStreamReader(client.getInputStream());
               BufferedWriter writer=new BufferedWriter(out);
               BufferedReader reader=new BufferedReader(in);
               writer.write("hello");
               writer.flush();

               String input=reader.readLine();
                System.out.println("client: "+ input);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}

