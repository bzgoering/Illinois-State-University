import java.util.*;

//main class for the game class
//will run the game class until user doesn't want to play again

public class Driver 
{
	public static void main(String[] args) 
	{
		//variables
		String repeat = "Y";
		Random rand = new Random();
		
		//welcome message
		System.out.println("Welcome to Eels and Escalators");
		System.out.println("Rules:"
						 + "\nPlayers will stay withing the board of 1 - 100 squares"
						 + "\nPlayers will take turns rolling a 6 sided die"
						 + "\nEach space has a possiblity of being a eel or a escalator square"
						 + "\nLand on an eel and you moveback 1 - 8 space(s)"
						 + "\nland on an escalator and you gain 1 - 8 space(s)"
						 + "\nFirst Person to get the the end wins\n");
		
		//main loop that run the program
		while (repeat.equals("Y"))
		{
			//variables
			int input = 0;
			Scanner scan = new Scanner (System.in);
			boolean repeatVal = true;
			
			//validates user input
			while (repeatVal == true)
			{
				System.out.print("Please input the number of players: ");
				try
				{
					input = scan.nextInt();
					scan.nextLine();
					repeatVal = false;
				}
				//if user enters incorrect input
				catch(InputMismatchException e)
				{
					System.out.println("Invalid Input - Please Try Again\n");
					scan.nextLine();
					repeatVal = true;
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Invalid Input - Please Try Again\n");
					scan.nextLine();
					repeatVal = true;
				}
			}
			//set up game
			Game game1 = new Game(input);
			
			//play game
			game1.play();
			
			//exit program if user enter y or Y
			//validates input too
			repeat = "exit";
			while (repeat.equals("exit"))
			{
				System.out.print("Would You Like To Play Again(Y/N)?");
				repeat = scan.nextLine();
				
				//gets user response and makes appropriate adjustments
				if (repeat.equals("n") || repeat.equals("N"))
				{
					repeat = "N";
				}
				else if (repeat.equals("Y") || repeat.equals("y"))
				{
					repeat = "Y";
				}
				else
				{
					System.out.println("Invalid Input - Please Try Again\n");
					repeat = "exit";
				}
			}
		}
		
		//exit statement
		System.out.println("Thanks For Playing - GoodBye");
	}
}
