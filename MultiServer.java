package multiserver;

//Imports 
import java.io.*;
import java.net.*;

//CNT4504 Project 2
//Brandon DeCrescenzo, Tytus Hamilton, Cina Kim, Chelsea Saffold, and Kevin Serrano
public class MultiServer {

    public static void main(String[] args) throws IOException {
        //Variables 
        int portNumber = Integer.parseInt(args[0]);

        //ERROR: If hostName or portNumber is wrong
        if (args.length < 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
        
        //If port number is correct, proceed...
        try {
            //Create a server 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started... Listening on port " + portNumber);
            System.out.println("Waiting for clients...");

            //Keep server open to accept multiple clients 
            while (true) {
                //Create a thread 
                new MultiThread(serverSocket.accept()).start();
            }//End while 
        }//End try 
        
        //End catches 
        catch (IOException e) {
            System.out.println("Exception caught..." + e);
        }//End catch 

    }//End main 
}//End MultiSever 
