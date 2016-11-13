/**
* File:    QuadraticProbingHashTable.java
* Project: 4
* Author:  Dave Van
* Date:    5/2/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/
package proj4;

import java.util.ArrayList;

/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 * @param <AnyType>
 */
public class QuadraticProbingHashTable<AnyType>
{
	private ArrayList<HashEntry<AnyType>> list = new ArrayList<HashEntry<AnyType>>(); //arrayList to be sorted
	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	private HashEntry<AnyType> [] array; // The array of elements
    private int currentSize;             // The number of occupied cells
    private int insertCount = 0;         // insert count
    private float probeCount = 0;		 // probe count
    private float firstAverage;			 // first average
    private float secondAverage;         // second average
    
    /**
     * Construct the hash table.
     */
    public QuadraticProbingHashTable()
    {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public QuadraticProbingHashTable(int size)
    {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param word the item to insert.
     */
	public void insert(AnyType word)
    {
		this.probeCount++;  //adds one to probe count
		this.insertCount++; //adds one to insert count
		
            // Insert x as active
        int currentPos = findPos(word);
        
        //if duplicate, change the frequency
        if(isActive(currentPos))
        {
        	this.probeCount++;  //if duplicate, add probe count
        	this.array[currentPos].count++;  //add to the count of the word
        	return;
        }
        
        //if element doesn't exist, make a new one
        array[currentPos] = new HashEntry<AnyType>(word, true);
        
        //if the load factor is .25
        if(currentSize == array.length / 4)
        {
        	//calculate the probe average
        	firstAverage = this.probeCount / this.insertCount;
        	
        	//resets the insert count to zero
        	resetInsertCount();
        	
        	//resets the probe count to zero
            resetProbeCount();
        }
        
        // Rehash; see Section 5.5
        //the load factor is .50
        if(++currentSize > array.length / 2)
        {
        	//calculate the probe average
        	secondAverage = this.probeCount / this.insertCount;
        	
        	//print out the statistics of probe count
        	this.print();
        	
        	//reset insert count
        	resetInsertCount();
        	
        	//reset probe count
    		resetProbeCount();
    		
    		//rehash
            rehash();
        }
    }
	
	/**
	 * Overloaded method insert
	 * Helps keep count of frequency when rehashing
	 */
	private void insert(AnyType word, int count)
	{
		this.probeCount++;
		this.insertCount++;
		int currentPos = findPos(word);
		
		array[currentPos] = new HashEntry<AnyType>(word, true);
		array[currentPos].setCount(count);
		currentSize++;
	}
	
	 /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param word the item to insert.
     */
    public void insertExclude( AnyType word )
    {
            // Insert x as active
        int currentPos = findPos( word );
        if( isActive( currentPos ) )
        {
        	this.array[currentPos].count++;
        	return;
        }

        array[ currentPos ] = new HashEntry<AnyType>( word, true );

            // Rehash; see Section 5.5
        if( ++currentSize > array.length / 2 )
            rehash( );
    }

    /**
     * Expand the hash table.
     */
    private void rehash()
    {
        HashEntry<AnyType> [] oldArray = array;
        
            // Create a new double-sized, empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;
        
            // Copy table over
        for(int i = 0; i < oldArray.length; i++)
            if(oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].getWord(), oldArray[i].count);
    }

    /**
     * Method that performs quadratic probing resolution.
     * Assumes table is at least half empty and table length is prime.
     * @param word the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos(AnyType word)
    {
        int offset = 1;
        int currentPos = myhash(word);
		
        while(array[currentPos] != null &&
                !array[currentPos].word.equals(word))
        {
        	probeCount++; //increment by 1(collision)
            currentPos += offset;  // Compute ith probe
            offset += 2;
            if(currentPos >= array.length)
                currentPos -= array.length;
        }
		
        return currentPos;
    }
    
    /**
     * adds from the hashtable to the arrayList
     * then sorts the list with selection sort
     */
    public void sort()
    {
    	//for every element in the array
    	for(int i = 0; i < array.length; i++)
    	{
    		//if null
    		if(array[i] == null)
    		{
    			;//do nothing
    		}
    		else
    		{
    			//add element into arrayList
    			list.add(array[i]);
    		}
    	}
    	
    	//sort the list by descending order
    	selectionSort(list);
    }
    
    /**
     * sorts the list by descending order
     */
    private void selectionSort(ArrayList<HashEntry<AnyType>> list)
    {
    	//store the size of the arrayList
    	int size = list.size();
    	
    	//go through the whole list
    	for(int i = 0; i < size; i++)
    	{
    		//find the largest value's index
    		int largestIdx = findLargest(list, i, size);

    		//create a temp element
    		HashEntry<AnyType> temp = list.get(largestIdx);
    		
    		//switch the elements with the index
    		list.set(largestIdx, list.get(i));
    		
    		//set the top value to the top of the arrayList
    		list.set(i, temp);
    	}
    }
    
    /**
     * Finds the largest index and value
     * @param the list to search through
     * @param the starting index
     * @param the stopping index
     * @return the largest index
     */
    private int findLargest(ArrayList<HashEntry<AnyType>> list, int start, int stop)
    {
    	//get the starting index
    	int largestIdx = start;
    	
    	//gets the value of the starting value
    	int largestValue = list.get(start).count;

    	//go through the arrayList
    	for(int i = start + 1; i < list.size(); i++)
    	{
    		//if a count of a word is larger than the current value
    		if(list.get(i).count > largestValue)
    		{
    			//set the element's index
    			largestIdx = i;
    			
    			//set the value
    			largestValue = list.get(i).count;
    		}
    	}
    	//return the index of the largest value
    	return largestIdx;
    }
    
    /**
	 * Prints the output
	 */
	public void print() 
	{
    	System.out.println("lambda > 0.25     lambda > 0.5     current table size     # distinct words before rehashing");
		System.out.format("    %.3f       ", firstAverage);
		System.out.format("        %.3f              ", secondAverage);
		System.out.println("  " + array.length + "                     " + currentSize + "\n");
	}
	
	/**
	 * Prints the final output of the averages
	 */
	public void printFinal()
	{
		System.out.println("lambda > 0.25     lambda > 0.5     current table size     # distinct words before rehashing");
		System.out.format("    %.3f       ", firstAverage);
		System.out.format("        --              ");
		System.out.println("  " + array.length + "                     " + currentSize + "\n");
	}
	
	/**
     * prints out the top twenty words and there frequencies
     * @param number of words included
     */
    public void printTopTwenty(float included)
    {
    	//for the top twenty elements
    	for(int i = 0; i < 20; i++)
    	{
    		//calculate the frequency
    		float average = list.get(i).count / included;
    		
    		//print out the word and the frequency
    		System.out.format("%-15s %.5f\n", list.get(i).getWord(), average);
    	}
    }
    
    public void printTable()
    {
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i] == null)
    		{
    			;
    		}
    		else
    		{
    			System.out.println(array[i].toString());
    		}
    	}
    }

    /**
     * Find an item in the hash table.
     * @param word the item to search for.
     * @return the matching item.
     */
    public boolean contains(AnyType word)
    {
        int currentPos = findPos(word);
        return isActive(currentPos);
    }
    
    /**
     * Gets the length of the array
     * @return the length of the array
     */
    public int getLength()
    {
    	return array.length;
    }
    
    /**
     * Resets the count of the probes to zero
     * @return probeCount to zero
     */
    public float resetProbeCount()
    {
    	return probeCount = 0;
    }
    
    /**
     * Resets the count of the attempted inserts to zero
     * @return insertCount to zero
     */
    public int resetInsertCount()
    {
    	return insertCount = 0;
    }
    
    /**
     * Gets the count of probes
     * @return the count of probes
     */
    public float getProbeCount()
    {
    	return probeCount;
    }

    /**
     * Gets the size of the array
     * @return the size of the array
     */
    public int size()
    {
    	return currentSize;
    }
    
    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive(int currentPos)
    {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty()
    {
        currentSize = 0;
        for(int i = 0; i < array.length; i++)
            array[i] = null;
    }

    private int myhash(AnyType word)
    {
        int hashVal = word.hashCode();

        hashVal %= array.length;
        if(hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
     @SuppressWarnings("unchecked")
    private void allocateArray(int arraySize)
    {
        array = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n)
    {
        if(n <= 0)
            n = 3;

        if(n % 2 == 0)
            n++;

        for(;!isPrime( n ); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
            return true;

        if(n == 1 || n % 2 == 0)
            return false;

        for(int i = 3; i * i <= n; i += 2)
            if( n % i == 0 )
                return false;

        return true;
    }
    
    //PRIVATE CLASS************************************************************
    private static class HashEntry<AnyType>
    {
        private AnyType  word;     // the word
        private int count;         //the frequency
        private boolean isActive;  // false if marked deleted
        
        /**
         * Construct the HashEntry.
         */
        public HashEntry(AnyType word, boolean active)
        {
        	this.word = word;
        	this.count = 1;
        	this.isActive = active;
        }

        /**
         * Gets the word
         * @return the word
         */
        public AnyType getWord()
        {
        	return word;
        }
        
        /**
         * a mutator that sets the count
         * @param count to set
         * @return the new count
         */
        public int setCount(int count)
        {
        	return this.count = count;
        }
        
        /**
         *  override method that prints out the word and count
         *  @return the string of word and count
         */
        public String toString()
        {
        	return this.word + " : " + this.count;
        }
        
        @SuppressWarnings("unchecked")
		public boolean equals(Object o)
        {
        	HashEntry<AnyType> entry = (HashEntry<AnyType>) o;
        	
        	return (entry.getWord() == word) && (entry.count == count);
        }
    }
}
