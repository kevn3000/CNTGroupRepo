import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
	// Variables - Global
	public static String userInput;
	public static int option;

	/**
	 * @param args The command line arguments for output
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Variables - Local
		String hostName = "192.168.100.102";
		int portNumber = 2202;

		// Start try - open socket
		try{         
			Socket clientSocket = new Socket(hostName, portNumber);
			System.out.println("Connecting to server " + hostName + " on port " + portNumber + "\n");
		
			// Reader, writer
			while(true){
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				// * Display response for user
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				Scanner s = new Scanner(System.in);
   
				// Action timer
				long start_time = System.currentTimeMillis(); 
   
				// * Display a menu
				System.out.println( "1) Server Current Date and Time\n"
						+           "2) Server Uptime\n"
						+           "3) Server Memory use\n"
						+           "4) Server Netstat\n"
						+           "5) Server Current Users\n"
						+           "6) Server Running Processes\n"
						+           "7) Quit\n" );
   
				// * Prompt the user for a command
				System.out.println("Please select an option: ");
				option = stdIn.read();
   
				// * Display response for user [ System.out.println(in.readLine()); ]
				switch (option) {
				case '1': 
					out.println("O:1");
					System.out.println(in.readLine());
				break;
				case '2':
					out.println("O:2");
					System.out.println(in.readLine());
				break;
				case '3':
					out.println("O:3");
					System.out.println(in.readLine());
				break;
				case '4':
					out.println("O:4");
					System.out.println(in.readLine());
				break;
				case '5':
					out.println("O:5");
					System.out.println(in.readLine());
				break;
				case '6':
					out.println("O:6");
					System.out.println(in.readLine());
				break;
				case '7':
					out.println("O:7");
					System.out.println(in.readLine());
				return;
				default:
					System.err.println("Invalid option. Choose an option listed.");
				continue;
				} //end switch
   
				while ((userInput = in.readLine()) != null && !userInput.equalsIgnoreCase("Finished")) {
					// * Send that command request to the server on the host
					out.println(userInput);
   
					// Action timer
					long end_time = System.currentTimeMillis();
   
					// Print length of time and status of option (diff action timers)
					System.out.println("--" + "Time for compeltion was " + (end_time-start_time) + "ms");
					System.out.println("Option " + option + " is complete.");
				} // end while loop
			} // end while loop
		} //end try

		/** * Test user input for command validity.
		 * If user command is invalid, inform the user and redisplay the menu.
		 * (completed by the case default
		 */
		catch(NumberFormatException e){
			System.out.print("Please enter valid option.\n");
			System.exit(1);
		} //end catch
		catch (UnknownHostException e){
			System.err.println("Invalid host: " + hostName);
			System.exit(1);
		} //end catch
		catch (IOException e){
			System.err.println("Could not establish connection to " + hostName);
			System.exit(1);
		} //end catch
		catch (InputMismatchException e){
			System.err.println ( "Unrecognized input: "  + e);
		} //end catch

	} //end main
} //end class Client
