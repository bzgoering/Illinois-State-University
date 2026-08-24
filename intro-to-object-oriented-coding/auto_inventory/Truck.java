public class Truck extends Auto
{
	private String type;
	private int towingCapacity;
	
	//constructor
	//param: vin, make, model, year, price, type, towing capacity
	public Truck(String v, String ma, String mo, int y, double p, String t, int towCap) 
	{
		super(v, ma, mo, y, p);
		type = t;
		towingCapacity = towCap;
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
			   result += "Information: This vehicle tow up to " + towingCapacity + " pounds!";
		return result;
	}
}
