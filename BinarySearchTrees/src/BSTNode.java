// Nicholas Espinosa
// PID n2431401
// COP 3503 - 0011
// 02.11.2016
// Recitation Week 5

public class BSTNode 
{
	BSTNode m_objLeftNode, m_objRightNode, m_objParentNode;
	int m_nKeyValue, m_nSubtreeSize, m_nRank;
	
	public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objParentNode = m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value to default of 0.
		m_nKeyValue = 0;
		
		// Set this node's subtree size to the default of 0
		m_nSubtreeSize = 0;
		
		// Set this node's rank to the default of 0
		m_nRank = 0;
	}

	public BSTNode(int nKeyValue)
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objParentNode = m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value
		m_nKeyValue = nKeyValue;
		
		// Set this node's subtree size to the default of 0
		m_nSubtreeSize = 0;
		
		// Set this node's rank to the default of 0
		m_nRank = 0;
	}

	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		m_objLeftNode = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		m_objRightNode = objRightNode;
	}
	
	public void setParentNode(BSTNode objParentNode)
	{
		m_objParentNode = objParentNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( m_objLeftNode );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( m_objRightNode );
	}
	
	public BSTNode getParentNode()
	{
		return (m_objParentNode);
	}

	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		m_nKeyValue = nKeyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( m_nKeyValue );
	}
	
	public void setRank(int nValue)
	{
		m_nRank = nValue;
	}
	
	public int getRank()
	{
		return m_nRank;
	}
	
	public void setSubtreeSize(int nValue)
	{
		m_nSubtreeSize = nValue;
	}
	
	public int getSubtreeSize()
	{
		return m_nSubtreeSize;
	}
	

}
