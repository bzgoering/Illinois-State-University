import java.util.*;
/**
 * Main class to Patient, Covid19Patient and RegularPatient classes
 */
public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//variables
		String input = "";
		Scanner scan = new Scanner(System.in);
		ArrayList<Patient> patientList = new ArrayList<>();
		
		//loops until user enter 6 to exit
		while (!input.equals("6"))
		{
			//variables
			int i;
			String f;
			String l;
			int a;
			double t;
			String m;
			
			//main menu prompt
			System.out.print( "1. Admit a patient \n"
							+ "2. Print patient information \n"
							+ "3. Submit a PCR test result \n"
							+ "4. Do rounds \n"
							+ "5. Discharge patient \n"
							+ "6. Exit \n"
							+ "Enter option here: ");
			input = scan.nextLine();
			
			switch (input)
			{
			//adds patient
			//if pcr is positive then, covid patient
			//if pcr is negative then, regular patient
			case"1":
				
				//ask what type of patient
				System.out.print("Enter your PCR result (pos/neg): ");
				input = scan.nextLine();
				
				//makes covid patient
				if (input.equals("pos"))
				{
					//asks for covid patient info
					System.out.print("Enter patient's ID: ");
					i = scan.nextInt();
					scan.nextLine();
					
					System.out.print("Enter patient's First Name: ");
					f = scan.nextLine();
					
					System.out.print("Enter patient's Last Name: ");
					l = scan.nextLine();
					
					System.out.print("Enter patient's age: ");
					a = scan.nextInt();
					scan.nextLine();
					
					System.out.print("Enter patient's Temperature: ");
					t = scan.nextDouble();
					scan.nextLine();
					
					//makes and add covid patient to the list
					Covid19Patient patient = new Covid19Patient(i, f, l, a, t);
					patient.setPcr(true);
					patientList.add(patient);
				}
				
				//makes regular patient
				else if (input.equals("neg"))
				{
					//asks for regular patient info
					System.out.print("Enter patient's ID: ");
					i = scan.nextInt();
					scan.nextLine();
					
					System.out.print("Enter patient's First Name: ");
					f = scan.nextLine();
					
					System.out.print("Enter patient's Last Name: ");
					l = scan.nextLine();
					
					System.out.print("Enter patient's age: ");
					a = scan.nextInt();
					scan.nextLine();
					
					System.out.print("Enter patient's main symptom : ");
					m = scan.nextLine();
					
					//adds ad creates regular patient to list
					RegularPatient patient = new RegularPatient(i, f, l, a, m);
					patient.setPcr(false);
					patientList.add(patient);
				}
				
				//if user enter anything besides pos or neg
				else
				{
					System.out.println("Input invalid, try again");
				}
				break;
				
			//displays all patients listed
			case"2":
				System.out.print("\nPrinting all patients: ");
				
				for (Patient p: patientList)
				{
					System.out.print(p.toString());
				}
				break;
			
			//Changes patient's PCR test results and discharge is applicable
			case"3":
				//variables
				int index = -1;
				
				//gets patient id
				System.out.print("Please enter patient ID: ");
				i = scan.nextInt();
				scan.nextLine();
								
				//gets patient index
				for (int x = 0; x < patientList.size(); x++)
				{
					if (patientList.get(x).getId() == i)
					{
						index = x;
					}
				}
				
				//stops if index is not found
				if (index == -1)
				{
					System.out.println("Patient not found\n");
					break;
				}
				
				//gets new pcr result
				System.out.print("Enter new PCR test result(pos/neg): ");
				input = scan.nextLine();
				
				//implement changes if regular patient with positive PCR result
				if (input.equals("pos") && patientList.get(index).isPcr() == false)
				{
					//gets patient info
					i = patientList.get(index).getId();
					a = patientList.get(index).getAge();
					f = patientList.get(index).getfName();
					l = patientList.get(index).getlName();
					
					//gets temp of new covid patient
					System.out.print("What is the patient's temperature: ");
					t = scan.nextDouble();
					scan.nextLine();
					
					//turns regular to covid
					patientList.remove(index);
					Covid19Patient patient = new Covid19Patient(i, f, l, a, t);
					patientList.add(patient);
					System.out.println("Patient is now a covid patient");

				}
				
				//gets rid of covid patient with negative pcr result
				else if (input.equals("neg")&& patientList.get(index).isPcr() == true)
				{
					//discharges covid patient
					patientList.remove(index);
					System.out.println("Patient has been discharged");
				}
				break;
			
			//searches for covid patients and updates their temperature from user
			//then prints every patients id with their treatment
			case"4":
				//searches arraylist for covid patients
				for (int x = 0; x < patientList.size(); x++)
				{
					if (patientList.get(x).isPcr() == true)
					{
						//gets new temperatures
						System.out.print("Enter temperature for Patient ID num " + patientList.get(x).getId() + ": ");
						t = scan.nextDouble();
						scan.nextLine();
						
						//changes covid 19 patients temperatures
						Covid19Patient temp = (Covid19Patient) patientList.get(x);
						temp.setTemperature(t);
						patientList.remove(x);
						patientList.add(x,temp);
					}
				}
				
				//treat and prints patients and their treatment
				for (Patient p: patientList)
				{
					p.treat();
					System.out.println("ID "+ p.getId() + " treatment:" + p.treat());
				}
				break;
			
			//discharges patient from user input except if they are acovid patient
			case"5":
				//variables
				index = -1;
				
				//gets patient to discharge
				System.out.print("Enter patient ID that you want to discharge: ");
				i = scan.nextInt();
				scan.nextLine();
				
				//gets patient index
				for (int x = 0; x < patientList.size(); x++)
				{
					if (patientList.get(x).getId() == i)
					{
						index = x;
					}
				}
				
				//stops if index is not found
				if (index == -1)
				{
					System.out.println("Patient not found\n");
					break;
				}
				
				//makes sure patient can be discharged
				if (patientList.get(index).isPcr() == false)
				{
					System.out.println("Patient is discharged");
					patientList.remove(index);
				}
				else
				{
					System.out.println("Patient still has positive PCR result and can't be discharged at this time");
				}
				break;
				
			//exit program
			case "6":
				System.out.println("Goody-Bye");
				scan.close();
				break;
				
			default:
				System.out.println("Ivalid input, please try again\n");
			}
			System.out.println();
		}
	}

}
