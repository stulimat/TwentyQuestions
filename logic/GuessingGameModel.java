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

    YesNoTree tree;
	public GuessingGameModel()
	{
		buildFromFile();
	//	buildFromScratch();
	//	this.tree = buildFromScratch();
	}

	private void buildFromFile()
	{

		//read the XML file using DOM parser. DOM parser loads the file into the memory
		//making an object model of it.
		this.tree = readXMLAnimalsFile("/Users/catalino/git/TwentyQuestions/logic/data.xml");
        this.tree.getCurrentNode().getInfo().toString();

	}
	 /**
	    * Parses XML file.
	    * @return expression YesNoTree corresponding to file.
	    **/
	   public static YesNoTree readXMLAnimalsFile( String file )
	   {
	     return readXMLAnimalsFile( new File( file ) );
	   }

	   /**
	    * Parses XML file
	    * @return expression YesNoTree corresponding to file.
	    **/
	   public static YesNoTree readXMLAnimalsFile( File file )
	   {
	      DocumentBuilderFactory factory =
	         DocumentBuilderFactory.newInstance();
	      
	      try 
	      {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document document = builder.parse( file );

	         return parseTree( document );	    
	         
	      } 
	      catch (SAXException sxe) 
	      {
	         // Error generated during parsing)
	         Exception  x = sxe;
	         if (sxe.getException() != null)
	            x = sxe.getException();
	         x.printStackTrace();         
	      } 
	      catch (ParserConfigurationException pce) 
	      {
	         // Parser with specified options can't be built
	         pce.printStackTrace();
	      }   
	      catch (IOException ioe) 
	      {
	         // I/O error
	         ioe.printStackTrace();
	      }
	      
	      return null;
	   }
	   
	   /**
	    * Parses XML Document. 
	    * @return parsed YesNoTree.
	    **/
	   private static YesNoTree parseTree( Document document )
	   {
	     YesNoTree tree = new YesNoTree();

	     // parse root
	     Element root = (Element)document.getDocumentElement();

	     tree = new YesNoTree( parseExprNode( root ) );

	     return tree;
	   }
	   
	  /**
	   * Parses expr element.
	   * @return TreeNode represented by element.
	   **/
	  private static TreeNode parseExprNode( Element element )
	  {  
	      // get children
		  if(element.hasChildNodes())
		  {
	        NodeList children = element.getChildNodes();
	        // iterate through, looking for operator and two operands
	        // NOTE: operand order does not matter because operators are
	        // commutative
	        String text="";
	        TreeNode yesAnswerNode = null;
	        TreeNode noAnswerNode = null;
	        Element currentElt;

	        for ( int i = 0; i < children.getLength(); i++ )
	        {
	          // if not text node
	          if ( children.item(i) instanceof Element )
	          {
	            currentElt = (Element)children.item(i);
	            // test tag name 
	            // if question
	            if ( currentElt.getTagName().equals( "question" ) )
	            {
	              text = currentElt.getAttribute( "value" );
	            }
	            else if(currentElt.getTagName().equals("answer"))
	            {
	        	  if(currentElt.getAttribute("value").equals("yes"))
	        	  {
	        	    yesAnswerNode = parseExprNode(currentElt);
	        	  }
	        	  if(currentElt.getAttribute("value").equals("no"));
	        	  {
	        		//Set the "No" Answer Node by parsing farther down the tree.
	                noAnswerNode = parseExprNode(currentElt);
	        	  }
	            }
	            else if(currentElt.getTagName().equals("node"))
	            {
	              //Go one node deeper.
	        	  return parseExprNode(currentElt);
	            }
	            else if(currentElt.getTagName().equals("statement"))
	  		    {
	            	//Return the statement only and back out of the recursive calls.
	  		        return new TreeNode(currentElt.getAttribute("value"));
	  		    }
	            
	         
			 }
	        }

	      // create node
	      TreeNode exprNode = new TreeNode(text);
	      // set left and right children; arbitrary order
	      exprNode.setNoNode( noAnswerNode );
	      exprNode.setYesNode( yesAnswerNode );
	      return exprNode;
		}
		return new TreeNode();
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
