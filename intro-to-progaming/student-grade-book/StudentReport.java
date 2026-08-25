import java.io.*;
import java.text.*;
import java.util.*;
/**
 * Author: Ben Goering
 * StudentReport can read and write to file about a students grade in a certain project.
 */
public class StudentReport {
	private String fileName;
	private double[][] grades;
	private String[] students;
	private String[] evaluatedItems;
	int gradeRowCount;
	int gradeColumnCount;
	int itemCount;
	int studentCount;
	
	//Constructor that instance variables
	//@param: String of file name
	public StudentReport (String file)
	{
		gradeRowCount = 0;
		gradeColumnCount = 0;
		itemCount = 0;
		studentCount = 0;
		fileName = file;
		grades = new double[1000][1000];
		students = new String [1000];
		evaluatedItems = new String [1000];
	}
	
	//Read file method will read the file in a student report object and add to the variables
	public void readFile()
	 {
		 
		 try 
		 {
			//Scanner set-up
			Scanner fileScan = new Scanner (new File (fileName));
			String line = fileScan.nextLine();
			Scanner scan = new Scanner(line);
			scan.useDelimiter(",");
			scan.next();
				
			//scans items
			while (scan.hasNext()) 
			{
				evaluatedItems[itemCount] = scan.next(); 
				itemCount++;
			}
			
			while (fileScan.hasNext())
			{
				//resets variables
				fileScan.useDelimiter(",");
				line = fileScan.nextLine();
				scan = new Scanner(line);
				scan.useDelimiter(",");
				gradeColumnCount = 0;
				
				//scans name
				students[studentCount] = scan.next();
				studentCount++;
			
				while(scan.hasNextDouble())
				{
					//scans for grades
					grades[gradeRowCount][gradeColumnCount] = scan.nextDouble();
					gradeColumnCount++;
				}

				//System.out.print("out");
				gradeRowCount++;

			}
			System.out.println("File reading complete\n");
			fileScan.close();
			scan.close();
		 }
		 catch(FileNotFoundException e)
		 {
			 System.out.println("Error, no file found");
		 }
		 catch(InputMismatchException e)
		 {
			 System.out.println("Error, Invalid input from file");
		 }
	 }

	//Generates a report card for the selected students and save to .txt file
	public void writeFile(String studentName)
	{
		//finds index of student's name
		int rowIndex = -1;
		int total = 0;
		String[] letter = new String[1000];
		
		for (int x = 0; x < studentCount; x++)
		{
			if (students[x].equals(studentName))
			{
				rowIndex = x;
			}
		}
		
		if (rowIndex != -1)
		{
			//begins making writer if name is found
			try
			{
				PrintWriter outfile = new PrintWriter(new FileWriter(studentName + ".txt",false));

				//prints name
				outfile.println(students[rowIndex]);
				
				//prints item names and grades except exams
				for (int x = 0; x < itemCount-3; x++)
				{
					outfile.print(evaluatedItems[x] + ": ");
					outfile.println((int)grades[rowIndex][x]);
				}
				
				//prints item names and grades for exams
				for (int x = itemCount-3; x < itemCount-1; x++)
				{
					outfile.print(evaluatedItems[x] + ": ");
					outfile.println((int)grades[rowIndex][x]);
				}
				
				//prints last test
				outfile.print(evaluatedItems[itemCount-1] + ": ");
				outfile.println((int)grades[rowIndex][itemCount-1]);
				
				//gets total
				total = getTotal(rowIndex);
				outfile.println("Total Grade: " + total);
				
				//gets letter grade
				letter = getTotalLetterGrade();
				outfile.println("Letter Grade:  " + letter[rowIndex]);
				outfile.close();
			}

			catch (IOException e) 
			{
				System.out.println("Error while writing file");
			}
			System.out.println("File has been written");
		}
		else
		{
			System.out.println("Sorry an error has occurred, please try again");
		}
	}
	
	//converts the original grade to a percent
	public void convertGrades()
	{
		//converts grade for HW and quizzes
		for (int row = 0; row < gradeRowCount; row++)
		{
			for (int column = 0; column < gradeColumnCount-3; column++)
			{
				grades[row][column] = (grades[row][column] / 10) * 100;
			}
		}
		
		//converts grade for exams
		for (int row = 0; row < gradeRowCount; row++)
		{
			for (int column = gradeColumnCount-3; column < gradeColumnCount-1; column++)
			{
				grades[row][column] = (grades[row][column] / 15) * 100;
			}
		}
		
		//converts final exam
		for (int row = 0; row < gradeRowCount; row++)
		{
				grades[row][gradeColumnCount-1] = (grades[row][gradeColumnCount-1] / 20) * 100;
		}
	}
	
