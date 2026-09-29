import java.rmi.*;

public interface ComputeInterface extends Remote 
{
    //abstract functions for implimentations that calculate basic math operations
    double add(double x, double y) throws RemoteException;
    double sub(double x, double y) throws RemoteException;
    double multiply(double x, double y) throws RemoteException;
    double divide(double x, double y) throws RemoteException;
}
