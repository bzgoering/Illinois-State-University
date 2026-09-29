package StoreSimulation;

import java.time.*;
import java.util.*;

public class StoreSimulationDriver 
{
	//global variables subject to change through simulation
	private static List<Widget> batch = new LinkedList<>(); //list of widgets in one batch
	private static Deque<Widget> Inventory = new ArrayDeque<>(); //store inventory of widgets
	private static Deque<Order> orderQueue = new ArrayDeque<>(); //queue of older orders we couldn't complete at the current time
	private static List<Widget> customerOrder = new LinkedList<>(); //temp list of widgets that the customer ordered
	private static int count = 1; //keeps track of day and simulation runs
	
	//customizable variables to edit simulation
	private final static int batchSize = 5; //amount of widget in one batch
	private final static int daysToSimulate = 100; //how many days to simulate
	private final static int numOfBatches = 2; //how many batches arrive per day
	
	public static void main(String[] args) 
	{
		//welcome intro
		System.out.println("                       Welcome the the Widget Factory Store Simulation");
		
		//main simulation
		simulation();
	}
	
	//simulation
	private static void simulation()
	{
		//initial setup
		newDay();
			
		//queue isn't full
		if (orderQueue.size() < 3)
		{	
			//gets next order
			Order newOrder = new Order();
			
			//prints new order
			System.out.println("New order: \n" + newOrder.getOrder());
			
			//check to see if we can process
			if (newOrder.getWidget() <= Inventory.size())
			{
				System.out.println("Processing the New order...");
				//fulfill order
				process(newOrder);
			}
			else
			{
				//not enough widgets
				System.out.println("Not Enough widgets for the order, saving it for future deliveries.\n");
				//adds to queue if we can't fulfill
				orderQueue.offer(newOrder);
			}
		}
		//when the queue is full
		if (orderQueue.size() == 3)
		{
			//prints msg if full with next order
			System.out.println("Stop taking new Orders, waiting to process the order:\n" + orderQueue.peek().getOrder());
			processAllOlderOrders();
		}
		//continues with regular simulation day
		else
		{
			//process old order if possible
			processOlderOrder();
		}
		
		//exit simulation loop when needed
		if (count == daysToSimulate)
		{
			return;
		}
		//double check count
		else if (count < daysToSimulate)
		{
			//loops simulation
			simulation();
		}
		//run time error msg
		else if (count > daysToSimulate)
		{
			System.out.println("\nError, simulated too many days (" + (count - daysToSimulate) + ")");
			return;
		}
	}
	
	//prints the current simulation date
	//prints date
	private static void newDay()
	{
		//initial day
		LocalDate day = LocalDate.of(2000, 1, 1);
		//configure to correct date by number of simulations loops
		day = day.plusDays(count-1);
		//output
		System.out.println("Date: " + day);
		//adds a day/simulation
		count++;
		
		addBatch(1); //adds 2 batches of 5 widgets to inventory
		System.out.println("Widgets in stock: " + Inventory.size() +"\n"); //display inventory
	}
	
	//adds batch to inventory
	//adds batch to inventory
	private static void addBatch(int loop)
	{
		//adds widgets to batch
		batch();
		
		//adds batch to widget
		while (!batch.isEmpty())
		{
			Inventory.push(batch.remove(0));
		}
		
		if (loop != numOfBatches)
		{
			addBatch(loop+1);
		}
	}
	
	//process orders if we can
	//process New Order
	private static void process(Order order)
	{
		//fulfill order if available
		System.out.println("Delivering the following widgets:");
		//takes widget from inventory to a new list
		getCustomerWidget(order.getWidget());
		//prints the widgets going into customer order
		printList(customerOrder);
		//adds widgets to customer order
		order.fulfillOrder(customerOrder);
		//output receipt and inventory size
		System.out.println(order.toString() + "Widgets in stock: " + Inventory.size() + "\n");
		return;
	}
	
	//process older orders until we can't
	//process older orders until we can't
	private static void processOlderOrder()
	{
		//try's to process older orders
		if (!orderQueue.isEmpty())
		{
			//if we have enough widgets
			if (orderQueue.peek().getWidget() <= Inventory.size())
			{
				//output older order information
				System.out.println("Processing an old order:");
				System.out.println(orderQueue.peek().getOrder());
				
				//process order
				process(orderQueue.poll());
				//try's to process next older order
				processOlderOrder();
			}
		}
		
		return;
	}

	//processes all queue orders
	private static void processAllOlderOrders()
	{
		//check to make sure simulation is within the limit
		if (count == daysToSimulate)
		{
			return;
		}
		
		//new simulated day
		newDay();
		
		//process old order
		processOlderOrder();
		
		//main clause to loop if there's more
		if (!orderQueue.isEmpty())
		{
			//new day if we can't process the next old order
			if (orderQueue.peek().getWidget() > Inventory.size())
			{
				System.out.println("Next order to process:\n" + orderQueue.peek().getOrder());
			}
			
			//loops
			processAllOlderOrders();
		}
		
		//exit when queue is empty
		return;
	}
	
	//helper method that adds widget to batch
//Helper method
	//makes batch of 5 widgets
	private static void batch()
	{
		//adds widgets based off how big a batch is
		for (int x = 0; x < batchSize; x++)
		{
			batch.add(new Widget());
		}
		}
	
	//helper method that removes widget from inventory and adds to the temp list
	//removes widgets from inventory and adds to temp list
	private static void getCustomerWidget(int count)
	{
		for (int x = 0; x < count; x++)
		{
			customerOrder.add(Inventory.pop());
		}
	}
	
	//prints all widgets from a list
	//prints contents of inventory
	private static void printList(List<Widget> list)
	{
		Iterator<Widget> iterate = list.iterator();
			
		//prints widgets in a list
		while(iterate.hasNext())
		{
			System.out.print(iterate.next());
		}
	}
		
}