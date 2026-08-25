package bookingSimulation;

/**
 * primary helper class that makes the appointments in period
 * author: Ben Goering
 */
public class AppointmentBook {
	private Period[] periods = new Period[6];
	
	//Default constructor
	//sets period array to 8
	public AppointmentBook()
	{
		periods = new Period[8];
		for (int x = 0; x < periods.length; x++)
		{
			periods[x] = new Period();
		}
	}
	
	//sets period
	//params period array
	public void setPeriods(Period[] newArray)
	{
		int len = periods.length-1;
		
		for (int x = 0; x <= len; x++)
		{
			periods[x] = newArray[x];
		}
	}
	
	//returns period
	public Period[] getPeriod()
	{
		return periods;
	}
	
	//prints each period with the minutes inside showing which minute is free and not free
	//returns a string
	public String toString()
	{
		String result = "";
		int count = 1;
		for (int x = 0; x < periods.length; x++)
		{
			result += "Period: " + count + "\n";
			result += periods[x].toString();
			count ++;
		}
		return result;
	}
	
	//checks to make sure a certain minute in period is availible or not
	//param int period for which period to use and what minute
	//returns a boolean, true if it is free, false if not
	public boolean isMinuteFree (int period, int minute)
	{
		boolean result = false;
		
		Appointment[] app =  periods[period].getAppointmentList();
		if (app[minute].getAvailable() == true)
		{
			result = true;
		}
		return result;
	}
	
	//takes in the param and recerves a block of time useing the input
	//params int period of which array, int startmin of what minute to start at, int duration
	private void reserveBlock(int period, int startMin, int duration)
	{
		int end = startMin + duration;
		
		for (int x = startMin; x <= end; x++)
		{
			periods[period].getAppointmentList()[x].setAvailable(false);
		}
	}
	
	//looks through period and finds an open spot that is as long as the user input
	//return int
	//params period index of int and int durration of minutes
	private int findFreeBlock(int period, int duration)
	{
	        for (int x = 0; x <= 60 - duration; x++) 
	        {
	            boolean isBlockFree = true;
	            for (int y = x; y < x + duration; y++) 
	            {
	                if (!isMinuteFree(period, y)) 
	                {
	                    isBlockFree = false;
	                    break;
	                }
	            }
	            if (isBlockFree == true) 
	            {
	                return x;
	            }
	        }
	        return -1;
	    }
	
	//calls other methods to help make an appointment in period
	//return a boolean
	//params int of period index from array and a int for duration in minutes of appointment
	public boolean makeAppointment(int period, int duration)
	{
		int startMin = findFreeBlock(period, duration);
        if (startMin == -1) 
        {
        	return false;
        }
        reserveBlock(period, startMin, duration);
        return true;
	}
}