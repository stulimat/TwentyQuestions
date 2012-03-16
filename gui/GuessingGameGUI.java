package gui;
import javax.swing.*;

public class GuessingGameGUI extends JPanel{
     //Main Control Panels 
	 JPanel displayPanel;
     JPanel mainPanel;
     JPanel controlPanel;
     JButton yesButton = new JButton("Yes");
     JButton noButton = new JButton("No");
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
     }
}
