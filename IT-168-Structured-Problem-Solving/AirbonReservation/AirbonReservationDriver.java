package AirbonReservation;

import java.util.*;
/**
 * This class will test the airbnblisting class
 * 
 *Author: Ben Goering
 */
public class AirbonReservationDriver {
	public static void main(String[] args) {
		//variables
		Scanner scan = new Scanner (System.in);
		String menuOption = " ";
		String option;
		int typeOption;
		int rateOption;
		int nightOption;
		
		//creates three objects
		AirbnbListing listing1 = new AirbnbListing("Spotlight Studio", "22 N Main St., Normal", "Studio", 50);
		AirbnbListing listing2 = new AirbnbListing("RedBird Apartment", "200 College Ave., Normal", "Apartment", 85);
		AirbnbListing listing3 = new AirbnbListing ("TownHouse near Rivian", "13 Mulbery St., Bloomington", "Town House", 250);
		
		//main opening
		System.out.println("Welcome to Airbnb listing");
		
		//menu loop
		while(!menuOption.equals("Q"))
		{
		//opening menu
		System.out.print(displayMenu());
		menuOption = scan.next();
		
			//if user inputs L
			if (menuOption.equals("L"))
			{
				//outputs all the listings
				System.out.print("\nCode  Listing name              City          Type          Rating \n");
				System.out.println(listing1.toString());
				System.out.println(listing2.toString());
				System.out.println(listing3.toString());
			}
			
			//if User inputs LL
			if (menuOption.equals("LL"))
			{
				//finds and outputs the listing with desired city
				System.out.print("What city? ");
				option = scan.next();
				
				if(listing1.getCityName().equals(option))
				{
					System.out.println(listing1.toString());
				}
				else if(listing2.getCityName().equals(option))
				{
					System.out.println(listing2.toString());
				}
				else if(listing3.getCityName().equals(option))
				{
					System.out.println(listing3.toString());
				}
				else
					System.out.println("Sorry no listings in that area");
			}
			
			//if user inputs LT
			if (menuOption.equals("LT"))
			{
				//finds and outputs listings with desired type of room
				System.out.println("what type of unit are you looking for?");
				System.out.println("1 - Private room");
				System.out.println("2 - Studio");
				System.out.println("3 - Town House");
				System.out.print("Enter your choice: ");
				typeOption = scan.nextInt();
				String choice = "";
				
				//validation
				if(typeOption == 1)
				{
					choice = "Private room";
				}
				else if(typeOption == 2)
				{
					choice = "Studio";
				}
				else if(typeOption == 3)
				{
					choice = "Town House";
				}
				else
				{
					System.out.print("invalid response");
				}
				
				//print
				if(listing1.getListingType().equals(choice))
				{
					System.out.println(listing1.toString());
				}
				if(listing2.getListingType().equals(choice))
				{
					System.out.println(listing2.toString());
				}
				if(listing3.getListingType().equals(choice))
				{
					System.out.println(listing3.toString());
				}
			}
			
			//if user inputs LR
			if(menuOption.equals("LR"))
			{
				//finds and outputs the listing with desired star rating
				System.out.print("What minimum rating are you looking for? ");
				rateOption = scan.nextInt();
				if (listing1.getAvgRating() >= rateOption)
				{
					System.out.println(listing1.toString());
				}
				else if (listing2.getAvgRating() >= rateOption)
				{
					System.out.println(listing2.toString());
				}
				else if (listing3.getAvgRating() >= rateOption)
				{
					System.out.println(listing3.toString());
				}
				else 
				{
					System.out.print("Sorry no listings meets your required stars");
				}
			}
			
			//if user inputs B
			if(menuOption.equals("B"))
			{
				//if user inputs listing 1 code
				System.out.print("For which listing? ");
				option = scan.next();
				if (option.equals(listing1.getListingCode()))
				{
					//makes booking for desired nights customer wants if it meets the requirements
					System.out.print("How many nights? ");
					nightOption = scan.nextInt();
					if(nightOption >= listing1.getNight())
					{
						listing1.setNight(nightOption);
						System.out.println("\nBooking Successful");
						System.out.println(listing1.bookingListing(nightOption) + "\n");
						System.out.print("Do you want a copy of your receipt (y/n)? ");
						option = scan.next();
						if (option.equals("y"))
						{
							System.out.println("\n" + listing1.displayReceipt());
						}
					}
					else 
					{
						System.out.println("Sorry, we require a minimum of " + listing1.getNight() + " night for this listing");
					}
					
				}
				
				//if user inputs listing 2 code
				else if (option.equals(listing2.getListingCode()))
				{
					//makes booking for desired nights customer wants if it meets the requirements
					System.out.print("How many nights? ");
					nightOption = scan.nextInt();
					if(nightOption >= listing2.getNight())
					{
						listing2.setNight(nightOption);
						System.out.println("\nBooking Successful");
						System.out.println(listing2.bookingListing(nightOption) + "\n");
						System.out.print("Do you want a copy of your receipt (y/n)? ");
						option = scan.next();
						if (option.equals("y"))
						{
							System.out.println("\n" + listing1.displayReceipt());
						}
					}
					else 
					{
						System.out.println("Sorry, we require a minimum of " + listing2.getNight() + " night for this listing");
					}
					
				}
				
				//if user inputs listing 3 code
				else if (option.equals(listing3.getListingCode()))
				{
					//makes booking for desired nights customer wants if it meets the requirements
					System.out.print("How many nights? ");
					nightOption = scan.nextInt();
					if(nightOption >= listing3.getNight())
					{
						listing1.setNight(nightOption);
						System.out.println("\nBooking Successful");
						System.out.println(listing3.bookingListing(nightOption) + "\n");
						System.out.print("Do you want a copy of your receipt (y/n)? ");
						option = scan.next();
						if (option.equals("y"))
						{
							System.out.println("\n" + listing3.displayReceipt());
						}
					}
					else 
					{
						System.out.println("Sorry, we require a minimum of " + listing3.getNight() + " night for this listing");
					}
					
				}
				else
				{
					System.out.println("Sorry, invalid code");
				}
				
			}
			
			//invalid prompt
			if(!menuOption.equals("L") && !menuOption.equals("LL") && !menuOption.equals("LT") && !menuOption.equals("LR") && !menuOption.equals("B") && !menuOption.equals("Q"))
			{
				System.out.println("Invalid input");
			}
		}//while loop end
		System.out.println("\nGood Bye!");
        scan.close();
	}
	
	
	//outputs the main menu
	private static String displayMenu() 
	{
		String result = "\nPlease choose one of the folling:\n";
		result += "L - list available listings\n";
		result += "LL - list available listings by location\n";
		result += "LT - list available listings by type\n";
		result += "LR - list available listings by rating\n";
		result += "B - Book a listing\n";
		result += "Q - quit\n";
		result += "\nEnter you choice: ";
		return result;
	}
}