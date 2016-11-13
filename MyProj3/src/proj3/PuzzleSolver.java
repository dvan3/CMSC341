/**
* File:    PuzzleSolver.java
* Project: 3
* Author:  Dave Van
* Date:    4/18/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/
package proj3;

import java.util.ArrayList;

/**
 * @author Dave
 *
 */
public class PuzzleSolver 
{
	//initialize instance variables
	private int pCost = 0;
	private Board puzzleBoard = null;
	private Board goalBoard = null;
	
	/**
	 * Name: PuzzleSolver
	 * Description: takes in the initial board and the goal board
	 */
	public PuzzleSolver(Board board, Board goalBoard)
	{
		puzzleBoard = board;
		this.goalBoard = goalBoard;
	}
	
	/**
	 * Name: solvePuzzle
	 * Description: solves the puzzle using best-first search
	 * PreCondition: the puzzle must have a goal state
	 * PostCondition: solves the puzzle
	 */
	public void solvePuzzle() throws UnderflowException
	{
		//create the priority queue
		PriorityQueue<State> openQ = new PriorityQueue<State>();
		
		//create an ArrayList to store each state
		ArrayList<State> list = new ArrayList<State>();
		
		//initializing some variables
		pCost = 0;
		State newState = null; 
		State parentState = null;
		
		//create the goal state
		State goalState = new State(0, 0, goalBoard, null, "Goal");
		
		//create the current state
		State currentState = new State(pCost, heuristic(puzzleBoard), puzzleBoard, parentState, "Start");
		
		//insert the current state into the priority queue
		openQ.insert(currentState);
		
		//create a temporary board
		Board tempBoard = new Board();
		
		//while the queue is not empty
		while(!openQ.isEmpty())
		{
			//create a blank board
			Board board = null;
			board = new Board();
			
			//add one to the path cost
			pCost++; 
			
			State min2;
			
			//get the best merit state
			currentState = openQ.findMin();
			
			//delete the minimum
			openQ.deleteMin();
			
			//add the current state into the array
			list.add(currentState);
			
			//if the size is not 0
			if(openQ.getSize() != 0)
			{
				//find the minimum to compare
				min2 = openQ.findMin();
				
				//if they are the same
				if(currentState.compareTo(min2) == 0)
				{
					//delete the minimum
					openQ.deleteMin();
					
					//create a new board
					Board board2 = new Board();

					//create a temp board
					Board temp2 = new Board();

					//make a board copy to move
					board2.copyBoard(min2.getBoard());

					//make a board to keep the current state
					temp2.copyBoard(board2);
					
					//generate the children of min2
					generateChildren(board2, temp2, newState, parentState, openQ);
					
					//add min2 to the list
					list.add(min2);
					
					//remove the currentState from the list
					list.remove(currentState);
				}
			}
			
			//board to move
			board.copyBoard(currentState.getBoard());
			
			//board to copy after being moved
			tempBoard.copyBoard(board);
			
			//since the initial state does not have a parent
			//this will make the initial state the parent of itself
			if(currentState.getParent() == null)
			{
				parentState = currentState;
			}
			
			//prints out the path to the goal state
//			currentState.printState();
//			System.out.println("cost = " + currentState.getTotalCost());
//			System.out.println("g = " + currentState.getPathCost());
//			System.out.println("h = " + currentState.getHeuristicCost());
//			System.out.println("move = " + currentState.getMove());
//			System.out.println();
			
			//checks to see if it has reached the goal state
			if(currentState.getBoard().equals(goalState.getBoard()) == true)
			{
				//if there is a solution that is less than 5
				if(list.size() < 5)
				{
					//print the solution
					for(int i = 0; i < list.size(); i++)
					{
						list.get(i).getBoard().printBoard();
						System.out.println("cost = " + list.get(i).getTotalCost());
						System.out.println("g = " + list.get(i).getPathCost());
						System.out.println("h = " + list.get(i).getHeuristicCost() + "\n");
					}
				}
				else //if there is a solution more than 5
				{
					//print the solution
					printFiveStates(list);
				}
				System.out.println("Solution found");
				System.out.println("Path to Solution = " + currentState.getPathCost());
				System.out.println("OpenQ size = " + openQ.getSize());
				openQ.makeEmpty();
			}
			else //if the current state is not the goal state
			{
				//generate the children of the current state
				generateChildren(board, tempBoard, newState, parentState, openQ);
				
				//update the parent
				parentState = currentState;
			}
		}
	}
	
