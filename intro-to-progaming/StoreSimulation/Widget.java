package StoreSimulation;

import java.util.Random;

//object of what we are selling
public class Widget 
{
	private int id;
	private int price;
	private static int count = 1;
	
	//Constructor
	//Initializes all variables
	//price is a random integer: [10,20]
	//ID is a unique number
	public Widget()
	{
		Random rand = new Random();
		price = 10 + rand.nextInt(11);
		
		id = count;
		count++;
	}
	
	//gets widget price
	public int getPrice()
	{
		return price;
	}
	
	//formats widget ID and id number
	public String toString()
	{
		String result = String.format("            %-10s %s%n", "Widget ID" + id, "Price:$" + price);
		return result;
	}
}