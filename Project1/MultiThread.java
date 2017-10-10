import java.io.*;
import java.net.*;

public class MultiThread extends Thread {
	//Global Vars
	Socket s = null;
    
	//Thread Method
    public MultiThread(Socket clientSocket){
        this.s = clientSocket;
    } //end constructor
    
	//run method
    @Override
    public void run(){
		//Announce new client connection
		System.out.println("Client connected...starting " + Thread.currentThread().getName() );

		//Listen
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true); //keep the output open
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream())); //listen for input

			//while it is open (listening for requests)
			while(true){     
				//local vars
				String option = in.readLine();
				String send = "str";
				Process cmdProc;
				cmdProc=null;
				int minMen = 0;
				int maxMen = 8;
				
				//Checks menu choice   
				if (option.equalsIgnoreCase( "/*!@#$%^&*()\"{}_[]|\\?/<>,.")) {
					System.err.println("Unrecognized option...please try again");
					return;
				}//end if
		
	
				//exectute commend in linux format
				switch (option){
					case "1": 
						System.out.println("Responding to date request from the client ");
						String[] cmd = {"bash", "-c", "date +%D%t%T%t%Z"};
						cmdProc = Runtime.getRuntime().exec(cmd);
					break;
					case "2":
						System.out.println("Responding to uptime request from the client ");
						String[] cmdA = {"bash", "-c", "uptime -p"};
						cmdProc = Runtime.getRuntime().exec(cmdA);
					break;
					case "3":
						System.out.println("Responding to number of active socket connections request from the client ");
						String[] cmdB = {"bash", "-c", "free -m"};
						cmdProc = Runtime.getRuntime().exec(cmdB);
					break;
					case "4":
						System.out.println("Responding to netstat request from the client ");
						String[] cmdC = {"bash", "-c", "netstat -r"};
						cmdProc = Runtime.getRuntime().exec(cmdC);
					break;
					case "5":
						System.out.println("Responding to current users request from the client ");
						String[] cmdD = {"bash", "-c", "users"};
						cmdProc = Runtime.getRuntime().exec(cmdD);
					break;
					case "6":
						System.out.println("Responding to current processes request from the client ");
						String[] cmdE = {"bash", "-c", "ps -aux | less"};
						cmdProc = Runtime.getRuntime().exec(cmdE);
					break;
					case "7":
						System.out.println("Quitting...");
						String[] cmdF = {"bash", "-c", "exit"};
						cmdProc = Runtime.getRuntime().exec(cmdF);
						s.close();
						System.out.println("Socket closed.\n");
					break;
					default:
						System.out.println("Unknown request ");
					return;
				}//end switch

				if(option.equals("3") || option.equals("4") || option.equals("6")){
					out.println("");
				}//end if
			
				// Read the result of the commands and send the result to the client one line at a time
				// followed by the line "ServerDone"
				else {
					BufferedReader cmdin = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
					String cmdans;
					while((cmdans = cmdin.readLine()) != null)
						out.println(cmdans);
				}//end else
				out.println("Finished");
			}//end while
		}//end try
		catch (IOException e){
			System.out.println("Exception caught " + e);
			System.out.println(e.getMessage());
		}// end catch
  
	}//end run()
} //end class MultiThread
