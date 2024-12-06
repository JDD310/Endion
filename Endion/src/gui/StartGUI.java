package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import core.GameEngine;

public class StartGUI {

	private JFrame frame;
	
	// Method to switch menus
		private void switchMenu(String menuName) {
			CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
			layout.show(frame.getContentPane(), menuName);
		}

	
	public StartGUI() {
		initialize();
	}

	
    private void initialize() {
        frame = new JFrame("Endion RPG");
        frame.setBounds(100, 100, 960, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout());

        addStartMenu();
        addPlayerMenu();

        frame.setVisible(true);
    }
	
	// Add Start Menu
    private void addStartMenu() {
        JPanel startMenu = new JPanel(null);
        startMenu.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Endion", SwingConstants.CENTER);
        title.setFont(new Font("Castellar", Font.PLAIN, 71));
        title.setBounds(312, 72, 321, 91);
        startMenu.add(title);

        JLabel description = new JLabel("The Text-Based Adventure RPG Game", SwingConstants.CENTER);
        description.setBounds(335, 174, 275, 36);
        startMenu.add(description);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
        startButton.setBounds(390, 260, 188, 73);
        startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMenu("playerMenu");
			}
		});
        startMenu.add(startButton);

        frame.getContentPane().add(startMenu, "startMenu");
    }
    
    // Add Start Menu
    private void addPlayerMenu() {
        JPanel playerMenu = new JPanel(null);
        playerMenu.setBackground(Color.LIGHT_GRAY);
        
        // Creating and Adding the Prompt to the playerMenu
        JLabel prompt = new JLabel("Enter Player Name:", SwingConstants.CENTER);
        prompt.setFont(new Font("Perpetua", Font.PLAIN, 50));
        prompt.setBounds(264, 69, 418, 91);
        playerMenu.add(prompt);
        
        JTextField playerName = new JTextField();
        playerName.setFont(new Font("Tahoma", Font.PLAIN, 30));
        playerName.setBounds(233, 218, 479, 66);
        playerName.setColumns(10);
        playerMenu.add(playerName);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(423, 306, 100, 25);
        playerMenu.add(submitButton);
        
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(323, 182, 300, 25);
        playerMenu.add(resultLabel);
        
        // Action Listener for the button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = playerName.getText();
                if (!selectedName.isEmpty()) {
                	// Kill the current GUI
                	frame.dispose();
                	// Initialize GameEngine with the player's name
                    new GameEngine(selectedName, "GameData.json");
                } else {
                    resultLabel.setText("Please enter a valid name.");
                }
            }
        });
        
        frame.getContentPane().add(playerMenu, "playerMenu");
    }
}
