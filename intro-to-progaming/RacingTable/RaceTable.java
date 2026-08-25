package RacingTable;

import java.util.*;
import java.text.DecimalFormat;
public class RaceTable {
/**
* * This is will gather information of three drivers from the Indy 500
* * and display their names, net pay, base winnings, bonus pay,amount of laps,
* * and miles covered and all three of their totals at the end.
**/
	public static void main(String[] args) {
		//variables
		DecimalFormat money = new DecimalFormat("$###,###.##");
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		final double LAP_DISTANCE = 2.5;
		final int TOTAL_LAPS = 200;
		final double EARNING_PER_LAP = 21.77;
		final double ENTRY_FEE = 300.00;
		String first_Name;
		String last_Name;
		int racer_Num;
		int num_Of_Laps;
		Double winnings = 0.0;
		String car;
		double bonus = 0;
		double miles = 0;
		double net;
		String driver1;
		String driver2;
		String driver3;
		double total_Miles;
		double total_Base_Winnings;
		double total_Bonus;
		double total_Net;
		
//1st Racer information
		
		//gets driver information
		System.out.println("-----Driver 1 information-----");
		System.out.print("Enter first name: ");
		first_Name = scan.nextLine();
		System.out.print("Enter last name: ");
		last_Name = scan.nextLine();
		first_Name.toLowerCase();
		last_Name.toLowerCase();
		
		//Laps?
		System.out.print("Enter number of laps completed: ");
		num_Of_Laps = scan.nextInt();
		
		//type of car?
		System.out.print("Enter make of vehicle: ");
		car = scan.next();
		car.toLowerCase();
		
		//assigns racer number
		racer_Num = rand.nextInt(5000)+1;

		//pay math
		winnings += num_Of_Laps * EARNING_PER_LAP;
		if (num_Of_Laps >= 50 && num_Of_Laps <= 100)
		{
			winnings *= 1.33;
		}
		if (num_Of_Laps >= 101 && num_Of_Laps <= 150)
		{
			winnings *= 1.66;
		}
		if (num_Of_Laps >= 151 && num_Of_Laps <= TOTAL_LAPS)
		{
			winnings *= 2.00;
		}
		if (num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 15750;
		}
		if(car.equals("ford") && num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 3500;
		}
		miles = num_Of_Laps * LAP_DISTANCE;
		net = winnings + bonus - ENTRY_FEE;
		driver1 = (racer_Num + "     " + first_Name + " " + last_Name + "   " + car + "      " + num_Of_Laps + "       " + miles + "      " + money.format(winnings) + "     " + money.format(bonus) + "      " + money.format(net));
		
		//calculates totals
		total_Miles = miles;
		total_Base_Winnings = winnings;
		total_Bonus = bonus;
		total_Net = net;
		
//2nd Racer info
		
		//reset variables
		racer_Num = 0;
		num_Of_Laps = 0;
		winnings = 0.0;
		bonus = 0;
		miles = 0;
		net = 0;
		
		//gets driver information
		System.out.println("\n-----Driver 2 information-----");
		System.out.print("Enter first name: ");
		first_Name = scan.next();
		System.out.print("Enter last name: ");
		last_Name = scan.next();
		first_Name.toLowerCase();
		last_Name.toLowerCase();

		//Laps?
		System.out.print("Enter number of laps completed: ");
		num_Of_Laps = scan.nextInt();
		
		//type of car?
		System.out.print("Enter make of vehicle: ");
		car = scan.next();
		car.toLowerCase();
		
		//assigns racer number
		racer_Num = rand.nextInt(5000)+1;

		//pay math
		winnings += num_Of_Laps * EARNING_PER_LAP;
		if (num_Of_Laps >= 50 && num_Of_Laps <= 100)
		{
			winnings *= 1.33;
		}
		if (num_Of_Laps >= 101 && num_Of_Laps <= 150)
		{
			winnings *= 1.66;
		}
		if (num_Of_Laps >= 151 && num_Of_Laps <= TOTAL_LAPS)
		{
			winnings *= 2.00;
		}
		if (num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 15750;
		}
		if(car.equals("ford") && num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 3500;
		}
		miles = num_Of_Laps * LAP_DISTANCE;
		net = winnings + bonus - ENTRY_FEE;
		driver2 = (racer_Num + "     " + first_Name + " " + last_Name + "   " + car + "      " + num_Of_Laps + "       " + miles + "      " + money.format(winnings) + "     " + money.format(bonus) + "      " + money.format(net));

		//calculates totals
		total_Miles += miles;
		total_Base_Winnings += winnings;
		total_Bonus += bonus;
		total_Net += net;
		
//3rd Racer info
		
		//reset variables
		racer_Num = 0;
		num_Of_Laps = 0;
		winnings = 0.0;
		bonus = 0;
		miles = 0;
		net = 0;
		
		//gets driver information
		System.out.println("\n-----Driver 3 information-----");
		System.out.print("Enter first name: ");
		first_Name = scan.next();
		System.out.print("Enter last name: ");
		last_Name = scan.next();
		first_Name.toLowerCase();
		last_Name.toLowerCase();

		//Laps?
		System.out.print("Enter number of laps completed: ");
		num_Of_Laps = scan.nextInt();
		
		//type of car?
		System.out.print("Enter make of vehicle: ");
		car = scan.next();
		car.toLowerCase();
		
		//assigns racer number
		racer_Num = rand.nextInt(5000)+1;

		//pay math
		winnings += num_Of_Laps * EARNING_PER_LAP;
		if (num_Of_Laps >= 50 && num_Of_Laps <= 100)
		{
			winnings *= 1.33;
		}
		if (num_Of_Laps >= 101 && num_Of_Laps <= 150)
		{
			winnings *= 1.66;
		}
		if (num_Of_Laps >= 151 && num_Of_Laps <= TOTAL_LAPS)
		{
			winnings *= 2.00;
		}
		if (num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 15750;
		}
		if(car.equals("ford") && num_Of_Laps == TOTAL_LAPS)
		{
			bonus += 3500;
		}
		miles = num_Of_Laps * LAP_DISTANCE;
		net = winnings + bonus - ENTRY_FEE;
		driver3 = (racer_Num + "     " + first_Name + " " + last_Name + "   " + car + "      " + num_Of_Laps + "       " + miles + "      " + money.format(winnings) + "     " + money.format(bonus) + "      " + money.format(net));
		
		//calculates totals
		total_Miles += miles;
		total_Base_Winnings += winnings;
		total_Bonus += bonus;
		total_Net += net;
		
		//output
		System.out.println("\nNASCAR Indy 500 Race Results\n");
		System.out.println("Bib#     Name          Make     laps     miles     Base Winnings    Bonus     NetWinnings");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println(driver1);
		System.out.println(driver2);
		System.out.println(driver3 + "\n");
		System.out.println("Total Miles Covered: " + total_Miles);
		System.out.println("Total Base Winnings: " + money.format(total_Base_Winnings));
		System.out.println("Total Bonus Winnings: " + money.format(total_Bonus));
		System.out.println("Total Net Winnings: " + money.format(total_Net));
		scan.close();
	}
}