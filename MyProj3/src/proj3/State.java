/**
* File:    State.java
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
public class State implements Comparable<State>
{
	//initialize instance variables
	private String move;
	private Board board = null;
	private int pCost;
	private int hCost;
	private int totalCost;
	private State parent = null;
	
	/**
	 * Name: State
	 * Description: creates a State
	 */
	public State(int pCost, int hCost, Board board, State parent, String move)
	{
		this.board = board;
		this.hCost = hCost;
		this.pCost = pCost;
		this.totalCost = pCost + hCost;
		this.parent = parent;
		this.move = move;
	}
	
	/**
	 * Name: compareTo
	 * Description: compares the total cost f(n) = g(n) + h(n)
	 * PreCondition: none
	 * PostCondition: returns either greater than, less than, or equals. 
	 */
	public int compareTo(State obj)
	{
		State state = (State) obj;
		
		if(this.totalCost > state.totalCost)
		{
			return 1;
		}
		else if(this.totalCost < state.totalCost)
		{
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Name: getMove
	 * Description: gets the move
	 * PreCondition: none
	 * PostCondition: returns move
	 */
	public String getMove()
	{
		return move;
	}
	
	/**
	 * Name: getParent
	 * Description: gets the parent
	 * PreCondition: none
	 * PostCondition: returns parent
	 */
	public State getParent()
	{
		return parent;
	}
	
	/**
	 * Name: getBoard
	 * Description: gets the board
	 * PreCondition: none
	 * PostCondition: returns board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Name: getPathCost
	 * Description: gets the path cost
	 * PreCondition: none
	 * PostCondition: returns pCost
	 */
	public int getPathCost()
	{
		return pCost;
	}
	
	/**
	 * Name: getHeuristicCost
	 * Description: gets the heuristic cost
	 * PreCondition: none
	 * PostCondition: returns hCost
	 */
	public int getHeuristicCost()
	{
		return hCost;
	}
	
	/**
	 * Name: getTotalCost
	 * Description: gets the total cost
	 * PreCondition: none
	 * PostCondition: returns totalCost
	 */
	public int getTotalCost()
	{
		return totalCost;
	}
	
	/**
	 * Name: printState
	 * Description: prints out the board
	 * PreCondition: none
	 * PostCondition: prints the board out
	 */
	public void printState()
	{
		this.board.printBoard();
	}
}
