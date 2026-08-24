import java.util.ArrayList;

//main class that keeps track of players in game
//Author: Ben Goering

public class Player 
{
	//variables
	private ArrayList<Card> cards = new ArrayList<>();
	private String name;
	private Card discard;
	
	//constructor for player
	//has name and array of 4 integers
	public Player(String n, ArrayList<Card> card)
	{
		name = n;
		cards = (card);
	}
	
	//gets player's name
	public String getName()
	{
		return name;
	}
	
	//gets player's hand
	public String getCards()
	{
		//variables
		String result = "";
		
		//goes through players hand
		for (int x = 0; x < cards.size(); x++)
		{
			result += cards.get(x).toString();
		}
		
		return result;
	}
	
	//returns card discarded
	public Card getDiscard()
	{
		return discard;
	}
	
	//checks to see if player will win
	public boolean win()
	{
		//variables
		int total = 0;
		String suit = cards.get(0).suit;
		
		//adds cards up
		for (int x = 0; x < cards.size(); x++)
		{
			total += cards.get(x).number;
		}
		//checks if 23 if so win will return true
		if (total == 23)
		{
			return true;
		}
		
		//checks if all cards are the same suit		
		for (int x = 0; x < cards.size(); x++)
		{
			//returns false if they arn't
			if (!suit.equals(cards.get(x).suit))
			{
				return false;
			}
		}
	
		//returns true if they are
		return true;
	}
	
	//helper method that checks if 4 out of 5 cards have the same suit
	//Knowing that the initial hand is not a winner, 
	//checks to see if that new card will get them a win through the same suit
	private boolean checkSuit(Card c)
	{
		//variables
		int count = 0;
		
		//loops through players cards
		for (int x = 0; x < cards.size(); x++)
		{
			//finds any cards suit that matches to the card
			if (cards.get(x).getSuit().equals(c.getSuit()))
			{
				count ++;
			}
		}
		
		//checks how many cards have the same suit
		if (count == 4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//helper method that checks if 4 out of 5 cards will equal to 23
	//knowing the initial 4 cards don't, and the 5 could.
	private ArrayList<Card> checkTotal(Card c)
	{
		//variables
		int total = 0;
		
		//loops through all combos of 3 hands from the given four
		for (int x = 0; x < cards.size(); x++)
		{
			for (int y = x+1; y < cards.size(); y++)
			{
				for (int z = y+1; z < cards.size(); z ++)
				{
					//checks if the combo with the new card will equal to 23
					total = cards.get(x).getNumber() + cards.get(y).getNumber() + cards.get(z).getNumber() + c.getNumber();
					if (total == 23)
					{
						return getWinnerCards(cards.get(x), cards.get(y), cards.get(z), c);
					}
				}
			}
		}
		return null;
	}
	
	//gets the four cards that equal to 23 and output them
	private ArrayList<Card> getWinnerCards(Card x, Card y, Card z, Card c)
	{
		ArrayList<Card> temp = new ArrayList<>();
		temp.add(x);
		temp.add(y);
		temp.add(z);
		temp.add(c);
		return temp;
	}
	
	//decides if they should pick up face up card weather on if they can win or not
	//returns true if they should
	//false if they should'nt
	public boolean decide(Card c)
	{	
		//makes sure card isnt null
		if (c == null)
		{
			return false;
		}
		
		//checks to see if new card will get win or not
		if (checkTotal(c) != null || checkSuit(c) == true)
		{
			return true;
		}
		
		//card does'nt give win
		return false;
	}
	
	//draws a card from either face up or down cards
	public boolean drawCard(Card c)
	{
		//if player can win by total of 23
		if (checkTotal(c) != null)
		{
			cards = checkTotal(c);

			//adds cards
			System.out.println(name + " picks up a " + c.toString());
			
			//output new hand
			System.out.println(toString() + "\n");
			
			//output winner
			System.out.println("The Winner is: " + name);

			return true;
		}
		
		//adds card
		cards.add(c);
		System.out.println(name + " picks up a " + c.toString());
		
		//discard card
		discard = throwCard();
		System.out.println(name + " drops a " + discard.toString());
		
		//prints new hand
		System.out.println(toString() + "\n");
		
		//checks for win
		if (win() == true)
		{
			System.out.println("The Winner is " + name);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//finds the smallest suit count thats greater than 0
	private String smallSuit()
	{
		//variables
		int[] count = new int[4];
		int small = 999;
		
		//loops through players hand
		for (int x = 0; x < cards.size(); x++)
		{
			//counts suit of card
			if (cards.get(x).getSuit().equals("Hearts"))
			{
				count[0] ++;
			}
			else if(cards.get(x).getSuit().equals("Spades"))
			{
				count[1] ++;
			}
			else if (cards.get(x).getSuit().equals("Clubs"))
			{
				count[2] ++;
			}
			else if (cards.get(x).getSuit().equals("Diamonds"))
			{
				count[3] ++;
			}
		}
		
		//gets smallest count thats greater than 0
		for (int x = 0; x < count.length; x++)
		{
			if (count[x] > 0 && count[x] < small)
			{
				small = count[x];
			}
		}
		
		//returns smallest suit
		if (small == count[0])
		{
			return "Hearts";
		}
		else if (small == count[1])
		{
			return "Spades";
		}
		else if (small == count[2])
		{
			return "Clubs";
		}
		else if (small == count[3])
		{
			return "Diamonds";
		}
		
		return "Not found";
	}
	
	//finds smallest suit count and discard one of them
	private Card throwCard()
	{
		//variables
		String suit = smallSuit();		
		Card discard;
		
		//goes through hand
		for (int x = 0; x < cards.size(); x++)
		{
			//discard the extra card with lowest suit count
			if (cards.get(x).getSuit().equals(suit))
			{
				discard = cards.get(x);
				cards.remove(x);
				return discard;
			}
		}
		return null;
	}
	
	//override toString method
	@Override
	public String toString()
	{
		String result = name + ": " + getCards();
		return result;
	}
}
