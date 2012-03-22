package logic;

public class YesNoTree 
{

    private TreeNode root;


	public YesNoTree()
	{
		this.root = new TreeNode();
	}
	
	public YesNoTree(TreeNode root)
	{
		this.root = root;
	}
	public void insertInfo(TreeNode node, String info)
	{
	  node.setInfo(info);
	}
	public void insertYesNode(TreeNode node, TreeNode yesNode)
	{
		node.setYesNode(yesNode);
	}
	public void insertNoNode(TreeNode node, TreeNode noNode)
	{
		node.setNoNode(noNode);
	}
	public String getInfo(TreeNode node)
	{
		return node.getInfo();
	}
	//Traversing the tree is destructive - there is no going back up the tree.
	public String moveYesNode()
	{
		this.root = this.root.getYesNode();
		return this.root.getInfo();
	}
	public String moveNoNode()
	{
		this.root = this.root.getNoNode();
		return this.root.getInfo();
	}
	public boolean hasNext()
	{
		if(this.root.isLeaf())
		{
			return false;
		}
		else
		{
	       return true;
		}
	}
	
	
	public boolean isEmpty()
	{
		if(root.getInfo() == "")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public TreeNode getCurrentNode()
	{
	  return this.root;
	}
	
}
