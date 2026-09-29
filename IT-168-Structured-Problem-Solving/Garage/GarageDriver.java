package Garage;

/*
 * this is the main driver class for the collection and vehicle classes
 * Author: Ben Goering
 */

import java.util.*;

public class GarageDriver 
{
	//variables
	static private Scanner scan = new Scanner(System.in);
	static private Collection garage = new Collection();
	
	//main method that just calls setup
	public static void main(String[] args) 
	{
		setUp();
	}
	
	//main menu
	public static void setUp()
	{
		//variables
		String[] menuArray = {"Add a vehicle", "Edit a vehicle", "Search for a vehicle", "Display vehicles that need work", "Display the entire collection", "Load from a file", "Write to a file", "Delete a vehicle", "quite"};
		TextMenu menu = new TextMenu(menuArray);
		int input = 0;
		String plate = "";
		
		//prints menu on loop until 9 is entered\
		while (input != 9)
		{
			input = menu.getChoice();
			switch(input)
			{
			
			//adds vehicle
			case 1:
				System.out.print("Enter the plate number: ");
			    plate = scan.nextLine();
				
				System.out.print("Eneter year of vehicle ");
				String year = scan.nextLine();
				
				System.out.print("Enter the manufacturer:  ");
				String manu = scan.nextLine();
				
				System.out.print("Enter the model of vehicle:  ");
				String model = scan.nextLine();
				
				System.out.print("Enter the current miles: ");
				String mile = scan.nextLine();
				
				System.out.print("Enter the color: ");
				String color = scan.nextLine();
				
				System.out.print("Enter the type of vehicle: ");
				String type = scan.nextLine();
				
				System.out.print("Enter the date acquired: ");
				String date = scan.nextLine();
				
				System.out.print("Enter the purchase price:");
				double cost = scan.nextDouble();
				
				System.out.print("Enter the current value: ");
				double current = scan.nextDouble();
				scan.nextLine();
				
				System.out.print("is work needed (yes/no): ");
				String work1 = scan.nextLine();
				
				//makes work a boolean
				boolean work = true;
				if (work1.equals("yes"))
				{
					work = true;
				}
				if (work1.equals("no"))
				{
					work = false;
				}
				
				System.out.print("Enter nature of the work required (enter 'none' if none needed): ");
				String note = scan.nextLine();
				
				Vehicle newCar = new Vehicle(plate, manu, type, year, model, color, date, cost, current, work, note, mile);
				garage.addVehicle(newCar);
				System.out.print("Number of vehicles in garage: " + garage.getCount());
				break;
				
			//edit vehicle
			case 2:
				System.out.print("Enter plate number: ");
				plate = scan.nextLine();
				
				int index = garage.findPlate(plate);
				if (index == -1) 
				{ 
					 System.out.println("Error - Plate not found"); 
					 input = 0; 
				}
				else
				{
					garage.updateVehicle(plate);
				}
				break;
				
			//search for vehicle
			case 3:
				System.out.print("Enter the plate number: ");
				scan.nextLine();
				plate = scan.nextLine();
				
				System.out.println(garage.displayVehicle(plate));
				break;
				
			//display work needed vehicles
			case 4:
				garage.sortArray();
				System.out.println(garage.needsWork());
				break;
				
			//display entire garage
			case 5:
				garage.sortArray();
				System.out.print(garage.displayCollection());
				break;
				
			//load file
			case 6:
				garage.sortArray();
				System.out.print("Enter the name of the file to be read: ");
				String fileName = scan.next();
				garage.readFile(fileName);
				scan.nextLine();
				break;
				
			//write to file
			case 7:
				System.out.print("Enter file to write: ");
				garage.writeFile(scan.nextLine());
				break;
			//deletes vehicle
			case 8:
				System.out.print("Enter the plate number: ");
				garage.removeVehicle(scan.nextLine());
				break;
			}
			
		}
		System.out.println("--------Ending Application--------");
		System.out.print("Good Bye!");
	}
}
