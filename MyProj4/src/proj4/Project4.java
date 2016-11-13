/**
* File:    Project4.java
* Project: 4
* Author:  Dave Van
* Date:    5/2/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/

package proj4;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Dave
 *
 */
public class Project4 {
	
	public static void main(String[] args) throws CommandLineErrorException
	{
		try
		{
			//stores the file
			String excludedFile = args[0];
			String inputFile = args[1];
			
			//checks if there are too many command line arguments
			if(args.length > 2)
			{
				throw new CommandLineErrorException("There are too many command line arguments. Please input 1 only.");
			}
			
			//scans both text files
			Scanner mainText = new Scanner(new FileInputStream(inputFile));
			Scanner excludedText = new Scanner(new FileInputStream(excludedFile));
	
			//creates two hashtables; one for excluded words and one for included words
			QuadraticProbingHashTable<String> HT0 = new QuadraticProbingHashTable<String>();
			QuadraticProbingHashTable<String> HT1 = new QuadraticProbingHashTable<String>();
			
			String excludedWords;          //holds an excluded word
			String word;                   //holds an included word
			Integer totalWordCount = 0;    //total word count
			Integer totalExcludeCount = 0; //total excluded word count
	
			//keep reading the file until there aren't more words
			while(excludedText.hasNext())
			{
				//convert the word to lower case and strip the punctuation and numbers
				excludedWords = excludedText.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
				
				//insert into the first hashtable
				HT0.insertExclude(excludedWords);
			}
			
			System.out.println("Average Number of Probing");
			
			//keep reading the file until there aren't more words
			while(mainText.hasNext())
			{
				//convert the word to lower case and strip the punctuation and numbers
				word = mainText.next().toLowerCase().replaceAll("\\W", "").replaceAll("[^a-zA-Z]", "");
				
				//if it is a blank space
				if(word.compareTo("") == 0)
				{
					; //do nothing
				}
				else
				{		
					//if it is an excluded word
					if(HT0.contains(word) == true)
					{
						totalExcludeCount++; //add 1 to excluded count
					}
					else
					{
						HT1.insert(word); //insert into the second hashtable
					}
					totalWordCount++; //add 1 to total word count
				}
			}
			
			float averageWord = (float) 1 / HT1.size();              //calculates average word frequency
			int includedWords = totalWordCount - totalExcludeCount;  //calculates included words
			float includedWords2 = includedWords;                    //temp variable that stores included words to a float
			int size = HT1.size();                                   //gets the size of the second hashtable
			
			HT1.sort(); //sorts the hashtable
			
			//prints the statistics out
			statistics(HT1, totalWordCount, totalExcludeCount, includedWords, includedWords2, size, averageWord);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	//Helper method that prints out the statistics of the hashtable
	private static void statistics(QuadraticProbingHashTable<String> HT1, int totalWordCount, int totalExcludeCount, int includedWords, float includedWords2, int size, float averageWord)
	{
		System.out.println("---------------------------------Final Average----------------------------------");
		HT1.printFinal();
		DecimalFormat averageWordFormat = new DecimalFormat("0.00000");
		System.out.println("Total Words: " + totalWordCount);
		System.out.println("Total Excluded Words: " + totalExcludeCount);
		System.out.println("Total Included Words: " + includedWords);
		System.out.println("Total Distint Words: " + size);
		System.out.println("The Average Word Frequency: " + averageWordFormat.format(averageWord));
		System.out.println("The 20 most frequently occurring words are");
		HT1.printTopTwenty(includedWords2);
	}
}
