import java.util.*;

//main class for eels and escalator a game similar to snakes and ladders where players roll a 6 sided die
//Players will then advance the amount rolled
//each square a has the possibility of being a eel or escalator
//eels will move players back 1 - 8 spaces while the escalator can move players up 1 - 8 spaces
//first person to reach square 100 win the game

public class Game 
{
	//inner class for each square
	private static class Square
	{
		//variables
		Square prev;
		Square next;
		int squareNum;
		int jump;
		
		//constructor with int, int, Square, parameters
		Square(int num, int jumpValue, Square p)
		{
			squareNum = num;
			jump = jumpValue;
			prev = p;
		}
	}
	
	//variables
	private Square start;
	private Square end;
	private ArrayList <Square> playerPos;
	private int currentPlayer;
	private int totalPlayers;
	boolean winner;
	
	//constructor
	//param: integer of number of players to set the arraylist
	public Game(int num)
	{
		setUpBoard();
		playerPos = new ArrayList<>(2);
		
		//fills ArrayList
		for (int x = 0; x < num; x++)
		{
			playerPos.add(x, start);
		}
		
		//sets currentPlayer to default player 1
		currentPlayer = 0;
		
		//sets total player counr
		totalPlayers = num;
	}
	
	//private helper method for constructor
	//sets up the squares for the game board
	private void setUpBoard()
	{
		//variables
		Square curr = null;
		Random rand = new Random();

		//sets squares number, jump value, and prev
		for (int x = 1; x <= 100; x++)
		{
			//sets spaces 0 and 100 jump value to 0 but all others are random
			if (x == 1 || x == 100)
			{
				curr = new Square(x, 0, curr);
			}
			//makes 80% have a jump of 0
			else if (rand.nextInt(10) < 8)
			{
				curr = new Square(x, 0, curr);
			}
			//10% are eels, jump value will be as low as -8
			else if (rand.nextInt(10) < 5)
			{
				curr = new Square(x, (rand.nextInt(8) - 8), curr);
			}
			//10% are ladders, jump value will be as high as 8
			else 
			{
				curr = new Square(x, (rand.nextInt(8)+1), curr);
			}

			if (curr.prev != null)
			{
				curr.prev.next = curr;
			}
			
			//sets up start and end squares
			if (x == 1)
			{
				start = curr;
			}
			if (x == 100)
			{
				end = curr;
			}
		}
	}
	
	//main method that allows the player to play the game
	public void play()
	{
		//Variables
		int roll;
		currentPlayer = 0;
		Scanner scan = new Scanner(System.in);
		String movement = "";
		
		//main while loop to run the game
		//continues going until someone wins
		while (winner == false)
		{
			System.out.println("_________________________________________________________________");
			
			//which player should roll die
			System.out.println("Player " + (currentPlayer+1) + " turn");
			
			//waits for user input to roll die
			System.out.print("Hit any key and Enter to Roll Die: ");
			scan.nextLine();

			//rolls die
			roll = rollDie();
			
			//moves player to new space
			movement = move(currentPlayer, roll);
				
			//shows players moving on board
			System.out.print("\n" + toString());
			System.out.println(movement);

			//will go through every player then reset to player 1
			if (currentPlayer < totalPlayers-1)
			{
				currentPlayer++;
			}
			else
			{
				currentPlayer = 0;
			}
		}
			
	}
	
	//helper method for play method
	//gets random number between 1 and 6
	private int rollDie()
	{
		Random rand = new Random();
		return(rand.nextInt(6 - 1 + 1) + 1);
	}
	
	//helper method for the play method
	//moves player from the roll
	//will return the players movements
	//param: player we will move and the amount of spaces
	public String move(int p, int rollNum)
	{
		//variables
		String result = "";
		
		//first checks to make sure player wont go out of bounds
		if (OOB(rollNum, p) == true)
		{
			playerPos.set(p, end);
			result += ("\nYou moved your way to the finish line: +" + rollNum + " your on space: " + playerPos.get(p).squareNum);
			result += ("\nPlayer " + (p+1) + " Is The Winner!\n");			
			winner = true;
			return result;
		}
		//gets next space from roll
		else
		{
			//moves player to the next space
			playerPos.set(p,getNext(rollNum,playerPos.get(p)));
			result += ("\nyou moved " + rollNum + " spaces" + " your on space: " + playerPos.get(p).squareNum);
			
		}
		//makes the appropriate jump from the new space, will only jump once
		result += (jump(p));
		
		return result;
	}
	
