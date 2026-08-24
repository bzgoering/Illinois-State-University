import java.util.*;
public class passwordVault 
{
public static void main(String arg[])
{
	//Variables
	String website;
	String user;
	String email;
	String note;
	String password = ("");
	
	Scanner scan = new Scanner(System.in);
	Scanner noteScan = new Scanner(System.in);
	Random rand = new Random();
	
	//User input
	System.out.println("Welcome to the Password Vault!\nLet's create a new password entry.");
	System.out.print("Enter the website name: ");
	website = scan.next();
	System.out.print("Enter the username: ");
	user = scan.next();
	System.out.print("Enter the email: ");
	email = scan.next();
	System.out.print("Add a note: ");
	note = noteScan.nextLine();

	//creating password
	for(int x = 0; x<6; x++)
	{
		int number = rand.nextInt(9)+1;
		password += (number);
	
		int tableNum = rand.nextInt(126-58+1)+58;
		password += ((char)tableNum);
	}

	//my output
	System.out.println("********************\n   Item Summary     \n********************");
	System.out.println("Website Name: " + website);
	System.out.println("Website URL: https://www." + website + ".com");
	System.out.println("Email: " + email);
	System.out.println("Username: " + user);
	System.out.println("password: " + password);
	System.out.println("Note: " + note);
}
}
