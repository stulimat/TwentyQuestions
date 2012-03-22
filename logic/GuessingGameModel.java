package logic;
import java.io.File;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

public class GuessingGameModel 
{
 
	TreeNode root;

	public GuessingGameModel()
	{
		buildFromFile();
	}
	
	private void buildFromFile()
	{
		try 
		{
			
		//read the XML file using DOM parser. DOM parser loads the file into the memory 
		//making an object model of it. 
		File file = new File ("/Users/catalino/git/TwentyQuestions/logic/data.xml"); 
		XMLReader parser = new SAXParser();
		parser.parse("/Users/catalino/git/TwentyQuestions/logic/data.xml");
		
		}
		catch(IOException e)
		{
			// Do stuff when you encounter an IO Exception.
			System.out.println("IO Exception thrown! Look for data file in the right path");
		}
		catch(IllegalArgumentException i)
		{
			//Do stuff when the parser is configured incorrectly
		}
		catch(SAXException s)
		{
			
		}
		
	}
	
	private void buildFromScratch()
	{
		root = new TreeNode();
		root.setInfo("Does it fly?");
		root.setYesNode(new TreeNode("Duck"));
		root.setNoNode(new TreeNode("Does it live in Water?",new TreeNode("This is a whale"),new TreeNode("This is a moose")));
	}

	//
	 
	
	
	
	
	
	
}
