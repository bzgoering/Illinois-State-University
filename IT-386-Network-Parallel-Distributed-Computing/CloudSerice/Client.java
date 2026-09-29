import java.io.*;
import java.rmi.Naming;


public class Client{

public static void main (String[] args) {
    try {
    
        //ensures satisfied conditons are met
      if (args.length != 3) {
        System.out.println ("Usage: java Client <port number> <integer> <integer>");
        return;
      }
      //gets up arguments
      int portNumber = Integer.parseInt(args[0]);
      int x = Integer.parseInt(args[1]);
      int y = Integer.parseInt(args[2]);

      //begins computation process
      ComputeInterface remoteObj =
        (ComputeInterface) Naming.lookup("//localhost:"+portNumber+"/Server");

      System.out.println ("addition: " + remoteObj.add(x,y));
      System.out.println ("subtraction: " + remoteObj.sub(x,y));
      System.out.println ("multiplication: " + remoteObj.multiply(x,y));
      System.out.println ("division: " + remoteObj.divide(x,y));

    } catch (Exception e) {
      System.out.println ("Client exception: " + e);
    }
  }
}
