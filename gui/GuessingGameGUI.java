package gui;
import javax.swing.*;
import java.awt.*; 

public class GuessingGameGUI extends JPanel{
     //Main Control Panels 
	 JPanel displayPanel;
     JPanel mainPanel;
     JPanel controlPanel;
     JButton yesButton = new JButton("Yes");
     JButton noButton = new JButton("No");
     JButton myAnimalButton = new JButton("You guessed my animal!");
     JButton newGameButton = new JButton("New Game");
     JLabel questionLabel = new JLabel("Question Goes Here");
     JLabel answerLabel = new JLabel("Answer Goes Here");
     JLabel questionText = new JLabel("Question:");
     JLabel answerText = new JLabel("I think your animal is a :");
     
     public GuessingGameGUI()
     {
    	 //Initialize Panels
    	 initPanels();
     }
     
     private void initPanels()
     {
    	 //Initialize the panels
    	 setupDisplayPanel();
    	 setupControlPanel();
    	 setupMainPanel();
     }
     
     private void setupDisplayPanel()
     {
    	 
    	//Set up the display panel that will display
    	 this.displayPanel = new JPanel();
    	 this.displayPanel.setLayout(new GridLayout(4,1));
    	 this.displayPanel.setPreferredSize(new Dimension (200,600));

 
    	 this.displayPanel.add(questionText);
    	 this.displayPanel.add(questionLabel);
    	 
    	 // the guess text and if you know what the answer is. 
    	 this.displayPanel.add(answerText);
    	 this.displayPanel.add(answerLabel);
     }
     
     private void setupControlPanel()
     {
    	//Set up the control panel - the yes/no and new game buttons. 
    	 this.controlPanel = new JPanel();
    	 this.controlPanel.setLayout(new FlowLayout());
    	 this.controlPanel.setPreferredSize(new Dimension(200,600));
    	 this.controlPanel.add(newGameButton);
    	 this.controlPanel.add(yesButton);
    	 this.controlPanel.add(noButton); 
    	 this.controlPanel.add(myAnimalButton);

    	
     }
     
     private void setupMainPanel()
     {	//Set up the main panel and add all the other panels to it 
    	 mainPanel = new JPanel(); 
    	 mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
    	 mainPanel.setPreferredSize(new Dimension(800,600));
    	 this.mainPanel.add(displayPanel); 
    	 this.mainPanel.add(controlPanel);
     }
     
     public JPanel returnGUI()
     {
    	return this.mainPanel;  
     }
}
