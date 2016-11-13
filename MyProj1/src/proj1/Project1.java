/**
* File:    Project1.java
* Project: One
* Author:  Dave Van
* Date:    2/14/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/
package proj1;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * @author Dave
 * 
 *
 */
public class Project1
{
	/**
	 * @throws FileNotFoundException 
	 * @throws CommandLineErrorException 
	 * @throws printNothingException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws FileNotFoundException, CommandLineErrorException, PrintNothingException 
	{
		// TODO Auto-generated method stub
		
		//Check for exceptions
		try
		{
			//setting variables to each command line argument
			String firstCommand = args[0];
			int secCommand = Integer.parseInt(args[1]);
			String commandFile = args[2];
		
			//creates a new list to store AList objects
			AList [] mainList = new AList[secCommand];
		
			//making a scanner with a file to scans
			Scanner fileReader = new Scanner(new FileInputStream(commandFile));
		
			//if there are more than 3 command line arguments, throw exception
			if(args.length > 3)
			{
				throw new CommandLineErrorException("There are too many command line arguments. Please input 3 only.");
			}
		
			//keep reading file while it has a next line
			while(fileReader.hasNext())
			{
				try
				{
					//variable for reading the file
					String line = fileReader.nextLine();
				
					//splits the command lines into an array
					String [] comParts = line.split(" ");
					
					//if command line is INSERT, insert the key and value
					if(comParts[0].equals("INSERT"))
					{	
						System.out.println(line);
						int mainListIdx = Integer.parseInt(comParts[1]);
						
						//if the first command line is string-int 
						if(firstCommand.equals("string-int"))
						{
							//insert as String as key and Integer as value
							String key = comParts[2];
							int value = Integer.parseInt(comParts[3]);
							
							//if the list already exist, insert there
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].insert(key, value);
							}
							else
							{
								//if list doesn't exist, create a new list and insert there
								AList<String, Integer> aList = new AList<String, Integer>();
								aList.insert(key, value);
								mainList[mainListIdx] = aList;
							}
						}
						//if the first command line is int-string 
						else if(firstCommand.equals("int-string"))
						{
							//insert as Integer as key and String as value
							int key = Integer.parseInt(comParts[2]);
							String value = comParts[3];
							
							//if the list already exist, insert there 
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].insert(key, value);
							}
							else
							{
								//if list doesn't exist, create a new list and insert there
								AList<Integer, String> aList = new AList<Integer, String>();
								aList.insert(key, value);
								mainList[mainListIdx] = aList;
							}
						}
					}
					//if the command line is FIND, find the key given
					else if(comParts[0].equals("FIND"))
					{
						System.out.println(line);
						int mainListIdx = Integer.parseInt(comParts[1]);
						
						//if first command line is string-int, search for a String type key
						if(firstCommand.equals("string-int"))
						{
							String key = comParts[2];
							
							//if list exist, find the key
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].find(key);
							}
						}
						//if first command line is int-string, search for a Integer type key
						if(firstCommand.equals("int-string"))
						{
							int key = Integer.parseInt(comParts[2]);
							
							//if list exist, find the key
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].find(key);
							}
						}
					}
					//if command line is REMOVE, remove the key given
					else if(comParts[0].equals("REMOVE"))
					{
						System.out.println(line);
						int mainListIdx = Integer.parseInt(comParts[1]);
						
						//if first command is string-int, remove a type string key
						if(firstCommand.equals("string-int"))
						{
							String key = comParts[2];
							
							//if list exist, remove the key
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].remove(key);
							}
						}
						//if first command is int-string, remove type Integer key
						if(firstCommand.equals("int-string"))
						{
							int key = Integer.parseInt(comParts[2]);
							
							//if list exist, remove the key
							if(mainList[mainListIdx] != null)
							{
								mainList[mainListIdx].remove(key);
							}
						}
					}
					//if command line is PRINT, print the key and value from the given list
					else if(comParts[0].equals("PRINT"))
					{
						System.out.println(line);
						int mainListIdx = Integer.parseInt(comParts[1]);
						
						//if the list exist, print it
						if(mainList[mainListIdx] != null)
						{
							System.out.println(mainList[mainListIdx].print());
						}	
						else
						{
							//throw exception if list doesn't exist
							throw new PrintNothingException("The index you are trying to print doesn't exist");
						}
					}
					//if command line is UNION, add the same elements together
					else if(comParts[0].equals("UNION"))
					{
						System.out.println(comParts[0]);
						int mainListIdx = Integer.parseInt(comParts[1]);
						int idx1 = Integer.parseInt(comParts[2]);
						int idx2 = Integer.parseInt(comParts[3]);
						
						//if string-int, union key as String and value as Integer
						if(firstCommand.equals("string-int"))
						{
							AList<String, Integer> list1 = mainList[idx1];
							AList<String, Integer> list2 = mainList[idx2];
							AList<String, Integer> unionList = list1.union(list2);
							mainList[mainListIdx] = unionList;
						}
						//if int-string, union key as Integer and value as String
						else if(firstCommand.equals("int-string"))
						{
							AList<Integer, String> list1 = mainList[idx1];
							AList<Integer, String> list2 = mainList[idx2];
							AList<Integer, String> unionList = list1.union(list2);
							mainList[mainListIdx] = unionList;
						}
					}
					//if command line is INTERSECTION, add the same elements together
					else if(comParts[0].equals("INTERSECTION"))
					{
						System.out.println(comParts[0]);
						int mainListIdx = Integer.parseInt(comParts[1]);
						int idx1 = Integer.parseInt(comParts[2]);
						int idx2 = Integer.parseInt(comParts[3]);
						
						//if string-int, union key as String and value as Integer
						if(firstCommand.equals("string-int"))
						{
							AList<String, Integer> list1 = mainList[idx1];
							AList<String, Integer> list2 = mainList[idx2];
							AList<String, Integer> intersectionList = list1.intersection(list2);
							mainList[mainListIdx] = intersectionList;
						}
						//if int-string, union key as Integer and value as String
						else if(firstCommand.equals("int-string"))
						{
							AList<Integer, String> list1 = mainList[idx1];
							AList<Integer, String> list2 = mainList[idx2];
							AList<Integer, String> intersectionList = list1.intersection(list2);
							mainList[mainListIdx] = intersectionList;
						}
					}	
					//if command line is DIFFERENCE, add the same elements together
					else if(comParts[0].equals("DIFFERENCE"))
					{
						System.out.println(comParts[0]);
						int mainListIdx = Integer.parseInt(comParts[1]);
						int idx1 = Integer.parseInt(comParts[2]);
						int idx2 = Integer.parseInt(comParts[3]);
						
						//if string-int, union key as String and value as Integer
						if(firstCommand.equals("string-int"))
						{
							AList<String, Integer> list1 = mainList[idx1];
							AList<String, Integer> list2 = mainList[idx2];
							AList<String, Integer> differenceList = list1.difference(list2);
							mainList[mainListIdx] = differenceList;
						}
						//if int-string, union key as Integer and value as String
						else if(firstCommand.equals("int-string"))
						{
							AList<Integer, String> list1 = mainList[idx1];
							AList<Integer, String> list2 = mainList[idx2];
							AList<Integer, String> differenceList = list1.difference(list2);
							mainList[mainListIdx] = differenceList;
						}
					}
				}
				catch(KeyExistException e)
				{
					System.out.println(e.getMessage());
				}
				catch(KeyNonexistanceException e)
				{
					System.out.println(e.getMessage());
				}
				catch(KeyAreSameException e)
				{
					System.out.println(e.getMessage());
				}
				catch(PrintNothingException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		catch(CommandLineErrorException e)
		{
			System.out.println(e.getMessage());
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
