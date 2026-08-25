package AutoInventory;

//imports
import java.io.*;
import java.util.*;

public class AutoInventoryDriver
{
	public static void main(String[] args) 
	{
		//intro screen
		System.out.println("Welcome to The Auto Inventory System");
		
		//variables
		int input = 0;
		Auto[] vehicles = new Auto[10];

		//read file and fills array
		vehicles = read("Auto_Inventory.csv");

		//single scanner shared for the life of the program
		Scanner scan = new Scanner(System.in);

		//main loop
		while (input != 5)
		{
			//display menu
			menu();
			System.out.print("Please select: ");

			//validates
			input = validation(scan);

			//output based off user input
			switch(input)
			{
			//shows Sedan's
				case(1):
					findType("Sedan", vehicles);
					break;
			//shows SUV
				case(2):
					findType("SUV", vehicles);
					break;
			//shows Trucks
				case(3):
					findType("Truck", vehicles);
					break;
			//Shows cars based of budget
				case(4):
					System.out.print("\nPlease enter your brudget: ");

					//validates input
					double budget = budgetValidation(scan);

					//prints vehicles under budget
					getBudgetCar(budget, vehicles);
					break;
			}
		}

		//application stops msg
		System.out.println("Good-Bye");
		scan.close();
	}
	
	//prints cars based on budget
	public static void getBudgetCar(double price, Auto[] array)
	{
		//variables
		int x = 0;
		boolean find = false;
		int count = 0;
		
		//traverse through vehicle array until none left
		while (array[x] != null)
		{
			//finds cars under budget and couts how many
			if (array[x].calcMSRP() < price)
			{
				System.out.println("\n" + array[x].toString());
				find = true;
				count ++;
			}
			x++;
		}
		
		//outputs response to how many cars found
		if (find == false)
		{
			System.out.println("No vehicles under your budget");
		}
		else
		{
			System.out.println("\nCongrats we found " + count + " vehicles under your budget");
		}
	}
	
	//displays menu
	public static void menu()
	{
		//menu
		System.out.println("\n1. Show Sedans\n"
						   + "2. Show SUV's\n"
						   + "3. Show Trucks\n"
						   + "4. Show vehicles based on your budget\n"
						   + "5. Quit\n");
	}
	
	//validates the menu input
	public static int validation(Scanner input)
	{
		//varibles
		boolean repeat = true;

		//loops until user enter correct input
		while (repeat)
		{
			//gets user input
			try
			{
				int userInput = input.nextInt();

				if (userInput < 1 || userInput > 5)
				{
					//try again output
					input.nextLine();
					System.out.print("Please input a valid input[1,5]: ");

					//Reinforces repeat to be true
					repeat = true;
				}
				else
				{
					return userInput;
				}
			}
			//if error makes them do it again
			catch(InputMismatchException e)
			{
				//try again output
				input.nextLine();
				System.out.print("Please input a valid input[1,5]: ");

				//Reinforces repeat to be true
				repeat = true;
			}
		}

		//never gets here
		return -1;
	}

	//validates budget input
	public static double budgetValidation(Scanner input)
		{
		//Variables
			boolean repeat = true;

			//loops until user enter correct input
			while (repeat)
			{
				//trys user input
				try
				{
					double userInput = input.nextDouble();
					return userInput;
				}
				//makes user repeat if errors
				catch(InputMismatchException e)
				{
					//try again output
					input.nextLine();
					System.out.print("Error - Please input a valid budget: ");

					//Reinforces repeat to be true
					repeat = true;
				}
			}

			//never gets here
			return -1;
		}
		
	//fills Auto array
	public static Auto[] read(String fileName)
	{
		//temp variables
		String vehicleInfo = null;
		String VIN;
		String make;
		String model;
		int year;
		String type;
		double originalPrice;
		int passenger = -1;
		int tow = -1;
		boolean offRoad = false;
		int count = 0;
		Auto[] tempArray = new Auto[10];

		
		//validation for reading file
		try (Scanner fileScan = new Scanner(new File(fileName)))
		{
			fileScan.nextLine();

			//main reading loop
			while (fileScan.hasNext())
			{
				//reads vehicle info from file
				vehicleInfo = fileScan.nextLine();

				//scanner to read vehicle info for appropriate variables
				Scanner infoScan = new Scanner(vehicleInfo);
				infoScan.useDelimiter(",");
				
				//gets VIN
				VIN = infoScan.next();
				//gets Make
				make = infoScan.next();
				//gets Model
				model = infoScan.next();
				//gets year
				year = infoScan.nextInt();
				//gets type
				type = infoScan.next();
				//gets OG price
				originalPrice = infoScan.nextDouble();
				//gets Passenger
				if (infoScan.hasNextInt())
				{
					passenger = infoScan.nextInt();
				}
				else
				{
					infoScan.next();
				}
				//gets towing car pound
				if (infoScan.hasNextInt())
				{
					tow = infoScan.nextInt();
				}
				else
				{
					infoScan.next();
				}
				//gets off-Road info
				String temp = infoScan.next();
				if (temp.equals("No") || temp.equals("N/A"))
				{
					offRoad = false;
				}
				else if (temp.equals("Yes"))
				{
					offRoad = true;
				}
				
				
				//makes Auto vehicle object				
				if (type.equals("Sedan"))
				{
					tempArray[count] = new Sedan(VIN, make, model, year, originalPrice, type, passenger);
				}
				else if (type.equals("SUV"))
				{
					tempArray[count] = new SUV(VIN, make, model, year, originalPrice, type, offRoad);
				}
				else if (type.equals("Truck"))
				{
					tempArray[count] = new Truck(VIN, make, model, year, originalPrice, type, tow);
				}
				
				//resets appropriate variables
				passenger = -1;
				tow = -1;
				count++;
				infoScan.close();
			}
		}
		
		//handles any errors that may occur
		catch(FileNotFoundException e)
		{
			System.out.println("Error - File not Found");
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Error - Invalid input");
		}
		
		//output
		return tempArray;

	}

	//finds type of car and outputs
	public static void findType(String type, Auto[] list)
	{
		//main travser loop through vehicle array
		for (int x = 0; x < list.length; x++)
		{
			//makes sure vehicle in in array spot
			if (list[x] != null)
			{
				//prints vehicle
				if (list[x].getType().equals(type))
				{
					System.out.println("\n" + list[x].toString());
				}
			}
			
		}
	}
}
