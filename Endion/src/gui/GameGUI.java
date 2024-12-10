package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;


import utilities.GameUtils;

public class GameGUI {
	
	private ActionController controller;
	
	private JFrame frame;
    private JTextArea mainGameTextArea;
//  private JPanel gridPanel;
    private JList<String> questList;
    private JList<String> attackList;
    private JList<String> itemList;
    private JTextField healthField;
    
    
    public GameGUI() {
    }
    /**
    * Set the ActionController after the GameGUI has been created.
    * @param actionController The ActionController to associate with this GameGUI.
    */
    public void setActionController(ActionController actionController) {
        this.controller = actionController;
    }
    
    
    // Helper Methods    
    
    // Getter for the Main Game Text Area
    public JTextArea getGameOutputTextArea() {
		return mainGameTextArea;
	}
    
    /**
    * Updates the three JLists with the latest player information when called.
    * 
    * @param attacks The list of player's attacks as strings
    * @param quests The list of player quests as strings
    * @param items The list of player items as strings
    */
    public void updateStatusMenu(List<String> attacks, List<String> quests, List<String> items) {
    	// Update the quests list
        GameUtils.fillJList(questList, quests);

        // Update the items list (convert the player's item list to strings)
        GameUtils.fillJList(itemList, items);

        // Update the equipment list (for example, weapons, armor, etc.)
        GameUtils.fillJList(attackList, attacks);
    }
    
