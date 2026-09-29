package BookingSimulation;

import java.util.*;
/**
 * the main class where the user is able to see which minute in any period is open or closed and
 * they are able to make an appointment for as long as the period if its open
 * author: Ben Goering
 */
public class BookingSimulationDriver {

	/**
	 * @param args
	 */
    //global variables
    private static AppointmentBook book = new AppointmentBook();
    private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		//variables
		String input = "";
		
		//main while loop
		while (!input.equals("4"))
		{
			//prints main menu and reads input
			System.out.print(displayMenu());
			System.out.print("Enter choice: ");
			input = scan.next();
		
			//validates input 1-4
			if (!input.equals("1") &&
				!input.equals("2") && 
				!input.equals("3") &&
				!input.equals("4"))
			{
				System.out.println("Error - Invalid input\n");
			}
		
			//if input is 1 - requests an appointment in users wanted period and time slot
			else if (input.equals("1")) 
			{
				System.out.print(requestAppointment());
			}
			//if input is 2 - prints the wanted period time slot
			else if (input.equals("2"))
			{
				System.out.print(printSchedules(2));
			}
			//if input is 3 - prints every period time slot
			else if (input.equals("3"))
			{
				System.out.print(printSchedules(3));
			}
			//if input is 4 - quites
			else if (input.equals("4"))
			{
				System.out.print("GoodBye!");
			}
		}
		
	}
	
	//returns the main display menu
	public static String displayMenu()
	{
		String result = "What would you like to do? \n";
				result += "1--Schedule an appointment \n";
				result += "2--List Period Schedule \n";
				result += "3--List Full Schedule \n";
				result += "4--Quit \n";
		return result;
	}
	
	//trys to make appointment with user input
	//returns a string if it made it or not
	public static String requestAppointment()
	{
		//variables
		String result = "An Error occuried trying to request Appointment\n";
		
		//user prompt
		System.out.print("What period would you like: ");
		int period = scan.nextInt();
		System.out.print("How long would you like your appointment to be: ");
		int hr = scan.nextInt();
		
		//trys to make appointment
		if (book.makeAppointment(period, hr) == false)
		{
			result = hr + " minutes is not available during period " + period + "\n\n";
		}
		else
		{
			result = "Your " + hr + " minute appointment has been scheduled\n\n";
		}
		//output
		return result;
	}
	
	//outputs the period objects depending on user input
	//returns string
	public static String printSchedules(int input)
	{
		String result = "An Error occuried trying to print schedule";
		int p;
		
		//prints single period
		if (input == 2)
		{
			System.out.print("What period would you like: ");
			p = scan.nextInt();
			result = book.getPeriod()[p].toString();
			
		}
		//prints everything
		if (input == 3)
		{
			result = book.toString();
		}
		return result;
	}
}
