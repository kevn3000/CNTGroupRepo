import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements RemoteInterface {
        
    public Server() {}

    @Override
    public String getName() {
        return "Hello, world!";
    }
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
             RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject( obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(1989);
            registry.bind("Server", stub);

            System.err.println("Server ready");
        } 
        catch (AlreadyBoundException | RemoteException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
    @Override
    public String getDateRequest() throws RemoteException{
            Process cmdProc = null;
            System.out.println("Responding to date request from client ");
            try{
                    String[] cmd = {"bash", "-c", "date"};
                    cmdProc = Runtime.getRuntime().exec(cmd);
            }
            catch(IOException ex){
                    System.out.println(ex);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
            String send = "";
            try{
                    send = in.readLine();
            }
            catch(IOException ex){
                    System.out.println(ex);
            }
            return send;
    }
}
