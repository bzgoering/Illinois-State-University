package StoreSimulation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order 
{
	private int widgets;
	private int orderNumber;
	private List<Widget> theOrder;
	private static int count = 1;
	private double totalPrice = 0;
	
	//constructor
	//Initializes all variables
	//widget amount is a random integer [1,30] and order number is a unique number
	public Order()
	{
		Random rand = new Random();
		widgets = 1+ rand.nextInt(30);
		orderNumber = count;
		count++;
		theOrder = new ArrayList<>();
	}
	
	//return number of required widgets
	public int getWidget()
	{
		return widgets;
	}
	
	//adds list of widgets to order list
	public void fulfillOrder(List<Widget> theOrder)
	{
		for (int x = 0; x < widgets; x++)
		{
			Widget current = theOrder.removeFirst();
			totalPrice += current.getPrice();
			this.theOrder.add(current);
		}
	}
	
	//prints order number and widgets needed
	public String getOrder()
	{
		String result = String.format("            %-10s %s%n", "Order number: " + orderNumber, "    Widgets ordered: " + widgets);
		return result;
	}
	
	//prints order number with total
	public String toString()
	{
		DecimalFormat deci = new DecimalFormat("$###,###.00");
		double tax = .08 * totalPrice;
		totalPrice += tax;
		
		String result = String.format("            %-10s %s%n", "Order number: " + orderNumber, "    Order total: " + deci.format(totalPrice));
		return result;
	}
}
