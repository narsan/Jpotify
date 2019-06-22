import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public void sendRequestToServer(String string){
        try {
            System.out.println(string);
            writer.println(string);
            writer.flush();
        }catch (Exception e){

        }
    }
    @Override
    public void run() {
        int sum ;
        while (true) {
            try {
                sum = reader.read();
                System.out.println(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public Client(){

        try
        {
            socket = new Socket("localhost",3504);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Thread tr = new Thread(client);
        tr.start();
        while (true){
            Scanner in = new Scanner(System.in);
            String input = in.next();
            client.sendRequestToServer(input);
        }
    }

}
