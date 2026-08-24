/**
 * Codivd 19 patients are patients with a positive PCR test result
 */
public class CovidPatient extends Patient 
{
	//variables
	private double temperature;
	
	//constructor for covid 19 patient
	//param: int, string, string, int, double
	public Covid19Patient(int i, String f, String l, int a, double t) 
	{
		super(i, f, l, a);
		temperature = t;
	}
	
	
	//@return the temperature
	public double getTemperature() {
		return temperature;
	}
	
	//@param temperature the temperature to set
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String treat() 
	{
		//gives treatments based of patient personal info such as age and temperature
		String result = "";
		
		if (temperature > 39.75 && getAge() > 70)
		{
			result = "Paxlovid";
		}
		else
		{
			result = "Fluids, Acetaminophen";
		}
		
		if (temperature > 41)
		{
			result = "Dexamethasone";
		}
		return result;
	}
	
	@Override
	public String toString()
	{
		//prints Covid 19 patient info
		String result = "\nID: " + getId() + "\n" +
						"Full Name: " + getfName() + " " + getlName() + "\n" +
						"Age: " + getAge() + "\n" +
						"Temperature: " + temperature + "\n" +
						"PCR test result: Positive \n" +
						"Treatment: " + treat() + "\n";
		
		return result;
	}
	
}
