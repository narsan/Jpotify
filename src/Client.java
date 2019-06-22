import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private InputStreamReader in;
    BufferedWriter writer;
    BufferedReader reader;

    public Client() throws IOException {

        socket=new Socket("192.168.43.141",5478);
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
        System.out.println("server: "+ input);
        try {
            writer.write("khobi");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
