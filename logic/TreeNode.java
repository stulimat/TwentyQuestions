package logic;

public class TreeNode {

	public TreeNode yesNode; //reference to node if user enters yes 
	public TreeNode noNode; //reference to node if user enters no 
	public String info; //Holds the question being asked to the user 
	
	
	public TreeNode(String value, TreeNode left, TreeNode right ) 
	
	{
		this.yesNode = left; 
		this.noNode = right; 
		this.info = value; 
		
	}
	
	public TreeNode()
	{
      this.yesNode = null;
      this.noNode = null;
      this. info = "";
	}
	public TreeNode(String value)
	{
	  yesNode = null;
	  noNode = null;
	  info = value;
	}

	
	public void setInfo (String newInfo) 
	{
		this.info = newInfo; 
	}
	
	public String getInfo ()
	{
		return this.info;
	}
	
	public void setYesNode(TreeNode node)
	{
		this.yesNode = node; 
	}
	
	public void setNoNode(TreeNode node)
	{
		this.noNode = node; 
	}
	
	public TreeNode getYesNode()
	{
		return this.yesNode;
	}
	
	public TreeNode getNoNode()
	{
		return this.noNode;
	}

	public boolean isLeaf()
	{
		if (this.yesNode == null && this.noNode == null)  
		{
			return true;
		}
		else 
		{
			return false; 
		}
	}

}