	/**
	 * Name: printFiveStates
	 * Description: helper method that prints only the first five state configurations
	 * PreCondition: none
	 * PostCondition: outputs the board, total cost, path cost, and heuristic cost
	 */
	private void printFiveStates(ArrayList<State> list)
	{
		for(int i = 0; i < 5; i++)
		{
			list.get(i).getBoard().printBoard();
			System.out.println("cost = " + list.get(i).getTotalCost());
			System.out.println("g = " + list.get(i).getPathCost());
			System.out.println("h = " + list.get(i).getHeuristicCost() + "\n");
		}
	}
	
	/**
	 * Name: generateChildren
	 * Description: helper method that generates the children of the parent
	 * PreCondition: board must be able to move, and must not be equal to the goal state
	 * PostCondition: inserts the newState into the openQ
	 */
	private void generateChildren(Board board, Board tempBoard, State newState, State parentState, PriorityQueue<State> openQ)
	{
		//check if the board can move
		if(board.moveUp() == true)
		{
			//make a new state with the moved board
			newState = new State(pCost, heuristic(board), board, parentState, "UP");
			
			//if the new state doesn't equal the parent
			if(newState.getBoard().equals(parentState.getBoard()) == false)
			{
				//insert the new state
				openQ.insert(newState); 
			}
		}
		
		//create a new board from the previous move
		board = new Board();
		board.copyBoard(tempBoard);
		
		//check if the board can move
		if(board.moveLeft() == true)
		{
			//make a new state with the moved board
			newState = new State(pCost, heuristic(board), board, parentState, "LEFT");
			
			//if the new state doesn't equal the parent
			if(newState.getBoard().equals(parentState.getBoard()) == false)
			{
				//insert the new state
				openQ.insert(newState);
			}
		}
		
		//create a new board from the previous move
		board = new Board();
		board.copyBoard(tempBoard);
		
		//check if the board can move
		if(board.moveDown() == true)
		{
			//make a new state with the moved board
			newState = new State(pCost, heuristic(board), board, parentState, "DOWN");
			
			//if the new state doesn't equal the parent
			if(newState.getBoard().equals(parentState.getBoard()) == false)
			{
				//insert the new state
				openQ.insert(newState); 
			}
		}
		
		//create a new board from the previous move
		board = new Board();
		board.copyBoard(tempBoard);
		
		//check if the board can move
		if(board.moveRight() == true) //check if the board can move
		{
			//make a new state with the moved board
			newState = new State(pCost, heuristic(board), board, parentState, "RIGHT");
			
			//if the new state doesn't equal the parent
			if(newState.getBoard().equals(parentState.getBoard()) == false)
			{
				//insert the new state
				openQ.insert(newState);
			}
		}
	}

	/**
	 * Name: heuristic
	 * Description: calculates how many tiles are out of place compared to the goal state
	 * PreCondition: none
	 * PostCondition: returns the tiles out of place
	 */
	private int heuristic(Board puzzleBoard)
	{
		//initialize variable
		int tileOutOfPlace = 0;
		
		//for through the 2d array one index at a time
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				//set the value of each index
				int value = puzzleBoard.getGameBoard()[row][col];
				
				//set the goal value of each index
				int goalValue = goalBoard.getGameBoard()[row][col];
				
				// check to see how many tiles are out of place compared to goal state
				if(value != goalValue && value != 0) 
				{
					//add one
					tileOutOfPlace++;
				}
			}
		}
		// return the tiles out of place
		return tileOutOfPlace; 
	}
}
