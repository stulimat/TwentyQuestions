package gui;

import java.awt.event.*;
import javax.swing.*;

import logic.GuessingGameModel;

public class GuessingGame extends JApplet 

{
	public void init()
	{

		initializeGame(); 
	}

	public void initializeGame()
	{
		//Init the GUI
		GuessingGameGUI gui = new GuessingGameGUI(new GuessingGameModel());
		getContentPane().add(gui.returnGUI());
	    GuessingGameModel model = new GuessingGameModel();
	}
	
	

}
