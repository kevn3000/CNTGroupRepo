import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry(args[0],1989);
            RemoteInterface stub = (RemoteInterface) registry.lookup("Server");
            System.out.println("Connected to Server");
            //String response = rmi.getName();
            //System.out.println("response: " + response);
        } 
        catch (NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
        }
        /*while(true){
        int swValue = 0;
            int numRun = 0;
            String answer;
            long start_time = -1;
            long end_time = -1;

                while(swValue != 7){
                System.out.println(
                    "\n" +
                    "  1) Get Current Date and Time on Server \n" +
                    "  2) Get Number of Running Processes on Server \n" +
                    "  3) Get Number of Active Socket Connections on Server \n" +
                    "  4) Get Time of Server's Last Reboot \n" +
                    "  5) Get Current Users on Server \n" +
                    "  6) Get Back What is Sent to Server \n" +
                    "  7) Quit \n"               
                );
                swValue = Keyin.inInt(" Select option: ");
                
                if (swValue == 1 || swValue == 2 || swValue == 3 || swValue == 4 || swValue == 5 || swValue == 6){
                    //ask for number of times to run
                    numRun = Keyin.inInt("\tNumber of times you would like to run: ");
                }
                
                switch(swValue){
                    case 1:
                        //Display running command
                        System.out.println("\tRequesting Current Date and Time on Server");
                        //Start time
                        start_time = System.currentTimeMillis();
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:1");
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 1: "+numRun+" time(s)\n");
                        break;

                    case 2:
                        //Display running command
                        System.out.println("\tRequesting Number of Running Processes on Server");
                        //Start time
                        start_time = System.currentTimeMillis();
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:2");
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 2: "+numRun+" time(s)\n");
                        break;

                    case 3:
                        //Display running command
                        System.out.println("\tRequesting Number of Active Socket Connections on Server");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:3");
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim()); 
                            } 
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 3: "+numRun+" time(s)\n");
                        break; 

                    case 4:
                        //Display running command
                            System.out.println("\tRequesting Time of Server's Last Reboot");
                            //Start time
                            start_time = System.currentTimeMillis(); 
                            System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times    
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:4");
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            } 
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 4: "+numRun+" time(s)\n");
                        break; 

                    case 5:
                        //Display running command
                        System.out.println("\tRequesting Current Users on Server");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:5");
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 5: "+numRun+" time(s)\n");
                        break;

                    case 6:
                        //Input to echo
                        String swEcho = Keyin.inString("\tEnter what you would like to send: ");
                        //Display running command
                        System.out.println("\tRequesting \"" + swEcho + "\" to be Sent Back from Server");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:6");
                            out.println(swEcho);
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            } 
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 6: "+numRun+" time(s)\n");
                        break;    

                    case 7:
                        //Display running command
                        System.out.println("\tRequesting Disconnect and Quitting");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //send command to server
                        out.println("MC:7");
                        //Display server response
                        while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                        {
                                System.out.println("\t" + answer.trim());
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms"); 
                        //Display complete message and goodbye                        
                        System.out.println("Option 7 Completed: Goodbye\n");
                        break;

                    default:
                        System.out.println("Invalid Option\n");
                        break;
                }
                }
        return;
        //elseif(<1 or >7){
        //    System.eer.println("Must be between 1-7");
        //    Continue; //goes back to while
        //}
        }*/
    }
}
