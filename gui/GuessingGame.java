package gui;

import java.awt.event.*;
import javax.swing.*;

public class GuessingGame extends JApplet implements ActionListener

{
	public void init()
	{
		initializeGame(); 
	}

	public void initializeGame()
	{
		//Init the GUI
		GuessingGameGUI gui = new GuessingGameGUI();
		getContentPane().add(gui.returnGUI());
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}

}
