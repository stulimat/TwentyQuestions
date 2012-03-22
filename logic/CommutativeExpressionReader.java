package logic;

/**
 * CommutativeExpressionReader.java
 * CS 211 
 * Audrey St. John
 **/

// XML
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  
import javax.xml.parsers.ParserConfigurationException;
 
import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  

import org.w3c.dom.*;
import org.w3c.dom.DOMException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;  

// io
import java.io.*;

/**
 * CommutativeExpressionReader reads xml files of expressions with
 * binary, commutative operators.
 *
 * @author Audrey Lee
 */
public class CommutativeExpressionReader
{

   /**
    * Parses XML file.
    * @return expression YesNoTree corresponding to file.
    **/
   public static YesNoTree readCommutativeExpr( String file )
   {
     return readCommutativeExpr( new File( file ) );
   }

   /**
    * Parses XML file
    * @return expression YesNoTree corresponding to file.
    **/
   public static YesNoTree readCommutativeExpr( File file )
   {
      DocumentBuilderFactory factory =
         DocumentBuilderFactory.newInstance();
      
      try 
      {
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document document = builder.parse( file );

         return parseExprTree( document );	    
         
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
   private static YesNoTree parseExprTree( Document document )
   {
     YesNoTree tree = new YesNoTree();

     // parse root
     Element root = 
       (Element)document.getDocumentElement();

     tree.setRoot( parseExprNode( root ) );

     return tree;
   }
   
  /**
   * Parses expr element.
   * @return TreeNode represented by element.
   **/
  private static TreeNode parseExprNode( Element element )
  {
    // base case: atom
    if ( element.getAttribute( "type" ).equals( "atom" ) )
    {
      // must have exactly one non-text child
      // get children
      NodeList children = element.getChildNodes();      

      for ( int i = 0; i < children.getLength(); i++ )
      {
        // if not text node
        if ( children.item(i) instanceof Element )
        {
          Element atom = (Element)children.item(i);
      
          // get attribute by name
          return new TreeNode( atom.getAttribute( "value" ) );    
        }
      }
      
      // error if gets to here
      return null;
    }
    // recursive case
    else
    {
      // get children
      NodeList children = element.getChildNodes();      

      // iterate through, looking for operator and two operands
      // NOTE: operand order does not matter because operators are
      // commutative
      String commutativeOp = "";
      TreeNode operand1 = null;
      TreeNode operand2 = null;
      Element currentElt;

      for ( int i = 0; i < children.getLength(); i++ )
      {
        // if not text node
        if ( children.item(i) instanceof Element )
        {
          currentElt = (Element)children.item(i);

          // test tag name 
          // if operator
          if ( currentElt.getTagName().equals( "operator" ) )
            commutativeOp = currentElt.getAttribute( "value" );
          // otherwise, should be expression
          else if ( currentElt.getTagName().equals( "expr" ) )
          {
            // store in operand1, if nothing there yet
            if ( operand1 == null )
              operand1 = parseExprNode( currentElt );
            // otherwise, put in operand2
            else
              operand2 = parseExprNode( currentElt );
          }
        }
      }

      // create node
      TreeNode exprNode = new TreeNode( commutativeOp );
      // set left and right children; arbitrary order
      exprNode.set( operand1 );
      exprNode.setRightChild( operand2 );
      
      return exprNode;
    }
  }

}
