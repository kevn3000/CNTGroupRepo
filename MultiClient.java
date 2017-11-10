package multiclient;

//Imports
import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient {

    public static String userInput;
    public static int times, option;

    //CNT4504 Project 2
    //Brandon DeCrescenzo, Tytus Hamilton, Cina Kim, Chelsea Saffold, and Kevin Serrano 
    public static void main(String[] args) throws IOException {

        //Local variables 
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String serverResponse;

        //ERROR: If hostName or portNumber is wrong 
        if (args.length != 2) {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        //If hostName and portNumber is correct, proceed...
        try {
            //Create socket 
            Socket clientSocket = new Socket(hostName, portNumber);

            //While socket is opening & listening...
            while (true) {

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                Scanner s = new Scanner(System.in);

                //Start time 
                long start_time = System.currentTimeMillis();

                //Display menu to client 
                System.out.println(
                        "1) Host Current Date and Time\n"
                        + "2) Host Uptime\n"
                        + "3) Host Memory Use\n"
                        + "4) Host Netstat\n"
                        + "5) Host Current Users\n"
                        + "6) Host Running Processes\n"
                        + "7) Quit\n"
                );

                //Ask client for selection 
                System.out.println("Select your option: ");
                option = stdIn.read();

                //Ask client how threads they would like to run in the background 
                //TODO: Create for loop on server side 
                //System.out.println("How many times would you like to execute this command?");
                //times = s.nextInt();
                
                //Switch for options 
                switch (option) {
                    //Host Current Date & Time
                    case '1':
                        out.println("1");
                        System.out.println("Current Date & Time: " + in.readLine());
                        break;
                    //Host Uptime
                    case '2':
                        out.println("2");
                        System.out.println("Uptime: " + in.readLine());
                        break;
                    //Host Memory Use
                    case '3':
                        out.println("3");
                        System.out.println("Memory Use: " + in.readLine());
                        while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye.")) {
                            System.out.println(serverResponse);
                        }
                        continue;
                    //Host Netstat
                    case '4':
                        out.println("4");
                        System.out.println("Netstat: " + in.readLine());
                        while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye.")) {
                            System.out.println(serverResponse);
                        }
                        serverResponse = null;
                        continue;
                    //Host Current Users 
                    case '5':
                        out.println("5");
                        System.out.println("");
                        String users = in.readLine();
                        System.out.println("Current Users: " + in.readLine());
                        break;
                    //Host Running Processes 
                    case '6':
                        out.println("6");
                        System.out.println("Running Processes: " + in.readLine());
                        while ((serverResponse = in.readLine()) != null && !serverResponse.equals("Bye.")) {
                            System.out.println(serverResponse);
                        }
                        serverResponse = null;
                        continue;
                    //Quit 
                    case '7':
                        out.println("7");
                        System.out.println("Quiting..." + in.readLine());
                        return;
                    //Invalid Input 
                    default:
                        System.err.println("ERROR! Invalid input... Please type a number between 1 and 7.");
                        continue;
                }//End switch 

                //while ((userInput = in.readLine()) != null && !userInput.equalsIgnoreCase("ServerDone")) {
                    //out.println(userInput);
                //}
                
                //End timer 
                long end_time = System.currentTimeMillis();
                
                System.out.println("Request is complete..."); 
                //Print length of time and status of option
                System.out.println("--" + "This command took " + (end_time - start_time) + "ms");
            }//End while loop
        }//End try
        
        //Catch errors 
        //If input for menu is not in number format 
        catch (NumberFormatException e) {
            System.out.print("Please enter a valid option...\n");
            System.exit(1);
        }//End catch 
        //If hostName is incorrect
        catch (UnknownHostException e) {
            System.err.println("Unable to find " + hostName + " ...");
            System.exit(1);
        }//End catch
        //If server cannot connect to host 
        catch (IOException e) {
            System.err.println("Couldn't connection to " + hostName + " ...");
            System.exit(1);
        }//End catch
        //If input is incorrect
        catch (InputMismatchException e) {
            System.err.println("Unrecognized input: " + e + " Please enter a valid option...");
        }//End catch

    }//End main

}//End MultiClient

