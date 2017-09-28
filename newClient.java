import java.util.*;
import java.io.*;
import java.lang.*;

//Client 
public class newClient extends Clock 
{
   //main method   
   public static void main(String[] args) throws IOException
   {
      String choice = "x";
      
      //menu
      System.out.println("Main Menu");
      System.out.println("---------------");
   
      System.out.println("1. Host current date and time");
      System.out.println("2. Host uptime");
      System.out.println("3. Host memory use");
      System.out.println("4. Host netstat");
      System.out.println("5. Host current user");
      System.out.println("6. Host running processes");
      System.out.println("7. Quit\n");
   
      //cases : menu options
      while(choice != "7")
      {
         Scanner option = new Scanner(System.in);
         System.out.print("Enter choice: ");
         choice = option.nextLine();
         
         switch(choice)
         {
         case "1":
            System.out.println("Retrieving host date and time...");
            Clock clock = new Clock();
            clock.getCurrentTime();
            break;
         
         case "2":
            System.out.println("Clear");
            break;
            
         case "3":
            System.out.println("Check");
            break;
         
         case "4":
            System.out.println("All's good");
            break;
            
         case "5":
            System.out.println("Hello");
            break;
            
         case "6":
            System.out.println("Retrieving running processes...");
               
           Runtime runtime = Runtime.getRuntime();
           String cmds[] = {"cmd", "/c", "tasklist"};
           Process process = runtime.exec(cmds);
           InputStream inputstream = process.getInputStream();
           InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
           BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
           String line;
           while ((line = bufferedreader.readLine()) != null)
           {
               System.out.println(line);
           }
              
            break;
            
         case "7":
            System.exit(0);
            
         default:
            System.out.println("\nInvalid Choice.\n");
            
            //Redisplays menu for incorrect input
            System.out.println("Main Menu");
            System.out.println("---------------");
   
            System.out.println("1. Host current date and time");
            System.out.println("2. Host uptime");
            System.out.println("3. Host memory use");
            System.out.println("4. Host netstat");
            System.out.println("5. Host current user");
            System.out.println("6. Host running processes");
            System.out.println("7. Quit\n");
            break;
         }   
      }
   }
}

//Clock class
class Clock
{
   Calendar calendar = new GregorianCalendar();
   int hour, minute, second;
   int month, day, year;
   
   public Clock()
   {
      //constructor
   }
   
   //get time/date
   public void getCurrentTime()
   {  
      this.hour = calendar.get(Calendar.HOUR_OF_DAY);
      this.minute = calendar.get(Calendar.MINUTE);
      this.second = calendar.get(Calendar.SECOND);
      
      this.month = calendar.get(Calendar.MONTH);
      this.day = calendar.get(Calendar.DAY_OF_MONTH);
      this.year = calendar.get(Calendar.YEAR);
      System.out.println("Date:" + getMonth() + "/" + getDay() + "/" + getYear() + "\t\t" +
                         "Time:" + getHour() + ":" + getMinute() +":" + getSecond());
      System.out.println();
      
   }
   //getMonth
    public int getMonth()
   {
      return month;
   } 
   //setMonth
   public void setMonth(int month)
   {
      this.month = month;
      getCurrentTime();
   }
   //getDay
    public int getDay()
   {
      return day;
   } 
   //setDay
   public void setDay(int day)
   {
      this.day = day;
      getCurrentTime();
   }
   //getYear
    public int getYear()
   {
      return year;
   } 
   //setYear
   public void setYear(int year)
   {
      this.year = year;
      getCurrentTime();
   }
   //getHour
   public int getHour()
   {
      return hour;
   }
   //setHour 
   public void setHour(int hour)
   {
      this.hour = hour;
      getCurrentTime();
   }
   //getMinute
   public int getMinute()
   {
      return minute;
   }
   //setMinute
   public void setMinute(int minute)
   {
      this.minute = minute;
      getCurrentTime();
   }
   //getSecond
   public int getSecond()
   {
      return second;
   }
   //setSecond
   public void setSecond(int second)
   {
      this.second = second;
      getCurrentTime();
   }
}
