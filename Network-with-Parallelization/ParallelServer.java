//By: Ben Goering
//java ParallelServer <portnum>

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelServer 
{
    static int count = 0;
    public static class WorkerServer implements Runnable
    {
        private Socket connectionSocket;
        static ReentrantLock mutex = new ReentrantLock();

        public WorkerServer(Socket socket)
        {
            this.connectionSocket = socket;
        }

        @Override
        public void run()
        {
                try{
                    BufferedReader inFromClient = new BufferedReader (new InputStreamReader (connectionSocket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    String clientSentence;
                    String returnSentence;
                    int clientId;
                    
                    mutex.lock();
                    try{
                        count++;
                        clientId = count;
                    }
                    finally
                    {
                        mutex.unlock();
                    }

                    clientSentence = inFromClient.readLine();
                    System.out.println("Message from Client " + clientId + ": " + clientSentence);
                    returnSentence = "Client " + clientId + ": " + clientSentence + "\n";
                    outToClient.writeBytes(returnSentence.toUpperCase());
                    connectionSocket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

        }
    }

    public static void main(String[] args) throws InterruptedException, IOException
    {
        if (args.length != 1)
        {
            System.err.println("Usage: java ServerTCP <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        ServerSocket welcomeSocket = new ServerSocket(portNumber);
        System.out.println("TCP server waiting ...");

        //creates thread upon connection
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            Runnable obj = new WorkerServer(connectionSocket);
            
            Thread thread = new Thread(obj);
            thread.start();
        }
    }
}
