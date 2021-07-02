package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class UserThread extends Thread {
    private Socket socket;
    private Main.ChatServer server;
    private PrintWriter writer;
    public UserThread(Socket socket, Main.ChatServer server) {
            this.socket = socket;
            this.server = server;
    }
    public void runServer() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            printUsers();
            String userName = reader.readLine();
            server.addUserName(userName);
            String serverMessage = "New user connected: " + userName;
            server.PublishMessage(serverMessage, this);
            String clientMsg;
            do {
                clientMsg = reader.readLine();
                serverMessage = "{" + userName + "}" + clientMsg;
                server.PublishMessage(serverMessage, this);
            } while (!clientMsg.equals("bye"));
            server.removeUser(userName, this);
            socket.close();
            serverMessage = userName + "has Quit.";
            server.PublishMessage(serverMessage, this);
        } catch (IOException ex) {
            System.out.println("Error in userThread" + ex.getMessage());
            ex.printStackTrace();
        }
    }
        void printUsers() {
            if (server.hasUsers()) {
                writer.println("Connected users: " + server.getUserNames());
            } else {
                writer.println("No other users are connected");
            }
        }
            void sendMessage (String msg) {
                writer.println(msg);
            }
    }
