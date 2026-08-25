import java.rmi.*;
import java.rmi.server.*;

//Implimentation of interface for server
public class ComputeImpl extends UnicastRemoteObject implements ComputeInterface
{
    public ComputeImpl () throws RemoteException {}

    public double add(double x, double y) throws RemoteException
    {
        return x+y;
    }
    public double sub(double x, double y) throws RemoteException
    {
        return x-y;
    }
    public double multiply(double x, double y) throws RemoteException
    {
        return x*y;
    }
    public double divide(double x, double y) throws RemoteException
    {
        return x/y;
    }
}
