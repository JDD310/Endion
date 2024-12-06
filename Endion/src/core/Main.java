package core;

import core.*;
import gui.StartGUI;

public class Main {
	
	private String Name;
	
    public static void main(String[] args) {
        // Need to Run GUI first so that player can Select Name
    	new StartGUI();
    	// After this, the rest of the game is run through GameEngine and GUI. 
    }
}
