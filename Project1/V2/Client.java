import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	//global vars
    public static String userInput;
	public static String stopVar = "Stop";
    public static int times, option;

    public static void main(String[] args) throws IOException {
	//local vars
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);
	String serverResponse;

		//if the user executes the java without params (args)
        if (args.length != 2){
			System.err.println("You need the server and port: java Client <host name> <port number>");
			System.err.println("  --                 Example: java Client 192.168.100.102 3333");
            System.exit(1);
        }//end if

		//When successful, it will establish a socket
        try{         
            Socket clientSocket = new Socket(hostName, portNumber);
      
            //While the socket is open, it will listen for host response and display menu after
			while(true){  
           
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // doorstop to keep listening on socket
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // in is the input from server response
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //user menu input
				Scanner s = new Scanner(System.in); // s var to loop executions

				long start_time = System.currentTimeMillis(); 
		  
		 
				 
				System.out.println( "1) Server Current Date and Time\n"
						+           "2) Server Current Uptime\n"
						+           "3) Server Current Memory Use\n"
						+           "4) Server Current Netstat\n"
						+           "5) Server Current Users\n"
						+           "6) Server Running Processes\n"
						+           "7) Quit\n" );

				System.out.println("Select your option: ");
					option = stdIn.read();

				//System.out.println("How many times would you like to execute this command?");
				//	times = s.nextInt();
				times = 1;
				for(int i=0; i<times; i++){   
					// Switch Menu
					serverResponse=null;
					switch (option) {
						case '1':
							out.println("1");
							System.out.println("");
							System.out.println("The Date and Time: " + in.readLine());
							break;
						case '2':
							out.println("2");
							System.out.println("");
							System.out.println("System has been " + in.readLine());
							break;
						case '3':
							out.println("3");
							System.out.println("");
							System.out.println("=-=-=-=-=-\tServer Memory Response:");
							while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye."))
								System.out.println(serverResponse);
							System.out.println("=-=-=-=-=-\tEnd of Response");
							continue;
						case '4':
							out.println("4");
							System.out.println("");
							System.out.println("=-=-=-=-=-\tServer Netstat Response:");
							while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye."))
								System.out.println(serverResponse);
							System.out.println("=-=-=-=-=-\tEnd of Response");
							serverResponse=null;
							continue;
						case '5':
							out.println("5");
							System.out.println("");
							String users = in.readLine();
							System.out.println("Current Users: " + users);
							break;
						case '6':
							out.println("6");
							System.out.println("");
							System.out.println("=-=-=-=-=-\tServer Processes Response:");
							while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye."))
								System.out.println(serverResponse);
							System.out.println("=-=-=-=-=-\tEnd of Response");
							serverResponse=null;
							continue;
						case '7':
							out.println("7");
							System.out.println("");
							System.out.println("  --  Quitting...");
							return;
						default:
							System.err.println ("  --  Unrecognized option.");
						continue;
					} //end switch
					//System.out.println("\n  --  Request " + (i+1) + " is done");

					//send out the option selected
					while ((userInput = in.readLine()) != null && !userInput.equalsIgnoreCase("Bye.")) {
						out.println(userInput);
					}//end while
					break;
				} //end for loop
				long end_time = System.currentTimeMillis();

				//Print length of time and status of option
				System.out.println("  --  Completed in " + (end_time-start_time) + "ms\n");
			}// end while loop
		}//end try

		//Catchs
		catch(NumberFormatException e){
			System.out.print("  --  Please enter valid option!\n");
			System.exit(1); 
		}//end catch
		catch (UnknownHostException e){
			System.err.println("  --  Unknown Host: " + hostName );
			System.exit(1);
		}//end catch
		catch (IOException e) {
			System.err.println("  --  Accept from " + hostName + " failed.");
			System.exit(1);
		}// end catch
		catch (InputMismatchException e){
			System.err.println ("  --  Unrecognized input "  + e );
		}//end catch
		System.out.println(" -- Connected");
	}//end main
}//end class Client
