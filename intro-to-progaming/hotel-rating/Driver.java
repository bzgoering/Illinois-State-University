import java.util.ArrayList;

public class Driver {
	static ArrayList<Hotel> hotels;
	
	public static void main(String[] args)
	{
		//application start
		generateData();
		System.out.println("Welcome to hotel Ratings\n");
		displayAllRating();
		System.out.println();
		findTheBestHotels();
		System.out.println();
		findTheWorstHotels();
	}
	
	//creates a list of hotels
	public static void generateData()
	{
		hotels = new ArrayList<>();
		
		hotels.add(new Hotel("Ritz Carlton, Chicago"));
		hotels.add(new Hotel("Four Seasons, Chicago"));
		hotels.add(new Hotel("Beijing Hotel"));
		hotels.add(new Hotel("JW Marriot, Beijing"));
		hotels.add(new Hotel("Mandarin Oriental, Barelona"));
		hotels.add(new Hotel("The Peninsula, Chicago"));
		hotels.add(new Hotel("Shangri La, Beijing"));
		hotels.add(new Hotel("Waldorf Astoria, Chicago"));
	}
	
	//creates main display
	public static void gernerateBanner()
	{
		System.out.println("Hotel                              2022  2021  2020  2019  2018  2017  2016  2015  2014  2013");
		System.out.println("_____________________________________________________________________________________________");
	}
	
	//prints out every hotel listing
	public static void displayAllRating()
	{
		//banner
		System.out.println("All Ratings:");
		gernerateBanner();
		
		for (int x = 0; x < hotels.size(); x++)
		{
			System.out.println(hotels.get(x).toString());
		}
	}
	
	//only prints hotels with a single rating of 95 or above
	public static void findTheBestHotels()
	{
		//variables
		boolean best = false;
		String result = "";
		
		//traverse through hotel list
		for (int x = 0; x < hotels.size(); x++)
		{
			//Traverse through each hotel rating
			for(int y = 0; y < hotels.get(x).getRatings().length; y++)
			{
				//output any individual rating 95 and above
				if (hotels.get(x).getRatings()[y] >= 95)
				{
					result += (hotels.get(x).toString()) + "\n";
					best = true;
				}
			}
		}

		//outputs depending if it found best hotel or not
		System.out.println("Best Ratings:");

		if (best)
		{
			//banner
			gernerateBanner();
			System.out.print(result);
		}
		else
		{
			System.out.println("Sorry, there is no hotel with a rating of 95 or higher");
		}
		
	}

	//finds and print worst hotel
	public static void findTheWorstHotels()
	{
		//variables
		int worstRate = 101;
		int tempCount = 0;
		int finalCount = 0;
		int hotelCount = 0;
		String result = "";
		boolean found = false;
		
		//Overall Gets the worst Rating
		//traverse through hotel list
		for (int x = 0; x < hotels.size(); x++)
		{
			//Traverse through each hotel rating
			for(int y = 0; y < hotels.get(x).getRatings().length; y++)
			{
				//finds lowest rate
				if (hotels.get(x).getRatings()[y] < worstRate)
				{
					//keeps track of worst rate and hotel list position
					worstRate = hotels.get(x).getRatings()[y];
				}
			}
		}
	
		//overall gets hotel with worst hotel rating and counts them and finds the hotel with most worst rating
		//traverse through hotel list
		for (int x = 0; x < hotels.size(); x++)
		{
			//reset variables
			found = false;
			tempCount = 0;
			
			//Traverse through each hotel rating
			for(int y = 0; y < hotels.get(x).getRatings().length; y++)
			{
				//counts how many worst rate each hotel has
				if (hotels.get(x).getRatings()[y] == worstRate)
				{
					tempCount++;
					found = true;
				}
			}
			
			//if hotel has worst rate then it'll either add the hotel to the output if it has an equal amount of worst rates or reset output with new most worst rate
			//ex: if 1 hotel has 2 worst rates then itll get rid of the hotels with only 1 worst rate
			if (found)
			{
				if (finalCount == tempCount)
				{
					result += (hotels.get(x).toString()) + "\n";
					hotelCount++;
				}
				if (finalCount < tempCount)
				{
					finalCount = tempCount;
					result = (hotels.get(x).toString()) + "\n";
					hotelCount = 1;
				}
			}
		}
		
		//output
		System.out.println(hotelCount + " hotel(s) has recieved the worst rating of " + worstRate + ", " + finalCount + " time(s)");
		gernerateBanner();
		System.out.print(result);
	}
}
