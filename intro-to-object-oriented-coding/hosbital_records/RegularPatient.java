/**
 * A patient that has a negative PCR test result
 */
public class RegularPatient extends Patient
{
	//variables
	private String mainSymptom;
	
	//constructor with param
	//param: int, string, string, int, string
	public RegularPatient(int i, String f, String l, int a, String m) 
	{
		super(i, f, l, a);
		mainSymptom = m;
	}

	//@return the mainSymptom
	public String getMainSymptom() 
	{
		return mainSymptom;
	}
	

	//@param mainSymptom the mainSymptom to set
	public void setMainSymptom(String mainSymptom) 
	{
		this.mainSymptom = mainSymptom;
	}


	@Override
	public String treat() 
	{
		String result = "";
		
		//gives treatments based on patients Symptom
		if (mainSymptom.equals("coughing") || 
			mainSymptom.equals("runny nose") || 
			mainSymptom.equals("stuffy nose"))
		{
			result = "Amoxicillin";
		}
		else if (mainSymptom.equals("hypertension"))
		{
			result = "ACE inhibitors";
		}
		else
		{
			result = "IV fluids";
		}
		
		return result;
	}
	
	@Override
	public String toString()
	{
		//prints all regular patient information
		String result = "\nID: " + getId() + "\n" +
				"Full Name: " + getfName() + " " + getlName() + "\n" +
				"Age: " + getAge() + "\n" +
				"Main Symptom: " + mainSymptom + "\n" +
				"PCR test result: Negative \n" +
				"Treatment: " + treat() + "\n";
		
		return result;
	}
	
	
}
