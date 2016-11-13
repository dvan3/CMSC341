/**
* File:    Alist.java
* Project: One
* Author:  Dave Van
* Date:    2/14/11
* Section: 03
* E-mail:  dvan3@umbc.edu
* Class Invariant: 1. size must be greater or equal to 0
*/
package proj1;

import java.util.ArrayList;

/**
 * @author Dave
 *
 *
 */
public class AList<K, V> 
{
	//instance variables
	private ArrayList<AListElement<K, V>> aList;
	private int size;
	
	//constructor
	public AList()
	{
		this.aList = new ArrayList<AListElement<K, V>>();
		this.size = 0;
	}

	/**
	 * Name: insert
	 * Description: inserts a key and value into an aList 
	 * PreCondition: there must not be a same key and the key
	 *               must not be flagged as deleted
	 * PostCondition: inserts the key and value into an aList
	 */
	public void insert(K key, V value) throws KeyExistException
	{
		boolean insert = true;
	
		//checks through the aList
		for(AListElement<K, V> element : aList)
		{
			//checks if keys are equal
			if(element.getKey().equals(key))
			{
				//checks if the key isn't deleted
				if(element.getDeleted() == false)
				{
					//throw exception
					insert = false;
					throw new KeyExistException("The key you are trying to insert already exists");
				}
			}
		}
		//if everything has passed then add the AListElement
		if(insert == true)
		{
			aList.add(new AListElement<K, V>(key, value));
			size++;
		}
	}
	
	/**
	 * Name: find
	 * Description: searches the aList for a key and prints out the value
	 * PreCondition: the key must not be flagged as deleted
	 *               and the key is equal to the key given
	 * PostCondition: prints out the value that is associated with the key
	 */
	public void find(K key) throws KeyNonexistanceException
	{
		boolean found = false;
		
		//goes through the aList
		for(AListElement<K, V> element : aList)
		{
			//checks if keys are equal
			if(element.getKey().equals(key))
			{
				//checks if the key is flagged as deleted
				if(element.getDeleted() == false)
				{
					//print out the value
					found = true;
					System.out.println(element.getKey() + " is associated with the value "
							+ element.getValue());
				}
			}
		}
		//if the key was flagged as deleted
		//or if the keys aren't equal throw exception
		if(found == false)
		{
			throw new KeyNonexistanceException("The key you are looking for does not exist");
		}
	}
	
	/**
	 * Name: remove
	 * Description: does a lazy deletion to the selected key 
	 * PreCondition: key must equal the key given
	 * PostCondition: flags the selected key as deleted and
	 *                reduces the size by one
	 */
	public void remove(K key) throws KeyNonexistanceException
	{
		boolean removed = false;
		
		//goes through the aList
		for(AListElement<K, V> element : aList)
		{
			//checks if keys are equal
			if(element.getKey().equals(key))
			{
				//check if element is not deleted
				if(element.getDeleted() == false)
				{
					//if equal flag key as deleted
					removed = true;
					element.setDeleted();
					size--;
				}
			}
		}
		//if keys aren't equal, throw exception
		if(removed == false)
		{
			throw new KeyNonexistanceException("The key you are trying to remove doesn't exist");
		}
	}
	
	/**
	 * Name: union
	 * Description: adds two lists together into a new aList 
	 * PreCondition: elements in the aList must not have the same key and different value
	 * PostCondition: returns the union aList.
	 * @throws KeyAreSameException 
	 * @throws KeyExistException
	 * @throws KeyNonexistanceException 
	 */
	public AList<K, V> union(AList<K, V> otherList) throws KeyExistException, KeyAreSameException, KeyNonexistanceException
	{
		//create a union list
		AList<K, V> unionList = new AList<K, V>();
		
		//add aList elements to the union list
		for(AListElement<K, V> element : this.aList)
		{
			//check if element is not deleted
			if(element.getDeleted() == false)
			{
				unionList.insert(element.getKey(), element.getValue());
			}
		}
		
		//check through otherList elements
		for(AListElement<K, V> element : otherList.aList)
		{
			//check through unionList elements
			for(AListElement<K, V> element2 : unionList.aList)
			{
				//checks if the elements are not deleted
				if(element.getDeleted() == false && element2.getDeleted() == false)
				{
					//if the keys are the same
					if(element.getKey().equals(element2.getKey()))
					{
						//if the values are the same
						if(element.getValue().equals(element2.getValue()))
						{
							//remove the common element from union list
							unionList.remove(element2.getKey());
						}
						else
						{
							throw new KeyAreSameException("Key are the same, values are different");
						}
					}
				}
			}
			
			if(element.getDeleted() == false)
			{
				//insert the rest of the elements from otherList to unionList
				unionList.insert(element.getKey(), element.getValue());
			}
		}
		
		return unionList;
	}
	