	//helper method for the move method
	//gets the squares jump value
	//if negative - will move player back
	//if positive - will move player up
	//returns a string of movements it did
	//params: player we will move
	public String jump(int p)
	{
		//variables
		int jumpValue = playerPos.get(p).jump;		
		String result = "";

		//if there isnt a jump value it quits
		if (jumpValue == 0)
		{
			return result;
		}
		//if there is a jump value, this checks to make sure it does not jump out of bounds
		//if it does it quits and makes the player win
		else if (OOB(jumpValue, p) == true)
		{
			playerPos.set(p, end);
			result += ("\nYour climbed the ladder: +" + jumpValue + " your on space: " + playerPos.get(p).squareNum);
			result += ("\nPlayer " + (p+1) + " Is The Winner!");
			winner = true;
			return result;
		}
		//if its in bounds then it check to make sure it doesn't go negative
		if (playerPos.get(p).squareNum - jumpValue <= 0)
		{
			playerPos.set(p, start);
			result += ("\nYour rode the snake back to the start" + " your on space: " + playerPos.get(p).squareNum);
		}
		//if it clears then it check if its positive of negative
		//if its positive itll add the amound
		else if (jumpValue > 0)
		{
			playerPos.set(p, getNext(jumpValue, playerPos.get(p)));
			result += ("\nYour climbed the ladder: +" + jumpValue + " your on space: " + playerPos.get(p).squareNum);
		}
		//if its negative it'll subtract the amount 
		else if (jumpValue < 0)
		{
			playerPos.set(p, getPrev(jumpValue, playerPos.get(p)));
			result += ("\nYou rode the snake: -" + (jumpValue*-1) + " your on space: " + playerPos.get(p).squareNum);
		}
		return result;
	}
	
	//helper method for move and jump that gets the next square from a roll or jump
	//returns the square
	//params: amount of squares to go forward and the current square
	private Square getNext(int n, Square s)
	{
		//variables
		Square temp = s;
		
		//makes sure the amount of spaces is positive
		if (n > 0)
		{
			//main loop for traversing
			for (int x = 0; x < n; x++)
			{
				//makes sure there's a next square if not then its automatically at the end
				if (temp.next == null)
				{
					temp = end;
				}
				//gets next square
				else
				{
					temp = temp.next;
				}
			}
		}
		
		return temp;
	}
	
	//helper method for jump that gets the previous square
	//returns the square
	//params: amount of squares to go backwards and the current square
	private Square getPrev(int n, Square s)
	{
		//variables
		Square temp = s;
		
		//makes sure n is negative
		if (n < 0)
		{
			//main loop for traversing
			for (int x = 0; x > n; x--)
			{
				//makes sure theres a previous, if not then its automatically at the start
				if (temp.prev == null)
				{
					temp = start;
				}
				//gets previous square
				else
				{
					temp = temp.prev;
				}
			}
		}
		
		return temp;
	}
	
	//helper method that makes sure player stays within 100 squares
	private boolean OOB(int jumpNum, int p)
	{
		//gets space number of next jump
		int total = playerPos.get(p).squareNum + jumpNum;
		
		//if the number i greater then 100 or 100 returns true that it will got out of bounds or reach the end
		if (total >= 100)
		{
			return true;
		}
		//if not then its returns false
		else
		{
			return false;
		}
	}
	
	//prints out game board with players on it
	public String toString()
	{
		//Variables for squares on board
		String result = "";
		Square temp = start;
		Square temp2 = start;
		int current = 0;

		//main loop to add all board squares
		for (int x = 0; x < 5; x++)
		{	
			//outputs 10 spaces
			for (int y = 0; y < 10; y ++)
			{
				result += "[" + temp.squareNum;
				current = 0;

				//finds and places players position
				for (int z = 0; z < totalPlayers; z++)
				{
					if (playerPos.get(current).squareNum == temp.squareNum)
					{
						result += " P" + (current+1);
					}
					current++;
				}
				
				//gets next square
				result += "]";
				temp = temp.next;
			}
			
			//sets up the next 10 spaces but in reverse order
			result += "\n";
			temp2 = temp;
			temp2 = temp2.next.next.next.next.next.next.next.next.next;
			temp = temp2.next;
			
			//outputs next 10 spaces but reverse from the first 10
			//so 11 will be under 10 and so on
			for (int y = 0; y < 10; y ++)
			{
				result += "[" + temp2.squareNum;
				current = 0;
				
				//finds and places players position
				for (int z = 0; z < totalPlayers; z++)
				{
					if (playerPos.get(current).squareNum == temp2.squareNum)
					{
						result += " P" + (current+1);
					}
					current++;
				}
				
				//gets previous square
				result += "]";
				temp2 = temp2.prev;
			}
			
			result += "\n";
		}
		
		return result;
		
	}
}
