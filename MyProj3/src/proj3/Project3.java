/**
* File:    Project3.java
* Project: 3
* Author:  Dave Van
* Date:    4/19/11
* Section: 07
* E-mail:  dvan3@umbc.edu
*/
package proj3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Dave
 *
 */
public class Project3
{
	
	public static void main(String[] args) throws CommandLineErrorException, IOException
	{
		// TODO Auto-generated method stub
		try
		{
			//stores the file
			String commandFile = args[0];
			
			//checks if there are too many command line arguments
			if(args.length > 1)
			{
				throw new CommandLineErrorException("There are too many command line arguments. Please input 1 only.");
			}
		
			//creates two boards; one for intial, one for goal
			Board initialBoard = new Board();
			Board goalBoard = new Board();
			
			StringWriter writer = new StringWriter();
			BufferedReader reader = new BufferedReader(new FileReader(commandFile));
			
			for(String line = reader.readLine(); line != null; line = reader.readLine())
			{
				writer.write(line + " ");
			}
			
			StringTokenizer tokens = new StringTokenizer(writer.toString());
			List<Integer> list = new ArrayList<Integer>();
			List<Integer> list2 = new ArrayList<Integer>();
			
			while(tokens.hasMoreTokens())
			{
				//go through the first 9 elements in the command file
				for(int i = 0; i < 9; i++) 
				{
					String str = tokens.nextToken();
					
					//add the first 9 into an array list
					list.add(new Integer(str));
				}
				
				//go through the last 9 elements in the command file
				for(int i = 9; i < 18; i++)
				{
					String str2 = tokens.nextToken();
					
					//add the last 9 into an array list
					list2.add(new Integer(str2));
				}
			}
			
			// create an array
			int [] array = new int[list.size()];
			
			//go through the list 9 times
			for(int i = 0; i < 9; i++)
			{
				//transfer the 9 elements into an array
				array[i] = list.get(i); 
				
				//copy the array to the initial board
				initialBoard.boardFromArray(array); 
			}
			
			//go through the list 9 times
			for(int i = 0; i < 9; i++) //go through the list 9 times
			{
				//transfer the 9 elements into an array
				array[i] = list2.get(i); 
				
				//copy the array to the initial board
				goalBoard.boardFromArray(array); 
			}	
			
			//create a puzzle solver
			PuzzleSolver solver = new PuzzleSolver(initialBoard, goalBoard);
			
			//solve the puzzle
			solver.solvePuzzle();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnderflowException e)
		{
			e.printStackTrace();
		}
	}
}
