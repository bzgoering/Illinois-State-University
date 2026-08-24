//This class makes card from 1-40 for the game
//[1-10] are hearts, 
//[11-20] are spades, 
//[21-30] are clubs, 
//[31-40] are diamonds

//Author: Ben Goering

public class Card 
{
	//variables
	String suit;
	int number;
	
	public Card(int num)
	{
		//for suit
		if (num <= 10)
		{
			suit = "Hearts";
		}
		else if(num <= 20)
		{
			suit = "Spades";
		}
		else if(num <= 30)
		{
			suit = "Clubs";
		}
		else if(num <= 40)
		{
			suit = "Diamonds";
		}
		
		//gets number
		number = num % 10;
		
		if (number == 0)
		{
			number = 10;
		}
		
	}

	/**
	 * @return the suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	
	public String toString()
	{
		return "[" + number + " " + suit + "]";
	}
}
