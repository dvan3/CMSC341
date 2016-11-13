/**
* File:    Board.java
* Project: 3
* Author:  Dave Van
* Date:    4/18/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/
package proj3;

/**
 * @author Dave
 *
 */
public class Board 
{
	//initialize variables
	private int gameBoard[][] = new int[3][3];
	private int blankBox;
	
	/**
	 * Name: Board
	 * Description: creates a blank board
	 */
	public Board()
	{
		//go through each index of the 2d array
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				//set each one to 0
				this.gameBoard[row][col] = 0;
			}
		}
	}
	
	/**
	 * Name: boardFromArray
	 * Description: transfers a 1d array to a 2d array
	 * PreCondition: must be able to fill the 2d array up
	 * PostCondition: a completely filled 2d array
	 */
	public void boardFromArray(int[] board)
	{
		int x = 0;
		
		//go through each index of the 2d array
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				//set each index of 2d array with the 1d array
				this.gameBoard[row][col] = board[x++];
				
				//checks for a blank box
				if(board[x - 1] == 0)
				{
					//sets the blank box index
					blankBox = x - 1;
				}
			}
		}
	}
	
	/**
	 * Name: moveableBoxes
	 * Description: finds the possible moves for the blank box
	 * PreCondition: blank box must not move out of the edge of the array
	 * PostCondition: puts in the index of the 2d array it can move to in an array
	 */
	public int [] moveableBoxes()
	{
		int pos = blankBox;
		int x[] = new int [4];
		int xPos = pos / 3;
		int yPos = pos % 3;
		
		//Move up, finds the index of where the blank box can move
		if(xPos > 0)
		{
			x[0] = ((xPos - 1) * 3) + (yPos);
		}
		else
		{
			x[0] = -1;
		}
		
		//Move down, finds the index of where the blank box can move
		if(xPos < 2)
		{
			x[1] = ((xPos + 1) * 3) + (yPos);
		}
		else
		{
			x[1] = -1;
		}
		
		//Move left, finds the index of where the blank box can move
		if(yPos > 0 )
		{
			x[2] = (xPos * 3) + (yPos - 1);
		}
		else
		{
			x[2] = -1;
		}
		
		//Move right, finds the index of where the blank box can move
		if(yPos < 2)
		{
			x[3] = (xPos * 3) + (yPos + 1);
		}
		else
		{
			x[3] = -1;
		}
		
		return x;
	}
	
	/**
	 * Name: moveUp
	 * Description: moves the blank box up
	 * PreCondition: the blank box is not out of the array size
	 * PostCondition: moves the blank box where it is able to move up
	 */
	public boolean moveUp()
	{
		int moveTo, moveFrom, moveToX, moveToY, moveFromX, moveFromY, tempValue;
		
		//move blank box up
		if(moveableBoxes()[0] != -1)
		{
			moveTo = moveableBoxes()[0];
			moveFrom = blankBox;
			blankBox = moveTo;
			moveToX = moveTo / 3;
			moveToY = moveTo % 3;
			moveFromX = moveFrom / 3;
			moveFromY = moveFrom % 3;
			tempValue = gameBoard[moveToX][moveToY];
			gameBoard[moveToX][moveToY] = gameBoard[moveFromX][moveFromY];
			gameBoard[moveFromX][moveFromY] = tempValue;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Name: moveDown
	 * Description: moves the blank box down
	 * PreCondition: the blank box is not out of the array size
	 * PostCondition: moves the blank box where it is able to move down
	 */
	public boolean moveDown()
	{
		int moveTo, moveFrom, moveToX, moveToY, moveFromX, moveFromY, tempValue;
		
		//move blank box up
		if(moveableBoxes()[1] != -1)
		{
			moveTo = moveableBoxes()[1];
			moveFrom = blankBox;
			blankBox = moveTo;
			moveToX = moveTo / 3;
			moveToY = moveTo % 3;
			moveFromX = moveFrom / 3;
			moveFromY = moveFrom % 3;
			tempValue = gameBoard[moveToX][moveToY];
			gameBoard[moveToX][moveToY] = gameBoard[moveFromX][moveFromY];
			gameBoard[moveFromX][moveFromY] = tempValue;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Name: moveLeft
	 * Description: moves the blank box left
	 * PreCondition: the blank box is not out of the array size
	 * PostCondition: moves the blank box where it is able to move left
	 */
	public boolean moveLeft()
	{
		int moveTo, moveFrom, moveToX, moveToY, moveFromX, moveFromY, tempValue;
		
		//move blank box up
		if(moveableBoxes()[2] != -1)
		{
			moveTo = moveableBoxes()[2];
			moveFrom = blankBox;
			blankBox = moveTo;
			moveToX = moveTo / 3;
			moveToY = moveTo % 3;
			moveFromX = moveFrom / 3;
			moveFromY = moveFrom % 3;
			tempValue = gameBoard[moveToX][moveToY];
			gameBoard[moveToX][moveToY] = gameBoard[moveFromX][moveFromY];
			gameBoard[moveFromX][moveFromY] = tempValue;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Name: moveRight
	 * Description: moves the blank box right
	 * PreCondition: the blank box is not out of the array size
	 * PostCondition: moves the blank box where it is able to move right
	 */
	public boolean moveRight()
	{
		int moveTo, moveFrom, moveToX, moveToY, moveFromX, moveFromY, tempValue;
		
		//move blank box up
		if(moveableBoxes()[3] != -1)
		{
			moveTo = moveableBoxes()[3];
			moveFrom = blankBox;
			blankBox = moveTo;
			moveToX = moveTo / 3;
			moveToY = moveTo % 3;
			moveFromX = moveFrom / 3;
			moveFromY = moveFrom % 3;
			tempValue = gameBoard[moveToX][moveToY];
			gameBoard[moveToX][moveToY] = gameBoard[moveFromX][moveFromY];
			gameBoard[moveFromX][moveFromY] = tempValue;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Name: getGameBoard
	 * Description: gets the gameBoard
	 * PreCondition: none
	 * PostCondition: returns gameBoard
	 */
	public int[][] getGameBoard()
	{
		return gameBoard;
	}
	
	/**
	 * Name: printBoard
	 * Description: prints the board
	 * PreCondition: none
	 * PostCondition: prints the board
	 */
	void printBoard()
	{
		for(int x = 0; x < 3; x++)
		{
			System.out.println(gameBoard[x][0] + " " + gameBoard[x][1] + " " + gameBoard[x][2]);
		}
	}
	
	/**
	 * Name: equals
	 * Description: checks if 2 boards are equal
	 * PreCondition: none
	 * PostCondition: returns true or false depending if they are equal or not
	 */
	public boolean equals(Object x)
	{
		//true if it is equal
		boolean same = true;
		
		Board y = (Board)x;
		
		//go through each index of the 2d array
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				//if they don't equal
				if(y.gameBoard[row][col] != gameBoard[row][col])
				{
					//set variable to false
					same = false;
				}
			}
		}
		return same; //returns true or false
	}
	
	/**
	 * Name: copyBoard
	 * Description: makes a copy of a board
	 * PreCondition: none
	 * PostCondition: creates a copy of a board
	 */
	public void copyBoard(Board board)
	{
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				this.gameBoard[row][col] = board.gameBoard[row][col];
			}
		}
		this.blankBox = board.blankBox;
	}
}