	//gets student total grade
	private int getTotal(int index)
	{
		int total = 0;
		
		for (int column = 0; column < gradeColumnCount; column++)
		{
			total += grades[index][column];
		}
		
		total = (total / itemCount);
		
		return total;
	}
	
	public String getLetterGrade()
	{
		String result = "";
		double total = 0;
		double aCount = 0;
		int dCount = 0;
		int fCount = 0;
		
		for (int column = 0; column < itemCount; column++)
		{
			//starts message
			result += evaluatedItems[column] + " ";
			
			//resets values
			aCount = 0;
			dCount = 0;
			fCount = 0;
			
			for(int row = 0; row < gradeRowCount; row++)
			{
				//gets all grades for each item
				total = 0;
				total = grades[row][column];
				
				if (total >= 90)
				{
					aCount++;
				}
				else if (total >= 60)
				{
					dCount++;
				}
				else if (total < 69)
				{
					fCount++;
				}
			}
			
			//math
			aCount = (aCount/studentCount);

			//output
			DecimalFormat deci = new DecimalFormat(".#%");
			
			result += deci.format(aCount) + " A's " + 
					dCount + " D's " +
					fCount + " F's " +
					  "\n";
		}
		
		return result;
	}
	
	//gets letter grade of all students
	//returns String
	private String[] getTotalLetterGrade()
	{
		String[] letterGrade = new String [1000];
		int total = 0;
		
		for (int row = 0; row < gradeRowCount; row++)
		{
			total = getTotal(row);
			
			if (total >= 90)
			{
				letterGrade[row] = "A";
			}
			else if (total >= 80)
			{
				letterGrade[row] = "B";
			}
			else if (total >= 70)
			{
				letterGrade[row] = "C";
			}
			else if (total >= 60)
			{
				letterGrade[row] = "D";
			}
			else if (total < 69)
			{
				letterGrade[row] = "F";
			}
		}
		
		return letterGrade;
	}
	
	//takes total grades of all students and gets avg
	//returns string of percentages of kids who got a letter grade
	public String getClassAvg()
	{
		//variables
		String result = "";
		String[] temp = getTotalLetterGrade();
		double aCount = 0;
		double bCount = 0;
		double cCount = 0;
		double dCount = 0;
		double fCount = 0;
		
		//counts how many kids got what letter grade
		for (int x = 0; x < gradeRowCount; x++)
		{
			if (temp[x].equals("A"))
			{
				aCount++;
			}
			else if (temp[x].equals("B"))
			{
				bCount++;
			}
			else if (temp[x].equals("C"))
			{
				cCount++;
			}
			else if (temp[x].equals("D"))
			{
				dCount++;
			}
			else if (temp[x].equals("F"))
			{
				fCount++;
			}
		}
		
		
		//gets percentage
		aCount = aCount / gradeRowCount;
		bCount = bCount / gradeRowCount;
		cCount = cCount / gradeRowCount;
		dCount = dCount / gradeRowCount;
		fCount = fCount / gradeRowCount;
		
		//formats the String
		DecimalFormat deci = new DecimalFormat("0.#%");
		result =  deci.format(aCount) + " of students got an A as an overall grade\n";
		result += deci.format(bCount) + " of students got a B as an overall grade\n";
		result += deci.format(cCount) + " of students got a C as an overall grade\n";
		result += deci.format(dCount) + " of students got a D as an overall grade\n";
		result += deci.format(fCount) + " of students got a F as an overall grade\n";
		
		return result;
	}
	
	//Will format student's names from array
	//returns names of students
	public String toString()
	{
		String result = "";
		
		for (int x = 0; x < studentCount; x++)
		{
			result += (x+1) + ". " + students[x] + "\n";
		}
		
		return result;
	}

	/**
	 * @return the gradeRowCount
	 */
	public int getGradeRowCount() {
		return gradeRowCount;
	}

	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * @return the studentCount
	 */
	public int getStudentCount() {
		return studentCount;
	}
	
	
}
