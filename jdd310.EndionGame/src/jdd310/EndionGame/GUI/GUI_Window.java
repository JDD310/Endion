package jdd310.EndionGame.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class GUI_Window {

	/**
	 * I can't even give you documentation for most of this code, It was all done through the WindowBuilder or whatever its called.
	 * 
	 * I just used it and it wrote pretty much everything for me. I also created the file as they did in the video that was linked in lab slides,
	 * just with Swing instead of the one they used.
	 * 
	 * The only part of this i actually wrote by hand were the actionHandlers and the switchMenu method, but the actionHandlers are very simple and
	 * the switchMenu method i just wrote because i couldn't figure out how to switch menus dynamically many times without having a method to help me. 
	 * 
	 * It's late, but i hope whoever grades this can tell that i at least have a semblance of a GUI created. I just
	 * Can't finish it all tonight, some of it requires game logic that i don't have written in java quite yet, and
	 * It's too late at night to try to finish all of that, It'd take until morning easily.. 
	 * 
	 * CardLayout documentation i used to write switchMenu method:
	 * https://docs.oracle.com/javase/7/docs/api/java/awt/CardLayout.html
	 */
	
	
	private JFrame frame;
	private final JPanel StartMenu = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Window window = new GUI_Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Helper method to switch menus
	private void switchMenu(String menuName) {
	    CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
	    layout.show(frame.getContentPane(), menuName);
	}


	/**
	 * Create the application.
	 */
	public GUI_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		StartMenu.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(StartMenu, "startmenu");
		StartMenu.setLayout(null);
		
		JLabel Title = new JLabel("Endion");
		Title.setFont(new Font("Castellar", Font.PLAIN, 71));
		Title.setBounds(312, 72, 321, 91);
		StartMenu.add(Title);
		
		JLabel Description = new JLabel("The Text-Based Adventure RPG Game");
		Description.setBounds(335, 174, 275, 36);
		StartMenu.add(Description);
		
		JButton StartGame = new JButton("Start Game");
		StartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMenu("spawnmenu");
			}
		});
		StartGame.setFont(new Font("Tahoma", Font.PLAIN, 23));
		StartGame.setBounds(390, 260, 188, 73);
		StartMenu.add(StartGame);
		
		
		JPanel SpawnMenu = new JPanel();
		SpawnMenu.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(SpawnMenu, "spawnmenu");
		SpawnMenu.setLayout(null);
		
		JLabel Prompt = new JLabel("Select Spawn Location:");
		Prompt.setFont(new Font("Perpetua", Font.PLAIN, 50));
		Prompt.setBounds(264, 69, 418, 91);
		SpawnMenu.add(Prompt);
		
		JButton SpawnLoc1 = new JButton("Outskirts of Arvadale");
		SpawnLoc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// More Game logic will take the place of this code once completed,
				// but for now it just switches to the main game menu
				switchMenu("gamemenu");
			}
		});
		SpawnLoc1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SpawnLoc1.setBounds(331, 262, 276, 54);
		SpawnMenu.add(SpawnLoc1);
		
		JButton SpawnLoc2 = new JButton("Red Dragon Mountains");
		SpawnLoc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// More Game logic will take the place of this code once completed,
				// but for now it just switches to the main game menu
				switchMenu("gamemenu");
			}
		});
		SpawnLoc2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SpawnLoc2.setBounds(331, 327, 276, 54);
		SpawnMenu.add(SpawnLoc2);
		
		JButton SpawnLoc3 = new JButton("Magical Forest");
		SpawnLoc3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// More Game logic will take the place of this code once completed,
				// but for now it just switches to the main game menu
				switchMenu("gamemenu");
			}
		});
		SpawnLoc3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SpawnLoc3.setBounds(331, 392, 276, 54);
		SpawnMenu.add(SpawnLoc3);
		
		JPanel MainGameMenu = new JPanel();
		MainGameMenu.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(MainGameMenu, "gamemenu");
		MainGameMenu.setLayout(null);
		
		JButton btnMove = new JButton("Move");
		btnMove.setFont(new Font("Javanese Text", Font.PLAIN, 21));
		btnMove.setBounds(22, 402, 147, 90);
		MainGameMenu.add(btnMove);
		
		JButton btnExamineArea = new JButton("Examine Area");
		btnExamineArea.setFont(new Font("Javanese Text", Font.PLAIN, 21));
		btnExamineArea.setBounds(191, 402, 176, 90);
		MainGameMenu.add(btnExamineArea);
		
		JButton btnViewStatus = new JButton("View Status");
		btnViewStatus.setFont(new Font("Javanese Text", Font.PLAIN, 21));
		btnViewStatus.setBounds(558, 402, 155, 90);
		MainGameMenu.add(btnViewStatus);
		
		JButton btnSearchArea = new JButton("Search Area");
		btnSearchArea.setFont(new Font("Javanese Text", Font.PLAIN, 21));
		btnSearchArea.setBounds(389, 402, 147, 90);
		MainGameMenu.add(btnSearchArea);
		
		JButton btnEndAdventure = new JButton("End Adventure");
		btnEndAdventure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEndAdventure.setFont(new Font("Javanese Text", Font.PLAIN, 21));
		btnEndAdventure.setBounds(735, 402, 186, 90);
		MainGameMenu.add(btnEndAdventure);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(77, 58, 792, 316);
		MainGameMenu.add(scrollPane);
		
		JTextArea MainGameTextArea = new JTextArea();
		MainGameTextArea.setWrapStyleWord(true);
		MainGameTextArea.setLineWrap(true);
		MainGameTextArea.setText("This is just Sample Text, the Actual game output will be displayed here once i finish all of the logic. \r\n\r\nAll of the buttons here will also either display their own menus or will display info on the screen. \r\n\r\nThis is probably not the final version of this main menu, and i still need to add player statuses at top. \r\n\r\nJust showing that i do have a GUI created, and I'm working on it while i continue to write logic. ");
		MainGameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		scrollPane.setViewportView(MainGameTextArea);
	}
}
