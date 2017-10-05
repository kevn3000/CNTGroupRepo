import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
   /**
    * @param args The command line arguments
    */
	public static void main(String[] args) throws IOException {
	  //Variables - local
	  int portNumber = 2202;

	  //Open the socket and listen
      ServerSocket serverSocket = new ServerSocket(portNumber);
	  Socket s = serverSocket.accept();
      System.out.println("Server started. Listening on Port " + portNumber);
      System.out.println("Waiting for clients");
	  
	  // take and return client requests
	  try{
         PrintWriter out = new PrintWriter(s.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

         while(true){
            //Variables
            String option = in.readLine();
            String send = "str";
            Process cmdProc;
            cmdProc=null;

            //Checks for special characters    
            if(option.equalsIgnoreCase( "/*!@#$%^&*()\"{}_[]|\\?/<>,.")){
               System.err.println("Unrecognized option...please try again");
               return;
            }//end if

            // Execute the appropriate command.
            switch (option){
               case "O:1": 
                  System.out.println("Responding to date request from the client ");
                  String[] cmd1 = {"bash", "-c", "date"};
                  cmdProc = Runtime.getRuntime().exec(cmd1);
               break;
               case "O:2":
                  System.out.println("Responding to uptime request from the client ");
                  String[] cmd2 = {"bash", "-c", "uptime"};
                  cmdProc = Runtime.getRuntime().exec(cmd2);
               break;
               case "O:3":
                  System.out.println("Responding to memory used request from the client ");
                  String[] cmd3 = {"bash", "-c", "free"};
                  cmdProc = Runtime.getRuntime().exec(cmd3);
               break;
               case "O:4":
                  System.out.println("Responding to Netstat request from the client ");
                  String[] cmd4 = {"bash", "-c", "netstat"};
                  cmdProc = Runtime.getRuntime().exec(cmd4);
               break;
               case "O:5":
                  System.out.println("Responding to current users request from the client ");
                  String[] cmd5 = {"bash", "-c", "users"};
                  cmdProc = Runtime.getRuntime().exec(cmd5);
               break;
               case "O:6":
                  System.out.println("Responding to running processes request from the client ");
                  String[] cmd6 = {"bash", "-c", "ps -aux | less"};
                  cmdProc = Runtime.getRuntime().exec(cmd6);
               break;
               case "O:7":
                  System.out.println("Quitting...");
                  String[] cmd7 = {"bash", "-c", "exit"};
                  cmdProc = Runtime.getRuntime().exec(cmd7);
                  s.close();
               break;
               default:
                  System.out.println("Unknown request ");
               return;

            }//end switch
                   
            // Read the result of the commands and send the result to the client one line at a time
            // followed by the line "Finished"
            BufferedReader cmdin = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
            String cmdans;
            while((cmdans = cmdin.readLine()) != null) {
               out.println(cmdans);
			}//end while
            out.println("Finished");

        }//end while
      }//end try

      // catch exception errors
      catch (IOException e){
         System.out.println("Exception caught" + e);
      } //End catch
 
   } //end Main
} //end Server
