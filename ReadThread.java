package com.company;
import java.io.*;
import java.net.*;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
    try {
        InputStream input = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
    } catch (IOException ex) {
        System.out.println("Error, no input from stream " + ex.getMessage());
        ex.printStackTrace();
    }
}
public void runProgram() {
    while (true) {
        try {
            String response = reader.readLine();
            System.out.println("\n" + response);
            /* Print user name after displaying message */
            if(client.getUserName() != null) {
                System.out.println("{" + client.getUserName() + "}: ");
            }
        } catch (IOException ex) {
            System.out.println("Error cannot read from server" + ex.getMessage());
            ex.printStackTrace();
            break;
        }
    }
  }
}
