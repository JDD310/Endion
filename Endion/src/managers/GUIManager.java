package managers;

import javax.swing.*;

import entities.Player;
import entities.Tile;
import gui.GameGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIManager {
	
	// Directly access the static instance of the GUI, created to help with modifying Text Areas.
    private GameGUI gui;
    
//	private JPanel gridPanel;

    public GUIManager() {
         // i probably need something here idk lol
    }

    // Populate the grid panel with the map and highlight the player's position
//    public void populateGridPanel(Player player, WorldManager worldManager) {
//        gridPanel.removeAll(); // Clear existing components
//        
//        int rows = 9; // Could add a way for these to change, if the world size changes, but seeing as they
//        int cols = 5; // are currently static, there is no point.
//
//        for (int x = 0; x < rows; x++) {
//            for (int y = 0; y < cols; y++) {
//                JLabel cell = new JLabel("", SwingConstants.CENTER);
//                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border to each cell
//                cell.setOpaque(true); // Allow background color changes
//
//                Tile tile = worldManager.getTile(x, y);
//                if (tile != null) {
//                    cell.setText(tile.getName()); // Use the tile's name
//                    cell.setToolTipText(tile.getDescription()); // Add a tooltip for extra info
//                } else {
//                    cell.setText("Empty");
//                }
//
//                // Highlight the player's position
//                if (x == player.getPos()[0] && y == player.getPos()[1]) {
//                    cell.setBackground(Color.GREEN);
//                    cell.setText("Player");
//                } else {
//                    cell.setBackground(Color.LIGHT_GRAY); // Default color
//                }
//
//                gridPanel.add(cell); // Add the cell to the grid panel
//            }
//        }
//        gridPanel.revalidate(); // Refresh the panel
//        gridPanel.repaint();
//    }
    
    // Method to update the text field in the GUI with a new message
    public void updateMainMenuText(String message) {
        gui.updateMainGameTextArea(message);  // Update the GUI's text area
    }

    // Method to clear the text field
    public void clearTextField() {
        gui.clearTextArea();
    }
}
