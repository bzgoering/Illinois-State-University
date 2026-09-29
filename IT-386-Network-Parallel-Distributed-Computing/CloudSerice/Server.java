import java.io.*;
import java.rmi.Naming;
import java.rmi.server.*;

public class Server {
    public static void main(String args[]) {
        try 
        {
            //ensures there is a argument for port number
            if (args.length != 1) 
            {
                System.out.println ("Usage: java Server <port number>");
                return;
            }
            int portNumber = Integer.parseInt(args[0]);
            
            //sets up link to implimentation
            ComputeImpl obj = new ComputeImpl();
            Naming.rebind("//localhost:"+portNumber+"/Server", obj);
            System.out.println("The server is ready");
        } 
        catch (Exception e) 
        {
            System.out.println("Server failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
