package Network2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class ClientManager implements Runnable {
    Socket clientHolder;
    Server serverHolder;
    InputStream fromClientStream;
    OutputStream toClientStream;

    DataInputStream reader;
    PrintWriter writer;
    String name;

    public ClientManager(Server server, Socket client) {
        serverHolder = server;
        clientHolder = client;

    }

    public void run() {
        try {
            // input stream (stream from client)
            fromClientStream = clientHolder.getInputStream();

            // output stream (stream to client)
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);

            // Receive client response (javab=name:D)
            name = reader.readLine();

            // add "this" to Server "clientsMap" HashMap
            serverHolder.addClientManager(name, this);

            while (true) {
                // read command from client
                String command = reader.readLine();

                // now decide by command ;-)
                if (command.equals("SCHT")) {


                    String to = reader.readLine();
                    String text = reader.readLine();

                    sendTextToAnotherClient(to, text);
                } else if (command.equals("SFILE")) {
                    String fileName = reader.readLine();
                    String to = reader.readLine();

                    int fileLength = Integer.parseInt(reader.readLine());

                    byte[] fileData = new byte[fileLength];

                    reader.readFully(fileData);

                    sendFileToAnotherClient(fileName, to, fileData);
                }
            }
        } catch (Exception e) {
        }
    }


    private void sendTextToAnotherClient(String to, String text) {


        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendText(name, text);
    }

    private void sendText(String from, String text) {


        writer.println("CHT");
        writer.println(from);
        writer.println(text);
    }


    private void sendFileToAnotherClient(String fileName, String to, byte[] fileData) {

        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendFile(name, fileName, fileData);

    }

    private void sendFile(String from, String fileName, byte[] fileData) {
        try {
            writer.println("FILE");
            writer.println(from);
            writer.println(fileName);
            writer.println("" + fileData.length);

            toClientStream.write(fileData, 0, fileData.length);
            toClientStream.flush();// force to send data

        } catch (IOException e) {
        }
    }

}