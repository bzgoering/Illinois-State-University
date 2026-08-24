import java.util.Random;

public class Hotel 
{
//Variables
private String name;
private int[] ratings;

//constructor
public Hotel(String n)
{
	name = n;
	generateRatings();
}

//Generates random ratings for hotel
public void generateRatings()
{
	//get size [5,10]
	Random rand = new Random();
	ratings = new int[rand.nextInt(11-5) + 5];
	
	//fills ratings with [0,100]
	for (int x = 0; x < ratings.length; x++)
	{
		ratings[x] = rand.nextInt(100) + 1;
	}
}

//getter for rating array
public int[] getRatings()
{
	return ratings;
}

//getter for name
public String getName()
{
	return name;
}

//output hotel and ratings
public String toString()
{
	//for making string look nice and equal
	StringBuilder result = new StringBuilder();
	
	//output name
	result.append(String.format("%-" + 35 + "s", name));
	
	//output ratings
	for (int x = 0; x < ratings.length; x++)
	{
		result.append(String.format("%-" + 6 + "d", ratings[x]));
	}
	
	//fills rest of spots with N/A
	if(ratings.length < 10)
	{
		for (int x = ratings.length; x<10; x++)
			result.append(String.format("%-6s", "N/A"));
	}
	
	return result.toString();
}
}
