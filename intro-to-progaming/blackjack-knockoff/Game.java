import java.util.*;

//Main class that plays a simple card game that only uses cards 1-10 of hearts, spades, clubs, diamonds
//Author: Ben Goering

public class Game
{
	private LinkedList<Card> unshuffled = new LinkedList<>();//
	private Stack<Card> faceDown = new Stack<>(); //the cards players draw from
	private Stack<Card> faceUp = new Stack<>(); //cards thrown from player
	private Queue<Player> players = new LinkedList<>();//players
	
	//constructor
	//param String of names
	public Game(String n)
	{
		shuffle();
		addPlayers(n);
	}
	
	//adds the cards to the unshuffled list so we can shuffle them
	//[1-10] are hearts, 
	//[11-20] are spades, 
	//[21-30] are clubs, 
	//[31-40] are diamonds
	private void addCards()
	{
		//makes 40 cards 1-40
		for (int x = 0; x < 40; x++)
		{
			unshuffled.addLast(new Card(x+1));
		}
	}
	
	//shuffles list by getting random index from unshuffle and adding corresponding data to shuffle until
	private void shuffle()
	{
		//adds cards to unshuffle
		addCards();
		
		//variables
		LinkedList<Card> temp = new LinkedList<>(unshuffled);
		Random rand = new Random();
		
		//goes through temp of unshuffle until empty
		for (int x = 40; x > 0; x--)
		{
			//gets random index and placed card into shuffle from unshuffle at that index
			int num = rand.nextInt(x);
			faceDown.add(temp.get(num));
			temp.remove(num);
		}
	}
	
	//adds players from a string of names with commas separating them
	private void addPlayers(String n)
	{		
		//loops for player names
		while (n.contains(","))
		{
			//variables
			ArrayList<Card> card = new ArrayList<>();

			//gets players 4 cards
			for (int x = 0; x < 4; x ++)
			{
				card.add(faceDown.pop());
			}
			
			//makes names into players
			Player p1 = new Player(n.substring(0, n.indexOf(",")), card);
			
			//adds players to the queue
			players.add(p1);
			
			//gets the next name
			n = n.substring(n.indexOf(",")+1);
			
			
		}
	}
	
	//check for a win at first deal
	//check to see if anyone won at the start of the game
	private boolean winAtStart()
	{
		//variables
		String winnerName = "";
		boolean winner = false;
		Player current;
						
		//goes through player queue
		for (int x = 0; x < players.size(); x++)
		{
			//gets next player
			current = players.poll();
			players.offer(current);
							
			//shows player and their hand
			System.out.println(current.toString());
					
			//check for win at beginning
			if (current.win() == true)
			{
				winner = true;
				winnerName += current.getName() + " ";
			}
					
		}
				
		//ends game if there is a winner at the start
		if (winner == true)
		{
			System.out.println("\nThe Winner is: " + winnerName);
		}
		return winner;
	}
	
	//full game
	//simulates the card game
	//returns winner name
	public void sim()
	{
		//variables
		boolean winner = false;
		Player current;
		
		System.out.println("\nPlayer's cards");
		
		//check for wins at first deal
		if (winAtStart() == true)
		{
			return;
		}
		
		//just a space for looks
		System.out.println();
		
		//gets first player
		current = players.poll();
		players.offer(current);
		
		//sees if player wins with new card
		if (current.decide(faceDown.peek()) == true)
		{
			//player draws and discard, this will also show what happens
			winner = current.drawCard(faceDown.pop());
			
			//adds discarded card to face up pile
			faceUp.push(current.getDiscard());
			
			return;
		}
		else
		{
			//player draws and discard, this will also show what happens
			winner = current.drawCard(faceDown.pop());
			
			//adds discarded card to face up pile
			faceUp.push(current.getDiscard());
		}
		
		//runs rest of game until winner or no cards left
		while (faceDown.empty() == false && winner == false)
		{
			//gets next player
			current = players.poll();
			players.offer(current);
			
			//if the face up pile gets player a win
			if (current.decide(faceUp.peek()) == true)
				{
					//draws and discard a card, will also show what happens
					winner = current.drawCard(faceUp.pop());
					faceUp.push(current.getDiscard());
				}
			
			//draw from regular face down pile
			else
			{
				//draws and discard
				winner = current.drawCard(faceDown.pop());
				
				//adds discarded card to face up pile
				faceUp.push(current.getDiscard());
			}
		}
		
		//if game has no winner and no more cards left, nobody will win
		if (faceDown.empty() == true)
		{
			System.out.println("No winner - out of cards");
			return;
		}
		
		return;
	}
	
}
