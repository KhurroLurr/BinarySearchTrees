// Nicholas Espinosa
// PID n2431401
// COP 3503 - 0011
// 02.11.2016
// Recitation Week 5

/*	
 * Author's Node: PLEASE READ
 *  In order to keep track of the subtree size and ranks, I used a 
 *  integer called "element" which tracks the total number of values
 * 	in the BST. If it is determined the values are off, then the 
 * 	rank and subtree size are updated accordingly when you search for them.
 * 	The method in which this is done can be found in the relevant functions.
 */

public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	int kTime = 3, elements;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
		
		// Number of elements is set to the default of 0
		elements = 0;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
 
		// This node is null and simply needs to be allocated.
	    if( objNode == null )
	    {
	    	// Creating an new node in the BST 
	    	objNode = new BSTNode( nKeyValue );
	    	
	    	// Increase the number of elements in the BST
	    	elements++;
	    }
	    // If it is less than the value and passes the k-time test
	    else if( nKeyValue < objNode.GetKeyValue() - kTime)
	    {
	    	// Set the left node of this object by recursively walking left.
	    	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
	    	objNode.GetLeftNode().setParentNode(objNode);
	    }
	        
	     // If it is greater than the value and passes the k-time test
	     else if( nKeyValue > objNode.GetKeyValue() + kTime)
	     {
	    	// Set the right node of this object by recursively walking right.
	    	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
	    	objNode.GetRightNode().setParentNode(objNode);
	     }
	     else
	    	 return null;
	     
	     return objNode;
    }
    
    public void Delete(int nValue)
    {
    	BSTNode ds = Search(nValue);
    	
    	if(ds != null)
    		DeleteHelper(ds);
    }
    
    public void Delete(BSTNode ds)
    {
    	ds = Search(ds.GetKeyValue());
    	
    	if(ds != null)
    		DeleteHelper(ds);
    }
    
    // Deletes the given node
    private void DeleteHelper(BSTNode ds)
    {
    	if(ds == null)
    		return;
    	
    	// Decrease the total number of elements by 1
    	elements--;
    	
    	// The node has no children
        if(ds.GetLeftNode() == null && ds.GetRightNode() == null)
        {
        	// If the node is not the root
        	if(ds.getParentNode() != null)
        	{
	        	// Setting the parent's pointers to null
	        	if(ds.GetKeyValue() > ds.getParentNode().GetKeyValue())
	        		ds.getParentNode().SetRightNode(null);
	        	else
	        		ds.getParentNode().SetLeftNode(null);
        	}
        	else
        		m_objRootNode = null;
        		
        	// Deleting the node
        	ds = null;
        }
        // Two children
        else if(ds.GetLeftNode() != null && ds.GetRightNode() != null)
        {
	        // Replacement node is established
	    	BSTNode rep = ds.GetRightNode();
	    	
	    	while(rep.GetLeftNode() != null)
	    		rep = rep.GetLeftNode();
	    	
	    	// The Key values are replaced
	    	ds.SetKeyValue(rep.GetKeyValue());
	    		
	    	// Deleting the replacement node
	    	if(ds.getParentNode() != null) // Is root node
		    	rep.getParentNode().SetLeftNode(null);
	    	else // Is not root node
	    		ds.SetRightNode(null);
	    		
		    rep = null;
	    }
	    // Has one child
    	else
    	{
    		BSTNode child;
    		
    		// Determining which child is left
    		if(ds.GetLeftNode() != null)
    			child = ds.GetLeftNode();
    		else
    			child = ds.GetRightNode();
    		
    		
    		if(ds.getParentNode() != null)
    		{
    			// Establishing the parent pointer
	    		child.setParentNode(ds.getParentNode());
	    		
	    		// Establishing the pointer to the child
	    		if(ds.GetKeyValue() > ds.getParentNode().GetKeyValue())
	    			ds.getParentNode().SetRightNode(child);
	    		else
	    			ds.getParentNode().SetLeftNode(child);
    		}
    		else
    			m_objRootNode = child;
    		
    		// The current node is deleted
    		ds = null;
    		
    	}
    }
    
    // Find the subtree size of a node with the given value
    public int getSubtreeSize(int nValue)
    {
    	// Determine if the node exists in the BST
    	BSTNode ds = Search(nValue);
    	
    	// If so, return the subtree size
    	if(ds != null)
    		return subtreeHelper(ds);
    	// Otherwise, return a -1
    	else
    		return -1;
    	
    }
    
    // Find subtree size of a given node
    public int getSubtreeSize(BSTNode ds)
    {
    	// Searches for the node in the BST
    	ds = Search(ds.GetKeyValue());
    	
    	// If the node exists, find the subtree size
    	if(ds != null)
    		return subtreeHelper(ds);
    	// Otherwise, return -1
    	else
    		return -1;
    }
    
    // Returns the subtree size of a node
    private int subtreeHelper(BSTNode ds)
    {
    	// If the subtree size is not up to date, update it
    	if(m_objRootNode.getSubtreeSize() != elements)
    		updateSubtreeSize(m_objRootNode);
    	
    	// Return the subtree size of the node
    	return ds.getSubtreeSize();
    }
    
    // Updates the subtree size of all the nodes
    private void updateSubtreeSize(BSTNode ds)
    {
    	if(ds == null)
    		return;
    	
    	// Sets the subtree size of the given node
    	ds.setSubtreeSize(findSubtreeSize(ds));
    	
    	// Updates the subtree size of its children
    	updateSubtreeSize(ds.GetLeftNode());
    	updateSubtreeSize(ds.GetRightNode());
    }
    
    // Finds the subtree size of a node recursively
    private int findSubtreeSize(BSTNode ds)
    {
    	// If there are no more nodes, return 0
    	if(ds == null)
    		return 0;
    	
    	// Return 1 plus the subtree size of each child
    	return 1 + findSubtreeSize(ds.GetLeftNode()) + findSubtreeSize(ds.GetRightNode());
    }
    
    // Find the rank when given a value
    public int getRank(int nValue)
    {
    	// Searches for the node in the BST
    	BSTNode ds = Search(nValue);
    	
    	
    	
    	// If found, find the rank
    	if(ds != null)
    		return rankHelper(ds);
    	// Otherwise, return a negative 1
    	else
    		return -1;
    }
    
    // Find the rank when given a node
    public int getRank(BSTNode ds)
    {
    	// Searches the BST for the node
    	ds = Search(ds.GetKeyValue());
    	
    	// If the node exists, find the rank
    	if(ds != null)
    		return rankHelper(ds);
    	// Otherwise, return a negative 1
    	else
    		return -1;
    }
    
    // Returns the rank of a given node
    private int rankHelper(BSTNode ds)
    {
    	// If the rank is not up to date, update it
    	if(Search(getMax()).getRank() != elements - 1)
    		updateRank(m_objRootNode);
    	
    	// Return the rank of the node
    	return ds.getRank();
    	
    }
    
    // Updates the ranks of all the nodes
    private void updateRank(BSTNode ds)
    {
    	if(ds == null)
    		return;
    	
    	// Set the rank for the current node
    	ds.setRank(findRank(m_objRootNode, ds.GetKeyValue()));
    	
    	// Calls the update for subsequent nodes
    	updateRank(ds.GetLeftNode());
    	updateRank(ds.GetRightNode());
    }
    
    // Recursively finds the rank
    private int findRank(BSTNode ds, int nValue)
    {
    	// If the node is null, return 0
    	if(ds == null)
    		return 0;
    	// If the value is greater, increment the rank and travel down both sides
    	else if(nValue > ds.GetKeyValue())
    		return 1 + findRank(ds.GetLeftNode(), nValue) + findRank(ds.GetRightNode(), nValue);
    	// If lest than or equal to, don't increment and go down the left side
    	else
    		return findRank(ds.GetLeftNode(), nValue);
    }
    
    // Returns the root node
    public BSTNode getRootNode()
    {
    	return m_objRootNode;
    }
    
    // Returns the minimum value in the BST
    public int getMax()
    {
    	// Start at the root
    	BSTNode ds =  m_objRootNode;
    	
    	// Travel down the right side
    	while(ds.GetRightNode() != null)
    		ds = ds.GetRightNode();
    	
    	// Returns the maximum value
    	return ds.GetKeyValue();
    }
    
    // Returns the minimum value in the BST
    public int getMin()
    {
    	// Start at the root
    	BSTNode ds =  m_objRootNode;
    	
    	// Travel down the left side
    	while(ds.GetLeftNode() != null)
    		ds = ds.GetLeftNode();
    	
    	// Return the minimum value
    	return ds.GetKeyValue();
    }
}
    