    public void updateMainGameTextArea(String message) {
        // Update the text area immediately
        mainGameTextArea.setText(message);

        // Create a Timer with explicit ActionListener
        Timer timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop(); // Stops the timer
            }
        });

        timer.setRepeats(false); // Ensures the Timer runs only once
        timer.start();
    }
    
    public void updateHealthField(float health) {
    	healthField.setText("" + health);
    }
	
	// Method to clear the Main Game Text Area
    public void clearTextArea() {
        mainGameTextArea.setText("");  // Clears the text field
    }
	
	// Method to switch menus
	private void switchMenu(String menu) {
		CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
		layout.show(frame.getContentPane(), menu);
	}
	
	// Method to Create a SwitchMenu ActionListener
	private void switchMenuButton(JButton button, String menu) {
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		switchMenu(menu);
        	}
        });
    }
	
	// Method to Create a SwitchMenu ActionListener that also does an Action
	private void switchMenuActionButton(JButton button, String menu, String action) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	controller.doAction(action);
	        	switchMenu(menu);
	        }
	    });
	}
	
	// Close the Frame
	public void closeMenu() {
		frame.dispose();
	}
	
	
    // Initialize GUI for First time
    public void initialize() {
        frame = new JFrame("Endion RPG");
        frame.setBounds(100, 100, 960, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout());
        
        addSpawnMenu();
        addGameMenu();
        addStatusMenu();
        
        frame.setVisible(true);
    }
    
    // Add Spawn Menu
    private void addSpawnMenu() {
        JPanel spawnMenu = new JPanel(null);
        spawnMenu.setBackground(Color.LIGHT_GRAY);
        
        // Creating and Adding the Prompt to the SpawnMenu
        JLabel prompt = new JLabel("Select Spawn Location:", SwingConstants.CENTER);
        prompt.setFont(new Font("Perpetua", Font.PLAIN, 50));
        prompt.setBounds(264, 69, 418, 91);
        spawnMenu.add(prompt);
        
        // Creating the Spawn Location Buttons
        JButton spawnCity = createSpawnButton("Outskirts of Arvadale", 262, "setSpawnCity");
        JButton spawnMountain = createSpawnButton("Red Dragon Mountains", 327, "setSpawnMountain");
        JButton spawnForest = createSpawnButton("Magical Forest", 392, "setSpawnForest");
        
        // Adding the Spawn Location Buttons to the SpawnMenu
        spawnMenu.add(spawnCity);
        spawnMenu.add(spawnMountain);
        spawnMenu.add(spawnForest);

        frame.getContentPane().add(spawnMenu, "spawnMenu");
    }
    
    // Factory to Create Buttons for SpawnMenu
    private JButton createSpawnButton(String location, int y, String spawnLocation) {
        JButton button = new JButton(location);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(331, y, 276, 54);
        switchMenuActionButton(button, "gameMenu", spawnLocation);
        return button;
    }

    // Add Game Menu
    private void addGameMenu() {
        JPanel gameMenu = new JPanel(null);
        gameMenu.setBackground(Color.LIGHT_GRAY);
        
     // Creating the main output area for the game.
        mainGameTextArea = new JTextArea();
        mainGameTextArea.setWrapStyleWord(true);
		mainGameTextArea.setLineWrap(true);
		mainGameTextArea.setEditable(false);
		mainGameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
        
        // Creating All Five Main Buttons Using the Factory
		JButton speakButton = createMenuButton("Speak", 22, 147, null, "speak");
        JButton examineButton = createMenuButton("Examine Area", 191, 176, "gameMenu", "examine");
        JButton searchButton = createMenuButton("Search Area", 389, 147, "gameMenu", "search");
        JButton statusButton = createMenuButton("View Status", 558, 155, "statusMenu", "updateStatusMenu");
        JButton endButton = createMenuButton("End Adventure", 735, 186, null, "endGame");
        
        

        
        // Directional Move buttons created with the Factory
        JButton moveNorth = createMoveButton("Move North", "NORTH", 134, 45, "moveNorth");
        JButton moveSouth = createMoveButton("Move South", "SOUTH", 134, 137, "moveSouth");
        JButton moveEast = createMoveButton("Move East", "EAST", 244, 86, "moveEast");
        JButton moveWest = createMoveButton("Move West", "WEST", 24, 86, "moveWest");
        
        // Adding the buttons to the menu
        gameMenu.add(endButton);
        gameMenu.add(statusButton);
        gameMenu.add(searchButton);
        gameMenu.add(examineButton);
        gameMenu.add(speakButton);
        gameMenu.add(moveNorth);
        gameMenu.add(moveSouth);
        gameMenu.add(moveEast);
        gameMenu.add(moveWest);
		
        
        
		// Creating the Scroll pane the main output area sits inside 
        JScrollPane scrollPane = new JScrollPane(mainGameTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(367, 58, 502, 316);
        
        // Adding the Output area to the gameMenu
        gameMenu.add(scrollPane);

//		// Creating the Grid Panel that Displays the Player's current location on map.
//      gridPanel = new JPanel(new GridLayout(9, 5));
//      gridPanel.setBounds(441, 155, 439, 310);
//      guiManager.populateGridPanel(player, worldManager);
//      moveMenu.add(gridPanel);
        
        // Health Label
        JLabel healthLabel = new JLabel("Health:");
        healthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        healthLabel.setBounds(101, 13, 61, 23);
        gameMenu.add(healthLabel);
        
        healthField = new JTextField();
        healthField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        healthField.setBackground(Color.LIGHT_GRAY);
        healthField.setEditable(false);
        healthField.setBounds(149, 14, 75, 20);
        gameMenu.add(healthField);
        
        
        
        frame.getContentPane().add(gameMenu, "gameMenu");
    }
    
    // Factory to Create Buttons for GameMenu.
    private JButton createMenuButton(String text, int x, int w, String menu, String action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Javanese Text", Font.PLAIN, 21));
        button.setBounds(x, 402, w, 90);
        if(menu != null && action != null) {
        	switchMenuActionButton(button, menu, action);
        } else {
        	controller.doAction(action);
        }
        return button;
    }
    
    // Factory to Create the MoveMenu Buttons
    private JButton createMoveButton(String text, String direction, int x, int y, String action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
        button.setBounds(x, y, 108, 65);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.doAction(action);
			}
		});
        return button;
    }
   
		


    
   
    
    private void addStatusMenu() {
        // Create the Status Menu panel
        JPanel statusMenu = new JPanel(null);
        statusMenu.setBackground(Color.LIGHT_GRAY);

        // Add the "Back to Menu" button
        JButton exitStatusMenu = new JButton("Back to Menu");
        switchMenuButton(exitStatusMenu, "gameMenu");
		exitStatusMenu.setBounds(415, 439, 116, 53);
		statusMenu.add(exitStatusMenu);
		
		// Create the Three JLists to hold Status Info
        questList = new JList<String>();
        itemList = new JList<String>();
        attackList = new JList<String>();
		
        // Create the Three Scroll Panes to hold the different JLists
        JScrollPane questScrollPane = createScrollPane(641, 11, 293, 351, questList);
        JScrollPane itemsScrollPane = createScrollPane(11, 11, 315, 351, itemList);
        JScrollPane attackScrollPane = createScrollPane(337, 11, 293, 351, attackList);
        
        // Add the scroll panes to the status menu
        statusMenu.add(questScrollPane);
        statusMenu.add(itemsScrollPane);
        statusMenu.add(attackScrollPane);

        // Add the Status Menu to the frame
        frame.getContentPane().add(statusMenu, "statusMenu");
    }
    
    // Factory to create the Scroll Panes for the StatusMenu
    private JScrollPane createScrollPane(int x, int y, int width, int height, JList<String> list) {
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(x, 11, 293, 351);
        return scrollPane;
    }    
}