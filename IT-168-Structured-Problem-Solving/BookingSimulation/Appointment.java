package BookingSimulation;

/**
 * class that has a single variable that indicates which time slot is available or not
 * author: Ben Goering
 */
public class Appointment {
	//variables
	private boolean available;
	
	//default constructor
	//sets available to true
	public Appointment()
	{
		available = true;
	}
	
	//gets value of available
	public boolean getAvailable()
	{
		return available;
	}
	
	//sets value of available
	public void setAvailable(boolean x)
	{
		available = x;
	}
	
	//returns string if available
	@Override
	public String toString()
	{
		String result;
		if (available == true)
		{
			result = "True";
		}
		else
		{
			result = "False";
		}
		return result;
	}
	
}