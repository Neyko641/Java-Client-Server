
package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

/* import these libraries for input/output*/
public class Main {

    public static class ChatServer {
        private int port;
        /*port to listen to*/
        private Set<String> userNames = new HashSet<>();
        /* Set user names in a set for no duplicate names, then save them into a hashset to get their keys*/
        private Set<UserThread> userThreads = new HashSet<>();

        /*used to handle multiple threads*/
        public ChatServer(int port) {
            this.port = port;
            /*Initialize the port*/
        }

        public void executeServer() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Chat is listening on port: " + port);

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New user connected");
                    UserThread newUser = new UserThread(socket, this);
                    userThreads.add(newUser);
                    newUser.start();
                }
            } catch (IOException ex) {
                System.out.println("Error in the server: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        public static void main(String[] args) {
            if (args.length < 1) {
                System.out.println("ChatServer: <port-number>");
                System.exit(0);
            }
            int port = Integer.parseInt(args[0]);

            ChatServer server = new ChatServer(port);
            server.executeServer();
        }

        void PublishMessage(String msg, UserThread excludeUser) {
            for (UserThread user : userThreads) {
                if (user != excludeUser) {
                    user.sendMessage(msg);
                }
            }
        }
        void addUserName (String userName) {
            userNames.add(userName);
        }
        void removeUser(String userName,UserThread user) {
            boolean removed = userNames.remove(userName);
            if(removed) {
                userThreads.remove(user);
                System.out.println("The user " + userName + " has Quit.");
            }
        }
        Set<String> getUserNames() {
            return this.userNames;
        }
        boolean hasUsers() {
            return !this.userNames.isEmpty();
        }
    }
}
