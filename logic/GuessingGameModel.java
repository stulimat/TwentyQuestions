package logic;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GuessingGameModel 
{
 
    YesNoTree tree;
	public GuessingGameModel()
	{
		buildFromScratch();
		this.tree = buildFromScratch();
	}
	
	private void buildFromFile()
	{
		try 
		{
			
		//read the XML file using DOM parser. DOM parser loads the file into the memory 
		//making an object model of it. 
		File file = new File ("C:/Users/Tito/workspace/TwentyQuestions/src/gui/data.xml"); 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
	    NodeList list = doc.getElementsByTagName("node");
	    
		}
		catch(IOException e)
		{
			// Do stuff when you encounter an IO Exception.
			System.out.println("IO Exception thrown! Look for data file in the right path");
		}
		catch(ParserConfigurationException p)
		{
			//Do stuff when the parser is configured incorrectly
		}
		catch(SAXException s)
		{
			
		}
		
	}
	
	private YesNoTree buildFromScratch()
	{
		TreeNode root;
		root = new TreeNode();
		root.setInfo("Does it fly?");
		root.setYesNode(new TreeNode("Duck"));
		root.setNoNode(new TreeNode("Does it live in Water?",new TreeNode("This is a whale"),new TreeNode("This is a moose")));
		return new YesNoTree(root);
	}
	
	//reinitializes game when user presses restart 
	public void reInitialize()
	{
		
		this.tree = buildFromScratch();
		
	}
	
	public void yesSelection()
	{
		this.tree.moveYesNode();
	}
	
	public void noSelection()
	{
		this.tree.moveNoNode();
	}
	
	public boolean isQuestion()
	{
		return !this.tree.getCurrentNode().isLeaf();
	}
	
	public String updateInfo()
	{
		return this.tree.getCurrentNode().getInfo();
	}
	 
	
	
	
	
	
	
}
