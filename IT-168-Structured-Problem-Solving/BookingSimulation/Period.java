package BookingSimulation;

/**
 * an aggregate class that contains 8 different periods with their own appointment object inside
 * author: Ben Goering
 */
public class Period {
	//variables
	private Appointment[] appointment;
	
	//Default constructor
	//maKes appointment array with 60 spots
	public Period()
	{
		appointment = new Appointment[60];
		for (int x = 0; x < appointment.length; x++)
		{
			appointment[x] = new Appointment();
		}
	}
	
	
	//returns appointment array
	public Appointment[] getAppointmentList()
	{
		return appointment;
	}
	
	//toString
	@Override
	public String toString()
	{
		
		String result = "";
		
		for (int x = 0; x < appointment.length; x++)
		{
			result += "Minute: " + x + ": Available: " + appointment[x].getAvailable() + "\n";
		}
		result += "\n";
		return result;
	}
	
}
