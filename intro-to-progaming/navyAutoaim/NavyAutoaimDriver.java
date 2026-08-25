package navyAutoaim;

import java.util.Scanner;
/**
 * This class will calculate the angle the gun she be pointed to hit a the  target\
 * until the user enter q or Q to stop
 */
public class NavyAutoaimDriver {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//variables
		Scanner scan = new Scanner(System.in);
		final double gravity = 9.8;
		final double velocity = 853.44;
		double range;
		double target = 0;
		double angle = 1;
		boolean hit = false;
		char repeat = 'y';
						
		//introduction
		System.out.println("Nava Gunnery Helper\n");
		
		//Main loop
		while (repeat != 'q' && repeat != 'Q')
			{
				//reset hit and angle
				hit = false;
				angle = 0;
							
				//gets input
				System.out.print("Enter the distance (in meters) to the enemy ship: ");
				target = scan.nextDouble();
							
				//loop to find angle
				while (angle <= 45 && hit == false)
					{
						//math for range at angle x
						range = ((velocity * velocity)/gravity) * (Math.sin(Math.toRadians(2*angle)));
						System.out.println("With the gun at angle " + angle + " the projectile will travel " + range + " meters,");
								
						//test for hit
						if (target - range < 5 && target - range >-5)
						{
							hit = true;
							System.out.println("And hit the target");
							System.out.println("\nTo hit the target, the gun should be set at 30 degrees.");
						}
						else
						{
							hit = false;
							System.out.println("So the target won't be hit");
						}
					
						angle++;
					}
				
				//if no angle can hit target message
				if (hit == false)
				{
					System.out.println("The target can't be hit at this distance");
				}
				
				//user input to loop
				System.out.print("\nEnter q or Q to quit: ");
				repeat = scan.next().charAt(0);
			}
		
		//tells user the loop ended
		System.out.println("\nNaval Gunnery Helper ended");
		scan.close();
		}
	}