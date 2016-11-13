/**
* File:    BinarySearchTree.java
* Project: 2
* Author:  Dave Van
* Date:    3/15/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/

package proj2;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	// Basic node stored in unbalanced binary search trees
	private static class TreeNode<AnyType>
	{
		AnyType key; //the key of the node
		TreeNode<AnyType> left;  //left subtree node
		TreeNode<AnyType> right; //right subtree node
		int size; //number of nodes in tree
		
		// Constructors
		TreeNode(AnyType key)
		{
			this.key = key;
			this.left = null;
			this.right = null;
			this.size = 1;
		}
	}
	
	//a queue
	private class BSTQueue
	{
		//a node of a queue
		private class Node
		{
			TreeNode<AnyType> item; //the node
			Node next; //the next item
		}
		
		private Node head = null;
		private Node tail = null;
		
		/**
		 * Name: enqueue
		 * Description: adds the item to the back of the list
		 * PreCondition: none
		 * PostCondition: adds the item into the list
		 */
		void enqueue(TreeNode<AnyType> node)
		{
			Node newTail = new Node(); //make a new node
			newTail.item = node; //set the tail to the node
			
			if(head == null) //if queue is empty
			{
				head = newTail; //make the node the head
				tail = newTail; //make the node the tail
			}
			else //queue is not empty
			{
				tail.next = newTail; //add node to queue
				tail = newTail; //make it the new tail
			}
		} //end enqueue
		
		/**
		 * Name: dequeue
		 * Description: returns and removes the first item of the list
		 * PreCondition: list must not be empty
		 * PostCondition: returns the first item of the list
		 */
		TreeNode<AnyType> dequeue()
		{
			TreeNode<AnyType> firstItem = head.item; //get the first time of queue
			head = head.next; //get the next item and make it the new head
			
			if(head == null) //if head is empty then queue is empty
				tail = null; // make tail null
			
			return firstItem; //return the first item
		} //end dequeue
		
		boolean isEmpty()
		{
			return (head == null); //True if null, False if not null
		}
	}
	
	/** The tree root. */
	private TreeNode<AnyType> root;
	
	/**
     * Construct the tree.
     */
	public BinarySearchTree()
	{
		root = null; //first root is null
	} //end BinarySearchTree()
	
	/**
    * Insert into the tree; duplicates are ignored.
    * @param x the item to insert.
    */
	public void insert(AnyType x)
	{
		root = insert(x, root);
	} //end insert(AnyType)
	
	/**
     * gets the height of the tree
     * @return the height of the tree
     */
	public int height()
	{
		return height(root); //calls overloaded height(TreeNode)
	} //end height()
	
	/**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
     * calls the overloaded method to print the level order
     */
	public void printLevelOrder()
	{
		printLevelOrder(root); //calls overloaded printLevelOrder(TreeNode)
	} //end printLevelTree()

	/**
	 * Name: printLevelOrder
	 * Description: prints the tree in level order traversal
	 * PreCondition: root must not be null
	 * PostCondition: prints out the tree in level order
	 */
	private void printLevelOrder(TreeNode<AnyType> root)
	{
		int nodesInCurrentLevel = 1; //counter for current nodes in a level
		int nodesInNextLevel = 0; //counter for nodes in the next level
		
		if(isEmpty()) //if the root is empty
		{
			System.out.println("Tree is empty!! You need to insert things!!"); //return nothing
		} //end if statement
		BSTQueue queue = new BSTQueue(); //create a queue
		queue.enqueue(root); //enqueue the root of the tree
		while(!queue.isEmpty()) //while queue is full
		{
			TreeNode<AnyType> currNode = queue.dequeue(); //dequeue the first item
			nodesInCurrentLevel--; //minus 1 from counter
			if(currNode != null) //if the current node is not empty
			{
				System.out.print(currNode.key + " "); //print the node
				queue.enqueue(currNode.left); //enqueue the left child
				queue.enqueue(currNode.right); //enqueue the right child
				nodesInNextLevel += 2; //add 2 to the counter
			} //end if statement
			if(nodesInCurrentLevel == 0) //if there are no more nodes in the level
			{
				System.out.println(); //print a new line
				nodesInCurrentLevel = nodesInNextLevel; //set current level nodes with the next level's
				nodesInNextLevel = 0; //reset counter to zero
			}//end if statement
		} //end while
	} //end printLevelOrder(TreeNode)
	
	/**
	 * Name: size
	 * Description: the size of the node
	 * PreCondition: none
	 * PostCondition: returns the size of the node
	 */
	public int size()
	{
		return root.size; //returns the size of the tree
	} //end size()
	
	/**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
	private int height(TreeNode<AnyType> t)
	{
		if(t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	} //end height(TreeNode)
	
	/**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
	private TreeNode<AnyType> insert(AnyType x, TreeNode<AnyType> t)
	{
		if(t == null)
			return new TreeNode<AnyType>(x);
		
				int compareResult = x.compareTo(t.key);
		
		if( compareResult < 0 )
            t.left = insert(x, t.left);
        else if( compareResult > 0 )
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
		t.size++; //add one to size
		
		return t;
	} //end insert(AnyType, TreeNode)
}
