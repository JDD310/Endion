package core;

import managers.*;

import java.util.Map;

import org.json.JSONObject;

import entities.*;
import gui.GameGUI;
import utilities.*;

public class GameEngine {
	
	
	private Player player; // The main player
	
	// Managers
	private AttackManager attackManager;
	private CombatManager combatManager;
	private EnemyManager enemyManager;
	private EnvironmentManager environmentManager;
	private GUIManager guiManager;
	private InventoryManager inventoryManager;
	private NPCManager npcManager;
	private PlayerManager playerManager;
	private QuestManager questManager;
	private SkillManager skillManager;
    private WorldManager worldManager;
    
    // Core
    private GameConfig gameConfig;
    
    // GUI
    private GameGUI gameGUI;
    
    // Assigning Coordinates to Spawn Locations
 	private final int[] citySpawn = new int[]{3, 1};
 	private final int[] mountainSpawn = new int[]{2, 4};
 	private final int[] forestSpawn = new int[]{1, 7};
 	
 	// Assigning Data To Variables
 	private Map<String, Attack> Attacks;
	private Map<String, Enemy> Enemies;
	private Map<String, Tile> Tiles;
	
 	// Constructor
    public GameEngine(String playerName, String worldDataPath) {
        // Initialize the player
        this.player = new Player(playerName, gameConfig.getPlayerStartingHealth());
        
        //Initialize World (By loading Data from the json File)
        initializeWorld(worldDataPath);

        // Initialize WorldManager
        this.worldManager = new WorldManager(player, Tiles);
        
        // Initialize GUIManager
        this.guiManager = new GUIManager();
        
        // Initialize Other Managers
        this.attackManager = new AttackManager(Attacks);
        this.playerManager = new PlayerManager(player);
        this.environmentManager = new EnvironmentManager(player, worldManager, guiManager);
        this.enemyManager = new EnemyManager(worldManager , Enemies);
        this.combatManager = new CombatManager();
    }
    
    // Initialize the world (Load world data from the DataLoader)
    public void initializeWorld(String filePath) {
    	// Load game data from the JSON file
        JSONObject gameData = DataLoader.loadData(filePath);
        
        if (gameData != null) {
        	// Parse attack data first
        	Map<String, Attack> attacks = DataLoader.parseAttackData(gameData);
        	this.Attacks = attacks;
        	
            // Parse enemy data second
            Map<String, Enemy> enemies = DataLoader.parseEnemyData(gameData, attacks);
            this.Enemies = enemies;
            
            // Parse tile data and pass in the enemy data
            Map<String, Tile> tiles = DataLoader.parseTileData(gameData, enemies);
            this.Tiles = tiles;
            
        // Just in Case
        } else {
            System.out.println("Failed to load game data.");
        }
    }
    
    // Set the player's starting position based on the selected spawn location
 	public void spawnPlayer(String spawnLocation) {
 	    switch (spawnLocation.toLowerCase()) {
 	        case "city":
 	        	worldManager.setPlayerLocation(citySpawn);
 	            break;
 	        case "mountain":
 	        	worldManager.setPlayerLocation(mountainSpawn);
 	            break;
 	        case "forest":
 	        	worldManager.setPlayerLocation(forestSpawn);
 	            break;
 	    }
 	}
    
 	
 	
    // Getters and Setters
    
    public GUIManager getGUIManager() {
    	return guiManager;
    }
    
    public void setGUIManager(GUIManager guiManager) {
    	this.guiManager = guiManager;
    }
    
    public EnvironmentManager getEnvironmentManager() {
    	return environmentManager;
    }
    
    public void setEnvironmentManager(EnvironmentManager environmentManager) {
    	this.environmentManager = environmentManager;
    }
    
    public WorldManager getWorldManager() {
    	return worldManager;
    }
    
    public void setWorldManager(WorldManager worldManager) {
    	this.worldManager = worldManager;
    }

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public void setEnemyManager(EnemyManager enemyManager) {
		this.enemyManager = enemyManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public CombatManager getCombatManager() {
		return combatManager;
	}

	public void setCombatManager(CombatManager combatManager) {
		this.combatManager = combatManager;
	}

	public NPCManager getNpcManager() {
		return npcManager;
	}

	public void setNpcManager(NPCManager npcManager) {
		this.npcManager = npcManager;
	}

	public AttackManager getAttackManager() {
		return attackManager;
	}

	public void setAttackManager(AttackManager attackManager) {
		this.attackManager = attackManager;
	}

	public InventoryManager getInventoryManager() {
		return inventoryManager;
	}

	public void setInventoryManager(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	public QuestManager getQuestManager() {
		return questManager;
	}

	public void setQuestManager(QuestManager questManager) {
		this.questManager = questManager;
	}

	public SkillManager getSkillManager() {
		return skillManager;
	}

	public void setSkillManager(SkillManager skillManager) {
		this.skillManager = skillManager;
	}

	public GameConfig getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

	public GameGUI getGameGUI() {
		return gameGUI;
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}
}
