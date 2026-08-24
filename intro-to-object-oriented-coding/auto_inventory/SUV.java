public class SUV extends Auto
{
	//global variables
	private String type;
	private boolean offroad;
	
	//constructor
	//param: vin, make, model, year, price, type, off road ability?
	public SUV(String v, String ma, String mo, int y, double p, String t, boolean off) 
	{
		super(v, ma, mo, y, p);
		type = t;
		offroad = off;
	}
	
	//gets type of vehicle
	public String getType()
	{
		return type;
	}
	
	//gets all vehicle information
	public String toString()
	{
		String result = "VIN: " + getVIN() + "\n";
			   result += "Make: " + getMake() + "\n";
			   result += "Model: " + getModel() + "\n";
			   result += "Year: " + getYear() + "\n";
			   
		if (offroad)
			   result += "Information: This vehicle is amazing offroad!";
		else
			   result += "Information: This vehicle can do everything for you.";
		
		return result;
	}
}