	/**
	* Name: difference
	* Description: compares the first list to the second list
	*              creates a new list containing the difference from the first list
	* PreCondition:  elements must not have the same key and different value
	*                elements must not have the same key and same value
	* PostCondition: returns the differenceList
	 * @throws KeyAreSameException 
	 * @throws KeyExistException 
	 * @throws KeyNonexistanceException 
	*/
	public AList<K, V> difference(AList<K, V> otherList) throws KeyAreSameException, KeyExistException, KeyNonexistanceException 
	{
		//create a difference list
		AList<K, V> differenceList = new AList<K, V>();
		
		//add aList elements into the difference list
		for(AListElement<K, V> element : this.aList)
		{
			//check if element is not deleted
			if(element.getDeleted() == false)
			{
				differenceList.insert(element.getKey(), element.getValue());
			}
		}
		
		//check through otherList
		for(AListElement<K, V> element : otherList.aList)
		{
			//check through differenceList
			for(AListElement<K, V> element2 : differenceList.aList)
			{
				//checks if the elements are not deleted
				if(element.getDeleted() == false && element2.getDeleted() == false)
				{
					//if keys are the same
					if(element.getKey().equals(element2.getKey()))
					{
						//if values are the same
						if(element.getValue().equals(element2.getValue()))
						{
							//remove the common element
							differenceList.remove(element2.getKey());
						}
						else
						{
							throw new KeyAreSameException("Key are the same, values are different");
						}
					}
				}
			}
		}
		
		return differenceList;
	}
	
	/**
	 * Name: intersection
	 * Description: adds two lists together into a new aList
	 *              that have common key values
	 * PreCondition: elements in the aList must not have the same key and different value
	 * PostCondition: returns the intersection list
	 * @throws KeyAreSameException 
	 * @throws KeyExistException
	 * @throws KeyNonexistanceException 
	 */
	public AList<K, V> intersection(AList<K, V> otherList) throws KeyExistException, KeyAreSameException, KeyNonexistanceException
	{
		//create an intersection list
		AList<K, V> intersectionList = new AList<K, V>();
	
		//check through aList
		for(AListElement<K, V> element : aList)
		{
			//check through otherList
			for(AListElement<K, V> element2 : otherList.aList)
			{
				//checks if the elements are not deleted
				if(element.getDeleted() == false && element2.getDeleted() == false)
				{
					//if keys are the same
					if(element.getKey().equals(element2.getKey()))
					{
						//if values are the same
						if(element.getValue().equals(element2.getValue()))
						{
							//insert the common elements in otherList
							intersectionList.insert(element2.getKey(), element2.getValue());
						}
						else
						{
							throw new KeyAreSameException("Key are the same, values are different");
						}
					}
				}
			}
		}
		
		return intersectionList;
	}
	
	/**
	 * Name: size
	 * Description: gets the size of the aList
	 * PreCondition: none
	 * PostCondition: returns the size of the aList
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Name: print
	 * Description: prints out the key and value in the aList 
	 * PreCondition: if the key and flagged as deleted print nothing
	 * PostCondition: returns output that has the key and value
	 */
	public String print()
	{
		String output = "";

		//go through all the elements in aList
		for(AListElement<K, V> element : aList )
		{
			//checks if keys are deleted, if deleted, then print nothing
			if(element.getDeleted() == true)
			{
				output += "";
			}
			//if keys aren't deleted, print the key and the associated value
			else
			{
				output += element.toString() + "\n";
			}
		}
		return output;
	}
}
