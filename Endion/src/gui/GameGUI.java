package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import core.Engine;
import entities.Player;
import managers.GUIManager;
import managers.WorldManager;

public class GameGUI {
	private JFrame frame;
//    private JPanel gridPanel;
    private JTextArea mainGameTextArea;

    private final Player player;
    private final WorldManager worldManager;
    private final GUIManager guiManager;

    public GameGUI(Player player, WorldManager worldManager, GUIManager guiManager) {
        this.player = player;
        this.worldManager = worldManager;
        this.guiManager = guiManager;

        initialize();
    }
    
    
    // Helper Methods
    
    // Getter for the Main Game Text Area
    public JTextArea getGameOutputTextArea() {
		return mainGameTextArea;
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
	
	// Method to clear the Main Game Text Area
    public void clearTextArea() {
        mainGameTextArea.setText("");  // Clears the text field
    }
	
	// Method to switch menus
	private void switchMenu(String menuName) {
		CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
		layout.show(frame.getContentPane(), menuName);
	}
	
	
    // Initialize GUI components
    private void initialize() {
        frame = new JFrame("Endion RPG");
        frame.setBounds(100, 100, 960, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout());

        addSpawnMenu();
        addGameMenu();
        addMoveMenu();
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
        JButton spawnLoc1 = createSpawnButton("Outskirts of Arvadale", 262);
        JButton spawnLoc2 = createSpawnButton("Red Dragon Mountains", 327);
        JButton spawnLoc3 = createSpawnButton("Magical Forest", 392);
        
        // Adding the actions
        spawnLoc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchMenu("gameMenu");
			}
		});
        spawnLoc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchMenu("gameMenu");
			}
		});
        spawnLoc3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchMenu("gameMenu");
			}
		});
        
        // Adding the Spawn Location Buttons to the SpawnMenu
        spawnMenu.add(spawnLoc1);
        spawnMenu.add(spawnLoc2);
        spawnMenu.add(spawnLoc3);

        frame.getContentPane().add(spawnMenu, "spawnMenu");
    }
    
    // Factory to Create Buttons for SpawnMenu
    private JButton createSpawnButton(String location, int y) {
        JButton button = new JButton(location);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(331, y, 276, 54);
        return button;
    }

    // Add Game Menu
    private void addGameMenu() {
        JPanel gameMenu = new JPanel(null);
        gameMenu.setBackground(Color.LIGHT_GRAY);
        
        // Creating All Five Main Buttons Using the Factory
        JButton moveButton = createMenuButton("Move", 22, 147, "moveMenu");
        JButton examineButton = createMenuButton("Examine Area", 191, 176, null);
        JButton searchButton = createMenuButton("Search Area", 389, 147, null);
        JButton statusButton = createMenuButton("View Status", 558, 155, "statusMenu");
        JButton endButton = createMenuButton("End Adventure", 735, 186, null);
        
        // Adding the Actions for Each Button that wasn't added by Factory
        examineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
        
        // Adding the buttons to the menu
        gameMenu.add(moveButton);
        gameMenu.add(endButton);
        gameMenu.add(statusButton);
        gameMenu.add(searchButton);
        gameMenu.add(examineButton);

        // Creating the main output area for the game.
        mainGameTextArea = new JTextArea();
        mainGameTextArea.setWrapStyleWord(true);
		mainGameTextArea.setLineWrap(true);
		mainGameTextArea.setEditable(false);
		mainGameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		// Creating the Scroll pane the main output area sits inside 
        JScrollPane scrollPane = new JScrollPane(mainGameTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(77, 58, 792, 316);
        
        // Adding the Output area to the gameMenu
        gameMenu.add(scrollPane);

        frame.getContentPane().add(gameMenu, "gameMenu");
    }
    
    // Factory to Create Buttons for GameMenu.
    private JButton createMenuButton(String text, int x, int w, String menu) {
        JButton button = new JButton(text);
        button.setFont(new Font("Javanese Text", Font.PLAIN, 21));
        button.setBounds(x, 402, w, 90);
        if(menu != null) {
        	button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				switchMenu(menu);
    			}
    		});
        }
        return button;
    }

    // Add Move Menu
    private void addMoveMenu() {
        JPanel moveMenu = new JPanel(null);
        moveMenu.setBackground(Color.LIGHT_GRAY);
        
        
        // Directional Move buttons created with the Factory
        JButton moveNorth = createMoveButton("Move North", "NORTH", 134, 45);
        JButton moveSouth = createMoveButton("Move South", "SOUTH", 134, 137);
        JButton moveEast = createMoveButton("Move East", "EAST", 244, 86);
        JButton moveWest = createMoveButton("Move West", "WEST", 24, 86);
        
        // Button to Return to Menu
        JButton exitMoveMenu = new JButton("Back to Menu");
        exitMoveMenu.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		exitMoveMenu.setBounds(10, 399, 174, 93);
		exitMoveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMenu("gameMenu");
			}
		});
		
//		// Creating the Grid Panel that Displays the Player's current location on map.
//        gridPanel = new JPanel(new GridLayout(9, 5));
//        gridPanel.setBounds(441, 155, 439, 310);
//        guiManager.populateGridPanel(player, worldManager);
//        
        // Adding all elements to the menu
        moveMenu.add(moveNorth);
        moveMenu.add(moveSouth);
        moveMenu.add(moveEast);
        moveMenu.add(moveWest);
        moveMenu.add(exitMoveMenu);
//        moveMenu.add(gridPanel);

        frame.getContentPane().add(moveMenu, "moveMenu");
    }
    
    // Factory to Create the MoveMenu Buttons
    private JButton createMoveButton(String text, String direction, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
        button.setBounds(x, y, 108, 65);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				worldManager.move(direction);
//	            guiManager.populateGridPanel(player, worldManager);
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
        exitStatusMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMenu("gameMenu");
			}
		});
		exitStatusMenu.setBounds(415, 439, 116, 53);
		statusMenu.add(exitStatusMenu);

        // Add the quest list scroll pane
        JScrollPane questScrollPane = createScrollPane(641, 11, 293, 351);
        JList<String> questList = new JList<>();
        questScrollPane.setViewportView(questList);
        statusMenu.add(questScrollPane);

        // Add the items list scroll pane
        JScrollPane itemsScrollPane = createScrollPane(11, 11, 315, 351);
        JList<String> itemsList = new JList<>();
        itemsScrollPane.setViewportView(itemsList);
        statusMenu.add(itemsScrollPane);

        // Add the equipment list scroll pane
        JScrollPane equipmentScrollPane = createScrollPane(337, 11, 293, 351);
        JList<String> equipmentList = new JList<>();
        equipmentScrollPane.setViewportView(equipmentList);
        statusMenu.add(equipmentScrollPane);

        // Add the Status Menu to the frame
        frame.getContentPane().add(statusMenu, "statusMenu");
    }
    
    // Factory to create the Scroll Panes for the StatusMenu
    private JScrollPane createScrollPane(int x, int y, int width, int height) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(x, 11, 293, 351);
        return scrollPane;
    }    
}