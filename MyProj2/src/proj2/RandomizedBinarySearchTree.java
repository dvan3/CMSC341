/**
* File:    RandomizedBinarySearchTree.java
* Project: 2
* Author:  Dave Van
* Date:    3/28/11
* Section: 03
* E-mail:  dvan3@umbc.edu
*/

package proj2;

import java.util.Random;

/**
 * @author Dave
 *
 */
public class RandomizedBinarySearchTree<AnyType extends Comparable<? super AnyType>>
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
		
		TreeNode()
		{
			this.key = null;
			this.right = null;
			this.left = null;
			this.size = 1;
		}
	}
	
	//a queue
	private class RBSTQueue
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
	
	public RandomizedBinarySearchTree()
	{
		root = null; //first root is null
	} //end BinarySearchTree()
	
	/**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
	public boolean isEmpty()
	{
		return root == null;
	} //end isEmpty()
	
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
		}
		RBSTQueue queue = new RBSTQueue(); //create a queue
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
			} 
			if(nodesInCurrentLevel == 0) //if there are no more nodes in the level
			{
				System.out.println(); //print a new line
				nodesInCurrentLevel = nodesInNextLevel; //set current level nodes with the next level's
				nodesInNextLevel = 0; //reset counter to zero
			}
		} 
	} //end printLevelOrder(TreeNode)
	
	/**
	 * Name: size
	 * Description: the size of the node
	 * PreCondition: none
	 * PostCondition: returns the size of the tree
	 */
	public int size()
	{
		return size(root); //returns the size of the tree
	} //end size()
	
	/**
	 * Name: size
	 * Description: the size of the node
	 * PreCondition: the node must not be null
	 * PostCondition: returns the size of the node
	 */
	private int size(TreeNode<AnyType> t)
	{
		return (t == null)? 0 : t.size;
	}
	
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
     * gets the height of the tree
     * @return the height of the tree
     */
	public int height()
	{
		return height(root); //calls overloaded height(TreeNode)
	} //end height()
	
	/**
	 * cInsert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */
	public void randomizedInsert(AnyType x)
	{
		root = randomizedInsert(x, root);
	}
	
	/**
	 * Name: randomizedInsert
	 * Description: randomly insert a node at root or split into the tree
	 * PreCondition: the node must not be null
	 * PostCondition: returns the node T
	 */
	private TreeNode<AnyType> randomizedInsert(AnyType x, TreeNode<AnyType> T)
	{
		if(T == null)
		{
			return new TreeNode<AnyType>(x); //if no root node, make a new root node
		}
		else{
			int n = T.size; //stores the size of the tree
			Random rand = new Random(123456789); //make a random generator
			int randomNum = rand.nextInt(n + 1); //get a random number
			
			if(randomNum == n)
			{
				return insertAtRoot(x, T); //if the random number is equal to tree size, call method insertAtRoot
			}
			else //random number is not equal to tree size
			{
				int compareResult = x.compareTo(T.key); //compares x to the node key
				
				if(compareResult < 0)
				{
					T.left = randomizedInsert(x, T.left); //if x is smaller than node key, call randomizedInsert to the left
				}
				else if(compareResult > 0)
				{
					T.right = randomizedInsert(x, T.right); //if x is bigger than node key, call randomizedInsert to the right
				}
			}
			T.size++; //add one to the size of the tree
		}
		
		return T; //return the tree
	} //end randomizedInsert(AnyType, TreeNode)
	
	/**
	 * Name: insertAtRoot
	 * Description: inserts the node at the root
	 * PreCondition: L and R must not bee null
	 * PostCondition: returns the new root node
	 */
	private TreeNode<AnyType> insertAtRoot(AnyType x, TreeNode<AnyType> T)
	{
		TreeNode<AnyType> L = new TreeNode<AnyType>(); //create a node L
		TreeNode<AnyType> R = new TreeNode<AnyType>(); //create a node R
		
		split(x, T, L, R); //split the tree
		if(L.key == null)
		{
			L = null; //if the left key is null make L null
		}
		if(R.key == null)
		{
			R = null; //if the right key is null make R null
		}
		TreeNode<AnyType> newNode = new TreeNode<AnyType>(x); //create a new node
		newNode.key = x; //set x to the new node's key
		newNode.left = L; //set the left node to L
		newNode.right = R; //set the right node to R
		newNode.size = 1 + size(L) + size(R); //add 1 + the size of L and R
		
		return newNode; //return the new node
	} //end insertAtRoot(int, TreeNode)
	
	/**
	 * Name: split
	 * Description: splits the tree depending on the root being inserted
	 * PreCondition: the node T must not be null
	 * PostCondition: splits the tree and reattaches the nodes back 
	 *                together after inserting the new root
	 */
	private void split(AnyType x, TreeNode<AnyType> T, TreeNode<AnyType> L, TreeNode<AnyType> R)
	{
		TreeNode<AnyType> rNode = new TreeNode<AnyType>(); //create a right null node
		TreeNode<AnyType> lNode = new TreeNode<AnyType>(); //create a left null node
		
		if(T != null)
		{
			int compareResult = x.compareTo(T.key); //if T is not null compare x to the key of T
			
			if(compareResult < 0) //if x is smaller than key of T
			{
				R.size = T.size; //set R size to T size
				R.key = T.key; //set R key to T key
				R.left = T.left; //set R left to T left
				R.right = T.right; //set R right to T right
				split(x, T.left, L, rNode); //recursively call split to the left of T this time
				if(rNode.key == null)
				{
					R.left = null; //if the key of rNode is null make the left child of R null
				}
				else
				{
					R.left = rNode; //else set R left to rNode
				}
				R.size = 1 + size(R.left) + size(R.right);
			}
			else if(compareResult > 0)
			{
				L.size = T.size; //set L size to T size
				L.key = T.key; //set L key to T key
				L.left = T.left; //set L left to T left
				L.right = T.right; //set L right to T right
				split(x, T.right, lNode, R); //recursively call split to the right of T this time
				if(lNode.key == null)
				{
					L.right = null; //if the key of lNode is null make the right child of R null
				}
				else
				{
					L.right = lNode; //else set L right to lNode
				}
				L.size = 1 + size(L.left) + size(L.right);
			}
		}
	} // end split(AnyType, TreeNode, TreeNode, TreeNode)
}
