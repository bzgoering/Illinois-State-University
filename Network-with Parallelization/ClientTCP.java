
/**
 TCP client using Sockets
 Conpile: javac ClientTCP.java
 Run: java ClientTCP localhost <portnumber>
 IT 386 - Dr. Follmann
 Program 3 Final - Spring 2026
 */
import java.net.*;
import java.io.*;

class ClientTCP {
    public static void main(String[] args) throws Exception {
        String sentence;
        String returnSentence;
        if (args.length != 2) {
            System.err.println("Usage: java ClientTCP <host name> <port number>");
            System.exit(1);
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        /* Create the User input stream */
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        /* Create client socket, and connect it to the Server */
        Socket clientSocket = new Socket(hostName, portNumber);
        System.out.println("Connected. Type your message");
        /* Create the output data stream attached to the Socket */
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        sentence = inFromUser.readLine(); // read user input from terminal
        /* Write (Send) line out to the Server */
        outToServer.writeBytes(sentence + '\n');
        System.out.println("Message sent.");
        /*
         * 1 - Create input stream attached to socket to get incomming return message
         * from server
         */
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        /* 2 - Read the line from Server */
        returnSentence = inFromServer.readLine();
        /* 3 - Print returned message from server */
        System.out.println("From Server:" + returnSentence);
        clientSocket.close();
        System.out.println("Connection closed\n ");
    }
}
