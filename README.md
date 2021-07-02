# Java-Client-Server
Client-server-App in Java console, a bit buggy but will fix it.
This Chat application was written in Java, it allows users to write to each other using the command line.
The ChatServer class starts the server, listening on a specific port. When a new client gets connected, an instance of UserThread is created to serve that client. Since each connection is processed in a separate thread, the server is able to handle multiple clients at the same time.


How to run : 
Step 1 : Run Client by using cmd : 	
java Main 8989
This starts the server listening on the port number 8989, and you see the following output in the server once it started:
Chat Server is listening on port 8989.
Step 2 : run Chat client : To run the client, you need to specify the server’s hostname/IP address and port number in the command line. For example:
java ChatClient localhost 8989
This tells the client to connect to the server at localhost on port 8989. Then you see the following message in the server’s console:
New user connected
Connected to chat server
No other users connected
You see, the server tells the client how many users are connected, but there’s no user at this time. Then the program asks for the username:
Enter your name:_

