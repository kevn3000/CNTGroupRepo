import java.io.*;
import java.net.*;

public class MultiServer {
    public static void main(String[] args) throws IOException {
		//local vars
		int portNumber = Integer.parseInt(args[0]);
        
		//must specify a port number when running application  
        if(args.length < 1){
            System.err.println("\n\nYou need the port: java Server <port number>");
			System.err.println("  --      Example: java Server 3333");
            System.exit(1);
        }

		//opens the socket to listen for client.
        try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("\nServer started. Listening on Port " + portNumber);
			System.out.println("\nWaiting for clients");

			//Keep server open and accept multiple clients
			while(true){      
				new MultiThread(serverSocket.accept()).start();
			}//End while
        }//End try
        catch (IOException e){
            System.out.println("\nException caught" + e);
        }//End Catch

    }//End main
}//end MultiServer
