package gui;

import entities.Player;
import managers.PlayerManager;

public class GUIHelper {
	
    private GameGUI gui;
    private PlayerManager playerManager;

    public GUIHelper(PlayerManager playerManager, GameGUI gameGUI) {
    	this.playerManager = playerManager;
    	this.gui = gameGUI;
    }
    
    // Method to update the text field in the GUI with a new message
    public void updateMainMenuText(String message) {
        gui.updateMainGameTextArea(message);  // Update the GUI's text area
    }

    // Method to clear the text field
    public void clearTextField() {
        gui.clearTextArea();
    }
    
    public void endGame() {
 		gui.closeMenu();
 	}
    
    public void updateStatus() {
    	gui.updateStatusMenu(playerManager.getAttacksAsString(),
    						 playerManager.getInventoryAsString(),
    						 playerManager.getActiveQuestsAsString()
    						 );
    }
	
}
