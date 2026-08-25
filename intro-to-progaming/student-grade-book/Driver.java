import java.util.*;

/**
 * Author: Ben Goeirng
 * Main class to the StudentReport class
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//variables
		Scanner scan = new Scanner (System.in);
		StudentReport student1 = new StudentReport(null);
		String input;
		int loopOut = 0;
		
		//Main menu
		while (loopOut != -1)
		{
			System.out.print("Welcome to student report maker,"
			+ "								   		  \n1 - Read a file "
			+ "										  \n2 - Print students names "
			+ "										  \n3 - Generate a report card for a specific student"
			+ "										  \n4 - Display stats of class "
			+ "										  \n5 - Exit\n\n");
			
			System.out.print("Please input appropiate number: ");
			input = scan.nextLine();
		
			switch(input)
			{
			//reads file
			case "1":
				//Prompt user for file name
				System.out.print("Enter file name to process: ");
				input = scan.nextLine();
			
				//makes student report using input and read's file
				student1 = new StudentReport(input);
				student1.readFile();
				
				//turns grades to a percent
				student1.convertGrades();
				break;
			
			//prints student names
			case "2":
				System.out.println(student1.toString());
				
				break;
			
				//writes to student's file about their grades
			case "3":
				System.out.println("Reminder, the txt file this writes to is the input student's name");
				System.out.print("Enter student's name(without .txt): ");
				student1.writeFile(scan.nextLine());
				
				break;
				
			//prints stats about the class
			case "4":
				System.out.println("Grades break down by assignment");
				System.out.println(student1.getLetterGrade());
				System.out.println("End of semester report");
				System.out.println(student1.getClassAvg());
				break;
				
			//breaks loop
			case "5":
				loopOut = -1;
				System.out.println("Good-Bye!");
				scan.close();
				break;
			}
		}
	}

}
