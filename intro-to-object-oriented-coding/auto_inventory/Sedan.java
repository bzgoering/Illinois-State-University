public class Sedan extends Auto
{
	//global Variables
	String type;
	int capacity;
	
	//constructor
	//param: vin, make, model, year, price, type, capacity
	public Sedan(String v, String ma, String mo, int y, double p, String t, int cap) 
	{
		super(v, ma, mo, y, p);
		type = t;
		capacity = cap;
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
			   result += "Information: This vehicle can hold up to " + capacity + " people!";
		return result;
	}
	
}
