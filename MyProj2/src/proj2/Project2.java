/**
* File:    Project2.java
* Project: 2
* Author:  Dave Van
* Date:    3/24/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/
package proj2;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Dave
 *
 */
public class Project2 
{

	public static void main(String[] args) throws CommandLineErrorException, IOException 
	{
		// TODO Auto-generated method stub
		try
		{
			@SuppressWarnings("unused")
			int depth = Integer.parseInt(args[0]); //stores the depth of the tree
			String commandFile = args[1]; //stores the file
			
			BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>(); //create a BST object
			RandomizedBinarySearchTree<Integer> RBST = new RandomizedBinarySearchTree<Integer>(); //create a RBST object
			
			Scanner fileReader = new Scanner(new FileInputStream(commandFile)); //read the file
			
			if(args.length > 2) //throw an exception if more than 2 commandline arguments
			{
				throw new CommandLineErrorException("There are too many command line arguments. Please input 1 only.");
			}
			
			while(fileReader.hasNext()) //while there is a character still
			{
				String line = fileReader.nextLine(); //store the line the fileReader read
				
				String [] comParts = line.split(" "); //split each character with a space
				
				for(int i = 0; i < comParts.length; i++)
				{
					BST.insert(Integer.parseInt(comParts[i])); //insert each integer in the BST
					RBST.randomizedInsert(Integer.parseInt(comParts[i])); //insert each integer in the RBST
				}
			}
			System.out.println("The Level of each tree is 0-height" + "\n");
			
			System.out.println("Height of the normal BST: " + BST.height() + "\n"); //print the height of the tree
			System.out.println("Level order print of the normal BST"); //print the level order of the tree
			BST.printLevelOrder();
			
			System.out.println("-------------------------------------------------");
			
			System.out.println("Height of the randomized BST: " + RBST.height() + "\n"); //print the height of the tree
			System.out.println("Level order print of the randomized BST"); 
			RBST.printLevelOrder(); //print the level order of the tree
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
