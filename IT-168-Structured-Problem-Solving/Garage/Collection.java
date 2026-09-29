package Garage;

/*
 * this class uses the vehicle class and stores the vehicle object inside an array
 * this class will store methods that can manipulate the vehicles, adding, removing, finding certain vehicles, ect
 * Author: Ben Goering
 */
import java.util.*;
import java.io.*;

public class Collection 
{
	//variables
	private static Vehicle[] vehicleArr;
	private int countCar;
	
	//Default constructor that instantiates array and count with nothing or 0
	public Collection()
	{
		countCar = 0;
		vehicleArr = new Vehicle[100];
	}
	
	//returns countCar
	public int getCount()
	{
		return countCar;
	}
	
	//reads file from param and then adds vehicle to array
	//param: string of file name;
	public void readFile(String fileName)
	{
		try 
		{
			Scanner scan = new Scanner (new File (fileName));
			while (scan.hasNext())
			{
				String plate = scan.nextLine();
				String year = scan.nextLine();
				String model = scan.nextLine();
				String color = scan.nextLine();
				String type = scan.nextLine();
				String date = scan.nextLine();
				double cost = scan.nextDouble();
				scan.nextLine();
				double current = scan.nextDouble();
				scan.nextLine();
				String work1 = scan.nextLine();
				String note = scan.nextLine();
				if (scan.hasNext())
				{
					scan.nextLine();
				}

				//gets manufacturer from string year
				String manu = year.substring(year.indexOf(" ")+1);
				year = year.substring(0,year.indexOf(" "));

				//gets model without miles
				String mile = model.substring(model.lastIndexOf(" ")+1);
				model = model.substring(0,model.lastIndexOf(" "));
				
				//makes work a boolean
				boolean work = true;
				if (work1.equals("True"))
				{
					work = true;
				}
				if (work1.equals("False"))
				{
					work = false;
				}

				//makes new car and adds 1 to car count
				vehicleArr[countCar] = new Vehicle(plate, manu, type, year, model, color, date, cost, current, work, note, mile);
				countCar ++;
			}
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Error - invalid input");
		}
		catch (NoSuchElementException ime)
		{
			System.out.println("Error - No input");
		}
	}
	
	//writes to file with contents of vehicleArr
	//param file name
	public void writeFile(String fileName)
	{
		try 
		{
			PrintWriter outfile = new PrintWriter(new FileWriter(fileName, true));
			int count = 0;
			
			while (count < countCar)
			{
				outfile.println(vehicleArr[count].getPlateNum());
				outfile.println(vehicleArr[count].getYear() + " " + vehicleArr[count].getMaker());
				outfile.println(vehicleArr[count].getModel() + " " + vehicleArr[count].getMiles());
				outfile.println(vehicleArr[count].getColor());
				outfile.println(vehicleArr[count].getVtype());
				outfile.println(vehicleArr[count].getGotDate());
				outfile.println(vehicleArr[count].getCost());
				outfile.println(vehicleArr[count].getCurrentVal());
				outfile.println(vehicleArr[count].isWorkNeeded());
				outfile.println(vehicleArr[count].getNotes());
				outfile.println();
				
				count++;
			}
			outfile.close();
			System.out.println("Vehciles written to file: " + count);
		} 
		catch (IOException e) 
		{
			System.out.println("Error");
		}
	}
	
	//sorts vehicleArr array by plate number
	//returns vehicle array
	public  Vehicle[] sortArray()
	{
		for (int x = 0; x < countCar - 1; x++) 
		{
			int index = x;

	        for (int y = x + 1; y < countCar; y++) 
	        {
	        	if (vehicleArr[y].getPlateNum().compareTo(vehicleArr[index].getPlateNum()) < 0) 
	                {
	                    index = y;
	                }
	        }

	        // Swap vehicles[i] and vehicles[minIndex]
	        Vehicle temp = vehicleArr[index];
	        vehicleArr[index] = vehicleArr[x];
	        vehicleArr[x] = temp;
		}     
		return vehicleArr;
	}
	
	//param: vehicle newCar
	//will search array for input vehicle and if copy is not found then it will add
	//vehicle to the end of array
	public void addVehicle(Vehicle newCar)
	{
		int count = 0;
		boolean isCopy = false;
		while (count < countCar)
		{
			if (vehicleArr[count].getPlateNum().equals(newCar.getPlateNum()))
			{
				isCopy = true;
			}
			count++;
		}
		
		if (isCopy == false)
		{
			vehicleArr[count] = newCar;
			countCar ++;
		}
		if (isCopy == true)
		{
			System.out.println("Check input - duplicate found");
		}
	}
	
	//returns a string of vehicles inside vehiclArr
	public String displayCollection()
	{
		String result = "";
		int count = 0;
		
		while (count < countCar)
		{
			result += vehicleArr[count].toString();
			count++;
		}
		
		return result;
	}
	
