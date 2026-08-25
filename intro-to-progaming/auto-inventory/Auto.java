public abstract class Auto implements AutoInterface
{
	//global variables
	private String VIN;
	private String make;
	private String model;
	private int year;
	private double originalPrice;
	
	//main constructor
	//param: String vin, String make, String model, int year, double OG price
	public Auto(String v, String ma, String mo, int y, double p)
	{
		VIN = v;
		make = ma;
		model = mo;
		year = y;
		originalPrice = p;
	}
	
	//Calculates the MSRP of the vehicle
	//returns the new price as a double
	public double calcMSRP()
	{
		//variables
		double MSRP;
		final int currYear = 2024;
		
		//calculates percent off
		double off = (currYear - year) * 0.05;
		
		//calculates new price
		MSRP = originalPrice - (originalPrice * off);
		
		return MSRP;
	}
	
	//abstract method that gets type of vehicle
	public abstract String getType();

	//returns global variables
	public String getVIN() {
		return VIN;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}
	
	
}
