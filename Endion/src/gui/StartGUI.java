package gui;

import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import core.*;

public class StartGUI {

	private JFrame frame;
	
	private void setDifficulty(Difficulty difficulty) {
		Config.setDifficulty(difficulty);
	}
	
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
        addDifficultyMenu();
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
				switchMenu("difficultyMenu");
			}
		});
        startMenu.add(startButton);

        frame.getContentPane().add(startMenu, "startMenu");
    }
    
 // Add Difficulty Menu
    private void addDifficultyMenu() {
        JPanel difficultyMenu = new JPanel(null);
        difficultyMenu.setBackground(Color.LIGHT_GRAY);
        
        // Creating and Adding the Prompt to the DifficultyMenu
        JLabel prompt = new JLabel("Select Difficulty:", SwingConstants.CENTER);
        prompt.setFont(new Font("Perpetua", Font.PLAIN, 50));
        prompt.setBounds(264, 69, 418, 91);
        difficultyMenu.add(prompt);
        
        // Creating the Difficulty Buttons
        JButton difficulty1 = createDifficultyButton("Easy", 197);
        JButton difficulty2 = createDifficultyButton("Meduim", 262);
        JButton difficulty3 = createDifficultyButton("Hard", 327);
        JButton difficulty4 = createDifficultyButton("Impossible", 392);
        
        // Adding the actions
        difficulty1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDifficulty(Difficulty.EASY);
				switchMenu("playerMenu");
			}
		});
        difficulty2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDifficulty(Difficulty.NORMAL);
				switchMenu("playerMenu");
			}
		});
        difficulty3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDifficulty(Difficulty.HARD);
				switchMenu("playerMenu");
			}
		});
        difficulty4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDifficulty(Difficulty.IMPOSSIBLE);
				switchMenu("playerMenu");
			}
		});
        
        // Adding the Spawn Location Buttons to the SpawnMenu
        difficultyMenu.add(difficulty1);
        difficultyMenu.add(difficulty2);
        difficultyMenu.add(difficulty3);
        difficultyMenu.add(difficulty4);

        frame.getContentPane().add(difficultyMenu, "difficultyMenu");
    }
    
    // Factory to Create Buttons for DifficultyMenu
    private JButton createDifficultyButton(String difficulty, int y) {
        JButton button = new JButton(difficulty);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(331, y, 276, 54);
        return button;
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
                	// Initialize GameEngine with the player's name and difficulty
                	String diff = Config.getDiff().toString();
                	new Engine(selectedName, "GameData.json", diff);
                } else {
                    resultLabel.setText("Please enter a valid name.");
                }
            }
        });
        
        frame.getContentPane().add(playerMenu, "playerMenu");
    }
}