	//returns String of vehicles that need work done from vehicleArr
	public String needsWork()
	{
		String result = "";
		int count = 0;
		
		while (count < countCar)
		{
			if (vehicleArr[count].isWorkNeeded())
			{
				result += vehicleArr[count].toString();
			}
			count++;
		}
		if (result.equals(""))
		{
			result = "No cars found";
		}
		return result;
	}
	
	//param: plate num
	//edits vehicle that gets inputed
	public void updateVehicle(String plate)
	{
		int index = findPlate(plate);
		Scanner scan = new Scanner(System.in);
		int input = -1;
		
		while (input != 0)
		{
			
			System.out.println("0 quit");
			System.out.println("1 plate number");
			System.out.println("2 Manufacturer");
			System.out.println("3 Vehicle Type");
			System.out.println("4 year");
			System.out.println("5 model");
			System.out.println("6 color");
			System.out.println("7 date acquired");
			System.out.println("8 cost");
			System.out.println("9 current value");
			System.out.println("10 work needed(y/n)");
			System.out.println("11 nature of work");
			System.out.println("12 miles");

			System.out.println("Enter your choice: ");
			input = scan.nextInt();
			scan.nextLine();
			
			if (input != 0)
			{
				switch (input)
				{
					case(1):
						System.out.print("Enter new plate number: ");
						vehicleArr[index].setPlateNum(scan.nextLine());
						break;
						
					case(2):
						System.out.print("Enter new manufacturer: ");
						vehicleArr[index].setMaker(scan.nextLine());
						break;
						
					case(3):
						System.out.print("Enter new vehicle type: ");
						vehicleArr[index].setVtype(scan.nextLine());
						break;
						
					case(4):
						System.out.print("Enter new year: ");
						vehicleArr[index].setYear(scan.nextLine());
						break;
						
					case(5):
						System.out.print("Enter new model: ");
						vehicleArr[index].setModel(scan.nextLine());
						break;
						
					case(6):
						System.out.print("Enter new Color: ");
						vehicleArr[index].setColor(scan.nextLine());
						break;
						
					case(7):
						System.out.print("Enter new date: ");
						vehicleArr[index].setGotDate(scan.nextLine());
						break;
						
						
					case(8):
						System.out.print("Enter new cost: ");
						vehicleArr[index].setCost(scan.nextDouble());
						scan.nextLine();
						break;
						
					case(9):
						System.out.print("Enter new current value: ");
						vehicleArr[index].setCurrentVal(scan.nextDouble());
						scan.nextLine();
						break;
						
					case(10):
						System.out.print("Enter for work needed (y/n): ");
					
						if (scan.nextLine().equals("y"))
						{
							vehicleArr[index].setWorkNeeded(true);
						}
						else if (scan.nextLine().equals("n"))
						{
							vehicleArr[index].setWorkNeeded(false);
						}
					break;
					
					case(11):
						System.out.print("Enter work notes: ");
						vehicleArr[index].setNotes(scan.nextLine());
					break;
					
					case(12):				
						System.out.print("Enter new amount of miles: ");
						vehicleArr[index].setMiles(scan.nextLine());
						break;
						
					default:
						System.out.println("Error - invalid choice\n");
						break;
						
				}//switch
			}//if
		}//while
		
		if (input == 0)
		{
			System.out.println("Closeing vehicle editior");
		}
        scan.close();
	}
	
	//param: String of plate number
	//returns String of car details with the matching plate number as input
	public String displayVehicle(String plate)
	{
		String result = "Vehicle not found";
		
		for (int x = 0; x < countCar; x++)
		{
			if (vehicleArr[x].getPlateNum().equals(plate))
			{
				result = vehicleArr[x].toString();
			}
		}
		return result;
	}
	
	//param: string plate
	//will create a new vehicle array adding ever car that doesn't match the 
	//input plate and make it the new vehicleArr
	public void removeVehicle(String plate)
	{
		int count = 0;
		int count2 = 0;
		Vehicle[] newArr = new Vehicle[100];
		while (count < countCar)
		{
			if (!plate.equals(vehicleArr[count].getPlateNum()))
			{
				newArr[count2] = vehicleArr[count2];
				count2++;
			}
			if (plate.equals(vehicleArr[count].getPlateNum()))
			{
				countCar--;
			}
			else
			{
				System.out.println("Car not found");
			}
			count++;
		}
		
		vehicleArr = newArr;
		System.out.println("Number of vehicles in collection: " + countCar);
	}
	
	//returns int
	//finds the index of plate number
	//param plate to find
	public int findPlate(String plate)
	{
		int result = -1;
		
		for (int x = 0; x < countCar; x++)
		{
			if (vehicleArr[x].getPlateNum().equals(plate))
			{
				result = x;
			}
		}
		return result;
	}
}