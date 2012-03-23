package gui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import logic.GuessingGameModel;
import logic.YesNoTree;
import logic.TreeNode;

public class GuessingGameGUI extends JPanel implements ActionListener {
	
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
     JButton wrongGuess = new JButton("Wrong animal!");
     GuessingGameModel model;     
     
     public GuessingGameGUI(GuessingGameModel model)
     {
    	 this.model = model;
    	 //Initialize Panels
    	 initPanels();
    	 this.questionLabel.setText(model.updateInfo());
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
    	 newGameButton.addActionListener(this);
    	 this.controlPanel.add(yesButton);
    	 yesButton.addActionListener(this);
    	 this.controlPanel.add(noButton);
    	 noButton.addActionListener(this);
    	 this.controlPanel.add(myAnimalButton);
    	 myAnimalButton.addActionListener(this);
    	 this.controlPanel.add(wrongGuess);
    	 wrongGuess.addActionListener(this);
    	 myAnimalButton.setEnabled(false);
    	 wrongGuess.setEnabled(false);
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
     
     
     public void questionUpdater()
     {
    	 if (model.isQuestion()== true)
    	 {
    		 questionLabel.setText(model.updateInfo());
    		 
    	 }
    	 
     }
     
     private void checkIfEndGame()
     {
    	 if(!model.isQuestion())
    	 {
    		 this.yesButton.setEnabled(false);
    		 this.noButton.setEnabled(false);
    		 this.wrongGuess.setEnabled(true);
    		 this.myAnimalButton.setEnabled(true);
    	 }
     }
     
     private void resetButtons()
     {
    	 this.yesButton.setEnabled(true);
		 this.noButton.setEnabled(true);
		 this.wrongGuess.setEnabled(false);
		 this.myAnimalButton.setEnabled(false);
     }
     public void actionPerformed(ActionEvent e)
 	 {

    	 if(e.getSource() == newGameButton)
    	 {
    	  //Do stuff when user presses the new button.
    		  model.reInitialize();
    	 }
    	 
    	 if (e.getSource() == yesButton)
    	 {
    		 model.yesSelection();
    		 if(model.isQuestion())
    		 {
    		   this.questionLabel.setText(model.updateInfo());
    		 }
    		 else
    		 {
    		   this.answerLabel.setText(model.updateInfo());
    		 }
    	 }
    	 if (e.getSource() == noButton)
    	 {
    		 model.noSelection();
    		 if(model.isQuestion())
    		 {
    		   this.questionLabel.setText(model.updateInfo());
    		 }
    		 else
    		 {
    		   this.answerLabel.setText(model.updateInfo());
    		 }
    	 }
    	 if(e.getSource() == myAnimalButton)
    	 {
    		 JOptionPane.showMessageDialog(this, "Excellent!");
    	 }
    	 if(e.getSource() == wrongGuess)
    	 {
    		 JOptionPane.showMessageDialog(this,"We're sorry. Buy the advanced version to add more animals!");
    	 }
    	 if(e.getSource() == newGameButton)
    	 {
    		 this.model = new GuessingGameModel();
    		 questionUpdater();
    		 this.answerLabel.setText("");
    		 resetButtons();
    	 }
    	 checkIfEndGame();
    	 
 	 }    
}
