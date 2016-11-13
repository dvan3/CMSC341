/**
* File:    AListElement.java
* Project: One
* Author:  Dave Van
* Date:    2/14/11
* Section: 03
* E-mail:  dvan3@umbc.edu
* Class invariant: 1. AListElement must not be deleted already.
*/
package proj1;

/**
 * @author Dave
 * @param <T>
 *
 */
public class AListElement<K, V> 
{
	//instance variables
	private K key;
	private V value;
	private boolean deleted;

	//constructor
	public AListElement(K key, V value)
	{
		this.key = key;
		this.value = value;
		this.deleted = false;
	}
	
	/**
	 * Name: getKey
	 * Description: gets the key of the element
	 * PreCondition: none
	 * PostCondition: returns the key
	 */
	public K getKey()
	{
		return key;
	}
	
	/**
	 * Name: getValue
	 * Description: gets the value of the element
	 * PreCondition: none
	 * PostCondition: returns the value
	 */
	public V getValue()
	{
		return value;
	}
	
	/**
	 * Name: getDeleted 
	 * Description: gets the status of the element
	 * PreCondition: none
	 * PostCondition: returns the status
	 */
	public boolean getDeleted()
	{
		return deleted;
	}
	
	/**
	 * Name: setDeleted
	 * Description: changes the status of the element to deleted
	 * PreCondition: none
	 * PostCondition: returns the new status
	 */
	public boolean setDeleted()
	{
		deleted = true;
		return deleted;
	}
	
	/**
	 * Name: toString 
	 * Description: prints out the key and value
	 * PreCondition: none
	 * PostCondition: returns the key and value in a String.
	 */
	public String toString()
	{
		return getKey() + ":" + getValue();
	}
	
//  ***********************UNIT TESTING************************
	public static void main(String[] args) 
	{
		AListElement<String, Integer> test = new AListElement<String, Integer>("Dave", 20);
		AListElement<Integer, String> test2 = new AListElement<Integer, String>(20, "Dave");
		System.out.println(test.toString());
		System.out.println(test2.toString());
	}
	
}
