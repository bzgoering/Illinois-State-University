import java.util.*;

//main class for player and game class
//Author: Ben Goering

public class Driver 
{
	public static void main(String[] args)
	{
		//variables
		String names = "";
		String inputName;
		int input = 0;
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		boolean quit = false;
		
		//intro
		System.out.println("Welcome To The Card Game");
		
		//main game loop
		while (quit == false)
		{
			//resets variables
			loop = true;
			names = "";
			input = 0;
			
			//validation input for number of players
			while (loop == true)
			{
				//gets set up for game
				System.out.print("\nPlease Enter Number of Players: ");
				try 
				{
					input = scan.nextInt();
					scan.nextLine();
					loop = false;
					
					//makes sure player count is between 3 and 6 inclusive
					if (input < 3 || input > 6)
					{
						loop = true;
						System.out.println("Player count is out of bounds please select an integer between 3 and 6 inclusive.");
					}
				}
				//catches any error from user input
				catch(NoSuchElementException | IllegalStateException e)
				{
					System.out.println("Error - Invalid input please input an integer between 3 and 6 inclusive");
					scan.nextLine();
					loop = true;
				}
			}
		
			//gets name of players
			for (int x = 0; x < input; x++)
			{
				System.out.print("Enter Name " + (x+1) + ": ");
				inputName = scan.nextLine();
				
				//makes sure no commas are inputed
				if (inputName.contains(","))
				{
					inputName = inputName.replace(",", "");
				}
				
				names += inputName + ",";
			}
		
			//finish game set up
			Game game1 = new Game(names);
			
			//Initialize game
			game1.sim();
			
			//variables for exiting
			String exitInput = "";
			boolean exitLoop = true;
			
			//exit loop
			while (exitLoop == true)
			{
				//gets user input
				System.out.print("Would you like to keep playing(y/n)?");
				exitInput = scan.nextLine();
				
				//validates
				if (exitInput.equals("n"))
				{
					quit = true;
					exitLoop = false;
				}
				else if (!exitInput.equals("y"))
				{
					exitLoop = true;
				}
				else
				{
					exitLoop = false;
				}
			}
		}
		System.out.println("\nThanks For Playing");
		scan.close();
	}
}
